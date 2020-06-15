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

package com.liferay.osb.commerce.provisioning.internal;

import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.headless.osb.commerce.client.dto.v1_0.UserAccount;
import com.liferay.osb.commerce.provisioning.OSBCommercePortalInstanceStatus;
import com.liferay.osb.commerce.provisioning.constants.OSBCommerceNotificationConstants;
import com.liferay.osb.commerce.provisioning.constants.OSBCommercePortalInstanceConstants;
import com.liferay.osb.commerce.provisioning.constants.OSBCommerceProvisioningConstants;
import com.liferay.osb.commerce.provisioning.internal.cloud.client.DXPCloudClient;
import com.liferay.osb.commerce.provisioning.internal.cloud.client.DXPCloudClientClientFactory;
import com.liferay.osb.commerce.provisioning.internal.cloud.client.UserAccountClient;
import com.liferay.osb.commerce.provisioning.internal.cloud.client.UserAccountClientFactory;
import com.liferay.osb.commerce.provisioning.internal.cloud.client.dto.PortalInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(service = OSBCommerceProvisioning.class)
public class OSBCommerceProvisioning {

	@Transactional(
		propagation = Propagation.REQUIRED, rollbackFor = Exception.class
	)
	public void initializePortalInstance(long commerceOrderId)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		if (commerceOrder == null) {
			return;
		}

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		CommerceOrderItem commerceOrderItem = commerceOrderItems.get(0);

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.
				fetchCommerceSubscriptionEntryByCommerceOrderItemId(
					commerceOrderItem.getCommerceOrderItemId());

		if (commerceSubscriptionEntry == null) {
			return;
		}

		if (commerceSubscriptionEntry.getSubscriptionStatus() !=
				CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE) {

			return;
		}

		commerceSubscriptionEntry = _updateSubscriptionTypeSettingsProperties(
			commerceSubscriptionEntry,
			OSBCommercePortalInstanceConstants.PORTAL_INSTANCE_STATUS,
			String.valueOf(
				OSBCommercePortalInstanceStatus.IN_PROGRESS.getStatus()));

		PortalInstance portalInstance = _dxpCloudClient.postPortalInstance(
			"osb-commerce-portal-instance-initializer");

		User user = _userLocalService.getUser(commerceOrder.getUserId());

		_userAccountClient.postUserAccount(
			_toUserAccount(user), portalInstance.getVirtualHostname());

		_updateSubscriptionTypeSettingsProperties(
			commerceSubscriptionEntry,
			OSBCommercePortalInstanceConstants.PORTAL_INSTANCE_STATUS,
			String.valueOf(OSBCommercePortalInstanceStatus.ACTIVE.getStatus()),
			OSBCommercePortalInstanceConstants.PORTAL_INSTANCE_VIRTUAL_HOSTNAME,
			portalInstance.getVirtualHostname(),
			OSBCommercePortalInstanceConstants.PORTAL_INSTANCE_WEB_ID,
			portalInstance.getWebId());

		_commerceNotificationHelper.sendNotifications(
			_getCommerceChannelGroupId(
				commerceSubscriptionEntry.getCompanyId()),
			user.getUserId(),
			OSBCommerceNotificationConstants.PORTAL_INSTANCE_CREATED,
			commerceSubscriptionEntry);
	}

	@Activate
	protected void activate() {
		_dxpCloudClient = _dxpCloudClientClientFactory.getDXPCloudClient();
		_userAccountClient = _userAccountClientFactory.getUserAccountClient();
	}

	@Deactivate
	protected void deactivate() {
		_dxpCloudClient.destroy();
		_userAccountClient.destroy();
	}

	private long _getCommerceChannelGroupId(long companyId)
		throws PortalException {

		Group osbCommerceProvisioningSiteGroup =
			_groupLocalService.getFriendlyURLGroup(
				companyId,
				OSBCommerceProvisioningConstants.
					OSB_COMMERCE_PROVISIONING_FRIENDLY_URL);

		return _commerceChannelLocalService.
			getCommerceChannelGroupIdBySiteGroupId(
				osbCommerceProvisioningSiteGroup.getGroupId());
	}

	private UserAccount _toUserAccount(User user) throws Exception {
		return new UserAccount() {
			{
				birthDate = user.getBirthday();
				dateCreated = user.getCreateDate();
				dateModified = user.getModifiedDate();
				emailAddress = user.getEmailAddress();
				firstName = user.getFirstName();
				jobTitle = user.getJobTitle();
				languageId = user.getLanguageId();
				lastName = user.getLastName();
				male = user.isMale();
				middleName = user.getMiddleName();
				name = user.getFullName();
				password = user.getPassword();
				screenName = user.getScreenName();
			}
		};
	}

	private CommerceSubscriptionEntry _updateSubscriptionTypeSettingsProperties(
		CommerceSubscriptionEntry commerceSubscriptionEntry, String... values) {

		UnicodeProperties subscriptionTypeSettingsProperties =
			commerceSubscriptionEntry.getSubscriptionTypeSettingsProperties();

		for (int i = 0; i < values.length; i += 2) {
			subscriptionTypeSettingsProperties.setProperty(
				values[i], values[i + 1]);
		}

		commerceSubscriptionEntry.setSubscriptionTypeSettingsProperties(
			subscriptionTypeSettingsProperties);

		return _commerceSubscriptionEntryLocalService.
			updateCommerceSubscriptionEntry(commerceSubscriptionEntry);
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceNotificationHelper _commerceNotificationHelper;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	private DXPCloudClient _dxpCloudClient;

	@Reference
	private DXPCloudClientClientFactory _dxpCloudClientClientFactory;

	@Reference
	private GroupLocalService _groupLocalService;

	private UserAccountClient _userAccountClient;

	@Reference
	private UserAccountClientFactory _userAccountClientFactory;

	@Reference
	private UserLocalService _userLocalService;

}