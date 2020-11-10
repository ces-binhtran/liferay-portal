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

package com.liferay.portal.service.impl;

import com.liferay.admin.kernel.util.PortalMyAccountApplicationType;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.kernel.application.type.ApplicationType;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.PortletIdException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.SpriteProcessor;
import com.liferay.portal.kernel.image.SpriteProcessorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.EventDefinition;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.model.PortletCategory;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletFilter;
import com.liferay.portal.kernel.model.PortletInfo;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.PortletURLListener;
import com.liferay.portal.kernel.model.PublicRenderParameter;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.portlet.PortletDependencyFactoryUtil;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletContextFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletInstanceFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQNameUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.ServletContextClassLoaderPool;
import com.liferay.portal.kernel.servlet.ServletContextUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.model.impl.EventDefinitionImpl;
import com.liferay.portal.model.impl.PortletAppImpl;
import com.liferay.portal.model.impl.PortletFilterImpl;
import com.liferay.portal.model.impl.PortletImpl;
import com.liferay.portal.model.impl.PortletURLListenerImpl;
import com.liferay.portal.model.impl.PublicRenderParameterImpl;
import com.liferay.portal.service.base.PortletLocalServiceBaseImpl;
import com.liferay.portal.servlet.ComboServlet;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebAppPool;
import com.liferay.portlet.PortletBagFactory;
import com.liferay.portlet.UndeployedPortlet;
import com.liferay.portlet.extra.config.ExtraPortletAppConfig;
import com.liferay.portlet.extra.config.ExtraPortletAppConfigRegistry;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.util.JS;

import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import javax.portlet.PortletMode;
import javax.portlet.PreferencesValidator;
import javax.portlet.WindowState;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Eduardo Lundgren
 * @author Wesley Gong
 * @author Shuyang Zhou
 * @author Neil Griffin
 */
public class PortletLocalServiceImpl extends PortletLocalServiceBaseImpl {

