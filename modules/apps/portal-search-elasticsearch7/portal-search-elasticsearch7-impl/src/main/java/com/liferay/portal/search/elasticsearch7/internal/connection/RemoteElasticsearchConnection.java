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

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexFactory;
import com.liferay.portal.search.elasticsearch7.internal.settings.SettingsBuilder;
import com.liferay.portal.search.elasticsearch7.settings.SettingsContributor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = {
		"com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration",
		"com.liferay.portal.search.elasticsearch7.configuration.XPackSecurityConfiguration"
	},
	immediate = true, property = "operation.mode=REMOTE",
	service = ElasticsearchConnection.class
)
public class RemoteElasticsearchConnection extends BaseElasticsearchConnection {

	@Override
	public OperationMode getOperationMode() {
		return OperationMode.REMOTE;
	}

	@Override
	@Reference(unbind = "-")
	public void setIndexFactory(IndexFactory indexFactory) {
		super.setIndexFactory(indexFactory);
	}

	public void setTransportAddresses(Set<String> transportAddresses) {
		_transportAddresses = transportAddresses;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		replaceConfigurations(properties);
	}

	@Override
	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(operation.mode=REMOTE)"
	)
	protected void addSettingsContributor(
		SettingsContributor settingsContributor) {

		super.addSettingsContributor(settingsContributor);
	}

	protected void addTransportAddress(
			TransportClient transportClient, String transportAddress)
		throws UnknownHostException {

		String[] transportAddressParts = StringUtil.split(
			transportAddress, StringPool.COLON);

		String host = transportAddressParts[0];

		int port = GetterUtil.getInteger(transportAddressParts[1]);

		InetAddress inetAddress = InetAddressUtil.getInetAddressByName(host);

		transportClient.addTransportAddress(
			new TransportAddress(inetAddress, port));
	}

	protected void configureAuthentication(SettingsBuilder settingsBuilder) {
		String user =
			xPackSecurityConfiguration.username() + ":" +
				xPackSecurityConfiguration.password();

		settingsBuilder.put("xpack.security.user", user);
	}

	protected void configurePEMPaths(SettingsBuilder settingsBuilder) {
		settingsBuilder.put(
			"xpack.security.transport.ssl.certificate",
			xPackSecurityConfiguration.sslCertificatePath());
		settingsBuilder.putList(
			"xpack.security.transport.ssl.certificate_authorities",
			xPackSecurityConfiguration.sslCertificateAuthoritiesPaths());
		settingsBuilder.put(
			"xpack.security.transport.ssl.key",
			xPackSecurityConfiguration.sslKeyPath());
	}

	protected void configurePKCSPaths(SettingsBuilder settingsBuilder) {
		settingsBuilder.put(
			"xpack.security.transport.ssl.keystore.password",
			xPackSecurityConfiguration.sslKeystorePassword());
		settingsBuilder.put(
			"xpack.security.transport.ssl.keystore.path",
			xPackSecurityConfiguration.sslKeystorePath());
		settingsBuilder.put(
			"xpack.security.transport.ssl.truststore.password",
			xPackSecurityConfiguration.sslTruststorePassword());
		settingsBuilder.put(
			"xpack.security.transport.ssl.truststore.path",
			xPackSecurityConfiguration.sslTruststorePath());
	}

	protected void configureSSL(SettingsBuilder settingsBuilder) {
		settingsBuilder.put("xpack.security.transport.ssl.enabled", "true");
		settingsBuilder.put(
			"xpack.security.transport.ssl.verification_mode",
			StringUtil.toLowerCase(
				xPackSecurityConfiguration.transportSSLVerificationMode()));

		String certificateFormat =
			xPackSecurityConfiguration.certificateFormat();

		if (certificateFormat.equals("PKCS#12")) {
			configurePKCSPaths(settingsBuilder);
		}
		else {
			configurePEMPaths(settingsBuilder);
		}
	}

	@Override
	protected Client createClient() {
		if (_transportAddresses.isEmpty()) {
			throw new IllegalStateException(
				"There must be at least one transport address");
		}

		Thread thread = Thread.currentThread();

		ClassLoader contextClassLoader = thread.getContextClassLoader();

		Class<?> clazz = getClass();

		thread.setContextClassLoader(clazz.getClassLoader());

		try {
			TransportClient transportClient = createTransportClient();

			for (String transportAddress : _transportAddresses) {
				try {
					addTransportAddress(transportClient, transportAddress);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to add transport address " +
								transportAddress,
							e);
					}
				}
			}

			return transportClient;
		}
		finally {
			thread.setContextClassLoader(contextClassLoader);
		}
	}

	protected TransportClient createTransportClient() {
		if (xPackSecurityConfiguration.requiresAuthentication()) {
			configureAuthentication(settingsBuilder);

			if (xPackSecurityConfiguration.transportSSLEnabled()) {
				configureSSL(settingsBuilder);
			}
		}

		Settings settings = settingsBuilder.build();

		if (_log.isDebugEnabled()) {
			_log.debug("Settings: " + settings.toString());
		}

		if (xPackSecurityConfiguration.requiresAuthentication()) {
			return new PreBuiltXPackTransportClient(settings);
		}

		return new PreBuiltTransportClient(settings);
	}

	@Deactivate
	protected void deactivate(Map<String, Object> properties) {
		close();
	}

	@Override
	protected void loadRequiredDefaultConfigurations() {
		settingsBuilder.put(
			"client.transport.ignore_cluster_name",
			elasticsearchConfiguration.clientTransportIgnoreClusterName());
		settingsBuilder.put(
			"client.transport.nodes_sampler_interval",
			elasticsearchConfiguration.clientTransportNodesSamplerInterval());
		settingsBuilder.put(
			"client.transport.ping_timeout",
			elasticsearchConfiguration.clientTransportPingTimeout());
		settingsBuilder.put(
			"client.transport.sniff",
			elasticsearchConfiguration.clientTransportSniff());
		settingsBuilder.put(
			"cluster.name", elasticsearchConfiguration.clusterName());
		settingsBuilder.put(
			"request.headers.X-Found-Cluster",
			elasticsearchConfiguration.clusterName());
	}

	@Modified
	protected synchronized void modified(Map<String, Object> properties) {
		replaceConfigurations(properties);

		if (isConnected()) {
			close();
		}

		if (!isConnected() &&
			(elasticsearchConfiguration.operationMode() ==
				com.liferay.portal.search.elasticsearch7.configuration.
					OperationMode.REMOTE)) {

			connect();
		}
	}

	@Override
	protected void removeSettingsContributor(
		SettingsContributor settingsContributor) {

		super.removeSettingsContributor(settingsContributor);
	}

	protected void replaceConfigurations(Map<String, Object> properties) {
		elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);
		xPackSecurityConfiguration = ConfigurableUtil.createConfigurable(
			XPackSecurityConfiguration.class, properties);

		String[] transportAddresses =
			elasticsearchConfiguration.transportAddresses();

		setTransportAddresses(SetUtil.fromArray(transportAddresses));
	}

	@Reference
	protected Props props;

	protected volatile XPackSecurityConfiguration xPackSecurityConfiguration;

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteElasticsearchConnection.class);

	private Set<String> _transportAddresses = new HashSet<>();

}