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

import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleModel;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the TeamAccountRole service. Represents a row in the &quot;Koroneiki_TeamAccountRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>TeamAccountRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TeamAccountRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleImpl
 * @generated
 */
@JSON(strict = true)
public class TeamAccountRoleModelImpl
	extends BaseModelImpl<TeamAccountRole> implements TeamAccountRoleModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a team account role model instance should use the <code>TeamAccountRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_TeamAccountRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"teamId", Types.BIGINT}, {"accountId", Types.BIGINT},
		{"teamRoleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("teamId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teamRoleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_TeamAccountRole (teamId LONG not null,accountId LONG not null,teamRoleId LONG not null,primary key (teamId, accountId, teamRoleId))";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_TeamAccountRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY teamAccountRole.id.teamId ASC, teamAccountRole.id.accountId ASC, teamAccountRole.id.teamRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_TeamAccountRole.teamId ASC, Koroneiki_TeamAccountRole.accountId ASC, Koroneiki_TeamAccountRole.teamRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long TEAMID_COLUMN_BITMASK = 2L;

	public static final long TEAMROLEID_COLUMN_BITMASK = 4L;

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
	public static TeamAccountRole toModel(TeamAccountRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		TeamAccountRole model = new TeamAccountRoleImpl();

		model.setTeamId(soapModel.getTeamId());
		model.setAccountId(soapModel.getAccountId());
		model.setTeamRoleId(soapModel.getTeamRoleId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<TeamAccountRole> toModels(
		TeamAccountRoleSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<TeamAccountRole> models = new ArrayList<TeamAccountRole>(
			soapModels.length);

		for (TeamAccountRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public TeamAccountRoleModelImpl() {
	}

	@Override
	public TeamAccountRolePK getPrimaryKey() {
		return new TeamAccountRolePK(_teamId, _accountId, _teamRoleId);
	}

	@Override
	public void setPrimaryKey(TeamAccountRolePK primaryKey) {
		setTeamId(primaryKey.teamId);
		setAccountId(primaryKey.accountId);
		setTeamRoleId(primaryKey.teamRoleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new TeamAccountRolePK(_teamId, _accountId, _teamRoleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((TeamAccountRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return TeamAccountRole.class;
	}

	@Override
	public String getModelClassName() {
		return TeamAccountRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<TeamAccountRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<TeamAccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamAccountRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((TeamAccountRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<TeamAccountRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<TeamAccountRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(TeamAccountRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<TeamAccountRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<TeamAccountRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, TeamAccountRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			TeamAccountRole.class.getClassLoader(), TeamAccountRole.class,
			ModelWrapper.class);

		try {
			Constructor<TeamAccountRole> constructor =
				(Constructor<TeamAccountRole>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<TeamAccountRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<TeamAccountRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<TeamAccountRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<TeamAccountRole, Object>>();
		Map<String, BiConsumer<TeamAccountRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<TeamAccountRole, ?>>();

		attributeGetterFunctions.put("teamId", TeamAccountRole::getTeamId);
		attributeSetterBiConsumers.put(
			"teamId",
			(BiConsumer<TeamAccountRole, Long>)TeamAccountRole::setTeamId);
		attributeGetterFunctions.put(
			"accountId", TeamAccountRole::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId",
			(BiConsumer<TeamAccountRole, Long>)TeamAccountRole::setAccountId);
		attributeGetterFunctions.put(
			"teamRoleId", TeamAccountRole::getTeamRoleId);
		attributeSetterBiConsumers.put(
			"teamRoleId",
			(BiConsumer<TeamAccountRole, Long>)TeamAccountRole::setTeamRoleId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getTeamId() {
		return _teamId;
	}

	@Override
	public void setTeamId(long teamId) {
		_columnBitmask |= TEAMID_COLUMN_BITMASK;

		if (!_setOriginalTeamId) {
			_setOriginalTeamId = true;

			_originalTeamId = _teamId;
		}

		_teamId = teamId;
	}

	public long getOriginalTeamId() {
		return _originalTeamId;
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
	public long getTeamRoleId() {
		return _teamRoleId;
	}

	@Override
	public void setTeamRoleId(long teamRoleId) {
		_columnBitmask |= TEAMROLEID_COLUMN_BITMASK;

		if (!_setOriginalTeamRoleId) {
			_setOriginalTeamRoleId = true;

			_originalTeamRoleId = _teamRoleId;
		}

		_teamRoleId = teamRoleId;
	}

	public long getOriginalTeamRoleId() {
		return _originalTeamRoleId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public TeamAccountRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, TeamAccountRole>
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
		TeamAccountRoleImpl teamAccountRoleImpl = new TeamAccountRoleImpl();

		teamAccountRoleImpl.setTeamId(getTeamId());
		teamAccountRoleImpl.setAccountId(getAccountId());
		teamAccountRoleImpl.setTeamRoleId(getTeamRoleId());

		teamAccountRoleImpl.resetOriginalValues();

		return teamAccountRoleImpl;
	}

	@Override
	public int compareTo(TeamAccountRole teamAccountRole) {
		TeamAccountRolePK primaryKey = teamAccountRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TeamAccountRole)) {
			return false;
		}

		TeamAccountRole teamAccountRole = (TeamAccountRole)obj;

		TeamAccountRolePK primaryKey = teamAccountRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		TeamAccountRoleModelImpl teamAccountRoleModelImpl = this;

		teamAccountRoleModelImpl._originalTeamId =
			teamAccountRoleModelImpl._teamId;

		teamAccountRoleModelImpl._setOriginalTeamId = false;

		teamAccountRoleModelImpl._originalAccountId =
			teamAccountRoleModelImpl._accountId;

		teamAccountRoleModelImpl._setOriginalAccountId = false;

		teamAccountRoleModelImpl._originalTeamRoleId =
			teamAccountRoleModelImpl._teamRoleId;

		teamAccountRoleModelImpl._setOriginalTeamRoleId = false;

		teamAccountRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<TeamAccountRole> toCacheModel() {
		TeamAccountRoleCacheModel teamAccountRoleCacheModel =
			new TeamAccountRoleCacheModel();

		teamAccountRoleCacheModel.teamAccountRolePK = getPrimaryKey();

		teamAccountRoleCacheModel.teamId = getTeamId();

		teamAccountRoleCacheModel.accountId = getAccountId();

		teamAccountRoleCacheModel.teamRoleId = getTeamRoleId();

		return teamAccountRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<TeamAccountRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<TeamAccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamAccountRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((TeamAccountRole)this));
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
		Map<String, Function<TeamAccountRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<TeamAccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamAccountRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((TeamAccountRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, TeamAccountRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _teamId;
	private long _originalTeamId;
	private boolean _setOriginalTeamId;
	private long _accountId;
	private long _originalAccountId;
	private boolean _setOriginalAccountId;
	private long _teamRoleId;
	private long _originalTeamRoleId;
	private boolean _setOriginalTeamRoleId;
	private long _columnBitmask;
	private TeamAccountRole _escapedModel;

}