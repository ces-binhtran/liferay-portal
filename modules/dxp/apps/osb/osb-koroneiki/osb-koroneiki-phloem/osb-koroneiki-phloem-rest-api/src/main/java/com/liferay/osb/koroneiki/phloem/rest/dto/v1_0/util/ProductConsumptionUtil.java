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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Amos Fong
 */
public class ProductConsumptionUtil {

	public static ProductConsumption toProductConsumption(
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				productConsumption)
		throws Exception {

		return new ProductConsumption() {
			{
				accountId = productConsumption.getAccountId();
				dateCreated = productConsumption.getCreateDate();
				externalLinks = TransformUtil.transformToArray(
					productConsumption.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				id = productConsumption.getProductConsumptionId();
				productId = productConsumption.getProductEntryId();
				projectId = productConsumption.getProjectId();
				properties = productConsumption.getProductFieldsMap();
			}
		};
	}

}