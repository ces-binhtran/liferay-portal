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

package com.liferay.portal.servlet.filters.header;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.FastDateFormatFactoryImpl;
import com.liferay.portal.util.PortalImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author Binh TRan
 */
public class HeaderFilterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_setUpFastDateFormatFactoryUtil();
	}

	@Test
	public void testAppendNewHeaderValue() throws Exception {
		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(new PortalImpl());

		HeaderFilter headerFilter = new HeaderFilter();

		headerFilter.init(_filterConfig);

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setSession(_createHttpSession());

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		headerFilter.processFilter(
			mockHttpServletRequest, mockHttpServletResponse,
			ProxyFactory.newDummyInstance(FilterChain.class));

		headerFilter.init(
			new FilterConfig() {

				@Override
				public String getFilterName() {
					return "HeaderFilter";
				}

				@Override
				public String getInitParameter(String s) {
					return _initParameters.get(s);
				}

				@Override
				public Enumeration<String> getInitParameterNames() {
					return Collections.enumeration(_initParameters.keySet());
				}

				@Override
				public ServletContext getServletContext() {
					return null;
				}

				private final Map<String, String> _initParameters =
					HashMapBuilder.put(
						HttpHeaders.CACHE_CONTROL,
						"max-age=315360000, no-cache, no-store"
					).put(
						HttpHeaders.EXPIRES, "315360000"
					).build();

			});

		headerFilter.processFilter(
			mockHttpServletRequest, mockHttpServletResponse,
			ProxyFactory.newDummyInstance(FilterChain.class));

		List<String> cacheControlHeaders = mockHttpServletResponse.getHeaders(
			HttpHeaders.CACHE_CONTROL);

		List<String> expiresHeaders = mockHttpServletResponse.getHeaders(
			HttpHeaders.EXPIRES);

		Assert.assertEquals(
			cacheControlHeaders.toString(), 1, cacheControlHeaders.size());

		Assert.assertEquals(
			expiresHeaders.toString(), 1, expiresHeaders.size());

		Assert.assertEquals(
			"max-age=315360000,public,no-cache,no-store",
			cacheControlHeaders.get(0));
	}

	@Test
	public void testNotDuplicateHeaderWhenInvokeMultipleTime()
		throws Exception {

		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(new PortalImpl());

		HeaderFilter headerFilter = new HeaderFilter();

		headerFilter.init(_filterConfig);

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setSession(_createHttpSession());

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		headerFilter.processFilter(
			mockHttpServletRequest, mockHttpServletResponse,
			ProxyFactory.newDummyInstance(FilterChain.class));

		headerFilter.processFilter(
			mockHttpServletRequest, mockHttpServletResponse,
			ProxyFactory.newDummyInstance(FilterChain.class));

		List<String> cacheControlHeaders = mockHttpServletResponse.getHeaders(
			HttpHeaders.CACHE_CONTROL);

		List<String> expiresHeaders = mockHttpServletResponse.getHeaders(
			HttpHeaders.EXPIRES);

		Assert.assertEquals(
			cacheControlHeaders.toString(), 1, cacheControlHeaders.size());
		Assert.assertEquals(
			expiresHeaders.toString(), 1, expiresHeaders.size());
	}

	private HttpSession _createHttpSession() {
		HttpSession httpSession = Mockito.mock(HttpSession.class);

		Mockito.when(
			httpSession.isNew()
		).thenReturn(
			false
		);

		return httpSession;
	}

	private void _setUpFastDateFormatFactoryUtil() {
		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();

		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			new FastDateFormatFactoryImpl());
	}

	private final FilterConfig _filterConfig = new FilterConfig() {

		@Override
		public String getFilterName() {
			return "HeaderFilter";
		}

		@Override
		public String getInitParameter(String s) {
			return _initParameters.get(s);
		}

		@Override
		public Enumeration<String> getInitParameterNames() {
			return Collections.enumeration(_initParameters.keySet());
		}

		@Override
		public ServletContext getServletContext() {
			return null;
		}

		private final Map<String, String> _initParameters = HashMapBuilder.put(
			HttpHeaders.CACHE_CONTROL, "max-age=315360000, public"
		).put(
			HttpHeaders.EXPIRES, "315360000"
		).build();

	};

}