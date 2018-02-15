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

package com.liferay.portal.tools.service.builder;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class Entity implements Comparable<Entity> {

	public static final Accessor<Entity, String> NAME_ACCESSOR =
		new Accessor<Entity, String>() {

			@Override
			public String get(Entity entity) {
				return entity.getName();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<Entity> getTypeClass() {
				return Entity.class;
			}

		};

	public static EntityColumn getColumn(
		String name, List<EntityColumn> entityColumns) {

		for (EntityColumn entityColumn : entityColumns) {
			if (name.equals(entityColumn.getName())) {
				return entityColumn;
			}
		}

		throw new RuntimeException("Entity column " + name + " not found");
	}

	public static boolean hasColumn(
		String name, List<EntityColumn> entityColumns) {

		return hasColumn(name, null, entityColumns);
	}

	public static boolean hasColumn(
		String name, String type, List<EntityColumn> entityColumns) {

		int index = entityColumns.indexOf(new EntityColumn(name));

		if (index != -1) {
			EntityColumn entityColumn = entityColumns.get(index);

			if ((type == null) || type.equals(entityColumn.getType())) {
				return true;
			}
		}

		return false;
	}

	public Entity(String name) {
		this(
			null, null, null, null, name, null, null, null, false, false, false,
			true, null, null, null, null, null, true, false, false, false,
			false, false, null, null, null, null, null, null, null, null, null,
			null, false);
	}

	public Entity(
		String packagePath, String apiPackagePath, String portletName,
		String portletShortName, String name, String humanName, String table,
		String alias, boolean uuid, boolean uuidAccessor, boolean localService,
		boolean remoteService, String persistenceClass, String finderClass,
		String dataSource, String sessionFactory, String txManager,
		boolean cacheEnabled, boolean dynamicUpdateEnabled, boolean jsonEnabled,
		boolean mvccEnabled, boolean trashEnabled, boolean deprecated,
		List<EntityColumn> pkList, List<EntityColumn> regularColList,
		List<EntityColumn> blobEntityColumns, List<EntityColumn> collectionList,
		List<EntityColumn> entityColumns, EntityOrder order,
		List<EntityFinder> finderList, List<Entity> referenceList,
		List<String> unresolvedReferenceList, List<String> txRequiredList,
		boolean resourceActionModel) {

		_packagePath = packagePath;
		_apiPackagePath = apiPackagePath;
		_portletName = portletName;
		_portletShortName = portletShortName;
		_name = name;
		_humanName = GetterUtil.getString(
			humanName, ServiceBuilder.toHumanName(name));
		_table = table;
		_alias = alias;
		_uuid = uuid;
		_uuidAccessor = uuidAccessor;
		_localService = localService;
		_remoteService = remoteService;
		_persistenceClass = persistenceClass;
		_finderClass = finderClass;
		_dataSource = GetterUtil.getString(dataSource, _DATA_SOURCE_DEFAULT);
		_sessionFactory = GetterUtil.getString(
			sessionFactory, _SESSION_FACTORY_DEFAULT);
		_txManager = GetterUtil.getString(txManager, _TX_MANAGER_DEFAULT);
		_dynamicUpdateEnabled = dynamicUpdateEnabled;
		_jsonEnabled = jsonEnabled;
		_mvccEnabled = mvccEnabled;
		_trashEnabled = trashEnabled;
		_deprecated = deprecated;
		_pkList = pkList;
		_regularColList = regularColList;
		_blobEntityColumns = blobEntityColumns;
		_collectionList = collectionList;
		_entityColumns = entityColumns;
		_entityOrder = order;
		_entityFinders = finderList;
		_referenceList = referenceList;
		_unresolvedReferenceList = unresolvedReferenceList;
		_txRequiredList = txRequiredList;
		_resourceActionModel = resourceActionModel;

		if (_entityFinders != null) {
			Set<EntityColumn> finderColumns = new HashSet<>();

			for (EntityFinder entityFinder : _entityFinders) {
				finderColumns.addAll(entityFinder.getColumns());
			}

			_finderColumnsList = new ArrayList<>(finderColumns);

			Collections.sort(_finderColumnsList);
		}
		else {
			_finderColumnsList = Collections.emptyList();
		}

		if ((_blobEntityColumns != null) && !_blobEntityColumns.isEmpty()) {
			for (EntityColumn entityColumn : _blobEntityColumns) {
				if (!entityColumn.isLazy()) {
					cacheEnabled = false;

					break;
				}
			}
		}

		_cacheEnabled = cacheEnabled;

		boolean containerModel = false;

		if ((_entityColumns != null) && !_entityColumns.isEmpty()) {
			for (EntityColumn entityColumn : _entityColumns) {
				if (entityColumn.isContainerModel() ||
					entityColumn.isParentContainerModel()) {

					containerModel = true;

					break;
				}
			}
		}

		_containerModel = containerModel;
	}

	public void addReference(Entity reference) {
		_referenceList.add(reference);
	}

	@Override
	public int compareTo(Entity entity) {
		return _name.compareToIgnoreCase(entity._name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity)obj;

		String name = entity.getName();

		if (_name.equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getAlias() {
		return _alias;
	}

	public String getApiPackagePath() {
		return _apiPackagePath;
	}

	public List<EntityColumn> getBadNamedColumnsList() {
		List<EntityColumn> badNamedColumnsList = ListUtil.copy(_entityColumns);

		Iterator<EntityColumn> iterator = badNamedColumnsList.iterator();

		while (iterator.hasNext()) {
			EntityColumn entityColumn = iterator.next();

			String name = entityColumn.getName();

			if (name.equals(entityColumn.getDBName())) {
				iterator.remove();
			}
		}

		return badNamedColumnsList;
	}

	public List<EntityColumn> getBlobEntityColumns() {
		return _blobEntityColumns;
	}

	public List<EntityFinder> getCollectionFinderList() {
		List<EntityFinder> entityFinders = new ArrayList<>(
			_entityFinders.size());

		for (EntityFinder entityFinder : _entityFinders) {
			if (entityFinder.isCollection() &&
				!entityFinder.hasCustomComparator()) {

				entityFinders.add(entityFinder);
			}
		}

		return entityFinders;
	}

	public List<EntityColumn> getCollectionList() {
		return _collectionList;
	}

	public EntityColumn getColumn(String name) {
		return getColumn(name, _entityColumns);
	}

	public EntityColumn getColumnByMappingTable(String mappingTable) {
		for (EntityColumn entityColumn : _entityColumns) {
			String entityColumnMappingTable = entityColumn.getMappingTable();

			if ((entityColumnMappingTable != null) &&
				entityColumnMappingTable.equals(mappingTable)) {

				return entityColumn;
			}
		}

		return null;
	}

	public String getDataSource() {
		return _dataSource;
	}

	public List<EntityColumn> getEntityColumns() {
		return _entityColumns;
	}

	public EntityColumn getFilterPKColumn() {
		for (EntityColumn entityColumn : _entityColumns) {
			if (entityColumn.isFilterPrimary()) {
				return entityColumn;
			}
		}

		return _getPKColumn();
	}

	public String getFinderClass() {
		return _finderClass;
	}

	public List<EntityColumn> getFinderColumnsList() {
		return _finderColumnsList;
	}

	public List<EntityFinder> getFinderList() {
		return _entityFinders;
	}

	public String getHumanName() {
		return _humanName;
	}

	public String getHumanNames() {
		return TextFormatter.formatPlural(_humanName);
	}

	public List<EntityColumn> getLocalizedColumns() {
		return _localizedColumns;
	}

	public Entity getLocalizedEntity() {
		return _localizedEntity;
	}

	public String getName() {
		return _name;
	}

	public String getNames() {
		return TextFormatter.formatPlural(_name);
	}

	public EntityOrder getOrder() {
		return _entityOrder;
	}

	public String getPackagePath() {
		return _packagePath;
	}

	public List<String> getParentTransients() {
		return _parentTransients;
	}

	public String getPersistenceClass() {
		return _persistenceClass;
	}

	public String getPKClassName() {
		if (hasCompoundPK()) {
			return _name + "PK";
		}

		EntityColumn entityColumn = _getPKColumn();

		return entityColumn.getType();
	}

	public String getPKDBName() {
		if (hasCompoundPK()) {
			return getVarName() + "PK";
		}

		EntityColumn entityColumn = _getPKColumn();

		return entityColumn.getDBName();
	}

	public List<EntityColumn> getPKList() {
		return _pkList;
	}

	public String getPKVarName() {
		if (hasCompoundPK()) {
			return getVarName() + "PK";
		}

		EntityColumn entityColumn = _getPKColumn();

		return entityColumn.getName();
	}

	public String getPKVarNames() {
		if (hasCompoundPK()) {
			return getVarName() + "PKs";
		}

		EntityColumn entityColumn = _getPKColumn();

		return entityColumn.getNames();
	}

	public String getPortletName() {
		return _portletName;
	}

	public String getPortletShortName() {
		return _portletShortName;
	}

	public List<Entity> getReferenceList() {
		return _referenceList;
	}

	public List<EntityColumn> getRegularColList() {
		return _regularColList;
	}

	public String getSessionFactory() {
		return _sessionFactory;
	}

	public String getShortName() {
		if (_name.startsWith(_portletShortName)) {
			return _name.substring(_portletShortName.length());
		}
		else {
			return _name;
		}
	}

	public String getSpringPropertyName() {
		return TextFormatter.format(_name, TextFormatter.L);
	}

	public String getTable() {
		return _table;
	}

	public List<String> getTransients() {
		return _transients;
	}

	public String getTXManager() {
		return _txManager;
	}

	public List<String> getTxRequiredList() {
		return _txRequiredList;
	}

	public List<EntityColumn> getUADNonanonymizableEntityColumns() {
		List<EntityColumn> uadNonanonymizableEntityColumns = new ArrayList<>();

		for (EntityColumn entityColumn : _entityColumns) {
			if (entityColumn.isUADNonanonymizable()) {
				uadNonanonymizableEntityColumns.add(entityColumn);
			}
		}

		return uadNonanonymizableEntityColumns;
	}

	public List<String> getUADUserIdColumnNames() {
		List<String> uadUserIdColumnNames = new ArrayList<>();

		for (EntityColumn entityColumn : _entityColumns) {
			if (entityColumn.isUADUserId()) {
				uadUserIdColumnNames.add(entityColumn.getName());
			}
		}

		return uadUserIdColumnNames;
	}

	public List<EntityFinder> getUniqueFinderList() {
		List<EntityFinder> finderList = ListUtil.copy(_entityFinders);

		Iterator<EntityFinder> iterator = finderList.iterator();

		while (iterator.hasNext()) {
			EntityFinder entityFinder = iterator.next();

			if (entityFinder.isCollection() && !entityFinder.isUnique()) {
				iterator.remove();
			}
		}

		return finderList;
	}

	public List<String> getUnresolvedReferenceList() {
		if (_unresolvedReferenceList == null) {
			return new ArrayList<>();
		}

		return _unresolvedReferenceList;
	}

	public String getVarName() {
		return TextFormatter.format(_name, TextFormatter.I);
	}

	public String getVarNames() {
		return TextFormatter.formatPlural(getVarName());
	}

	public boolean hasActionableDynamicQuery() {
		if (hasColumns() && hasLocalService()) {
			if (hasCompoundPK()) {
				EntityColumn entityColumn = _pkList.get(0);

				return entityColumn.isPrimitiveType();
			}
			else {
				return hasPrimitivePK();
			}
		}

		return false;
	}

	public boolean hasArrayableOperator() {
		for (EntityFinder finder : _entityFinders) {
			if (finder.hasArrayableOperator()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasColumn(String name) {
		return hasColumn(name, _entityColumns);
	}

	public boolean hasColumn(String name, String type) {
		return hasColumn(name, type, _entityColumns);
	}

	public boolean hasColumns() {
		if (ListUtil.isEmpty(_entityColumns)) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasCompoundPK() {
		if (_pkList.size() > 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasEagerBlobColumn() {
		if ((_blobEntityColumns == null) || _blobEntityColumns.isEmpty()) {
			return false;
		}

		for (EntityColumn col : _blobEntityColumns) {
			if (!col.isLazy()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasFinderClass() {
		if (Validator.isNull(_finderClass)) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	public boolean hasLazyBlobColumn() {
		if ((_blobEntityColumns == null) || _blobEntityColumns.isEmpty()) {
			return false;
		}

		for (EntityColumn entityColumn : _blobEntityColumns) {
			if (entityColumn.isLazy()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasLocalService() {
		return _localService;
	}

	public boolean hasPrimitivePK() {
		return hasPrimitivePK(true);
	}

	public boolean hasPrimitivePK(boolean includeWrappers) {
		if (hasCompoundPK()) {
			return false;
		}

		EntityColumn entityColumn = _getPKColumn();

		if (entityColumn.isPrimitiveType(includeWrappers)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasRemoteService() {
		return _remoteService;
	}

	public boolean hasUuid() {
		return _uuid;
	}

	public boolean hasUuidAccessor() {
		return _uuidAccessor;
	}

	public boolean isAttachedModel() {
		if (!isTypedModel()) {
			return false;
		}

		if (hasColumn("classPK")) {
			EntityColumn classPKCol = getColumn("classPK");

			String classPKColType = classPKCol.getType();

			if (classPKColType.equals("long")) {
				return true;
			}
		}

		return false;
	}

	public boolean isAuditedModel() {
		if (hasColumn("companyId") && hasColumn("createDate", "Date") &&
			hasColumn("modifiedDate", "Date") && hasColumn("userId") &&
			hasColumn("userName")) {

			return true;
		}

		return false;
	}

	public boolean isCacheEnabled() {
		return _cacheEnabled;
	}

	public boolean isContainerModel() {
		return _containerModel;
	}

	public boolean isDefaultDataSource() {
		if (_dataSource.equals(_DATA_SOURCE_DEFAULT)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDefaultSessionFactory() {
		if (_sessionFactory.equals(_SESSION_FACTORY_DEFAULT)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDefaultTXManager() {
		if (_txManager.equals(_TX_MANAGER_DEFAULT)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDeprecated() {
		return _deprecated;
	}

	public boolean isDynamicUpdateEnabled() {
		return _dynamicUpdateEnabled;
	}

	public boolean isGroupedModel() {
		String pkVarName = getPKVarName();

		if (isAuditedModel() && hasColumn("groupId") &&
			!pkVarName.equals("groupId")) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isHierarchicalTree() {
		if (!hasPrimitivePK()) {
			return false;
		}

		EntityColumn entityColumn = _getPKColumn();

		String methodName = entityColumn.getMethodName();

		if ((_entityColumns.indexOf(new EntityColumn("parent" + methodName)) !=
				-1) &&
			(_entityColumns.indexOf(new EntityColumn("left" + methodName)) !=
				-1) &&
			(_entityColumns.indexOf(new EntityColumn("right" + methodName)) !=
				-1)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isJsonEnabled() {
		return _jsonEnabled;
	}

	public boolean isLocalizedModel() {
		for (EntityColumn entityColumn : _entityColumns) {
			if (entityColumn.isLocalized()) {
				return true;
			}
		}

		return false;
	}

	public boolean isMvccEnabled() {
		return _mvccEnabled;
	}

	public boolean isOrdered() {
		if (_entityOrder != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPermissionCheckEnabled() {
		for (EntityFinder finder : _entityFinders) {
			if (isPermissionCheckEnabled(finder)) {
				return true;
			}
		}

		return false;
	}

	public boolean isPermissionCheckEnabled(EntityFinder finder) {
		String finderName = finder.getName();

		if (_name.equals("Group") || _name.equals("User") ||
			finderName.equals("UUID_G") || !finder.isCollection() ||
			!hasPrimitivePK() || !_resourceActionModel) {

			return false;
		}

		if (hasColumn("groupId") && !finder.hasColumn("groupId")) {
			return false;
		}

		EntityColumn entityColumn = _getPKColumn();

		return StringUtil.equalsIgnoreCase("long", entityColumn.getType());
	}

	public boolean isPermissionedModel() {
		if (hasColumn("resourceBlockId")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPortalReference() {
		return _portalReference;
	}

	public boolean isResolved() {
		if ((_unresolvedReferenceList != null) &&
			_unresolvedReferenceList.isEmpty()) {

			return true;
		}

		return false;
	}

	public boolean isResourcedModel() {
		String pkVarName = getPKVarName();

		if (hasColumn("resourcePrimKey") &&
			!pkVarName.equals("resourcePrimKey")) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isShardedModel() {
		if (_packagePath.equals("com.liferay.portal") &&
			_name.equals("Company")) {

			return false;
		}

		return hasColumn("companyId");
	}

	public boolean isStagedAuditedModel() {
		if (isAuditedModel() && isStagedModel()) {
			return true;
		}

		return false;
	}

	public boolean isStagedGroupedModel() {
		if (isGroupedModel() && isStagedModel() &&
			hasColumn("lastPublishDate", "Date")) {

			return true;
		}

		return false;
	}

	public boolean isStagedModel() {
		if (hasUuid() && hasColumn("companyId") &&
			hasColumn("createDate", "Date") &&
			hasColumn("modifiedDate", "Date")) {

			return true;
		}

		return false;
	}

	public boolean isTrashEnabled() {
		return _trashEnabled;
	}

	public boolean isTreeModel() {
		if (hasColumn("treePath")) {
			return true;
		}

		return false;
	}

	public boolean isTypedModel() {
		if (hasColumn("classNameId")) {
			EntityColumn classNameIdCol = getColumn("classNameId");

			String classNameIdColType = classNameIdCol.getType();

			if (classNameIdColType.equals("long")) {
				return true;
			}
		}

		return false;
	}

	public boolean isUADEnabled() {
		for (EntityColumn col : _entityColumns) {
			if (col.isUADEnabled()) {
				return true;
			}
		}

		return false;
	}

	public boolean isWorkflowEnabled() {
		if (hasColumn("status") && hasColumn("statusByUserId") &&
			hasColumn("statusByUserName") && hasColumn("statusDate")) {

			return true;
		}
		else {
			return false;
		}
	}

	public void setLocalizedColumns(List<EntityColumn> localizedColumns) {
		_localizedColumns = localizedColumns;
	}

	public void setLocalizedEntity(Entity localizedEntity) {
		_localizedEntity = localizedEntity;

		_referenceList.add(localizedEntity);
	}

	public void setParentTransients(List<String> transients) {
		_parentTransients = transients;
	}

	public void setPortalReference(boolean portalReference) {
		_portalReference = portalReference;
	}

	public void setResolved() {
		_unresolvedReferenceList = null;
	}

	public void setTransients(List<String> transients) {
		_transients = transients;
	}

	private EntityColumn _getPKColumn() {
		if (_pkList.isEmpty()) {
			throw new RuntimeException(
				"There is no primary key for entity " + _name);
		}

		return _pkList.get(0);
	}

	private static final String _DATA_SOURCE_DEFAULT = "liferayDataSource";

	private static final String _SESSION_FACTORY_DEFAULT =
		"liferaySessionFactory";

	private static final String _TX_MANAGER_DEFAULT =
		"liferayTransactionManager";

	private final String _alias;
	private final String _apiPackagePath;
	private List<EntityColumn> _blobEntityColumns;
	private final boolean _cacheEnabled;
	private final List<EntityColumn> _collectionList;
	private final boolean _containerModel;
	private final String _dataSource;
	private final boolean _deprecated;
	private final boolean _dynamicUpdateEnabled;
	private final List<EntityColumn> _entityColumns;
	private final List<EntityFinder> _entityFinders;
	private final EntityOrder _entityOrder;
	private final String _finderClass;
	private final List<EntityColumn> _finderColumnsList;
	private final String _humanName;
	private final boolean _jsonEnabled;
	private List<EntityColumn> _localizedColumns;
	private Entity _localizedEntity;
	private final boolean _localService;
	private final boolean _mvccEnabled;
	private final String _name;
	private final String _packagePath;
	private List<String> _parentTransients;
	private final String _persistenceClass;
	private final List<EntityColumn> _pkList;
	private boolean _portalReference;
	private final String _portletName;
	private final String _portletShortName;
	private final List<Entity> _referenceList;
	private final List<EntityColumn> _regularColList;
	private final boolean _remoteService;
	private final boolean _resourceActionModel;
	private final String _sessionFactory;
	private final String _table;
	private List<String> _transients;
	private final boolean _trashEnabled;
	private final String _txManager;
	private final List<String> _txRequiredList;
	private List<String> _unresolvedReferenceList;
	private final boolean _uuid;
	private final boolean _uuidAccessor;

}