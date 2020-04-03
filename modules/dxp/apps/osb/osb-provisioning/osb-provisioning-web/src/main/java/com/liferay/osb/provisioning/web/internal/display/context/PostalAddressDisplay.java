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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class PostalAddressDisplay {

	public PostalAddressDisplay(
		HttpServletRequest httpServletRequest, PostalAddress postalAddress) {

		_httpServletRequest = httpServletRequest;
		_postalAddress = postalAddress;
	}

	public String getAddressCountry() {
		if (Validator.isNotNull(_postalAddress.getAddressCountry())) {
			return _postalAddress.getAddressCountry();
		}

		return StringPool.DASH;
	}

	public String getAddressLocality() {
		if (Validator.isNotNull(_postalAddress.getAddressLocality())) {
			return _postalAddress.getAddressLocality();
		}

		return StringPool.DASH;
	}

	public String getAddressRegion() {
		if (Validator.isNotNull(_postalAddress.getAddressRegion())) {
			return _postalAddress.getAddressRegion();
		}

		return StringPool.DASH;
	}

	public String getAddressType() {
		if (Validator.isNotNull(_postalAddress.getAddressType())) {
			return _postalAddress.getAddressType();
		}

		return StringPool.DASH;
	}

	public long getId() {
		return _postalAddress.getId();
	}

	public String getMailing() {
		if ((_postalAddress.getMailing() != null) &&
			_postalAddress.getMailing()) {

			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	public String getPostalCode() {
		if (Validator.isNotNull(_postalAddress.getPostalCode())) {
			return _postalAddress.getPostalCode();
		}

		return StringPool.DASH;
	}

	public String getPrimary() {
		if ((_postalAddress.getPrimary() != null) &&
			_postalAddress.getPrimary()) {

			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	public String getStreetAddressLine1() {
		if (Validator.isNotNull(_postalAddress.getStreetAddressLine1())) {
			return _postalAddress.getStreetAddressLine1();
		}

		return StringPool.DASH;
	}

	public String getStreetAddressLine2() {
		if (Validator.isNotNull(_postalAddress.getStreetAddressLine2())) {
			return _postalAddress.getStreetAddressLine2();
		}

		return StringPool.DASH;
	}

	public String getStreetAddressLine3() {
		if (Validator.isNotNull(_postalAddress.getStreetAddressLine3())) {
			return _postalAddress.getStreetAddressLine3();
		}

		return StringPool.DASH;
	}

	private final HttpServletRequest _httpServletRequest;
	private final PostalAddress _postalAddress;

}