	@Override
	@Transactional(enabled = false)
	public void addPortletCategory(long companyId, String categoryName) {
		PortletCategory portletCategory = (PortletCategory)WebAppPool.get(
			companyId, WebKeys.PORTLET_CATEGORY);

		if (portletCategory == null) {
			_log.error(
				"Unable to add portlet category for company " + companyId +
					" because it does not exist");

			return;
		}

		PortletCategory newPortletCategory = new PortletCategory(categoryName);

		if (newPortletCategory.getParentCategory() == null) {
			PortletCategory rootPortletCategory = new PortletCategory();

			rootPortletCategory.addCategory(newPortletCategory);
		}

		portletCategory.merge(newPortletCategory.getRootCategory());
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();

		Registry registry = RegistryUtil.getRegistry();

		Filter filter = registry.getFilter(
			"(objectClass=" + FriendlyURLMapper.class.getName() + ")");

		_serviceTracker = registry.trackServices(
			filter, new FriendlyURLMapperServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	@Override
	public void checkPortlet(Portlet portlet) throws PortalException {
		resourcePermissionLocalService.initPortletDefaultPermissions(portlet);

		initPortletAddToPagePermissions(portlet);
	}

	@Override
	public void checkPortlets(long companyId) throws PortalException {
		List<Portlet> portlets = getPortlets(companyId);

		for (Portlet portlet : portlets) {
			checkPortlet(portlet);
		}
	}

	@Override
	@Transactional(enabled = false)
	public void clearCache() {

		// Refresh the combo servlet cache

		ComboServlet.clearCache();

		// Refresh security path to portlet id mapping for all portlets

		_portletIdsByStrutsPath = null;

		// Refresh company portlets

		portletLocalService.clearPortletsMap();
	}

	@Clusterable
	@Override
	@Transactional(enabled = false)
	public void clearPortletsMap() {
		_portletsMaps.clear();
	}

	@Override
	@Transactional(enabled = false)
	public Portlet clonePortlet(String portletId) {
		Portlet portlet = getPortletById(portletId);

		return (Portlet)portlet.clone();
	}

	@Override
	public void deletePortlet(long companyId, String portletId, long plid)
		throws PortalException {

		String rootPortletId = PortletIdCodec.decodePortletName(portletId);

		resourceLocalService.deleteResource(
			companyId, rootPortletId, ResourceConstants.SCOPE_INDIVIDUAL,
			PortletPermissionUtil.getPrimaryKey(plid, portletId));

		List<PortletPreferences> portletPreferencesList =
			portletPreferencesLocalService.getPortletPreferences(
				plid, portletId);

		Portlet portlet = getPortletById(companyId, portletId);

		PortletLayoutListener portletLayoutListener = null;

		if (portlet != null) {
			portletLayoutListener = portlet.getPortletLayoutListenerInstance();

			PortletInstanceFactoryUtil.delete(portlet);
		}

		for (PortletPreferences portletPreferences : portletPreferencesList) {
			if (portletLayoutListener != null) {
				portletLayoutListener.onRemoveFromLayout(
					portletPreferences.getPortletId(), plid);
			}

			portletPreferencesLocalService.deletePortletPreferences(
				portletPreferences.getPortletPreferencesId());
		}
	}

	@Override
	public void deletePortlets(long companyId, String[] portletIds, long plid)
		throws PortalException {

		for (String portletId : portletIds) {
			deletePortlet(companyId, portletId, plid);
		}
	}

	@Override
	@Transactional(enabled = false)
	public void deployPortlet(Portlet portlet) throws Exception {
		PortletApp portletApp = portlet.getPortletApp();

		_portletApps.put(portletApp.getServletContextName(), portletApp);

		ServletContext servletContext = portletApp.getServletContext();

		PortletBagFactory portletBagFactory = new PortletBagFactory();

		portletBagFactory.setClassLoader(
			_getServletContextClassLoader(
				servletContext.getServletContextName()));
		portletBagFactory.setServletContext(servletContext);
		portletBagFactory.setWARFile(true);

		portletBagFactory.create(portlet, true);

		_portletsMap.put(portlet.getRootPortletId(), portlet);

		clearCache();
	}

	@Override
	public Portlet deployRemotePortlet(Portlet portlet, String categoryName)
		throws PortalException {

		return deployRemotePortlet(portlet, new String[] {categoryName});
	}

	@Override
	public Portlet deployRemotePortlet(Portlet portlet, String[] categoryNames)
		throws PortalException {

		return deployRemotePortlet(portlet, categoryNames, true);
	}

	@Override
	public Portlet deployRemotePortlet(
			Portlet portlet, String[] categoryNames, boolean eagerDestroy)
		throws PortalException {

		_portletsMap.put(portlet.getRootPortletId(), portlet);

		ResourceActionsUtil.check(portlet.getPortletId());

		if (eagerDestroy) {
			PortletInstanceFactoryUtil.clear(portlet, false);

			PortletConfigFactoryUtil.destroy(portlet);
		}

		clearCache();

		for (Company company : companyLocalService.getCompanies()) {
			Portlet companyPortletModel = (Portlet)portlet.clone();

			companyPortletModel.setCompanyId(company.getCompanyId());

			PortletCategory portletCategory = (PortletCategory)WebAppPool.get(
				companyPortletModel.getCompanyId(), WebKeys.PORTLET_CATEGORY);

			if (portletCategory == null) {
				_log.error(
					"Unable to register remote portlet for company " +
						companyPortletModel.getCompanyId() +
							" because it does not exist");

				return portlet;
			}

			portletCategory.separate(companyPortletModel.getPortletId());

			for (String categoryName : categoryNames) {
				PortletCategory newPortletCategory = new PortletCategory(
					categoryName);

				if (newPortletCategory.getParentCategory() == null) {
					PortletCategory rootPortletCategory = new PortletCategory();

					rootPortletCategory.addCategory(newPortletCategory);
				}

				Set<String> portletIds = newPortletCategory.getPortletIds();

				portletIds.add(companyPortletModel.getPortletId());

				portletCategory.merge(newPortletCategory.getRootCategory());
			}

			checkPortlet(companyPortletModel);
		}

		return portlet;
	}

	@Override
	public void destroy() {
		super.destroy();

		_serviceTracker.close();
	}

	@Override
	@Transactional(enabled = false)
	public void destroyPortlet(Portlet portlet) {
		_portletsMap.remove(portlet.getRootPortletId());

		PortletApp portletApp = portlet.getPortletApp();

		if (portletApp != null) {
			String servletContextName = portletApp.getServletContextName();

			_portletApps.remove(servletContextName);

			ExtraPortletAppConfigRegistry.unregisterExtraPortletAppConfig(
				servletContextName);
		}

		clearCache();
	}

	@Override
	@Transactional(enabled = false)
	public void destroyRemotePortlet(Portlet portlet) {
		destroyPortlet(portlet);
	}

	@Override
	@Transactional(enabled = false)
	public Portlet fetchPortletById(long companyId, String portletId) {
		portletId = PortalUtil.getJsSafePortletId(portletId);

		Map<String, Portlet> companyPortletsMap = getPortletsMap(companyId);

		String rootPortletId = PortletIdCodec.decodePortletName(portletId);

		if (portletId.equals(rootPortletId)) {
			return companyPortletsMap.get(portletId);
		}

		Portlet portlet = companyPortletsMap.get(rootPortletId);

		if (portlet != null) {
			portlet = portlet.getClonedInstance(portletId);
		}

		return portlet;
	}

	@Override
	@Transactional(enabled = false)
	public List<CustomAttributesDisplay> getCustomAttributesDisplays() {
		List<CustomAttributesDisplay> customAttributesDisplays =
			new ArrayList<>();

		for (Portlet portlet : getPortlets()) {
			if (!portlet.isActive() || !portlet.isInclude() ||
				!portlet.isReady() || portlet.isUndeployedPortlet()) {

				continue;
			}

			List<CustomAttributesDisplay> portletCustomAttributesDisplays =
				portlet.getCustomAttributesDisplayInstances();

			if ((portletCustomAttributesDisplays != null) &&
				!portletCustomAttributesDisplays.isEmpty()) {

				customAttributesDisplays.addAll(
					portletCustomAttributesDisplays);
			}
		}

		return customAttributesDisplays;
	}

	@Override
	@Transactional(enabled = false)
	public PortletCategory getEARDisplay(String xml) {
		try {
			return readLiferayDisplayXML(xml);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Override
	@Transactional(enabled = false)
	public List<Portlet> getFriendlyURLMapperPortlets() {
		String[] friendlyURLMapperRootPortletIds =
			_friendlyURLMapperRootPortletIds.get();

		List<Portlet> portlets = new ArrayList<>(
			friendlyURLMapperRootPortletIds.length);

		for (String rootPortletId : friendlyURLMapperRootPortletIds) {
			Portlet portlet = _portletsMap.get(rootPortletId);

			if ((portlet == null) || !portlet.isActive() ||
				!portlet.isInclude() || !portlet.isReady() ||
				portlet.isUndeployedPortlet()) {

				continue;
			}

			portlets.add(portlet);
		}

		return portlets;
	}

	@Override
	@Transactional(enabled = false)
	public List<FriendlyURLMapper> getFriendlyURLMappers() {
		List<FriendlyURLMapper> friendlyURLMappers = new ArrayList<>();

		for (Portlet portlet : getPortlets()) {
			if (!portlet.isActive() || !portlet.isInclude() ||
				!portlet.isReady() || portlet.isUndeployedPortlet()) {

				continue;
			}

			FriendlyURLMapper friendlyURLMapper =
				portlet.getFriendlyURLMapperInstance();

			if (friendlyURLMapper != null) {
				friendlyURLMappers.add(friendlyURLMapper);
			}
		}

		return friendlyURLMappers;
	}

	@Override
	@Transactional(enabled = false)
	public PortletApp getPortletApp(String servletContextName) {
		PortletApp portletApp = _portletApps.get(servletContextName);

		if (portletApp == null) {
			portletApp = new PortletAppImpl(servletContextName);

			_portletApps.put(servletContextName, portletApp);
		}

		return portletApp;
	}

	@Override
	@Transactional(enabled = false)
	public Portlet getPortletById(long companyId, String portletId) {
		Portlet portlet = fetchPortletById(companyId, portletId);

		if (portlet != null) {
			return portlet;
		}

		if (portletId.equals(PortletKeys.LIFERAY_PORTAL)) {
			return portlet;
		}

		if (_portletsMap.isEmpty()) {
			if (_log.isDebugEnabled()) {
				_log.debug("No portlets are installed");
			}
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"Portlet not found for ", companyId, " ", portletId));
			}

			portlet = new PortletImpl(CompanyConstants.SYSTEM, portletId);

			portlet.setPortletApp(getPortletApp(StringPool.BLANK));

			portlet.setPortletName(portletId);
			portlet.setDisplayName(portletId);
			portlet.setPortletClass(UndeployedPortlet.class.getName());

			Set<String> mimeTypePortletModes = new HashSet<>();

			mimeTypePortletModes.add(
				StringUtil.toLowerCase(PortletMode.VIEW.toString()));

			Map<String, Set<String>> portletModes = portlet.getPortletModes();

			portletModes.put(ContentTypes.TEXT_HTML, mimeTypePortletModes);

			Set<String> mimeTypeWindowStates = new HashSet<>();

			mimeTypeWindowStates.add(
				StringUtil.toLowerCase(WindowState.NORMAL.toString()));

			Map<String, Set<String>> windowStates = portlet.getWindowStates();

			windowStates.put(ContentTypes.TEXT_HTML, mimeTypeWindowStates);

			portlet.setPortletInfo(
				new PortletInfo(portletId, portletId, portletId, portletId));

			if (PortletIdCodec.hasInstanceId(portletId)) {
				portlet.setInstanceable(true);
			}

			portlet.setActive(true);
			portlet.setUndeployedPortlet(true);
		}

		return portlet;
	}

	@Override
	@Transactional(enabled = false)
	public Portlet getPortletById(String portletId) {
		return _portletsMap.get(PortletIdCodec.decodePortletName(portletId));
	}

	@Override
	@Transactional(enabled = false)
	public Portlet getPortletByStrutsPath(long companyId, String strutsPath) {
		String portletId = getPortletId(strutsPath);

		if (portletId == null) {
			return null;
		}

		return getPortletById(companyId, portletId);
	}

	@Override
	@Transactional(enabled = false)
	public List<Portlet> getPortlets() {
		return ListUtil.fromMapValues(_portletsMap);
	}

	@Override
	@Transactional(enabled = false)
	public List<Portlet> getPortlets(long companyId) {
		return getPortlets(companyId, true, true);
	}

	@Override
	@Transactional(enabled = false)
	public List<Portlet> getPortlets(
		long companyId, boolean showSystem, boolean showPortal) {

		List<Portlet> portlets = ListUtil.fromMapValues(
			getPortletsMap(companyId));

		if (showSystem && showPortal) {
			return portlets;
		}

		Iterator<Portlet> iterator = portlets.iterator();

		while (iterator.hasNext()) {
			Portlet portlet = iterator.next();

			String portletId = portlet.getPortletId();

			if (showPortal && portletId.equals(PortletKeys.PORTAL)) {
			}
			else if (!showPortal && portletId.equals(PortletKeys.PORTAL)) {
				iterator.remove();
			}
			else if (!showSystem && portlet.isSystem()) {
				iterator.remove();
			}
		}

		return portlets;
	}

	@Override
	public int getPortletsCount(long companyId) {
		return portletPersistence.countByCompanyId(companyId);
	}

	@Override
	@Transactional(enabled = false)
	public List<Portlet> getScopablePortlets() {
		List<Portlet> portlets = ListUtil.fromMapValues(_portletsMap);

		Iterator<Portlet> iterator = portlets.iterator();

		while (iterator.hasNext()) {
			Portlet portlet = iterator.next();

			if (!portlet.isScopeable()) {
				iterator.remove();
			}
		}

		return portlets;
	}

	@Override
	@Transactional(enabled = false)
	public PortletCategory getWARDisplay(
		String servletContextName, String xml) {

		try {
			return readLiferayDisplayXML(servletContextName, xml);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Override
	@Transactional(enabled = false)
	public boolean hasPortlet(long companyId, String portletId) {
		portletId = PortalUtil.getJsSafePortletId(portletId);

		Portlet portlet = null;

		Map<String, Portlet> companyPortletsMap = getPortletsMap(companyId);

		String rootPortletId = PortletIdCodec.decodePortletName(portletId);

		if (portletId.equals(rootPortletId)) {
			portlet = companyPortletsMap.get(portletId);
		}
		else {
			portlet = companyPortletsMap.get(rootPortletId);
		}

		if (portlet == null) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional(enabled = false)
	public void initEAR(
		ServletContext servletContext, String[] xmls,
		PluginPackage pluginPackage) {

		// Clear pools every time initEAR is called. See LEP-5452.

		portletLocalService.clearPortletsMap();

		_portletApps.clear();
		_portletsMap.clear();

		try {
			PortletApp portletApp = getPortletApp(StringPool.BLANK);

			portletApp.setServletContext(servletContext);

			Set<String> servletURLPatterns = readWebXML(xmls[4]);

			_readWebXML(xmls[4], portletApp.getServletContextName());

			Map<String, Portlet> portletsMap = readPortletXML(
				StringPool.BLANK, servletContext, xmls[0], servletURLPatterns,
				pluginPackage);

			portletsMap.putAll(
				readPortletXML(
					StringPool.BLANK, servletContext, xmls[1],
					servletURLPatterns, pluginPackage));

			for (Map.Entry<String, Portlet> entry : portletsMap.entrySet()) {
				_portletsMap.put(entry.getKey(), entry.getValue());
			}

			Map<String, String> portletIdsByStrutsPath =
				new ConcurrentHashMap<>();

			Configuration configuration = _getConfiguration(portletApp);

			Set<String> liferayPortletIds = readLiferayPortletXML(
				StringPool.BLANK, servletContext, xmls[2], portletsMap,
				portletIdsByStrutsPath, configuration);

			liferayPortletIds.addAll(
				readLiferayPortletXML(
					StringPool.BLANK, servletContext, xmls[3], portletsMap,
					portletIdsByStrutsPath, configuration));

			_portletIdsByStrutsPath = portletIdsByStrutsPath;

			// Check for missing entries in liferay-portlet.xml

			for (String portletId : portletsMap.keySet()) {
				if (_log.isWarnEnabled() &&
					!liferayPortletIds.contains(portletId)) {

					_log.warn(
						StringBundler.concat(
							"Portlet with the name ", portletId,
							" is described in portlet.xml but does not have a ",
							"matching entry in liferay-portlet.xml"));
				}
			}

			// Check for missing entries in portlet.xml

			for (String portletId : liferayPortletIds) {
				if (_log.isWarnEnabled() &&
					!portletsMap.containsKey(portletId)) {

					_log.warn(
						StringBundler.concat(
							"Portlet with the name ", portletId,
							" is described in liferay-portlet.xml but does ",
							"not have a matching entry in portlet.xml"));
				}
			}

			// Remove portlets that should not be included

			Set<Map.Entry<String, Portlet>> entrySet = _portletsMap.entrySet();

			Iterator<Map.Entry<String, Portlet>> iterator = entrySet.iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, Portlet> entry = iterator.next();

				Portlet portletModel = entry.getValue();

				if (!Objects.equals(
						portletModel.getPortletId(),
						PortletKeys.SERVER_ADMIN) &&
					!portletModel.isInclude()) {

					String portletId = PortletProviderUtil.getPortletId(
						PortalMyAccountApplicationType.MyAccount.CLASS_NAME,
						PortletProvider.Action.VIEW);

					if (!Objects.equals(
							portletModel.getPortletId(), portletId)) {

						iterator.remove();
					}
				}
			}

			// Sprite images

			if (PropsValues.SPRITE_ENABLED) {
				setSpriteImages(servletContext, portletApp, "/html/icons/");
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	@Override
	@Transactional(enabled = false)
	public List<Portlet> initWAR(
		String servletContextName, ServletContext servletContext, String[] xmls,
		PluginPackage pluginPackage) {

		Map<String, Portlet> portletsMap = null;

		Set<String> liferayPortletIds = null;

		PortletApp portletApp = getPortletApp(servletContextName);

		try {
			Set<String> servletURLPatterns = readWebXML(xmls[3]);

			_readWebXML(xmls[3], servletContextName);

			portletsMap = readPortletXML(
				servletContextName, servletContext, xmls[0], servletURLPatterns,
				pluginPackage);

			portletsMap.putAll(
				readPortletXML(
					servletContextName, servletContext, xmls[1],
					servletURLPatterns, pluginPackage));

			Map<String, String> portletIdsByStrutsPath =
				_portletIdsByStrutsPath;

			if (portletIdsByStrutsPath == null) {
				portletIdsByStrutsPath = new HashMap<>();
			}
			else {
				portletIdsByStrutsPath = new HashMap<>(portletIdsByStrutsPath);
			}

			liferayPortletIds = readLiferayPortletXML(
				servletContextName, servletContext, xmls[2], portletsMap,
				portletIdsByStrutsPath, _getConfiguration(portletApp));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		// Check for missing entries in liferay-portlet.xml

		for (String portletId : portletsMap.keySet()) {
			if (_log.isWarnEnabled() &&
				!liferayPortletIds.contains(portletId)) {

				_log.warn(
					StringBundler.concat(
						"Portlet with the name ", portletId,
						" is described in portlet.xml but does not have a ",
						"matching entry in liferay-portlet.xml"));
			}
		}

		// Check for missing entries in portlet.xml

		for (String portletId : liferayPortletIds) {
			if (_log.isWarnEnabled() && !portletsMap.containsKey(portletId)) {
				_log.warn(
					StringBundler.concat(
						"Portlet with the name ", portletId,
						" is described in liferay-portlet.xml but does not ",
						"have a matching entry in portlet.xml"));
			}
		}

		PortletBagFactory portletBagFactory = new PortletBagFactory();

		portletBagFactory.setClassLoader(
			_getServletContextClassLoader(servletContextName));
		portletBagFactory.setServletContext(servletContext);
		portletBagFactory.setWARFile(true);

		// Return the new portlets

		try {
			for (Map.Entry<String, Portlet> entry : portletsMap.entrySet()) {
				Portlet portlet = _portletsMap.remove(entry.getKey());

				if (portlet != null) {
					PortletInstanceFactoryUtil.clear(portlet);

					PortletConfigFactoryUtil.destroy(portlet);
					PortletContextFactoryUtil.destroy(portlet);
				}

				portlet = entry.getValue();

				portletBagFactory.create(portlet, true);

				_portletsMap.put(entry.getKey(), portlet);
			}

			// Sprite images

			if (PropsValues.SPRITE_ENABLED) {
				setSpriteImages(servletContext, portletApp, "/icons/");
			}

			return ListUtil.fromMapValues(portletsMap);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			// Clean up portlets added prior to error

			for (Map.Entry<String, Portlet> entry : portletsMap.entrySet()) {
				Portlet portlet = _portletsMap.remove(entry.getKey());

				if (portlet != null) {
					PortletInstanceFactoryUtil.clear(portlet);

					PortletConfigFactoryUtil.destroy(portlet);
					PortletContextFactoryUtil.destroy(portlet);
				}
			}

			return Collections.emptyList();
		}
		finally {
			clearCache();
		}
	}

	@Override
	public Map<String, Portlet> loadGetPortletsMap(long companyId) {
		Map<String, Portlet> portletsMap = new ConcurrentHashMap<>();

		for (Portlet portlet : _portletsMap.values()) {
			portlet = (Portlet)portlet.clone();

			portlet.setCompanyId(companyId);

			portletsMap.put(portlet.getPortletId(), portlet);
		}

		List<Portlet> portlets = portletPersistence.findByCompanyId(companyId);

		for (Portlet portlet : portlets) {
			Portlet portletModel = portletsMap.get(portlet.getPortletId());

			// Portlet may be null if it exists in the database but its portlet
			// WAR is not yet loaded

			if (portletModel != null) {
				portletModel.setPluginPackage(portlet.getPluginPackage());
				portletModel.setDefaultPluginSetting(
					portlet.getDefaultPluginSetting());
				portletModel.setRoles(portlet.getRoles());
				portletModel.setActive(portlet.isActive());
			}
		}

		return portletsMap;
	}

	@Clusterable
	@Override
	@Transactional(enabled = false)
	public void removeCompanyPortletsPool(long companyId) {
		_portletsMaps.remove(companyId);
	}

	@Override
	public Portlet updatePortlet(
		long companyId, String portletId, String roles, boolean active) {

		portletId = PortalUtil.getJsSafePortletId(portletId);

		Portlet portlet = portletPersistence.fetchByC_P(companyId, portletId);

		if (portlet == null) {
			long id = counterLocalService.increment();

			portlet = portletPersistence.create(id);

			portlet.setCompanyId(companyId);
			portlet.setPortletId(portletId);
		}

		portlet.setRoles(roles);
		portlet.setActive(active);

		portlet = portletPersistence.update(portlet);

		portletLocalService.removeCompanyPortletsPool(companyId);

		return portlet;
	}

	@Override
	@Transactional(enabled = false)
	public void visitPortlets(long companyId, Consumer<Portlet> consumer) {
		Map<String, Portlet> portletsMap = getPortletsMap(companyId);

		for (Map.Entry<String, Portlet> entry : portletsMap.entrySet()) {
			consumer.accept(entry.getValue());
		}
	}

	protected String getPortletId(String securityPath) {
		Map<String, String> portletIdsByStrutsPath = _portletIdsByStrutsPath;

		if (portletIdsByStrutsPath == null) {
			portletIdsByStrutsPath = new ConcurrentHashMap<>();

			for (Portlet portlet : _portletsMap.values()) {
				String strutsPath = portlet.getStrutsPath();

				String oldPortletId = portletIdsByStrutsPath.put(
					strutsPath, portlet.getPortletId());

				if ((oldPortletId != null) && _log.isWarnEnabled()) {
					_log.warn("Duplicate struts path " + strutsPath);
				}
			}

			_portletIdsByStrutsPath = portletIdsByStrutsPath;
		}

		String portletId = portletIdsByStrutsPath.get(securityPath);

		if (Validator.isNull(portletId)) {
			for (Map.Entry<String, String> entry :
					portletIdsByStrutsPath.entrySet()) {

				String strutsPath = entry.getKey();

				if (securityPath.startsWith(
						strutsPath.concat(StringPool.SLASH))) {

					portletId = entry.getValue();

					break;
				}
			}
		}

		if (Validator.isNull(portletId)) {
			_log.error(
				StringBundler.concat(
					"Struts path ", securityPath,
					" is not mapped to a portlet in liferay-portlet.xml"));
		}

		return portletId;
	}

	protected List<Portlet> getPortletsByPortletName(
		String portletName, String servletContextName,
		Map<String, Portlet> portletsMap) {

		int pos = portletName.indexOf(CharPool.STAR);

		if (pos == -1) {
			String portletId = portletName;

			if (Validator.isNotNull(servletContextName)) {
				portletId =
					portletId + PortletConstants.WAR_SEPARATOR +
						servletContextName;
			}

			portletId = PortalUtil.getJsSafePortletId(portletId);

			Portlet portlet = portletsMap.get(portletId);

			if (portlet == null) {
				return Collections.emptyList();
			}

			return Collections.singletonList(portlet);
		}

		return getPortletsByServletContextName(
			servletContextName, portletName.substring(0, pos), portletsMap);
	}

	protected List<Portlet> getPortletsByServletContextName(
		String servletContextName, String portletNamePrefix,
		Map<String, Portlet> portletsMap) {

		List<Portlet> portlets = new ArrayList<>();

		String servletContextNameSuffix = null;

		if (Validator.isNotNull(servletContextName)) {
			servletContextNameSuffix = PortalUtil.getJsSafePortletId(
				PortletConstants.WAR_SEPARATOR.concat(servletContextName));
		}

		for (Map.Entry<String, Portlet> entry : portletsMap.entrySet()) {
			String portletId = entry.getKey();

			if (!portletId.startsWith(portletNamePrefix)) {
				continue;
			}

			if (servletContextNameSuffix == null) {
				if (!portletId.contains(PortletConstants.WAR_SEPARATOR)) {
					portlets.add(entry.getValue());
				}
			}
			else if (portletId.endsWith(servletContextNameSuffix)) {
				portlets.add(entry.getValue());
			}
		}

		return portlets;
	}

	protected Map<String, Portlet> getPortletsMap(long companyId) {
		Map<String, Portlet> portletsMap = _portletsMaps.get(companyId);

		if (portletsMap == null) {
			portletsMap = portletLocalService.loadGetPortletsMap(companyId);

			_portletsMaps.put(companyId, portletsMap);
		}

		return portletsMap;
	}

	protected void initPortletAddToPagePermissions(Portlet portlet)
		throws PortalException {

		if (portlet.isSystem()) {
			return;
		}

		String[] roleNames = portlet.getRolesArray();

		Portlet existingPortlet = portletPersistence.fetchByC_P(
			portlet.getCompanyId(), portlet.getPortletId());

		if (existingPortlet != null) {
			roleNames = existingPortlet.getRolesArray();
		}

		if (roleNames.length == 0) {
			return;
		}

		List<String> actionIds = ResourceActionsUtil.getPortletResourceActions(
			portlet.getRootPortletId());

		if (actionIds.contains(ActionKeys.ADD_TO_PAGE)) {
			List<String> guestUnsupportedActionIds =
				ResourceActionsUtil.getPortletResourceGuestUnsupportedActions(
					portlet.getRootPortletId());

			boolean skipGuestRole = guestUnsupportedActionIds.contains(
				ActionKeys.ADD_TO_PAGE);

			for (String roleName : roleNames) {
				if (skipGuestRole && roleName.equals(RoleConstants.GUEST)) {
					continue;
				}

				Role role = roleLocalService.fetchRole(
					portlet.getCompanyId(), roleName);

				if (role == null) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Portlet ", portlet.getPortletName(),
								" requires role with company ID ",
								portlet.getCompanyId(), " and name ", roleName,
								". Create the role and redeploy the portlet."));
					}

					continue;
				}

				resourcePermissionLocalService.addResourcePermission(
					portlet.getCompanyId(), portlet.getRootPortletId(),
					ResourceConstants.SCOPE_COMPANY,
					String.valueOf(portlet.getCompanyId()), role.getRoleId(),
					ActionKeys.ADD_TO_PAGE);
			}
		}

		updatePortlet(
			portlet.getCompanyId(), portlet.getPortletId(), StringPool.BLANK);
	}

	protected void readLiferayDisplay(
		String servletContextName, Element element,
		PortletCategory portletCategory, Set<String> portletIds) {

		for (Element categoryElement : element.elements("category")) {
			String name = categoryElement.attributeValue("name");

			PortletCategory curPortletCategory = new PortletCategory(name);

			portletCategory.addCategory(curPortletCategory);

			Set<String> curPortletIds = curPortletCategory.getPortletIds();

			for (Element portletElement : categoryElement.elements("portlet")) {
				String portletId = portletElement.attributeValue("id");

				if (Validator.isNotNull(servletContextName)) {
					portletId =
						portletId + PortletConstants.WAR_SEPARATOR +
							servletContextName;
				}

				portletId = PortalUtil.getJsSafePortletId(portletId);

				portletIds.add(portletId);
				curPortletIds.add(portletId);
			}

			readLiferayDisplay(
				servletContextName, categoryElement, curPortletCategory,
				portletIds);
		}
	}

	protected PortletCategory readLiferayDisplayXML(String xml)
		throws Exception {

		return readLiferayDisplayXML(null, xml);
	}

	protected PortletCategory readLiferayDisplayXML(
			String servletContextName, String xml)
		throws Exception {

		PortletCategory portletCategory = new PortletCategory();

		if (xml == null) {
			Class<?> clazz = getClass();

			xml = ContentUtil.get(
				clazz.getClassLoader(),
				"com/liferay/portal/deploy/dependencies/liferay-display.xml");
		}

		Document document = UnsecureSAXReaderUtil.read(xml, true);

		Element rootElement = document.getRootElement();

		Set<String> portletIds = new HashSet<>();

		readLiferayDisplay(
			servletContextName, rootElement, portletCategory, portletIds);

		// Portlets that do not belong to any categories should default to the
		// Undefined category

		Set<String> undefinedPortletIds = new HashSet<>();

		for (Portlet portlet : _portletsMap.values()) {
			String portletId = portlet.getPortletId();

			PortletApp portletApp = portlet.getPortletApp();

			if ((servletContextName != null) && portletApp.isWARFile() &&
				portletId.endsWith(
					PortletConstants.WAR_SEPARATOR +
						PortalUtil.getJsSafePortletId(servletContextName)) &&
				!portletIds.contains(portletId)) {

				undefinedPortletIds.add(portletId);
			}
			else if ((servletContextName == null) && !portletApp.isWARFile() &&
					 !portletId.contains(PortletConstants.WAR_SEPARATOR) &&
					 !portletIds.contains(portletId)) {

				undefinedPortletIds.add(portletId);
			}
		}

		if (!undefinedPortletIds.isEmpty()) {
			PortletCategory undefinedCategory = new PortletCategory(
				"category.undefined");

			portletCategory.addCategory(undefinedCategory);

			Set<String> undefinedCategoryPortletIds =
				undefinedCategory.getPortletIds();

			undefinedCategoryPortletIds.addAll(undefinedPortletIds);
		}

		return portletCategory;
	}

	protected void readLiferayPortletXML(
		String servletContextName, ServletContext servletContext,
		Set<String> liferayPortletIds, Map<String, String> roleMappers,
		Element portletElement, Map<String, Portlet> portletsMap,
		Map<String, String> portletIdsByStrutsPath,
		Configuration configuration) {

		String portletId = portletElement.elementText("portlet-name");

		if (Validator.isNotNull(servletContextName)) {
			portletId = StringBundler.concat(
				portletId, PortletConstants.WAR_SEPARATOR, servletContextName);
		}

		portletId = PortalUtil.getJsSafePortletId(portletId);

		if (_log.isDebugEnabled()) {
			_log.debug("Reading portlet extension " + portletId);
		}

		liferayPortletIds.add(portletId);

		Portlet portletModel = portletsMap.get(portletId);

		if (portletModel == null) {
			return;
		}

		portletModel.setIcon(
			GetterUtil.getString(
				portletElement.elementText("icon"), portletModel.getIcon()));
		portletModel.setVirtualPath(
			GetterUtil.getString(
				portletElement.elementText("virtual-path"),
				portletModel.getVirtualPath()));
		portletModel.setStrutsPath(
			GetterUtil.getString(
				portletElement.elementText("struts-path"),
				portletModel.getStrutsPath()));

		String strutsPath = portletModel.getStrutsPath();

		if (Validator.isNotNull(strutsPath)) {
			String oldPortletId = portletIdsByStrutsPath.put(
				strutsPath, portletId);

			if ((oldPortletId != null) && !oldPortletId.equals(portletId) &&
				_log.isWarnEnabled()) {

				_log.warn("Duplicate struts path " + strutsPath);
			}
		}

		portletModel.setParentStrutsPath(
			GetterUtil.getString(
				portletElement.elementText("parent-struts-path"),
				portletModel.getParentStrutsPath()));

		if (Validator.isNotNull(
				portletElement.elementText("configuration-path"))) {

			_log.error(
				"The configuration-path element is no longer supported. Use " +
					"configuration-action-class instead.");
		}

		portletModel.setConfigurationActionClass(
			GetterUtil.getString(
				portletElement.elementText("configuration-action-class"),
				portletModel.getConfigurationActionClass()));

		List<String> indexerClasses = new ArrayList<>();

		for (Element indexerClassElement :
				portletElement.elements("indexer-class")) {

			indexerClasses.add(indexerClassElement.getText());
		}

		portletModel.setIndexerClasses(indexerClasses);

		portletModel.setOpenSearchClass(
			GetterUtil.getString(
				portletElement.elementText("open-search-class"),
				portletModel.getOpenSearchClass()));

		for (Element schedulerEntryElement :
				portletElement.elements("scheduler-entry")) {

			String description = GetterUtil.getString(
				schedulerEntryElement.elementText("scheduler-description"));

			String eventListenerClass = GetterUtil.getString(
				schedulerEntryElement.elementText(
					"scheduler-event-listener-class"));

			Trigger trigger = null;

			Element triggerElement = schedulerEntryElement.element("trigger");

			Element cronElement = triggerElement.element("cron");
			Element simpleElement = triggerElement.element("simple");

			if (cronElement != null) {
				Element propertyKeyElement = cronElement.element(
					"property-key");

				String cronException = null;

				if (propertyKeyElement != null) {
					cronException = configuration.get(
						propertyKeyElement.getTextTrim());
				}
				else {
					cronException = cronElement.elementText(
						"cron-trigger-value");
				}

				trigger = TriggerFactoryUtil.createTrigger(
					eventListenerClass, eventListenerClass, cronException);
			}
			else if (simpleElement != null) {
				Element propertyKeyElement = simpleElement.element(
					"property-key");

				String intervalString = null;

				if (propertyKeyElement != null) {
					intervalString = configuration.get(
						propertyKeyElement.getTextTrim());
				}
				else {
					Element simpleTriggerValueElement = simpleElement.element(
						"simple-trigger-value");

					intervalString = simpleTriggerValueElement.getTextTrim();
				}

				String timeUnitString = StringUtil.toUpperCase(
					GetterUtil.getString(
						simpleElement.elementText("time-unit"),
						TimeUnit.SECOND.getValue()));

				trigger = TriggerFactoryUtil.createTrigger(
					eventListenerClass, eventListenerClass,
					GetterUtil.getIntegerStrict(intervalString),
					TimeUnit.valueOf(timeUnitString));
			}

			SchedulerEntryImpl schedulerEntryImpl = new SchedulerEntryImpl(
				eventListenerClass, trigger, description);

			portletModel.addSchedulerEntry(schedulerEntryImpl);
		}

		portletModel.setPortletURLClass(
			GetterUtil.getString(
				portletElement.elementText("portlet-url-class"),
				portletModel.getPortletURLClass()));
		portletModel.setFriendlyURLMapperClass(
			GetterUtil.getString(
				portletElement.elementText("friendly-url-mapper-class"),
				portletModel.getFriendlyURLMapperClass()));
		portletModel.setFriendlyURLMapping(
			GetterUtil.getString(
				portletElement.elementText("friendly-url-mapping"),
				portletModel.getFriendlyURLMapping()));
		portletModel.setFriendlyURLRoutes(
			GetterUtil.getString(
				portletElement.elementText("friendly-url-routes"),
				portletModel.getFriendlyURLRoutes()));
		portletModel.setURLEncoderClass(
			GetterUtil.getString(
				portletElement.elementText("url-encoder-class"),
				portletModel.getURLEncoderClass()));
		portletModel.setPortletDataHandlerClass(
			GetterUtil.getString(
				portletElement.elementText("portlet-data-handler-class"),
				portletModel.getPortletDataHandlerClass()));

		List<String> stagedModelDataHandlerClasses = new ArrayList<>();

		for (Element stagedModelDataHandlerClassElement :
				portletElement.elements("staged-model-data-handler-class")) {

			stagedModelDataHandlerClasses.add(
				stagedModelDataHandlerClassElement.getText());
		}

		portletModel.setStagedModelDataHandlerClasses(
			stagedModelDataHandlerClasses);

		portletModel.setTemplateHandlerClass(
			GetterUtil.getString(
				portletElement.elementText("template-handler"),
				portletModel.getTemplateHandlerClass()));
		portletModel.setPortletLayoutListenerClass(
			GetterUtil.getString(
				portletElement.elementText("portlet-layout-listener-class"),
				portletModel.getPortletLayoutListenerClass()));
		portletModel.setPollerProcessorClass(
			GetterUtil.getString(
				portletElement.elementText("poller-processor-class"),
				portletModel.getPollerProcessorClass()));
		portletModel.setPopMessageListenerClass(
			GetterUtil.getString(
				portletElement.elementText("pop-message-listener-class"),
				portletModel.getPopMessageListenerClass()));

		List<String> socialActivityInterpreterClasses = new ArrayList<>();

		for (Element socialActivityInterpreterClassElement :
				portletElement.elements("social-activity-interpreter-class")) {

			socialActivityInterpreterClasses.add(
				socialActivityInterpreterClassElement.getText());
		}

		portletModel.setSocialActivityInterpreterClasses(
			socialActivityInterpreterClasses);

		portletModel.setSocialRequestInterpreterClass(
			GetterUtil.getString(
				portletElement.elementText("social-request-interpreter-class"),
				portletModel.getSocialRequestInterpreterClass()));
		portletModel.setUserNotificationDefinitions(
			GetterUtil.getString(
				portletElement.elementText("user-notification-definitions"),
				portletModel.getUserNotificationDefinitions()));

		List<String> userNotificationHandlerClasses = new ArrayList<>();

		for (Element userNotificationHandlerClassElement :
				portletElement.elements("user-notification-handler-class")) {

			userNotificationHandlerClasses.add(
				userNotificationHandlerClassElement.getText());
		}

		portletModel.setUserNotificationHandlerClasses(
			userNotificationHandlerClasses);

		portletModel.setWebDAVStorageToken(
			GetterUtil.getString(
				portletElement.elementText("webdav-storage-token"),
				portletModel.getWebDAVStorageToken()));
		portletModel.setWebDAVStorageClass(
			GetterUtil.getString(
				portletElement.elementText("webdav-storage-class"),
				portletModel.getWebDAVStorageClass()));
		portletModel.setXmlRpcMethodClass(
			GetterUtil.getString(
				portletElement.elementText("xml-rpc-method-class"),
				portletModel.getXmlRpcMethodClass()));

		Set<ApplicationType> applicationTypes = new HashSet<>();

		for (Element applicationTypeElement :
				portletElement.elements("application-type")) {

			try {
				applicationTypes.add(
					ApplicationType.parse(applicationTypeElement.getText()));
			}
			catch (IllegalArgumentException illegalArgumentException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unknown application type " +
							applicationTypeElement.getText());
				}
			}
		}

		if (applicationTypes.isEmpty()) {
			applicationTypes.add(ApplicationType.WIDGET);
		}

		portletModel.setApplicationTypes(applicationTypes);

		portletModel.setControlPanelEntryClass(
			GetterUtil.getString(
				portletElement.elementText("control-panel-entry-class"),
				portletModel.getControlPanelEntryClass()));

		List<String> assetRendererFactoryClasses = new ArrayList<>();

		for (Element assetRendererFactoryClassElement :
				portletElement.elements("asset-renderer-factory")) {

			assetRendererFactoryClasses.add(
				assetRendererFactoryClassElement.getText());
		}

		portletModel.setAssetRendererFactoryClasses(
			assetRendererFactoryClasses);

		List<String> atomCollectionAdapterClasses = new ArrayList<>();

		for (Element atomCollectionAdapterClassElement :
				portletElement.elements("atom-collection-adapter")) {

			atomCollectionAdapterClasses.add(
				atomCollectionAdapterClassElement.getText());
		}

		portletModel.setAtomCollectionAdapterClasses(
			atomCollectionAdapterClasses);

		List<String> customAttributesDisplayClasses = new ArrayList<>();

		for (Element customAttributesDisplayClassElement :
				portletElement.elements("custom-attributes-display")) {

			customAttributesDisplayClasses.add(
				customAttributesDisplayClassElement.getText());
		}

		portletModel.setCustomAttributesDisplayClasses(
			customAttributesDisplayClasses);

		portletModel.setPermissionPropagatorClass(
			GetterUtil.getString(
				portletElement.elementText("permission-propagator"),
				portletModel.getPermissionPropagatorClass()));

		List<String> trashHandlerClasses = new ArrayList<>();

		for (Element trashHandlerClassElement :
				portletElement.elements("trash-handler")) {

			trashHandlerClasses.add(trashHandlerClassElement.getText());
		}

		portletModel.setTrashHandlerClasses(trashHandlerClasses);

		List<String> workflowHandlerClasses = new ArrayList<>();

		for (Element workflowHandlerClassElement :
				portletElement.elements("workflow-handler")) {

			workflowHandlerClasses.add(workflowHandlerClassElement.getText());
		}

		portletModel.setWorkflowHandlerClasses(workflowHandlerClasses);

		portletModel.setPreferencesCompanyWide(
			GetterUtil.getBoolean(
				portletElement.elementText("preferences-company-wide"),
				portletModel.isPreferencesCompanyWide()));
		portletModel.setPreferencesUniquePerLayout(
			GetterUtil.getBoolean(
				portletElement.elementText("preferences-unique-per-layout"),
				portletModel.isPreferencesUniquePerLayout()));
		portletModel.setPreferencesOwnedByGroup(
			GetterUtil.getBoolean(
				portletElement.elementText("preferences-owned-by-group"),
				portletModel.isPreferencesOwnedByGroup()));
		portletModel.setUseDefaultTemplate(
			GetterUtil.getBoolean(
				portletElement.elementText("use-default-template"),
				portletModel.isUseDefaultTemplate()));
		portletModel.setShowPortletAccessDenied(
			GetterUtil.getBoolean(
				portletElement.elementText("show-portlet-access-denied"),
				portletModel.isShowPortletAccessDenied()));
		portletModel.setShowPortletInactive(
			GetterUtil.getBoolean(
				portletElement.elementText("show-portlet-inactive"),
				portletModel.isShowPortletInactive()));
		portletModel.setActionURLRedirect(
			GetterUtil.getBoolean(
				portletElement.elementText("action-url-redirect"),
				portletModel.isActionURLRedirect()));
		portletModel.setRestoreCurrentView(
			GetterUtil.getBoolean(
				portletElement.elementText("restore-current-view"),
				portletModel.isRestoreCurrentView()));
		portletModel.setMaximizeEdit(
			GetterUtil.getBoolean(
				portletElement.elementText("maximize-edit"),
				portletModel.isMaximizeEdit()));
		portletModel.setMaximizeHelp(
			GetterUtil.getBoolean(
				portletElement.elementText("maximize-help"),
				portletModel.isMaximizeHelp()));
		portletModel.setPopUpPrint(
			GetterUtil.getBoolean(
				portletElement.elementText("pop-up-print"),
				portletModel.isPopUpPrint()));
		portletModel.setLayoutCacheable(
			GetterUtil.getBoolean(
				portletElement.elementText("layout-cacheable"),
				portletModel.isLayoutCacheable()));
		portletModel.setInstanceable(
			GetterUtil.getBoolean(
				portletElement.elementText("instanceable"),
				portletModel.isInstanceable()));
		portletModel.setScopeable(
			GetterUtil.getBoolean(
				portletElement.elementText("scopeable"),
				portletModel.isScopeable()));
		portletModel.setSinglePageApplication(
			GetterUtil.getBoolean(
				portletElement.elementText("single-page-application"),
				portletModel.isSinglePageApplication()));
		portletModel.setUserPrincipalStrategy(
			GetterUtil.getString(
				portletElement.elementText("user-principal-strategy"),
				portletModel.getUserPrincipalStrategy()));
		portletModel.setPrivateRequestAttributes(
			GetterUtil.getBoolean(
				portletElement.elementText("private-request-attributes"),
				portletModel.isPrivateRequestAttributes()));
		portletModel.setPrivateSessionAttributes(
			GetterUtil.getBoolean(
				portletElement.elementText("private-session-attributes"),
				portletModel.isPrivateSessionAttributes()));

		Element autopropagatedParametersElement = portletElement.element(
			"autopropagated-parameters");

		Set<String> autopropagatedParameters = new HashSet<>();

		if (autopropagatedParametersElement != null) {
			String[] autopropagatedParametersArray = StringUtil.split(
				autopropagatedParametersElement.getText());

			for (String autopropagatedParameter :
					autopropagatedParametersArray) {

				autopropagatedParameters.add(autopropagatedParameter);
			}
		}

		portletModel.setAutopropagatedParameters(autopropagatedParameters);

		boolean defaultRequiresNamespacedParameters = GetterUtil.getBoolean(
			servletContext.getInitParameter(
				"com.liferay.portlet.requires-namespaced-parameters"),
			portletModel.isRequiresNamespacedParameters());

		portletModel.setRequiresNamespacedParameters(
			GetterUtil.getBoolean(
				portletElement.elementText("requires-namespaced-parameters"),
				defaultRequiresNamespacedParameters));

		portletModel.setActionTimeout(
			GetterUtil.getInteger(
				portletElement.elementText("action-timeout"),
				portletModel.getActionTimeout()));
		portletModel.setRenderTimeout(
			GetterUtil.getInteger(
				portletElement.elementText("render-timeout"),
				portletModel.getRenderTimeout()));
		portletModel.setRenderWeight(
			GetterUtil.getInteger(
				portletElement.elementText("render-weight"),
				portletModel.getRenderWeight()));
		portletModel.setAjaxable(
			GetterUtil.getBoolean(
				portletElement.elementText("ajaxable"),
				portletModel.isAjaxable()));

		List<String> headerPortalCssList = new ArrayList<>();

		for (Element headerPortalCssElement :
				portletElement.elements("header-portal-css")) {

			headerPortalCssList.add(headerPortalCssElement.getText());
		}

		portletModel.setHeaderPortalCss(headerPortalCssList);

		List<String> headerPortletCssList = new ArrayList<>();

		for (Element headerPortletCssElement :
				portletElement.elements("header-portlet-css")) {

			headerPortletCssList.add(headerPortletCssElement.getText());
		}

		portletModel.setHeaderPortletCss(headerPortletCssList);

		List<String> headerPortalJavaScriptList = new ArrayList<>();

		for (Element headerPortalJavaScriptElement :
				portletElement.elements("header-portal-javascript")) {

			headerPortalJavaScriptList.add(
				headerPortalJavaScriptElement.getText());
		}

		portletModel.setHeaderPortalJavaScript(headerPortalJavaScriptList);

		List<String> headerPortletJavaScriptList = new ArrayList<>();

		for (Element headerPortletJavaScriptElement :
				portletElement.elements("header-portlet-javascript")) {

			headerPortletJavaScriptList.add(
				headerPortletJavaScriptElement.getText());
		}

		portletModel.setHeaderPortletJavaScript(headerPortletJavaScriptList);

		List<String> headerRequestAttributePrefixes = new ArrayList<>();

		for (Element headerRequestAttributePrefixElement :
				portletElement.elements("header-request-attribute-prefix")) {

			headerRequestAttributePrefixes.add(
				headerRequestAttributePrefixElement.getText());
		}

		portletModel.setHeaderRequestAttributePrefixes(
			headerRequestAttributePrefixes);
		portletModel.setHeaderTimeout(
			GetterUtil.getInteger(
				portletElement.elementText("header-timeout"),
				portletModel.getHeaderTimeout()));

		List<String> footerPortalCssList = new ArrayList<>();

		for (Element footerPortalCssElement :
				portletElement.elements("footer-portal-css")) {

			footerPortalCssList.add(footerPortalCssElement.getText());
		}

		portletModel.setFooterPortalCss(footerPortalCssList);

		List<String> footerPortletCssList = new ArrayList<>();

		for (Element footerPortletCssElement :
				portletElement.elements("footer-portlet-css")) {

			footerPortletCssList.add(footerPortletCssElement.getText());
		}

		portletModel.setFooterPortletCss(footerPortletCssList);

		List<String> footerPortalJavaScriptList = new ArrayList<>();

		for (Element footerPortalJavaScriptElement :
				portletElement.elements("footer-portal-javascript")) {

			footerPortalJavaScriptList.add(
				footerPortalJavaScriptElement.getText());
		}

		portletModel.setFooterPortalJavaScript(footerPortalJavaScriptList);

		List<String> footerPortletJavaScriptList = new ArrayList<>();

		for (Element footerPortletJavaScriptElement :
				portletElement.elements("footer-portlet-javascript")) {

			footerPortletJavaScriptList.add(
				footerPortletJavaScriptElement.getText());
		}

		portletModel.setFooterPortletJavaScript(footerPortletJavaScriptList);

		portletModel.setPartialActionServeResource(
			GetterUtil.getBoolean(
				portletElement.elementText("partial-action-serve-resource"),
				portletModel.isPartialActionServeResource()));
		portletModel.setPortletDependencyCssEnabled(
			GetterUtil.getBoolean(
				portletElement.elementText("portlet-dependency-css-enabled"),
				portletModel.isPortletDependencyCssEnabled()));
		portletModel.setPortletDependencyJavaScriptEnabled(
			GetterUtil.getBoolean(
				portletElement.elementText(
					"portlet-dependency-javascript-enabled"),
				portletModel.isPortletDependencyJavaScriptEnabled()));
		portletModel.setCssClassWrapper(
			GetterUtil.getString(
				portletElement.elementText("css-class-wrapper"),
				portletModel.getCssClassWrapper()));
		portletModel.setAddDefaultResource(
			GetterUtil.getBoolean(
				portletElement.elementText("add-default-resource"),
				portletModel.isAddDefaultResource()));
		portletModel.setSystem(
			GetterUtil.getBoolean(
				portletElement.elementText("system"), portletModel.isSystem()));
		portletModel.setActive(
			GetterUtil.getBoolean(
				portletElement.elementText("active"), portletModel.isActive()));
		portletModel.setInclude(
			GetterUtil.getBoolean(
				portletElement.elementText("include"),
				portletModel.isInclude()));

		if (Validator.isNull(servletContextName)) {
			portletModel.setReady(true);
		}

		if (!portletModel.isAjaxable() &&
			(portletModel.getRenderWeight() < 1)) {

			portletModel.setRenderWeight(1);
		}

		Map<String, String> roleMappersMap = portletModel.getRoleMappers();

		roleMappersMap.putAll(roleMappers);

		portletModel.linkRoles();
	}

	protected Set<String> readLiferayPortletXML(
			String servletContextName, ServletContext servletContext,
			String xml, Map<String, Portlet> portletsMap,
			Map<String, String> portletIdsByStrutsPath,
			Configuration configuration)
		throws Exception {

		Set<String> liferayPortletIds = new HashSet<>();

		if (xml == null) {
			return liferayPortletIds;
		}

		Document document = UnsecureSAXReaderUtil.read(xml, true);

		Element rootElement = document.getRootElement();

		PortletApp portletApp = getPortletApp(servletContextName);

		Map<String, String> roleMappers = new HashMap<>();

		for (Element roleMapperElement : rootElement.elements("role-mapper")) {
			String roleName = roleMapperElement.elementText("role-name");
			String roleLink = roleMapperElement.elementText("role-link");

			roleMappers.put(roleName, roleLink);
		}

		Map<String, String> customUserAttributes =
			portletApp.getCustomUserAttributes();

		for (Element customUserAttributeElement :
				rootElement.elements("custom-user-attribute")) {

			String customClass = customUserAttributeElement.elementText(
				"custom-class");

			for (Element nameElement :
					customUserAttributeElement.elements("name")) {

				String name = nameElement.getText();

				customUserAttributes.put(name, customClass);
			}
		}

		for (Element portletElement : rootElement.elements("portlet")) {
			readLiferayPortletXML(
				servletContextName, servletContext, liferayPortletIds,
				roleMappers, portletElement, portletsMap,
				portletIdsByStrutsPath, configuration);
		}

		return liferayPortletIds;
	}

	protected void readPortletXML(
			String servletContextName, PluginPackage pluginPackage,
			PortletApp portletApp, Element portletElement,
			Map<String, Portlet> portletsMap,
			Set<String> validCustomPortletModes)
		throws PortletIdException {

		String portletName = portletElement.elementText("portlet-name");

		String portletId = portletName;

		if (Validator.isNotNull(servletContextName)) {
			portletId = StringBundler.concat(
				portletId, PortletConstants.WAR_SEPARATOR, servletContextName);
		}

		portletId = PortalUtil.getJsSafePortletId(portletId);

		if (portletId.length() >
				PortletIdCodec.PORTLET_INSTANCE_KEY_MAX_LENGTH) {

			// LPS-32878

			throw new PortletIdException(
				StringBundler.concat(
					"Portlet ID ", portletId, " has more than ",
					PortletIdCodec.PORTLET_INSTANCE_KEY_MAX_LENGTH,
					" characters"));
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Reading portlet " + portletId);
		}

		Portlet portletModel = _portletsMap.get(portletId);

		if (portletModel == null) {
			portletModel = new PortletImpl(CompanyConstants.SYSTEM, portletId);
		}

		portletModel.setPluginPackage(pluginPackage);
		portletModel.setPortletApp(portletApp);

		portletModel.setPortletName(portletName);
		portletModel.setDisplayName(
			GetterUtil.getString(
				portletElement.elementText("display-name"),
				portletModel.getDisplayName()));
		portletModel.setPortletClass(
			GetterUtil.getString(portletElement.elementText("portlet-class")));

		Map<String, String> initParams = new HashMap<>();

		for (Element initParamElement : portletElement.elements("init-param")) {
			initParams.put(
				initParamElement.elementText("name"),
				initParamElement.elementText("value"));
		}

		portletModel.setInitParams(initParams);

		Element expirationCacheElement = portletElement.element(
			"expiration-cache");

		if (expirationCacheElement != null) {
			portletModel.setExpCache(
				GetterUtil.getInteger(expirationCacheElement.getText()));
		}

		Map<String, Set<String>> portletModes = new HashMap<>();
		Map<String, Set<String>> windowStates = new HashMap<>();

		for (Element supportsElement : portletElement.elements("supports")) {
			String mimeType = supportsElement.elementText("mime-type");

			Set<String> mimeTypePortletModes = new HashSet<>();

			mimeTypePortletModes.add(
				StringUtil.toLowerCase(PortletMode.VIEW.toString()));

			for (Element portletModeElement :
					supportsElement.elements("portlet-mode")) {

				String portletMode = StringUtil.toLowerCase(
					portletModeElement.getTextTrim());

				if (_isCustomPortletMode(portletMode) &&
					!validCustomPortletModes.contains(portletMode)) {

					continue;
				}

				mimeTypePortletModes.add(portletMode);
			}

			portletModes.put(mimeType, mimeTypePortletModes);

			Set<String> mimeTypeWindowStates = new HashSet<>();

			mimeTypeWindowStates.add(
				StringUtil.toLowerCase(WindowState.NORMAL.toString()));

			List<Element> windowStateElements = supportsElement.elements(
				"window-state");

			if (windowStateElements.isEmpty()) {
				mimeTypeWindowStates.add(
					StringUtil.toLowerCase(WindowState.MAXIMIZED.toString()));
				mimeTypeWindowStates.add(
					StringUtil.toLowerCase(WindowState.MINIMIZED.toString()));
				mimeTypeWindowStates.add(
					StringUtil.toLowerCase(
						LiferayWindowState.EXCLUSIVE.toString()));
				mimeTypeWindowStates.add(
					StringUtil.toLowerCase(
						LiferayWindowState.POP_UP.toString()));
			}

			for (Element windowStateElement : windowStateElements) {
				mimeTypeWindowStates.add(
					StringUtil.toLowerCase(windowStateElement.getTextTrim()));
			}

			windowStates.put(mimeType, mimeTypeWindowStates);
		}

		portletModel.setPortletModes(portletModes);
		portletModel.setWindowStates(windowStates);

		Set<String> supportedLocales = new HashSet<>();

		//supportedLocales.add(
		//	LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		for (Element supportedLocaleElement :
				portletElement.elements("supported-locale")) {

			String supportedLocale = supportedLocaleElement.getText();

			supportedLocales.add(supportedLocale);
		}

		portletModel.setSupportedLocales(supportedLocales);

		portletModel.setResourceBundle(
			portletElement.elementText("resource-bundle"));

		Element portletInfoElement = portletElement.element("portlet-info");

		String portletInfoTitle = null;
		String portletInfoShortTitle = null;
		String portletInfoKeyWords = null;
		String portletInfoDescription = null;

		if (portletInfoElement != null) {
			portletInfoTitle = portletInfoElement.elementText("title");
			portletInfoShortTitle = portletInfoElement.elementText(
				"short-title");
			portletInfoKeyWords = portletInfoElement.elementText("keywords");
		}

		PortletInfo portletInfo = new PortletInfo(
			portletInfoTitle, portletInfoShortTitle, portletInfoKeyWords,
			portletInfoDescription);

		portletModel.setPortletInfo(portletInfo);

		Element portletPreferencesElement = portletElement.element(
			"portlet-preferences");

		String defaultPreferences = null;
		String preferencesValidator = null;

		if (portletPreferencesElement != null) {
			Element preferencesValidatorElement =
				portletPreferencesElement.element("preferences-validator");

			if (preferencesValidatorElement != null) {
				preferencesValidator = preferencesValidatorElement.getText();

				portletPreferencesElement.remove(preferencesValidatorElement);
			}

			defaultPreferences = portletPreferencesElement.asXML();
		}

		portletModel.setDefaultPreferences(defaultPreferences);
		portletModel.setPreferencesValidator(preferencesValidator);

		if (!portletApp.isWARFile() &&
			Validator.isNotNull(preferencesValidator) &&
			PropsValues.PREFERENCE_VALIDATE_ON_STARTUP) {

			try {
				PreferencesValidator preferencesValidatorObj =
					PortalUtil.getPreferencesValidator(portletModel);

				preferencesValidatorObj.validate(
					PortletPreferencesFactoryUtil.fromDefaultXML(
						defaultPreferences));
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Portlet with the name " + portletId +
							" does not have valid default preferences");
				}
			}
		}

		Set<String> unlinkedRoles = new HashSet<>();

		for (Element roleElement :
				portletElement.elements("security-role-ref")) {

			unlinkedRoles.add(roleElement.elementText("role-name"));
		}

		portletModel.setUnlinkedRoles(unlinkedRoles);

		Set<QName> processingEvents = new HashSet<>();

		for (Element supportedProcessingEventElement :
				portletElement.elements("supported-processing-event")) {

			Element qNameElement = supportedProcessingEventElement.element(
				"qname");
			Element nameElement = supportedProcessingEventElement.element(
				"name");

			QName qName = PortletQNameUtil.getQName(
				qNameElement, nameElement, portletApp.getDefaultNamespace());

			processingEvents.add(qName);

			Set<EventDefinition> eventDefinitions =
				portletApp.getEventDefinitions();

			for (EventDefinition eventDefinition : eventDefinitions) {
				Set<QName> qNames = eventDefinition.getQNames();

				if (qNames.contains(qName)) {
					processingEvents.addAll(qNames);
				}
			}
		}

		portletModel.setProcessingEvents(processingEvents);

		Set<QName> publishingEvents = new HashSet<>();

		for (Element supportedPublishingEventElement :
				portletElement.elements("supported-publishing-event")) {

			Element qNameElement = supportedPublishingEventElement.element(
				"qname");
			Element nameElement = supportedPublishingEventElement.element(
				"name");

			QName qName = PortletQNameUtil.getQName(
				qNameElement, nameElement, portletApp.getDefaultNamespace());

			publishingEvents.add(qName);
		}

		portletModel.setPublishingEvents(publishingEvents);

		Set<PublicRenderParameter> publicRenderParameters = new HashSet<>();

		for (Element supportedPublicRenderParameter :
				portletElement.elements("supported-public-render-parameter")) {

			String identifier = supportedPublicRenderParameter.getTextTrim();

			PublicRenderParameter publicRenderParameter =
				portletApp.getPublicRenderParameter(identifier);

			if (publicRenderParameter == null) {
				_log.error(
					"Supported public render parameter references unknown " +
						"identifier " + identifier);

				continue;
			}

			publicRenderParameters.add(publicRenderParameter);
		}

		portletModel.setPublicRenderParameters(publicRenderParameters);

		Map<String, String[]> containerRuntimeOptions =
			portletApp.getContainerRuntimeOptions();

		String containerRuntimeOptionPrefix =
			LiferayPortletConfig.class.getName();

		containerRuntimeOptionPrefix = containerRuntimeOptionPrefix.concat(
			portletName);

		for (Element containerRuntimeOptionElement :
				portletElement.elements("container-runtime-option")) {

			String name = GetterUtil.getString(
				containerRuntimeOptionElement.elementText("name"));

			List<String> values = new ArrayList<>();

			for (Element valueElement :
					containerRuntimeOptionElement.elements("value")) {

				values.add(valueElement.getTextTrim());
			}

			containerRuntimeOptions.put(
				containerRuntimeOptionPrefix.concat(name),
				values.toArray(new String[0]));
		}

		for (Element dependencyElement :
				portletElement.elements("dependency")) {

			String name = GetterUtil.getString(
				dependencyElement.elementText("name"));
			String scope = GetterUtil.getString(
				dependencyElement.elementText("scope"));
			String version = GetterUtil.getString(
				dependencyElement.elementText("version"));

			portletModel.addPortletDependency(
				PortletDependencyFactoryUtil.createPortletDependency(
					name, scope, version));
		}

		portletModel.setAsyncSupported(
			GetterUtil.getBoolean(
				portletElement.elementText("async-supported")));

		Element multipartConfigElement = portletElement.element(
			"multipart-config");

		if (multipartConfigElement != null) {
			portletModel.setMultipartFileSizeThreshold(
				GetterUtil.getInteger(
					multipartConfigElement.elementText("file-size-threshold")));
			portletModel.setMultipartLocation(
				multipartConfigElement.elementText("location"));
			portletModel.setMultipartMaxFileSize(
				GetterUtil.getLong(
					multipartConfigElement.elementText("max-file-size"), -1L));
			portletModel.setMultipartMaxRequestSize(
				GetterUtil.getLong(
					multipartConfigElement.elementText("max-request-size"),
					-1L));
		}

		portletsMap.put(portletId, portletModel);
	}

	protected Map<String, Portlet> readPortletXML(
			String servletContextName, ServletContext servletContext,
			String xml, Set<String> servletURLPatterns,
			PluginPackage pluginPackage)
		throws Exception {

		Map<String, Portlet> portletsMap = new HashMap<>();

		if (xml == null) {
			return portletsMap;
		}

		Document document = UnsecureSAXReaderUtil.read(
			xml, PropsValues.PORTLET_XML_VALIDATE);

		Element rootElement = document.getRootElement();

		PortletApp portletApp = getPortletApp(servletContextName);

		portletApp.addServletURLPatterns(servletURLPatterns);
		portletApp.setServletContext(servletContext);
		portletApp.setSpecMajorVersion(2);
		portletApp.setSpecMinorVersion(0);

		Attribute versionAttribute = rootElement.attribute("version");

		if (versionAttribute != null) {
			String[] versionAttributeParts = StringUtil.split(
				versionAttribute.getValue(), CharPool.PERIOD);

			if (versionAttributeParts.length > 0) {
				portletApp.setSpecMajorVersion(
					GetterUtil.getInteger(versionAttributeParts[0], 2));

				if (versionAttributeParts.length > 1) {
					portletApp.setSpecMinorVersion(
						GetterUtil.getInteger(versionAttributeParts[1]));
				}
			}
		}

		Set<String> userAttributes = portletApp.getUserAttributes();

		for (Element userAttributeElement :
				rootElement.elements("user-attribute")) {

			String name = userAttributeElement.elementText("name");

			userAttributes.add(name);
		}

		String defaultNamespace = rootElement.elementText("default-namespace");

		if (Validator.isNotNull(defaultNamespace)) {
			portletApp.setDefaultNamespace(defaultNamespace);
		}

		for (Element eventDefinitionElement :
				rootElement.elements("event-definition")) {

			Element qNameElement = eventDefinitionElement.element("qname");
			Element nameElement = eventDefinitionElement.element("name");
			String valueType = eventDefinitionElement.elementText("value-type");

			QName qName = PortletQNameUtil.getQName(
				qNameElement, nameElement, portletApp.getDefaultNamespace());

			EventDefinition eventDefinition = new EventDefinitionImpl(
				qName, valueType, portletApp);

			List<Element> aliases = eventDefinitionElement.elements("alias");

			for (Element alias : aliases) {
				qName = PortletQNameUtil.getQName(
					alias, null, portletApp.getDefaultNamespace());

				eventDefinition.addAliasQName(qName);
			}

			portletApp.addEventDefinition(eventDefinition);
		}

		for (Element publicRenderParameterElement :
				rootElement.elements("public-render-parameter")) {

			String identifier = publicRenderParameterElement.elementText(
				"identifier");

			Element qNameElement = publicRenderParameterElement.element(
				"qname");
			Element nameElement = publicRenderParameterElement.element("name");

			QName qName = PortletQNameUtil.getQName(
				qNameElement, nameElement, portletApp.getDefaultNamespace());

			PublicRenderParameter publicRenderParameter =
				new PublicRenderParameterImpl(identifier, qName, portletApp);

			portletApp.addPublicRenderParameter(publicRenderParameter);
		}

		for (Element containerRuntimeOptionElement :
				rootElement.elements("container-runtime-option")) {

			String name = GetterUtil.getString(
				containerRuntimeOptionElement.elementText("name"));

			List<String> values = new ArrayList<>();

			for (Element valueElement :
					containerRuntimeOptionElement.elements("value")) {

				values.add(valueElement.getTextTrim());
			}

			Map<String, String[]> containerRuntimeOptions =
				portletApp.getContainerRuntimeOptions();

			containerRuntimeOptions.put(name, values.toArray(new String[0]));

			if (name.equals(
					LiferayPortletConfig.RUNTIME_OPTION_PORTAL_CONTEXT) &&
				!values.isEmpty() && GetterUtil.getBoolean(values.get(0))) {

				portletApp.setWARFile(false);
			}
		}

		Set<String> validCustomPortletModes = new HashSet<>();

		for (Element customPortletModeElement :
				rootElement.elements("custom-portlet-mode")) {

			String portletMode = StringUtil.toLowerCase(
				customPortletModeElement.elementTextTrim("portlet-mode"));

			boolean portalManaged = Boolean.valueOf(
				customPortletModeElement.elementText("portal-managed"));

			if (_isCustomPortletMode(portletMode) && portalManaged) {
				continue;
			}

			validCustomPortletModes.add(portletMode);
		}

		for (Element portletElement : rootElement.elements("portlet")) {
			readPortletXML(
				servletContextName, pluginPackage, portletApp, portletElement,
				portletsMap, validCustomPortletModes);
		}

		List<PortletFilter> portletFilters = new ArrayList<>();

		for (Element filterElement : rootElement.elements("filter")) {
			String filterName = filterElement.elementText("filter-name");
			String filterClass = filterElement.elementText("filter-class");
			int ordinal = GetterUtil.getInteger(
				filterElement.elementText("ordinal"));

			Set<String> lifecycles = new LinkedHashSet<>();

			for (Element lifecycleElement :
					filterElement.elements("lifecycle")) {

				lifecycles.add(lifecycleElement.getText());
			}

			Map<String, String> initParams = new HashMap<>();

			for (Element initParamElement :
					filterElement.elements("init-param")) {

				initParams.put(
					initParamElement.elementText("name"),
					initParamElement.elementText("value"));
			}

			portletFilters.add(
				new PortletFilterImpl(
					filterName, filterClass, ordinal, lifecycles, initParams,
					portletApp));
		}

		Collections.sort(
			portletFilters, Comparator.comparingInt(PortletFilter::getOrdinal));

		for (PortletFilter portletFilter : portletFilters) {
			portletApp.addPortletFilter(portletFilter);
		}

		for (Element filterMappingElement :
				rootElement.elements("filter-mapping")) {

			String filterName = filterMappingElement.elementText("filter-name");

			PortletFilter portletFilter = portletApp.getPortletFilter(
				filterName);

			if (portletFilter == null) {
				_log.error(
					"Filter mapping references unknown filter name " +
						filterName);

				continue;
			}

			for (Element portletNameElement :
					filterMappingElement.elements("portlet-name")) {

				String portletName = portletNameElement.getTextTrim();

				List<Portlet> portletModels = getPortletsByPortletName(
					portletName, servletContextName, portletsMap);

				if (portletModels.isEmpty()) {
					_log.error(
						StringBundler.concat(
							"Filter mapping with filter name ", filterName,
							" references unknown portlet name ", portletName));
				}

				for (Portlet portletModel : portletModels) {
					Map<String, PortletFilter> portletFiltersMap =
						portletModel.getPortletFilters();

					portletFiltersMap.put(filterName, portletFilter);
				}
			}
		}

		List<PortletURLListener> portletURLListeners = new ArrayList<>();

		for (Element listenerElement : rootElement.elements("listener")) {
			String listenerClass = listenerElement.elementText(
				"listener-class");
			int ordinal = GetterUtil.getInteger(
				listenerElement.elementText("ordinal"));

			portletURLListeners.add(
				new PortletURLListenerImpl(listenerClass, ordinal, portletApp));
		}

		Collections.sort(
			portletURLListeners,
			Comparator.comparingInt(PortletURLListener::getOrdinal));

		for (PortletURLListener portletURLListener : portletURLListeners) {
			portletApp.addPortletURLListener(portletURLListener);
		}

		return portletsMap;
	}

	protected Set<String> readWebXML(String xml) throws Exception {
		Set<String> servletURLPatterns = new LinkedHashSet<>();

		if (xml == null) {
			return servletURLPatterns;
		}

		Document document = UnsecureSAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		for (Element servletMappingElement :
				rootElement.elements("servlet-mapping")) {

			String urlPattern = servletMappingElement.elementText(
				"url-pattern");

			servletURLPatterns.add(urlPattern);
		}

		return servletURLPatterns;
	}

	protected void setSpriteImages(
			ServletContext servletContext, PortletApp portletApp,
			String resourcePath)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			resourcePath);

		if ((resourcePaths == null) || resourcePaths.isEmpty()) {
			return;
		}

		List<URL> imageURLs = new ArrayList<>(resourcePaths.size());

		for (String curResourcePath : resourcePaths) {
			if (curResourcePath.endsWith(StringPool.SLASH)) {
				setSpriteImages(servletContext, portletApp, curResourcePath);
			}
			else if (curResourcePath.endsWith(".png")) {
				URL imageURL = servletContext.getResource(curResourcePath);

				if (imageURL != null) {
					imageURLs.add(imageURL);
				}
				else {
					_log.error(
						"Resource URL for " + curResourcePath + " is null");
				}
			}
		}

		String spriteRootDirName = PropsValues.SPRITE_ROOT_DIR;
		String spriteFileName = resourcePath.concat(
			PropsValues.SPRITE_FILE_NAME);
		String spritePropertiesFileName = resourcePath.concat(
			PropsValues.SPRITE_PROPERTIES_FILE_NAME);
		String rootPath = ServletContextUtil.getRootPath(servletContext);

		Properties spriteProperties = SpriteProcessorUtil.generate(
			servletContext, imageURLs, spriteRootDirName, spriteFileName,
			spritePropertiesFileName, rootPath, 16, 16, 10240);

		if (spriteProperties == null) {
			return;
		}

		spriteFileName = StringBundler.concat(
			servletContext.getContextPath(), SpriteProcessor.PATH,
			spriteFileName);

		portletApp.setSpriteImages(spriteFileName, spriteProperties);
	}

	protected Portlet updatePortlet(
		long companyId, String portletId, String roles) {

		Portlet existingPortlet = portletPersistence.fetchByC_P(
			companyId, portletId);

		boolean active = true;

		if (existingPortlet != null) {
			active = existingPortlet.isActive();
		}

		return updatePortlet(companyId, portletId, roles, active);
	}

	private Configuration _getConfiguration(PortletApp portletApp) {
		if (!portletApp.isWARFile()) {
			return ConfigurationFactoryImpl.CONFIGURATION_PORTAL;
		}

		String propertyFileName = "portlet";

		ServletContext servletContext = portletApp.getServletContext();

		ClassLoader classLoader = servletContext.getClassLoader();

		if (classLoader.getResource(propertyFileName + ".properties") == null) {
			return null;
		}

		return ConfigurationFactoryUtil.getConfiguration(
			classLoader, propertyFileName);
	}

	private ClassLoader _getServletContextClassLoader(
		String servletContextName) {

		ClassLoader classLoader = ServletContextClassLoaderPool.getClassLoader(
			servletContextName);

		if (classLoader == null) {
			throw new IllegalStateException(
				"Unable to find class loader for servlet context " +
					servletContextName);
		}

		return classLoader;
	}

	private boolean _isCustomPortletMode(String portletModeName) {
		return PortalUtil.isCustomPortletMode(new PortletMode(portletModeName));
	}

	private void _readWebXML(String xml, String servletContextName)
		throws Exception {

		Map<String, String> localeEncodings = new HashMap<>();

		Document document = UnsecureSAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		for (Element localeEncodingMappingListElement :
				rootElement.elements("locale-encoding-mapping-list")) {

			for (Element localeEncodingMappingElement :
					localeEncodingMappingListElement.elements(
						"locale-encoding-mapping")) {

				String locale = GetterUtil.getString(
					localeEncodingMappingElement.elementText("locale"));
				String encoding = GetterUtil.getString(
					localeEncodingMappingElement.elementText("encoding"));

				localeEncodings.put(locale, encoding);
			}
		}

		ExtraPortletAppConfig extraPortletAppConfig = new ExtraPortletAppConfig(
			localeEncodings);

		ExtraPortletAppConfigRegistry.registerExtraPortletAppConfig(
			servletContextName, extraPortletAppConfig);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletLocalServiceImpl.class);

	private static final Map<String, PortletApp> _portletApps =
		new ConcurrentHashMap<>();
	private static volatile Map<String, String> _portletIdsByStrutsPath;
	private static final Map<String, Portlet> _portletsMap =
		new ConcurrentHashMap<>();
	private static final Map<Long, Map<String, Portlet>> _portletsMaps =
		new ConcurrentHashMap<>();

	private final AtomicReference<String[]> _friendlyURLMapperRootPortletIds =
		new AtomicReference<>(new String[0]);
	private ServiceTracker<FriendlyURLMapper, String[]> _serviceTracker;

	private class FriendlyURLMapperServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<FriendlyURLMapper, String[]> {

		@Override
		public String[] addingService(
			ServiceReference<FriendlyURLMapper> serviceReference) {

			Object propertyValue = serviceReference.getProperty(
				"javax.portlet.name");

			if (propertyValue == null) {
				return null;
			}

			if (propertyValue instanceof String) {
				String portletId = (String)propertyValue;

				String rootPortletId = PortletIdCodec.decodePortletName(
					portletId);

				rootPortletId = JS.getSafeName(rootPortletId);

				while (true) {
					String[] friendlyURLMapperRootPortletIds =
						_friendlyURLMapperRootPortletIds.get();

					String[] newFriendlyURLMapperRootPortletIds =
						ArrayUtil.append(
							friendlyURLMapperRootPortletIds, rootPortletId);

					Arrays.sort(newFriendlyURLMapperRootPortletIds);

					if (_friendlyURLMapperRootPortletIds.compareAndSet(
							friendlyURLMapperRootPortletIds,
							newFriendlyURLMapperRootPortletIds)) {

						break;
					}
				}

				return new String[] {rootPortletId};
			}

			if (propertyValue instanceof String[]) {
				String[] portletIds = (String[])propertyValue;

				String[] rootPortletIds = new String[portletIds.length];

				for (int i = 0; i < portletIds.length; i++) {
					String rootPortletId = PortletIdCodec.decodePortletName(
						portletIds[i]);

					rootPortletIds[i] = JS.getSafeName(rootPortletId);
				}

				while (true) {
					String[] friendlyURLMapperRootPortletIds =
						_friendlyURLMapperRootPortletIds.get();

					String[] newFriendlyURLMapperRootPortletIds =
						ArrayUtil.append(
							friendlyURLMapperRootPortletIds, rootPortletIds);

					Arrays.sort(newFriendlyURLMapperRootPortletIds);

					if (_friendlyURLMapperRootPortletIds.compareAndSet(
							friendlyURLMapperRootPortletIds,
							newFriendlyURLMapperRootPortletIds)) {

						break;
					}
				}

				return rootPortletIds;
			}

			return null;
		}

		@Override
		public void modifiedService(
			ServiceReference<FriendlyURLMapper> serviceReference,
			String[] rootPortletIds) {

			removedService(serviceReference, rootPortletIds);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<FriendlyURLMapper> serviceReference,
			String[] rootPortletIds) {

			while (true) {
				String[] friendlyURLMapperRootPortletIds =
					_friendlyURLMapperRootPortletIds.get();

				String[] newFriendlyURLMapperRootPortletIds =
					friendlyURLMapperRootPortletIds;

				for (String rootPortletId : rootPortletIds) {
					newFriendlyURLMapperRootPortletIds = ArrayUtil.remove(
						newFriendlyURLMapperRootPortletIds, rootPortletId);
				}

				if (_friendlyURLMapperRootPortletIds.compareAndSet(
						friendlyURLMapperRootPortletIds,
						newFriendlyURLMapperRootPortletIds)) {

					break;
				}
			}
		}

	}

}