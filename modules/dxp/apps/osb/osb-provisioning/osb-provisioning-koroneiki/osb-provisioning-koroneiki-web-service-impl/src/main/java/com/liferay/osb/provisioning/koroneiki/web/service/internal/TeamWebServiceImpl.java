/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.TeamResource;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = TeamWebService.class
)
public class TeamWebServiceImpl implements TeamWebService {

	public Team getTeam(String teamKey) throws Exception {
		return _teamResource.getTeam(teamKey);
	}

	public List<Team> search(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception {

		Page<Team> teamsPage = _teamResource.getTeamsPage(
			search, filterString, Pagination.of(page, pageSize), sortString);

		if ((teamsPage != null) && (teamsPage.getItems() != null)) {
			return new ArrayList<>(teamsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String search, String filterString)
		throws Exception {

		Page<Team> teamsPage = _teamResource.getTeamsPage(
			search, filterString, Pagination.of(1, 1), StringPool.BLANK);

		if (teamsPage != null) {
			return teamsPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		TeamResource.Builder builder = TeamResource.builder();

		_teamResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).parameter(
			"nestedFields", "contacts"
		).build();
	}

	private TeamResource _teamResource;

}