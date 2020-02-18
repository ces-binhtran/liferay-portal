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

package com.liferay.osb.koroneiki.trunk.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ProductConsumption service. Represents a row in the &quot;Koroneiki_ProductConsumption&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumption
 * @generated
 */
@ProviderType
public interface ProductConsumptionModel
	extends BaseModel<ProductConsumption>, MVCCModel, ShardedModel,
			StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a product consumption model instance should use the {@link ProductConsumption} interface instead.
	 */

	/**
	 * Returns the primary key of this product consumption.
	 *
	 * @return the primary key of this product consumption
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this product consumption.
	 *
	 * @param primaryKey the primary key of this product consumption
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this product consumption.
	 *
	 * @return the mvcc version of this product consumption
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this product consumption.
	 *
	 * @param mvccVersion the mvcc version of this product consumption
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this product consumption.
	 *
	 * @return the uuid of this product consumption
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this product consumption.
	 *
	 * @param uuid the uuid of this product consumption
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the product consumption ID of this product consumption.
	 *
	 * @return the product consumption ID of this product consumption
	 */
	public long getProductConsumptionId();

	/**
	 * Sets the product consumption ID of this product consumption.
	 *
	 * @param productConsumptionId the product consumption ID of this product consumption
	 */
	public void setProductConsumptionId(long productConsumptionId);

	/**
	 * Returns the company ID of this product consumption.
	 *
	 * @return the company ID of this product consumption
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this product consumption.
	 *
	 * @param companyId the company ID of this product consumption
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this product consumption.
	 *
	 * @return the user ID of this product consumption
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this product consumption.
	 *
	 * @param userId the user ID of this product consumption
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this product consumption.
	 *
	 * @return the user uuid of this product consumption
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this product consumption.
	 *
	 * @param userUuid the user uuid of this product consumption
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this product consumption.
	 *
	 * @return the create date of this product consumption
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this product consumption.
	 *
	 * @param createDate the create date of this product consumption
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this product consumption.
	 *
	 * @return the modified date of this product consumption
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this product consumption.
	 *
	 * @param modifiedDate the modified date of this product consumption
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the product consumption key of this product consumption.
	 *
	 * @return the product consumption key of this product consumption
	 */
	@AutoEscape
	public String getProductConsumptionKey();

	/**
	 * Sets the product consumption key of this product consumption.
	 *
	 * @param productConsumptionKey the product consumption key of this product consumption
	 */
	public void setProductConsumptionKey(String productConsumptionKey);

	/**
	 * Returns the account ID of this product consumption.
	 *
	 * @return the account ID of this product consumption
	 */
	public long getAccountId();

	/**
	 * Sets the account ID of this product consumption.
	 *
	 * @param accountId the account ID of this product consumption
	 */
	public void setAccountId(long accountId);

	/**
	 * Returns the product entry ID of this product consumption.
	 *
	 * @return the product entry ID of this product consumption
	 */
	public long getProductEntryId();

	/**
	 * Sets the product entry ID of this product consumption.
	 *
	 * @param productEntryId the product entry ID of this product consumption
	 */
	public void setProductEntryId(long productEntryId);

}