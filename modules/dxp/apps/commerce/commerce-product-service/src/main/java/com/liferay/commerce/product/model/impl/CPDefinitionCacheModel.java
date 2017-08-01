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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPDefinition;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPDefinition in entity cache.
 *
 * @author Marco Leo
 * @see CPDefinition
 * @generated
 */
@ProviderType
public class CPDefinitionCacheModel implements CacheModel<CPDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionCacheModel)) {
			return false;
		}

		CPDefinitionCacheModel cpDefinitionCacheModel = (CPDefinitionCacheModel)obj;

		if (CPDefinitionId == cpDefinitionCacheModel.CPDefinitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPDefinitionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPDefinitionId=");
		sb.append(CPDefinitionId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", baseSKU=");
		sb.append(baseSKU);
		sb.append(", productTypeName=");
		sb.append(productTypeName);
		sb.append(", gtin=");
		sb.append(gtin);
		sb.append(", manufacturerPartNumber=");
		sb.append(manufacturerPartNumber);
		sb.append(", availableIndividually=");
		sb.append(availableIndividually);
		sb.append(", minCartQuantity=");
		sb.append(minCartQuantity);
		sb.append(", maxCartQuantity=");
		sb.append(maxCartQuantity);
		sb.append(", allowedCartQuantities=");
		sb.append(allowedCartQuantities);
		sb.append(", multipleCartQuantity=");
		sb.append(multipleCartQuantity);
		sb.append(", DDMStructureKey=");
		sb.append(DDMStructureKey);
		sb.append(", displayDate=");
		sb.append(displayDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", defaultLanguageId=");
		sb.append(defaultLanguageId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDefinition toEntityModel() {
		CPDefinitionImpl cpDefinitionImpl = new CPDefinitionImpl();

		if (uuid == null) {
			cpDefinitionImpl.setUuid(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setUuid(uuid);
		}

		cpDefinitionImpl.setCPDefinitionId(CPDefinitionId);
		cpDefinitionImpl.setGroupId(groupId);
		cpDefinitionImpl.setCompanyId(companyId);
		cpDefinitionImpl.setUserId(userId);

		if (userName == null) {
			cpDefinitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpDefinitionImpl.setCreateDate(null);
		}
		else {
			cpDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpDefinitionImpl.setModifiedDate(null);
		}
		else {
			cpDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (baseSKU == null) {
			cpDefinitionImpl.setBaseSKU(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setBaseSKU(baseSKU);
		}

		if (productTypeName == null) {
			cpDefinitionImpl.setProductTypeName(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setProductTypeName(productTypeName);
		}

		if (gtin == null) {
			cpDefinitionImpl.setGtin(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setGtin(gtin);
		}

		if (manufacturerPartNumber == null) {
			cpDefinitionImpl.setManufacturerPartNumber(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setManufacturerPartNumber(manufacturerPartNumber);
		}

		cpDefinitionImpl.setAvailableIndividually(availableIndividually);
		cpDefinitionImpl.setMinCartQuantity(minCartQuantity);
		cpDefinitionImpl.setMaxCartQuantity(maxCartQuantity);

		if (allowedCartQuantities == null) {
			cpDefinitionImpl.setAllowedCartQuantities(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setAllowedCartQuantities(allowedCartQuantities);
		}

		cpDefinitionImpl.setMultipleCartQuantity(multipleCartQuantity);

		if (DDMStructureKey == null) {
			cpDefinitionImpl.setDDMStructureKey(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setDDMStructureKey(DDMStructureKey);
		}

		if (displayDate == Long.MIN_VALUE) {
			cpDefinitionImpl.setDisplayDate(null);
		}
		else {
			cpDefinitionImpl.setDisplayDate(new Date(displayDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			cpDefinitionImpl.setExpirationDate(null);
		}
		else {
			cpDefinitionImpl.setExpirationDate(new Date(expirationDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			cpDefinitionImpl.setLastPublishDate(null);
		}
		else {
			cpDefinitionImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		cpDefinitionImpl.setStatus(status);
		cpDefinitionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			cpDefinitionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			cpDefinitionImpl.setStatusDate(null);
		}
		else {
			cpDefinitionImpl.setStatusDate(new Date(statusDate));
		}

		if (defaultLanguageId == null) {
			cpDefinitionImpl.setDefaultLanguageId(StringPool.BLANK);
		}
		else {
			cpDefinitionImpl.setDefaultLanguageId(defaultLanguageId);
		}

		cpDefinitionImpl.resetOriginalValues();

		return cpDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPDefinitionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		baseSKU = objectInput.readUTF();
		productTypeName = objectInput.readUTF();
		gtin = objectInput.readUTF();
		manufacturerPartNumber = objectInput.readUTF();

		availableIndividually = objectInput.readBoolean();

		minCartQuantity = objectInput.readInt();

		maxCartQuantity = objectInput.readInt();
		allowedCartQuantities = objectInput.readUTF();

		multipleCartQuantity = objectInput.readInt();
		DDMStructureKey = objectInput.readUTF();
		displayDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		defaultLanguageId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(CPDefinitionId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (baseSKU == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(baseSKU);
		}

		if (productTypeName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(productTypeName);
		}

		if (gtin == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gtin);
		}

		if (manufacturerPartNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manufacturerPartNumber);
		}

		objectOutput.writeBoolean(availableIndividually);

		objectOutput.writeInt(minCartQuantity);

		objectOutput.writeInt(maxCartQuantity);

		if (allowedCartQuantities == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(allowedCartQuantities);
		}

		objectOutput.writeInt(multipleCartQuantity);

		if (DDMStructureKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(DDMStructureKey);
		}

		objectOutput.writeLong(displayDate);
		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (defaultLanguageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(defaultLanguageId);
		}
	}

	public String uuid;
	public long CPDefinitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String baseSKU;
	public String productTypeName;
	public String gtin;
	public String manufacturerPartNumber;
	public boolean availableIndividually;
	public int minCartQuantity;
	public int maxCartQuantity;
	public String allowedCartQuantities;
	public int multipleCartQuantity;
	public String DDMStructureKey;
	public long displayDate;
	public long expirationDate;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String defaultLanguageId;
}