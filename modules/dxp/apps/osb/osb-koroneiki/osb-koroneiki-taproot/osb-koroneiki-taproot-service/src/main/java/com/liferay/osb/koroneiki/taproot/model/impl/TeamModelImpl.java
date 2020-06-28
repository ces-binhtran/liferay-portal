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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamModel;
import com.liferay.osb.koroneiki.taproot.model.TeamSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Team service. Represents a row in the &quot;Koroneiki_Team&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>TeamModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TeamImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamImpl
 * @generated
 */
@JSON(strict = true)
public class TeamModelImpl extends BaseModelImpl<Team> implements TeamModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a team model instance should use the <code>Team</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_Team";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"teamId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"teamKey", Types.VARCHAR},
		{"accountId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"system_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("teamId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("teamKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("system_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_Team (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,teamId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,teamKey VARCHAR(75) null,accountId LONG,name VARCHAR(150) null,system_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table Koroneiki_Team";

	public static final String ORDER_BY_JPQL = " ORDER BY team.teamId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_Team.teamId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long SYSTEM_COLUMN_BITMASK = 8L;

	public static final long TEAMKEY_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long TEAMID_COLUMN_BITMASK = 64L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Team toModel(TeamSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Team model = new TeamImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setTeamId(soapModel.getTeamId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTeamKey(soapModel.getTeamKey());
		model.setAccountId(soapModel.getAccountId());
		model.setName(soapModel.getName());
		model.setSystem(soapModel.isSystem());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Team> toModels(TeamSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Team> models = new ArrayList<Team>(soapModels.length);

		for (TeamSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public TeamModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _teamId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTeamId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _teamId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Team.class;
	}

	@Override
	public String getModelClassName() {
		return Team.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Team, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Team, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Team, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Team)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Team, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Team, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Team)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Team, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Team, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Team>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Team.class.getClassLoader(), Team.class, ModelWrapper.class);

		try {
			Constructor<Team> constructor =
				(Constructor<Team>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Team, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Team, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Team, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Team, Object>>();
		Map<String, BiConsumer<Team, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Team, ?>>();

		attributeGetterFunctions.put("mvccVersion", Team::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<Team, Long>)Team::setMvccVersion);
		attributeGetterFunctions.put("uuid", Team::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Team, String>)Team::setUuid);
		attributeGetterFunctions.put("teamId", Team::getTeamId);
		attributeSetterBiConsumers.put(
			"teamId", (BiConsumer<Team, Long>)Team::setTeamId);
		attributeGetterFunctions.put("companyId", Team::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Team, Long>)Team::setCompanyId);
		attributeGetterFunctions.put("userId", Team::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Team, Long>)Team::setUserId);
		attributeGetterFunctions.put("createDate", Team::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Team, Date>)Team::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Team::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Team, Date>)Team::setModifiedDate);
		attributeGetterFunctions.put("teamKey", Team::getTeamKey);
		attributeSetterBiConsumers.put(
			"teamKey", (BiConsumer<Team, String>)Team::setTeamKey);
		attributeGetterFunctions.put("accountId", Team::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId", (BiConsumer<Team, Long>)Team::setAccountId);
		attributeGetterFunctions.put("name", Team::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Team, String>)Team::setName);
		attributeGetterFunctions.put("system", Team::getSystem);
		attributeSetterBiConsumers.put(
			"system", (BiConsumer<Team, Boolean>)Team::setSystem);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getTeamId() {
		return _teamId;
	}

	@Override
	public void setTeamId(long teamId) {
		_teamId = teamId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getTeamKey() {
		if (_teamKey == null) {
			return "";
		}
		else {
			return _teamKey;
		}
	}

	@Override
	public void setTeamKey(String teamKey) {
		_columnBitmask |= TEAMKEY_COLUMN_BITMASK;

		if (_originalTeamKey == null) {
			_originalTeamKey = _teamKey;
		}

		_teamKey = teamKey;
	}

	public String getOriginalTeamKey() {
		return GetterUtil.getString(_originalTeamKey);
	}

	@JSON
	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_columnBitmask |= ACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalAccountId) {
			_setOriginalAccountId = true;

			_originalAccountId = _accountId;
		}

		_accountId = accountId;
	}

	public long getOriginalAccountId() {
		return _originalAccountId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public boolean getSystem() {
		return _system;
	}

	@JSON
	@Override
	public boolean isSystem() {
		return _system;
	}

	@Override
	public void setSystem(boolean system) {
		_columnBitmask |= SYSTEM_COLUMN_BITMASK;

		if (!_setOriginalSystem) {
			_setOriginalSystem = true;

			_originalSystem = _system;
		}

		_system = system;
	}

	public boolean getOriginalSystem() {
		return _originalSystem;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Team.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Team.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Team toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Team>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		TeamImpl teamImpl = new TeamImpl();

		teamImpl.setMvccVersion(getMvccVersion());
		teamImpl.setUuid(getUuid());
		teamImpl.setTeamId(getTeamId());
		teamImpl.setCompanyId(getCompanyId());
		teamImpl.setUserId(getUserId());
		teamImpl.setCreateDate(getCreateDate());
		teamImpl.setModifiedDate(getModifiedDate());
		teamImpl.setTeamKey(getTeamKey());
		teamImpl.setAccountId(getAccountId());
		teamImpl.setName(getName());
		teamImpl.setSystem(isSystem());

		teamImpl.resetOriginalValues();

		return teamImpl;
	}

	@Override
	public int compareTo(Team team) {
		long primaryKey = team.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Team)) {
			return false;
		}

		Team team = (Team)object;

		long primaryKey = team.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		TeamModelImpl teamModelImpl = this;

		teamModelImpl._originalUuid = teamModelImpl._uuid;

		teamModelImpl._originalCompanyId = teamModelImpl._companyId;

		teamModelImpl._setOriginalCompanyId = false;

		teamModelImpl._setModifiedDate = false;

		teamModelImpl._originalTeamKey = teamModelImpl._teamKey;

		teamModelImpl._originalAccountId = teamModelImpl._accountId;

		teamModelImpl._setOriginalAccountId = false;

		teamModelImpl._originalName = teamModelImpl._name;

		teamModelImpl._originalSystem = teamModelImpl._system;

		teamModelImpl._setOriginalSystem = false;

		teamModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Team> toCacheModel() {
		TeamCacheModel teamCacheModel = new TeamCacheModel();

		teamCacheModel.mvccVersion = getMvccVersion();

		teamCacheModel.uuid = getUuid();

		String uuid = teamCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			teamCacheModel.uuid = null;
		}

		teamCacheModel.teamId = getTeamId();

		teamCacheModel.companyId = getCompanyId();

		teamCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			teamCacheModel.createDate = createDate.getTime();
		}
		else {
			teamCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			teamCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			teamCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		teamCacheModel.teamKey = getTeamKey();

		String teamKey = teamCacheModel.teamKey;

		if ((teamKey != null) && (teamKey.length() == 0)) {
			teamCacheModel.teamKey = null;
		}

		teamCacheModel.accountId = getAccountId();

		teamCacheModel.name = getName();

		String name = teamCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			teamCacheModel.name = null;
		}

		teamCacheModel.system = isSystem();

		return teamCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Team, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Team, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Team, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Team)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Team, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Team, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Team, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Team)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Team>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _teamId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _teamKey;
	private String _originalTeamKey;
	private long _accountId;
	private long _originalAccountId;
	private boolean _setOriginalAccountId;
	private String _name;
	private String _originalName;
	private boolean _system;
	private boolean _originalSystem;
	private boolean _setOriginalSystem;
	private long _columnBitmask;
	private Team _escapedModel;

}