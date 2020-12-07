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

package com.liferay.analytics.reports.web.internal.model;

import com.liferay.analytics.reports.web.internal.model.util.TrafficChannelUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * @author David Arques
 */
public class ReferralTrafficChannelImpl implements TrafficChannel {

	public ReferralTrafficChannelImpl(boolean error) {
		_error = error;

		_referringDomains = Collections.emptyList();
		_referringPages = Collections.emptyList();
		_trafficAmount = 0;
		_trafficShare = 0;
	}

	public ReferralTrafficChannelImpl(
		List<ReferringURL> referringDomains, List<ReferringURL> referringPages,
		long trafficAmount, double trafficShare) {

		_referringDomains = referringDomains;
		_referringPages = referringPages;
		_trafficAmount = trafficAmount;
		_trafficShare = trafficShare;

		_error = false;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ReferralTrafficChannelImpl)) {
			return false;
		}

		ReferralTrafficChannelImpl referralTrafficChannelImpl =
			(ReferralTrafficChannelImpl)object;

		if (Objects.equals(
				getHelpMessageKey(),
				referralTrafficChannelImpl.getHelpMessageKey()) &&
			Objects.equals(getName(), referralTrafficChannelImpl.getName()) &&
			Objects.equals(
				_trafficAmount, referralTrafficChannelImpl._trafficAmount) &&
			Objects.equals(
				_trafficShare, referralTrafficChannelImpl._trafficShare)) {

			return true;
		}

		return false;
	}

	@Override
	public String getHelpMessageKey() {
		return "this-is-the-number-of-page-views-generated-by-people-coming-" +
			"to-your-page-from-other-sites-which-are-not-search-engine-pages-" +
				"or-social-sites";
	}

	@Override
	public String getName() {
		return "referral";
	}

	public List<ReferringURL> getReferringDomains() {
		return _referringDomains;
	}

	public List<ReferringURL> getReferringPages() {
		return _referringPages;
	}

	@Override
	public long getTrafficAmount() {
		return _trafficAmount;
	}

	@Override
	public double getTrafficShare() {
		return _trafficShare;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			getHelpMessageKey(), getName(), _trafficAmount, _trafficShare);
	}

	@Override
	public JSONObject toJSONObject(
		Locale locale, ResourceBundle resourceBundle) {

		JSONObject jsonObject = TrafficChannelUtil.toJSONObject(
			_error,
			ResourceBundleUtil.getString(resourceBundle, getHelpMessageKey()),
			getName(), ResourceBundleUtil.getString(resourceBundle, getName()),
			_trafficAmount, _trafficShare);

		if (ListUtil.isNotEmpty(_referringDomains)) {
			jsonObject.put("referringDomains", _getReferringDomainsJSONArray());
		}

		if (ListUtil.isNotEmpty(_referringPages)) {
			jsonObject.put("referringPages", _getReferringPagesJSONArray());
		}

		return jsonObject;
	}

	@Override
	public String toString() {
		return String.valueOf(
			TrafficChannelUtil.toJSONObject(
				_error, getHelpMessageKey(), getName(), getName(),
				_trafficAmount, _trafficShare));
	}

	private JSONArray _getReferringDomainsJSONArray() {
		if (ListUtil.isEmpty(_referringDomains)) {
			return JSONFactoryUtil.createJSONArray();
		}

		Stream<ReferringURL> stream = _referringDomains.stream();

		return JSONUtil.putAll(
			stream.map(
				ReferringURL::toJSONObject
			).toArray());
	}

	private JSONArray _getReferringPagesJSONArray() {
		if (ListUtil.isEmpty(_referringPages)) {
			return JSONFactoryUtil.createJSONArray();
		}

		Stream<ReferringURL> stream = _referringPages.stream();

		return JSONUtil.putAll(
			stream.map(
				ReferringURL::toJSONObject
			).toArray());
	}

	private final boolean _error;
	private final List<ReferringURL> _referringDomains;
	private final List<ReferringURL> _referringPages;
	private final long _trafficAmount;
	private final double _trafficShare;

}