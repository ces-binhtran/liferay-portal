/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.saml.opensaml.integration.internal.transport;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.saml.opensaml.integration.internal.transport.configuration.HttpClientFactoryConfiguration;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.solr.configuration.SolrHttpClientFactoryConfiguration",
	immediate = true, service = HttpClientFactory.class
)
public class HttpClientFactory {

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		HttpClientFactoryConfiguration httpClientFactoryConfiguration =
			ConfigurableUtil.createConfigurable(
				HttpClientFactoryConfiguration.class, properties);

		_poolingClientConnectionManager =
			new PoolingHttpClientConnectionManager();

		_poolingClientConnectionManager.setDefaultMaxPerRoute(
			httpClientFactoryConfiguration.defaultMaxConnectionsPerRoute());
		_poolingClientConnectionManager.setMaxTotal(
			httpClientFactoryConfiguration.maxTotalConnections());

		SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();

		socketConfigBuilder.setSoTimeout(
			httpClientFactoryConfiguration.getSoTimeout());

		_poolingClientConnectionManager.setDefaultSocketConfig(
			socketConfigBuilder.build());

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		httpClientBuilder.setConnectionManager(_poolingClientConnectionManager);

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();

		requestConfigBuilder.setConnectTimeout(
			httpClientFactoryConfiguration.getConnectionManagerTimeout());

		httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());

		HttpRequestRetryHandler httpRequestRetryHandler =
			new DefaultHttpRequestRetryHandler(0, false);

		httpClientBuilder.setRetryHandler(httpRequestRetryHandler);

		_closeableHttpClient = httpClientBuilder.build();

		_httpClientServiceRegistration = bundleContext.registerService(
			HttpClient.class, _closeableHttpClient, null);
	}

	@Deactivate
	protected void deactivate() {
		if (_closeableHttpClient != null) {
			try {
				_closeableHttpClient.close();
			}
			catch (IOException ioe) {
				if (_log.isDebugEnabled()) {
					_log.debug(ioe, ioe);
				}
			}
		}

		if (_httpClientServiceRegistration != null) {
			_httpClientServiceRegistration.unregister();

			_httpClientServiceRegistration = null;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Shutting down " + getClass().getName());
		}

		if (_poolingClientConnectionManager == null) {
			return;
		}

		int retry = 0;

		while (retry < 10) {
			PoolStats poolStats =
				_poolingClientConnectionManager.getTotalStats();

			int availableConnections = poolStats.getAvailable();

			if (availableConnections <= 0) {
				break;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						getClass().getName(), " is waiting on ",
						String.valueOf(availableConnections), " connections"));
			}

			_poolingClientConnectionManager.closeIdleConnections(
				200, TimeUnit.MILLISECONDS);

			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ie) {
			}

			retry++;
		}

		_poolingClientConnectionManager.shutdown();

		_poolingClientConnectionManager = null;

		if (_log.isDebugEnabled()) {
			_log.debug(toString() + " was shut down");
		}
	}

	@Modified
	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		deactivate();
		activate(bundleContext, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HttpClientFactory.class);

	private CloseableHttpClient _closeableHttpClient;
	private ServiceRegistration<HttpClient> _httpClientServiceRegistration;
	private PoolingHttpClientConnectionManager _poolingClientConnectionManager;

}