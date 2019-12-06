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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = LicenseKeyMigration.class)
public class LicenseKeyMigration {

	public void migrate(long userId) throws Exception {
		StringBundler sb = new StringBundler(7);

		sb.append("select corpProjectId, OSB_ProductEntry.name, licenseKeyId ");
		sb.append("from OSB_LicenseKey inner join OSB_AccountEntry on ");
		sb.append("OSB_LicenseKey.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_LicenseKey.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId where productId = ");
		sb.append("'Portal' and complimentary = 0 and active_ = 1");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account with accountId " +
							corpProjectId);

					continue;
				}

				String productEntryName = resultSet.getString(2);

				ProductEntry productEntry =
					_productEntryLocalService.fetchProductEntryByName(
						productEntryName);

				if (productEntry == null) {
					_log.error(
						"Unable to find product with name " + productEntryName);

					continue;
				}

				ProductConsumption productConsumption =
					_productConsumptionLocalService.addProductConsumption(
						userId, corpProjectId, productEntry.getProductEntryId(),
						Collections.emptyList());

				long licenseKeyId = resultSet.getLong(3);

				_externalLinkLocalService.addExternalLink(
					userId, ProductConsumption.class.getName(),
					productConsumption.getProductConsumptionId(),
					"provisioning", "license", String.valueOf(licenseKeyId));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

}