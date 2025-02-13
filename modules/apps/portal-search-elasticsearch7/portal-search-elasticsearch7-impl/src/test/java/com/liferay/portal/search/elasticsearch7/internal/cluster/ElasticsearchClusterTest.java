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

package com.liferay.portal.search.elasticsearch7.internal.cluster;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Artur Aquino
 */
public class ElasticsearchClusterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_replicasClusterContext = createReplicasClusterContext();
	}

	@Test
	public void testReplicaIndexNamesIncludeSystemCompanyId() {
		setUpCompanyLocalService(getCompanies(new long[] {42, 142857}));

		String[] targetIndexNames =
			_replicasClusterContext.getTargetIndexNames();

		Arrays.sort(targetIndexNames);

		Assert.assertEquals(
			"[cid-0, cid-142857, cid-42]", Arrays.toString(targetIndexNames));
	}

	protected ReplicasClusterContext createReplicasClusterContext() {
		ElasticsearchCluster elasticsearchCluster = new ElasticsearchCluster();

		elasticsearchCluster.companyLocalService = _companyLocalService;

		elasticsearchCluster.indexNameBuilder = new IndexNameBuilder() {

			@Override
			public String getIndexName(long companyId) {
				return getTestIndexName(companyId);
			}

		};

		return elasticsearchCluster.new ReplicasClusterContextImpl();
	}

	protected List<Company> getCompanies(long[] companyIds) {
		List<Company> companies = new ArrayList<>(companyIds.length);

		for (long companyId : companyIds) {
			companies.add(_getCompany(companyId));
		}

		return companies;
	}

	protected String getTestIndexName(long companyId) {
		return "cid-" + companyId;
	}

	protected void setUpCompanyLocalService(List<Company> companies) {
		Mockito.when(
			_companyLocalService.getCompanies()
		).thenReturn(
			companies
		);
	}

	private Company _getCompany(long companyId) {
		Company company = Mockito.mock(Company.class);

		Mockito.when(
			company.getCompanyId()
		).thenReturn(
			companyId
		);

		return company;
	}

	private final CompanyLocalService _companyLocalService = Mockito.mock(
		CompanyLocalService.class);
	private ReplicasClusterContext _replicasClusterContext;

}