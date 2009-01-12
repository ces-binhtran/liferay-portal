/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.events;

import com.liferay.portal.LayoutPermissionException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.lar.PortletDataHandlerKeys;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ColorSchemeImpl;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.model.impl.LayoutTypePortletImpl;
import com.liferay.portal.model.impl.ThemeImpl;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactory;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.service.permission.LayoutPermissionUtil;
import com.liferay.portal.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.theme.ThemeDisplayFactory;
import com.liferay.portal.util.CookieKeys;
import com.liferay.portal.util.FriendlyURLNormalizer;
import com.liferay.portal.util.LayoutClone;
import com.liferay.portal.util.LayoutCloneFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLImpl;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;

/**
 * <a href="ServicePreAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Felix Ventero
 *
 */
public class ServicePreAction extends Action {

	public ServicePreAction() {
		initImportLARFiles();
	}

	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		try {
			servicePre(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Running takes " + stopWatch.getTime() + " ms");
		}
	}

	protected void addDefaultLayoutsByLAR(
			long userId, long groupId, boolean privateLayout, File larFile)
		throws PortalException, SystemException {

		Map<String, String[]> parameterMap = new HashMap<String, String[]>();

		parameterMap.put(
			PortletDataHandlerKeys.PERMISSIONS,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA_CONTROL_DEFAULT,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.USER_PERMISSIONS,
			new String[] {Boolean.FALSE.toString()});

		LayoutLocalServiceUtil.importLayouts(
			userId, groupId, privateLayout, parameterMap, larFile);
	}

	protected void addDefaultUserPrivateLayoutByProperties(
			long userId, long groupId)
		throws PortalException, SystemException {

		String friendlyURL = getFriendlyURL(
			PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL);

		Layout layout = LayoutLocalServiceUtil.addLayout(
			userId, groupId, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_NAME, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(
			0, PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_TEMPLATE_ID, false);

		for (int i = 0; i < 10; i++) {
			String columnId = "column-" + i;
			String portletIds = PropsUtil.get(
				PropsKeys.DEFAULT_USER_PRIVATE_LAYOUT_COLUMN + i);

			String[] portletIdsArray = StringUtil.split(portletIds);

			layoutTypePortlet.addPortletIds(
				0, portletIdsArray, columnId, false);
		}

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		boolean updateLayoutSet = false;

		LayoutSet layoutSet = layout.getLayoutSet();

		if (Validator.isNotNull(
				PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_REGULAR_THEME_ID)) {

			layoutSet.setThemeId(
				PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_REGULAR_THEME_ID);

			updateLayoutSet = true;
		}

		if (Validator.isNotNull(
				PropsValues.
					DEFAULT_USER_PRIVATE_LAYOUT_REGULAR_COLOR_SCHEME_ID)) {

			layoutSet.setColorSchemeId(
				PropsValues.
					DEFAULT_USER_PRIVATE_LAYOUT_REGULAR_COLOR_SCHEME_ID);

			updateLayoutSet = true;
		}

		if (Validator.isNotNull(
				PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_WAP_THEME_ID)) {

			layoutSet.setWapThemeId(
				PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_WAP_THEME_ID);

			updateLayoutSet = true;
		}

		if (Validator.isNotNull(
				PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_WAP_COLOR_SCHEME_ID)) {

			layoutSet.setWapColorSchemeId(
				PropsValues.DEFAULT_USER_PRIVATE_LAYOUT_WAP_COLOR_SCHEME_ID);

			updateLayoutSet = true;
		}

		if (updateLayoutSet) {
			LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);
		}
	}

	protected void addDefaultUserPrivateLayouts(User user)
		throws PortalException, SystemException {

		Group userGroup = user.getGroup();

		if (privateLARFile != null) {
			addDefaultLayoutsByLAR(
				user.getUserId(), userGroup.getGroupId(), true, privateLARFile);
		}
		else {
			addDefaultUserPrivateLayoutByProperties(
				user.getUserId(), userGroup.getGroupId());
		}
	}

	protected void addDefaultUserPublicLayoutByProperties(
			long userId, long groupId)
		throws PortalException, SystemException {

		String friendlyURL = getFriendlyURL(
			PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_FRIENDLY_URL);

		Layout layout = LayoutLocalServiceUtil.addLayout(
			userId, groupId, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_NAME, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(
			0, PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_TEMPLATE_ID, false);

		for (int i = 0; i < 10; i++) {
			String columnId = "column-" + i;
			String portletIds = PropsUtil.get(
				PropsKeys.DEFAULT_USER_PUBLIC_LAYOUT_COLUMN + i);

			String[] portletIdsArray = StringUtil.split(portletIds);

			layoutTypePortlet.addPortletIds(
				0, portletIdsArray, columnId, false);
		}

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		boolean updateLayoutSet = false;

		LayoutSet layoutSet = layout.getLayoutSet();

		if (Validator.isNotNull(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_REGULAR_THEME_ID)) {

			layoutSet.setThemeId(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_REGULAR_THEME_ID);

			updateLayoutSet = true;
		}

		if (Validator.isNotNull(
				PropsValues.
					DEFAULT_USER_PUBLIC_LAYOUT_REGULAR_COLOR_SCHEME_ID)) {

			layoutSet.setColorSchemeId(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_REGULAR_COLOR_SCHEME_ID);

			updateLayoutSet = true;
		}

		if (Validator.isNotNull(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_WAP_THEME_ID)) {

			layoutSet.setWapThemeId(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_WAP_THEME_ID);

			updateLayoutSet = true;
		}

		if (Validator.isNotNull(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_WAP_COLOR_SCHEME_ID)) {

			layoutSet.setWapColorSchemeId(
				PropsValues.DEFAULT_USER_PUBLIC_LAYOUT_WAP_COLOR_SCHEME_ID);

			updateLayoutSet = true;
		}

		if (updateLayoutSet) {
			LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);
		}
	}

	protected void addDefaultUserPublicLayouts(User user)
		throws PortalException, SystemException {

		Group userGroup = user.getGroup();

		if (publicLARFile != null) {
			addDefaultLayoutsByLAR(
				user.getUserId(), userGroup.getGroupId(), false, publicLARFile);
		}
		else {
			addDefaultUserPublicLayoutByProperties(
				user.getUserId(), userGroup.getGroupId());
		}
	}

	protected void deleteDefaultUserPrivateLayouts(User user)
		throws PortalException, SystemException {

		Group userGroup = user.getGroup();

		LayoutLocalServiceUtil.deleteLayouts(userGroup.getGroupId(), true);
	}

	protected void deleteDefaultUserPublicLayouts(User user)
		throws PortalException, SystemException {

		Group userGroup = user.getGroup();

		LayoutLocalServiceUtil.deleteLayouts(userGroup.getGroupId(), false);
	}

	protected Object[] getDefaultLayout(
			HttpServletRequest request, User user, boolean signedIn)
		throws PortalException, SystemException {

		// Check the virtual host

		LayoutSet layoutSet = (LayoutSet)request.getAttribute(
			WebKeys.VIRTUAL_HOST_LAYOUT_SET);

		if (layoutSet != null) {
			List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
				layoutSet.getGroupId(), layoutSet.isPrivateLayout(),
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

			if (layouts.size() > 0) {
				Layout layout = layouts.get(0);

				return new Object[] {layout, layouts};
			}
		}

		Layout layout = null;
		List<Layout> layouts = null;

		if (signedIn) {

			// Check the user's personal layouts

			Group userGroup = user.getGroup();

			layouts = LayoutLocalServiceUtil.getLayouts(
				userGroup.getGroupId(), true,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

			if (layouts.size() == 0) {
				layouts = LayoutLocalServiceUtil.getLayouts(
					userGroup.getGroupId(), false,
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
			}

			if (layouts.size() > 0) {
				layout = layouts.get(0);
			}

			// Check the user's communities

			if (layout == null) {
				LinkedHashMap<String, Object> groupParams =
					new LinkedHashMap<String, Object>();

				groupParams.put("usersGroups", new Long(user.getUserId()));

				List<Group> groups = GroupLocalServiceUtil.search(
					user.getCompanyId(), null, null, groupParams,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				for (Group group : groups) {
					layouts = LayoutLocalServiceUtil.getLayouts(
						group.getGroupId(), true,
						LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

					if (layouts.size() == 0) {
						layouts = LayoutLocalServiceUtil.getLayouts(
							group.getGroupId(), false,
							LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
					}

					if (layouts.size() > 0) {
						layout = layouts.get(0);

						break;
					}
				}
			}
		}
		else {

			// Check the guest community

			Group guestGroup = GroupLocalServiceUtil.getGroup(
				user.getCompanyId(), GroupConstants.GUEST);

			layouts = LayoutLocalServiceUtil.getLayouts(
				guestGroup.getGroupId(), false,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

			if (layouts.size() > 0) {
				layout = layouts.get(0);
			}
		}

		return new Object[] {layout, layouts};
	}

	protected String getFriendlyURL(String friendlyURL) {
		friendlyURL = GetterUtil.getString(friendlyURL);

		return FriendlyURLNormalizer.normalize(friendlyURL);
	}

	protected Object[] getViewableLayouts(
			HttpServletRequest request, User user,
			PermissionChecker permissionChecker, Layout layout,
			List<Layout> layouts)
		throws PortalException, SystemException {

		if ((layouts == null) || (layouts.size() == 0)) {
			return new Object[] {layout, layouts};
		}

		boolean replaceLayout = true;

		if (LayoutPermissionUtil.contains(
				permissionChecker, layout, ActionKeys.VIEW)) {

			replaceLayout = false;
		}

		List<Layout> accessibleLayouts = new ArrayList<Layout>();

		for (int i = 0; i < layouts.size(); i++) {
			Layout curLayout = layouts.get(i);

			if (!curLayout.isHidden() &&
				LayoutPermissionUtil.contains(
					permissionChecker, curLayout, ActionKeys.VIEW)) {

				if ((accessibleLayouts.size() == 0) && replaceLayout) {
					layout = curLayout;
				}

				accessibleLayouts.add(curLayout);
			}
		}

		if (accessibleLayouts.size() == 0) {
			layouts = null;

			SessionErrors.add(
				request, LayoutPermissionException.class.getName());
		}
		else {
			layouts = accessibleLayouts;
		}

		return new Object[] {layout, layouts};
	}

	protected void initImportLARFiles() {
		String privateLARFileName =
			PropsValues.DEFAULT_USER_PRIVATE_LAYOUTS_LAR;

		if (_log.isDebugEnabled()) {
			_log.debug("Reading private LAR file " + privateLARFileName);
		}

		if (Validator.isNotNull(privateLARFileName)) {
			privateLARFile = new File(privateLARFileName);

			if (!privateLARFile.exists()) {
				_log.error(
					"Private LAR file " + privateLARFile + " does not exist");

				privateLARFile = null;
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug("Using private LAR file " + privateLARFileName);
				}
			}
		}

		String publicLARFileName = PropsValues.DEFAULT_USER_PUBLIC_LAYOUTS_LAR;

		if (_log.isDebugEnabled()) {
			_log.debug("Reading public LAR file " + publicLARFileName);
		}

		if (Validator.isNotNull(publicLARFileName)) {
			publicLARFile = new File(publicLARFileName);

			if (!publicLARFile.exists()) {
				_log.error(
					"Public LAR file " + publicLARFile + " does not exist");

				publicLARFile = null;
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug("Using public LAR file " + publicLARFileName);
				}
			}
		}
	}

	/**
	 * @deprecated Use <code>isViewableGroup</code>.
	 */
	protected boolean isViewableCommunity(
			User user, long groupId, boolean privateLayout,
			PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		return isViewableGroup(user, groupId, privateLayout, permissionChecker);
	}

	protected boolean isViewableGroup(
			User user, long groupId, boolean privateLayout,
			PermissionChecker permissionChecker)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		// Inactive communities are not viewable

		if (!group.isActive()) {
			return false;
		}
		else if (group.isStagingGroup()) {
			Group liveGroup = group.getLiveGroup();

			if (!liveGroup.isActive()) {
				return false;
			}
		}

		// User private layouts are only viewable by the user and anyone who can
		// update the user. The user must also be active.

		if (group.isUser()) {
			long groupUserId = group.getClassPK();

			if (groupUserId == user.getUserId()) {
				return true;
			}
			else {
				User groupUser = UserLocalServiceUtil.getUserById(groupUserId);

				if (!groupUser.isActive()) {
					return false;
				}

				if (privateLayout) {
					if (UserPermissionUtil.contains(
							permissionChecker, groupUserId,
							groupUser.getOrganizationIds(),
							ActionKeys.UPDATE)) {

						return true;
					}
					else {
						return false;
					}
				}
			}
		}

		// If the current group is staging, only users with editorial rights
		// can access it

		if (group.isStagingGroup()) {
			if (user.isDefaultUser()) {
				return false;
			}

			if (GroupPermissionUtil.contains(
					permissionChecker, groupId, ActionKeys.APPROVE_PROPOSAL) ||
				GroupPermissionUtil.contains(
					permissionChecker, groupId, ActionKeys.ASSIGN_REVIEWER) ||
				GroupPermissionUtil.contains(
					permissionChecker, groupId, ActionKeys.MANAGE_LAYOUTS) ||
				GroupPermissionUtil.contains(
					permissionChecker, groupId, ActionKeys.MANAGE_STAGING) ||
				GroupPermissionUtil.contains(
					permissionChecker, groupId, ActionKeys.PUBLISH_STAGING)) {

				return true;
			}

			return false;
		}

		// Most public layouts are viewable

		if (!privateLayout) {
			return true;
		}

		// Control panel layouts are only viewable by authenticated users

		if (group.getName().equals(GroupConstants.CONTROL_PANEL)) {
			if (user.isDefaultUser()) {
				return false;
			}
			else {
				return true;
			}
		}

		// Community or organization layouts are only viewable by users who
		// belong to the community or organization, or by users who can update
		// the community or organization

		if (group.isCommunity()) {
			if (GroupLocalServiceUtil.hasUserGroup(user.getUserId(), groupId)) {
				return true;
			}
			else if (GroupPermissionUtil.contains(
						permissionChecker, groupId, ActionKeys.UPDATE)) {

				return true;
			}
		}
		else if (group.isOrganization()) {
			long organizationId = group.getClassPK();

			if (OrganizationLocalServiceUtil.hasUserOrganization(
					user.getUserId(), organizationId)) {

				return true;
			}
			else if (OrganizationPermissionUtil.contains(
						permissionChecker, organizationId, ActionKeys.UPDATE)) {

				return true;
			}

			if (!PropsValues.ORGANIZATIONS_MEMBERSHIP_STRICT) {
				for (Organization organization : user.getOrganizations()) {
					for (Organization ancestorOrganization :
							organization.getAncestors()) {

						if (group.getClassPK() ==
								ancestorOrganization.getOrganizationId()) {

							return true;
						}
					}
				}
			}
		}
		else if (group.isUserGroup()) {
			if (GroupPermissionUtil.contains(
					permissionChecker, groupId, ActionKeys.MANAGE_LAYOUTS)) {

				return true;
			}
		}

		return false;
	}

	protected List<Layout> mergeAdditionalLayouts(
			HttpServletRequest request, User user,
			PermissionChecker permissionChecker, Layout layout,
			List<Layout> layouts)
		throws PortalException, SystemException {

		if ((layout == null) || layout.isPrivateLayout()) {
			return layouts;
		}

		long layoutGroupId = layout.getGroupId();

		Group guestGroup = GroupLocalServiceUtil.getGroup(
			user.getCompanyId(), GroupConstants.GUEST);

		if (layoutGroupId != guestGroup.getGroupId()) {
			Group layoutGroup = GroupLocalServiceUtil.getGroup(layoutGroupId);

			UnicodeProperties props = layoutGroup.getTypeSettingsProperties();

			boolean mergeGuestPublicPages = GetterUtil.getBoolean(
				props.getProperty("mergeGuestPublicPages"));

			if (!mergeGuestPublicPages) {
				return layouts;
			}

			List<Layout> guestLayouts = LayoutLocalServiceUtil.getLayouts(
				guestGroup.getGroupId(), false,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

			Object[] viewableLayouts = getViewableLayouts(
				request, user, permissionChecker, layout, guestLayouts);

			guestLayouts = (List<Layout>)viewableLayouts[1];

			layouts.addAll(0, guestLayouts);
		}
		else {
			HttpSession session = request.getSession();

			Long previousGroupId = (Long)session.getAttribute(
				WebKeys.LIFERAY_SHARED_VISITED_GROUP_ID_PREVIOUS);

			if ((previousGroupId != null) &&
				(previousGroupId.longValue() != layoutGroupId)) {

				Group previousGroup = null;

				try {
					previousGroup = GroupLocalServiceUtil.getGroup(
						previousGroupId.longValue());
				}
				catch (NoSuchGroupException nsge) {
					if (_log.isWarnEnabled()) {
						_log.warn(nsge);
					}

					return layouts;
				}

				UnicodeProperties props =
					previousGroup.getTypeSettingsProperties();

				boolean mergeGuestPublicPages = GetterUtil.getBoolean(
					props.getProperty("mergeGuestPublicPages"));

				if (!mergeGuestPublicPages) {
					return layouts;
				}

				List<Layout> previousLayouts =
					LayoutLocalServiceUtil.getLayouts(
						previousGroupId.longValue(), false,
						LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

				Object[] viewableLayouts = getViewableLayouts(
					request, user, permissionChecker, layout, previousLayouts);

				previousLayouts = (List<Layout>)viewableLayouts[1];

				layouts.addAll(previousLayouts);
			}
		}

		return layouts;
	}

	protected void rememberVisitedGroupIds(
		HttpServletRequest request, long currentGroupId) {

		String requestURI = GetterUtil.getString(request.getRequestURI());

		if (!requestURI.endsWith(_PATH_PORTAL_LAYOUT)) {
			return;
		}

		HttpSession session = request.getSession();

		Long recentGroupId = (Long)session.getAttribute(
			WebKeys.LIFERAY_SHARED_VISITED_GROUP_ID_RECENT);

		Long previousGroupId = (Long)session.getAttribute(
			WebKeys.LIFERAY_SHARED_VISITED_GROUP_ID_PREVIOUS);

		if (recentGroupId == null) {
			recentGroupId = new Long(currentGroupId);

			session.setAttribute(
				WebKeys.LIFERAY_SHARED_VISITED_GROUP_ID_RECENT,
				recentGroupId);
		}
		else if (recentGroupId.longValue() != currentGroupId) {
			previousGroupId = new Long(recentGroupId.longValue());

			recentGroupId = new Long(currentGroupId);

			session.setAttribute(
				WebKeys.LIFERAY_SHARED_VISITED_GROUP_ID_RECENT,
				recentGroupId);

			session.setAttribute(
				WebKeys.LIFERAY_SHARED_VISITED_GROUP_ID_PREVIOUS,
				previousGroupId);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Current group id " + currentGroupId);
			_log.debug("Recent group id " + recentGroupId);
			_log.debug("Previous group id " + previousGroupId);
		}
	}

	protected void servicePre(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();

		// Company

		Company company = PortalUtil.getCompany(request);

		long companyId = company.getCompanyId();

		// CDN host

		String cdnHost = ParamUtil.getString(
			request, "cdn_host", PortalUtil.getCDNHost());

		// Portal URL

		String portalURL = PortalUtil.getPortalURL(request);

		// Paths

		String contextPath = PortalUtil.getPathContext();
		String friendlyURLPrivateGroupPath =
			PortalUtil.getPathFriendlyURLPrivateGroup();
		String friendlyURLPrivateUserPath =
			PortalUtil.getPathFriendlyURLPrivateUser();
		String friendlyURLPublicPath = PortalUtil.getPathFriendlyURLPublic();
		String imagePath = PortalUtil.getPathImage();
		String mainPath = PortalUtil.getPathMain();

		String i18nLanguageId = (String)request.getAttribute(
			WebKeys.I18N_LANGUAGE_ID);

		if (Validator.isNotNull(i18nLanguageId)) {
			if (Validator.isNotNull(contextPath)) {
				mainPath = StringUtil.replaceFirst(
					mainPath, contextPath,
					contextPath + StringPool.SLASH + i18nLanguageId);
			}
			else {
				mainPath = StringPool.SLASH + i18nLanguageId + mainPath;
			}
		}

		// Company logo

		String companyLogo =
			imagePath + "/company_logo?img_id=" + company.getLogoId() + "&t=" +
				ImageServletTokenUtil.getToken(company.getLogoId());

		Image companyLogoImage = ImageLocalServiceUtil.getCompanyLogo(
			company.getLogoId());

		int companyLogoHeight = companyLogoImage.getHeight();
		int companyLogoWidth = companyLogoImage.getWidth();

		String realCompanyLogo = companyLogo;
		int realCompanyLogoHeight = companyLogoHeight;
		int realCompanyLogoWidth = companyLogoWidth;

		// User

		User user = PortalUtil.getUser(request);

		boolean signedIn = false;

		if (user == null) {
			user = company.getDefaultUser();
		}
		else if (!user.isDefaultUser()) {
			signedIn = true;
		}

		User realUser = user;

		Long realUserId = (Long)session.getAttribute(WebKeys.USER_ID);

		if (realUserId != null) {
			if (user.getUserId() != realUserId.longValue()) {
				realUser = UserLocalServiceUtil.getUserById(
					realUserId.longValue());
			}
		}

		String doAsUserId = ParamUtil.getString(request, "doAsUserId");
		String doAsUserLanguageId = ParamUtil.getString(
			request, "doAsUserLanguageId");
		long doAsGroupId = ParamUtil.getLong(request, "doAsGroupId");
		long refererPlid = ParamUtil.getLong(request, "refererPlid");

		// Permission checker

		PermissionChecker permissionChecker = PermissionCheckerFactory.create(
			user, true);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		// Locale

		Locale locale = (Locale)session.getAttribute(Globals.LOCALE_KEY);

		if (Validator.isNotNull(doAsUserLanguageId)) {
			locale = LocaleUtil.fromLanguageId(doAsUserLanguageId);
		}

		if (Validator.isNotNull(i18nLanguageId)) {
			locale = LocaleUtil.fromLanguageId(i18nLanguageId);
		}
		else if (locale == null) {
			if (signedIn) {
				locale = user.getLocale();
			}
			else {

				// User previously set their preferred language

				String languageId = CookieKeys.getCookie(
					request, CookieKeys.GUEST_LANGUAGE_ID);

				if (Validator.isNotNull(languageId)) {
					locale = LocaleUtil.fromLanguageId(languageId);
				}

				// Get locale from the request

				if ((locale == null) && PropsValues.LOCALE_DEFAULT_REQUEST) {
					locale = request.getLocale();
				}

				// Get locale from the default user

				if (locale == null) {
					locale = user.getLocale();
				}

				if (Validator.isNull(locale.getCountry())) {

					// Locales must contain the country code

					locale = LanguageUtil.getLocale(locale.getLanguage());
				}

				if (!LanguageUtil.isAvailableLocale(locale)) {
					locale = user.getLocale();
				}
			}

			session.setAttribute(Globals.LOCALE_KEY, locale);

			LanguageUtil.updateCookie(request, response, locale);
		}

		// Cookie support

		try {

			// LEP-4069

			CookieKeys.validateSupportCookie(request);
		}
		catch (Exception e) {
			CookieKeys.addSupportCookie(request, response);
		}

		// Time zone

		TimeZone timeZone = user.getTimeZone();

		if (timeZone == null) {
			timeZone = company.getTimeZone();
		}

		// Layouts

		if (signedIn) {
			updateUserLayouts(user);
		}

		Layout layout = null;
		List<Layout> layouts = null;

		long plid = ParamUtil.getLong(request, "p_l_id");

		if (plid > 0) {
			layout = LayoutLocalServiceUtil.getLayout(plid);
		}
		else {
			long groupId = ParamUtil.getLong(request, "groupId");
			boolean privateLayout = ParamUtil.getBoolean(
				request, "privateLayout");
			long layoutId = ParamUtil.getLong(request, "layoutId");

			if ((groupId > 0) && layoutId > 0) {
				layout = LayoutLocalServiceUtil.getLayout(
					groupId, privateLayout, layoutId);
			}
		}

		if (layout != null) {
			try {
				if (!signedIn && PropsValues.AUTH_FORWARD_BY_REDIRECT) {
					request.setAttribute(WebKeys.REQUESTED_LAYOUT, layout);
				}

				boolean isViewableCommunity = isViewableGroup(
					user, layout.getGroupId(), layout.isPrivateLayout(),
					permissionChecker);

				if (!isViewableCommunity) {
					StringBuilder sb = new StringBuilder();

					sb.append("User ");
					sb.append(user.getUserId());
					sb.append(" is not allowed to access the ");
					sb.append(layout.isPrivateLayout() ? "private": "public");
					sb.append(" pages of group ");
					sb.append(layout.getGroupId());

					if (_log.isWarnEnabled()) {
						_log.warn(sb.toString());
					}

					throw new PrincipalException(sb.toString());
				}
				else if (isViewableCommunity &&
						!LayoutPermissionUtil.contains(
							permissionChecker, layout, ActionKeys.VIEW)) {

					layout = null;
				}
				else {
					layouts = LayoutLocalServiceUtil.getLayouts(
						layout.getGroupId(), layout.isPrivateLayout(),
						LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

					if (!layout.getGroup().getName().equals(
							GroupConstants.CONTROL_PANEL)) {

						doAsGroupId = 0;
					}
				}
			}
			catch (NoSuchLayoutException nsle) {
			}
		}

		if (layout == null) {
			Object[] defaultLayout = getDefaultLayout(request, user, signedIn);

			layout = (Layout)defaultLayout[0];
			layouts = (List<Layout>)defaultLayout[1];

			request.setAttribute(WebKeys.LAYOUT_DEFAULT, Boolean.TRUE);
		}

		Object[] viewableLayouts = getViewableLayouts(
			request, user, permissionChecker, layout, layouts);

		String layoutSetLogo = null;

		layout = (Layout)viewableLayouts[0];
		layouts = (List<Layout>)viewableLayouts[1];

		LayoutTypePortlet layoutTypePortlet = null;

		layouts = mergeAdditionalLayouts(
			request, user, permissionChecker, layout, layouts);

		if (layout != null) {
			if (company.isCommunityLogo()) {
				LayoutSet layoutSet = layout.getLayoutSet();

				if (layoutSet.isLogo()) {
					long logoId = layoutSet.getLogoId();

					layoutSetLogo =
						imagePath + "/layout_set_logo?img_id=" + logoId +
							"&t=" + ImageServletTokenUtil.getToken(logoId);

					Image layoutSetLogoImage =
						ImageLocalServiceUtil.getCompanyLogo(logoId);

					companyLogo = layoutSetLogo;
					companyLogoHeight = layoutSetLogoImage.getHeight();
					companyLogoWidth = layoutSetLogoImage.getWidth();
				}
			}

			plid = layout.getPlid();

			// Updates to shared layouts are not reflected until the next time
			// the user logs in because group layouts are cached in the session

			layout = (Layout)((LayoutImpl)layout).clone();

			layoutTypePortlet = (LayoutTypePortlet)layout.getLayoutType();

			LayoutClone layoutClone = LayoutCloneFactory.getInstance();

			if (layoutClone != null) {
				String typeSettings = layoutClone.get(request, plid);

				if (typeSettings != null) {
					UnicodeProperties props = new UnicodeProperties(true);

					props.load(typeSettings);

					String stateMax = props.getProperty(
						LayoutTypePortletImpl.STATE_MAX);
					String stateMin = props.getProperty(
						LayoutTypePortletImpl.STATE_MIN);
					String modeAbout = props.getProperty(
						LayoutTypePortletImpl.MODE_ABOUT);
					String modeConfig = props.getProperty(
						LayoutTypePortletImpl.MODE_CONFIG);
					String modeEdit = props.getProperty(
						LayoutTypePortletImpl.MODE_EDIT);
					String modeEditDefaults = props.getProperty(
						LayoutTypePortletImpl.MODE_EDIT_DEFAULTS);
					String modeEditGuest = props.getProperty(
						LayoutTypePortletImpl.MODE_EDIT_GUEST);
					String modeHelp = props.getProperty(
						LayoutTypePortletImpl.MODE_HELP);
					String modePreview = props.getProperty(
						LayoutTypePortletImpl.MODE_PREVIEW);
					String modePrint = props.getProperty(
						LayoutTypePortletImpl.MODE_PRINT);

					layoutTypePortlet.setStateMax(stateMax);
					layoutTypePortlet.setStateMin(stateMin);
					layoutTypePortlet.setModeAbout(modeAbout);
					layoutTypePortlet.setModeConfig(modeConfig);
					layoutTypePortlet.setModeEdit(modeEdit);
					layoutTypePortlet.setModeEditDefaults(modeEditDefaults);
					layoutTypePortlet.setModeEditGuest(modeEditGuest);
					layoutTypePortlet.setModeHelp(modeHelp);
					layoutTypePortlet.setModePreview(modePreview);
					layoutTypePortlet.setModePrint(modePrint);
				}
			}

			request.setAttribute(WebKeys.LAYOUT, layout);
			request.setAttribute(WebKeys.LAYOUTS, layouts);

			if (layout.isPrivateLayout()) {
				permissionChecker.setCheckGuest(false);
			}
		}

		// Scope

		long scopeGroupId = PortalUtil.getScopeGroupId(request);

		rememberVisitedGroupIds(request, scopeGroupId);

		// Theme and color scheme

		Theme theme = null;
		ColorScheme colorScheme = null;

		boolean wapTheme = BrowserSnifferUtil.isWap(request);

		Group group = null;

		if (layout != null) {
			group = layout.getGroup();
		}

		if ((layout != null) &&
			group.getName().equals(GroupConstants.CONTROL_PANEL)) {

			String themeId = PropsValues.CONTROL_PANEL_LAYOUT_REGULAR_THEME_ID;
			String colorSchemeId =
				ColorSchemeImpl.getDefaultRegularColorSchemeId();

			theme = ThemeLocalServiceUtil.getTheme(
				companyId, themeId, wapTheme);
			colorScheme = ThemeLocalServiceUtil.getColorScheme(
				companyId, theme.getThemeId(), colorSchemeId, wapTheme);
		}
		else if (layout != null) {
			if (wapTheme) {
				theme = layout.getWapTheme();
				colorScheme = layout.getWapColorScheme();
			}
			else {
				theme = layout.getTheme();
				colorScheme = layout.getColorScheme();
			}
		}
		else {
			String themeId = null;
			String colorSchemeId = null;

			if (wapTheme) {
				themeId = ThemeImpl.getDefaultWapThemeId();
				colorSchemeId = ColorSchemeImpl.getDefaultWapColorSchemeId();
			}
			else {
				themeId = ThemeImpl.getDefaultRegularThemeId();
				colorSchemeId =
					ColorSchemeImpl.getDefaultRegularColorSchemeId();
			}

			theme = ThemeLocalServiceUtil.getTheme(
				companyId, themeId, wapTheme);
			colorScheme = ThemeLocalServiceUtil.getColorScheme(
				companyId, theme.getThemeId(), colorSchemeId, wapTheme);
		}

		request.setAttribute(WebKeys.THEME, theme);
		request.setAttribute(WebKeys.COLOR_SCHEME, colorScheme);

		boolean themeCssFastLoad = ParamUtil.getBoolean(
			request, "css_fast_load", PropsValues.THEME_CSS_FAST_LOAD);
		boolean themeImagesFastLoad = ParamUtil.getBoolean(
			request, "images_fast_load", PropsValues.THEME_IMAGES_FAST_LOAD);

		boolean themeJsBarebone = PropsValues.JAVASCRIPT_BAREBONE_ENABLED;

		if (themeJsBarebone) {
			if (signedIn) {
				themeJsBarebone = false;
			}
		}

		boolean themeJsFastLoad = ParamUtil.getBoolean(
			request, "js_fast_load", PropsValues.JAVASCRIPT_FAST_LOAD);

		String lifecycle = ParamUtil.getString(request, "p_p_lifecycle");

		String facebookCanvasPageURL = (String)request.getAttribute(
			WebKeys.FACEBOOK_CANVAS_PAGE_URL);

		boolean widget = false;

		Boolean widgetObj = (Boolean)request.getAttribute(WebKeys.WIDGET);

		if (widgetObj != null) {
			widget = widgetObj.booleanValue();
		}

		// Theme display

		ThemeDisplay themeDisplay = ThemeDisplayFactory.create();

		// Set the CDN host, portal URL, and Facebook application ID first
		// because other methods (setLookAndFeel) depend on them being set

		themeDisplay.setCDNHost(cdnHost);
		themeDisplay.setPortalURL(portalURL);
		themeDisplay.setFacebookCanvasPageURL(facebookCanvasPageURL);
		themeDisplay.setWidget(widget);

		themeDisplay.setCompany(company);
		themeDisplay.setCompanyLogo(companyLogo);
		themeDisplay.setCompanyLogoHeight(companyLogoHeight);
		themeDisplay.setCompanyLogoWidth(companyLogoWidth);
		themeDisplay.setRealCompanyLogo(realCompanyLogo);
		themeDisplay.setRealCompanyLogoHeight(realCompanyLogoHeight);
		themeDisplay.setRealCompanyLogoWidth(realCompanyLogoWidth);
		themeDisplay.setUser(user);
		themeDisplay.setRealUser(realUser);
		themeDisplay.setDoAsUserId(doAsUserId);
		themeDisplay.setDoAsUserLanguageId(doAsUserLanguageId);
		themeDisplay.setDoAsGroupId(doAsGroupId);
		themeDisplay.setRefererPlid(refererPlid);
		themeDisplay.setLayoutSetLogo(layoutSetLogo);
		themeDisplay.setLayout(layout);
		themeDisplay.setLayouts(layouts);
		themeDisplay.setPlid(plid);
		themeDisplay.setLayoutTypePortlet(layoutTypePortlet);
		themeDisplay.setScopeGroupId(scopeGroupId);
		themeDisplay.setSignedIn(signedIn);
		themeDisplay.setPermissionChecker(permissionChecker);
		themeDisplay.setLocale(locale);
		themeDisplay.setLanguageId(LocaleUtil.toLanguageId(locale));
		themeDisplay.setI18nLanguageId(i18nLanguageId);
		themeDisplay.setTimeZone(timeZone);
		themeDisplay.setLookAndFeel(contextPath, theme, colorScheme);
		themeDisplay.setThemeCssFastLoad(themeCssFastLoad);
		themeDisplay.setThemeImagesFastLoad(themeImagesFastLoad);
		themeDisplay.setThemeJsBarebone(themeJsBarebone);
		themeDisplay.setThemeJsFastLoad(themeJsFastLoad);
		themeDisplay.setServerName(request.getServerName());
		themeDisplay.setServerPort(request.getServerPort());
		themeDisplay.setSecure(request.isSecure());
		themeDisplay.setLifecycle(lifecycle);
		themeDisplay.setLifecycleAction(lifecycle.equals("1"));
		themeDisplay.setLifecycleRender(lifecycle.equals("0"));
		themeDisplay.setLifecycleResource(lifecycle.equals("2"));
		themeDisplay.setStateExclusive(LiferayWindowState.isExclusive(request));
		themeDisplay.setStateMaximized(LiferayWindowState.isMaximized(request));
		themeDisplay.setStatePopUp(LiferayWindowState.isPopUp(request));
		themeDisplay.setPathApplet(contextPath + "/applets");
		themeDisplay.setPathCms(contextPath + "/cms");
		themeDisplay.setPathContext(contextPath);
		themeDisplay.setPathFlash(contextPath + "/flash");
		themeDisplay.setPathFriendlyURLPrivateGroup(
			friendlyURLPrivateGroupPath);
		themeDisplay.setPathFriendlyURLPrivateUser(friendlyURLPrivateUserPath);
		themeDisplay.setPathFriendlyURLPublic(friendlyURLPublicPath);
		themeDisplay.setPathImage(imagePath);
		themeDisplay.setPathJavaScript(cdnHost + contextPath + "/html/js");
		themeDisplay.setPathMain(mainPath);
		themeDisplay.setPathSound(contextPath + "/html/sound");

		// URLs

		themeDisplay.setShowAddContentIcon(false);
		themeDisplay.setShowControlPanelIcon(signedIn);
		themeDisplay.setShowHomeIcon(true);
		themeDisplay.setShowMyAccountIcon(signedIn);
		themeDisplay.setShowPageSettingsIcon(false);
		themeDisplay.setShowPortalIcon(true);
		themeDisplay.setShowSignInIcon(!signedIn);
		themeDisplay.setShowSignOutIcon(signedIn);
		themeDisplay.setShowStagingIcon(false);

		String urlControlPanel = friendlyURLPrivateGroupPath + "/control_panel";

		if (Validator.isNotNull(doAsUserId)) {
			urlControlPanel = HttpUtil.addParameter(
				urlControlPanel, "doAsUserId", doAsUserId);
		}

		if (scopeGroupId > 0) {
			urlControlPanel = HttpUtil.addParameter(
				urlControlPanel, "doAsGroupId", scopeGroupId);
		}

		if (refererPlid > 0) {
			urlControlPanel = HttpUtil.addParameter(
				urlControlPanel, "refererPlid", refererPlid);
		}
		else if (plid > 0) {
			urlControlPanel = HttpUtil.addParameter(
				urlControlPanel, "refererPlid", plid);
		}

		themeDisplay.setURLControlPanel(urlControlPanel);

		PortletURL createAccountURL = new PortletURLImpl(
			request, PortletKeys.LOGIN, plid, PortletRequest.ACTION_PHASE);

		createAccountURL.setWindowState(WindowState.MAXIMIZED);
		createAccountURL.setPortletMode(PortletMode.VIEW);

		createAccountURL.setParameter("saveLastPath", "0");
		createAccountURL.setParameter(
			"struts_action", "/login/create_account");

		themeDisplay.setURLCreateAccount(createAccountURL);

		String currentURL = PortalUtil.getCurrentURL(request);

		themeDisplay.setURLCurrent(currentURL);

		String urlHome = PortalUtil.getHomeURL(request);

		themeDisplay.setURLHome(urlHome);

		if (layout != null) {
			if (layout.getType().equals(LayoutConstants.TYPE_PORTLET)) {
				boolean freeformLayout =
					layoutTypePortlet.getLayoutTemplateId().equals(
						"freeform");

				themeDisplay.setFreeformLayout(freeformLayout);

				boolean hasUpdateLayoutPermission =
					LayoutPermissionUtil.contains(
						permissionChecker, layout, ActionKeys.UPDATE);

				if (hasUpdateLayoutPermission) {
					if (!LiferayWindowState.isMaximized(request)) {
						themeDisplay.setShowAddContentIcon(true);
					}

					themeDisplay.setShowLayoutTemplatesIcon(true);

					themeDisplay.setURLAddContent(
						"LayoutConfiguration.toggle('" +
							PortletKeys.LAYOUT_CONFIGURATION + "');");

					themeDisplay.setURLLayoutTemplates(
						"Liferay.Layout.showTemplates();");
				}
			}

			boolean hasManageLayoutsPermission =
				GroupPermissionUtil.contains(
					permissionChecker, scopeGroupId, ActionKeys.MANAGE_LAYOUTS);

			if (group.isUser()) {
				if ((layout.isPrivateLayout() &&
					 !PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_MODIFIABLE) ||
					(layout.isPublicLayout() &&
					 !PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_MODIFIABLE)) {

					hasManageLayoutsPermission = false;
				}
			}

			if (hasManageLayoutsPermission) {
				themeDisplay.setShowPageSettingsIcon(true);

				PortletURL pageSettingsURL = new PortletURLImpl(
					request, PortletKeys.LAYOUT_MANAGEMENT, plid,
					PortletRequest.RENDER_PHASE);

				pageSettingsURL.setWindowState(WindowState.MAXIMIZED);
				pageSettingsURL.setPortletMode(PortletMode.VIEW);

				pageSettingsURL.setParameter(
					"struts_action", "/layout_management/edit_pages");

				if (layout.isPrivateLayout()) {
					pageSettingsURL.setParameter("tabs1", "private-pages");
				}
				else {
					pageSettingsURL.setParameter("tabs1", "public-pages");
				}

				pageSettingsURL.setParameter("redirect", currentURL);
				pageSettingsURL.setParameter(
					"groupId", String.valueOf(scopeGroupId));
				pageSettingsURL.setParameter("selPlid", String.valueOf(plid));

				themeDisplay.setURLPageSettings(pageSettingsURL);

				PortletURL publishToLiveURL = new PortletURLImpl(
					request, PortletKeys.LAYOUT_MANAGEMENT, plid,
					PortletRequest.RENDER_PHASE);

				publishToLiveURL.setWindowState(LiferayWindowState.EXCLUSIVE);
				publishToLiveURL.setPortletMode(PortletMode.VIEW);

				publishToLiveURL.setParameter(
					"struts_action", "/layout_management/export_pages");

				if (layout.isPrivateLayout()) {
					publishToLiveURL.setParameter("tabs1", "private-pages");
				}
				else {
					publishToLiveURL.setParameter("tabs1", "public-pages");
				}

				publishToLiveURL.setParameter("pagesRedirect", currentURL);
				publishToLiveURL.setParameter(
					"groupId", String.valueOf(scopeGroupId));
				publishToLiveURL.setParameter("selPlid", String.valueOf(plid));

				themeDisplay.setURLPublishToLive(publishToLiveURL);
			}

			if (group.hasStagingGroup() && !group.isStagingGroup()) {
				themeDisplay.setShowAddContentIcon(false);
				themeDisplay.setShowLayoutTemplatesIcon(false);
				themeDisplay.setShowPageSettingsIcon(false);
				themeDisplay.setURLPublishToLive(null);
			}

			if (group.getName().equals(GroupConstants.CONTROL_PANEL)) {
				themeDisplay.setShowPageSettingsIcon(false);
				themeDisplay.setURLPublishToLive(null);
			}

			// LEP-4987

			if (group.hasStagingGroup() || group.isStagingGroup()) {
				boolean hasApproveProposalPermission =
					GroupPermissionUtil.contains(
						permissionChecker, scopeGroupId,
						ActionKeys.APPROVE_PROPOSAL);

				if (hasManageLayoutsPermission) {
					themeDisplay.setShowStagingIcon(true);
				}
				else if (hasApproveProposalPermission) {
					themeDisplay.setShowStagingIcon(true);
				}
			}

			String myAccountNamespace = PortalUtil.getPortletNamespace(
				PortletKeys.MY_ACCOUNT);

			String myAccountRedirect = ParamUtil.getString(
				request, myAccountNamespace + "backURL", currentURL);

			Group controlPanelGroup = GroupLocalServiceUtil.getGroup(
				companyId, GroupConstants.CONTROL_PANEL);

			long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(
				controlPanelGroup.getGroupId(), true);

			PortletURLImpl myAccountURL = new PortletURLImpl(
				request, PortletKeys.MY_ACCOUNT, controlPanelPlid,
				PortletRequest.RENDER_PHASE);

			myAccountURL.setWindowState(WindowState.MAXIMIZED);
			myAccountURL.setPortletMode(PortletMode.VIEW);
			myAccountURL.setRefererPlid(plid);

			myAccountURL.setParameter("struts_action", "/my_account/edit_user");
			myAccountURL.setParameter("backURL", myAccountRedirect);

			themeDisplay.setURLMyAccount(myAccountURL);
		}

		if ((!user.isActive()) ||
			(PropsValues.TERMS_OF_USE_REQUIRED &&
			 !user.isAgreedToTermsOfUse())) {

			themeDisplay.setShowAddContentIcon(false);
			themeDisplay.setShowMyAccountIcon(false);
			themeDisplay.setShowPageSettingsIcon(false);
		}

		themeDisplay.setURLPortal(portalURL + contextPath);

		String urlSignIn = mainPath + "/portal/login";

		if (layout != null) {
			urlSignIn = HttpUtil.addParameter(
				urlSignIn, "p_l_id", layout.getPlid());
		}

		themeDisplay.setURLSignIn(urlSignIn);

		themeDisplay.setURLSignOut(mainPath + "/portal/logout");

		PortletURL updateManagerURL = new PortletURLImpl(
			request, PortletKeys.UPDATE_MANAGER, plid,
			PortletRequest.RENDER_PHASE);

		updateManagerURL.setWindowState(WindowState.MAXIMIZED);
		updateManagerURL.setPortletMode(PortletMode.VIEW);

		updateManagerURL.setParameter("struts_action", "/update_manager/view");

		themeDisplay.setURLUpdateManager(updateManagerURL);

		request.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		// Parallel render

		boolean parallelRenderEnable = true;

		if (layout != null) {
			if (layoutTypePortlet.getPortletIds().size() == 1) {
				parallelRenderEnable = false;
			}
		}

		Boolean parallelRenderEnableObj = Boolean.valueOf(ParamUtil.getBoolean(
			request, "p_p_parallel", parallelRenderEnable));

		request.setAttribute(
			WebKeys.PORTLET_PARALLEL_RENDER, parallelRenderEnableObj);
	}

	protected void updateUserLayouts(User user) throws Exception {

		// Private layouts

		boolean addDefaultUserPrivateLayouts = false;

		if (PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED &&
			PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_AUTO_CREATE) {

			addDefaultUserPrivateLayouts = true;

			if (PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_POWER_USER_REQUIRED) {
				Role powerUserRole = RoleLocalServiceUtil.getRole(
					user.getCompanyId(), RoleConstants.POWER_USER);

				if (!UserLocalServiceUtil.hasRoleUser(
						powerUserRole.getRoleId(), user.getUserId())) {

					addDefaultUserPrivateLayouts = false;
				}
			}
		}

		if (addDefaultUserPrivateLayouts && !user.hasPrivateLayouts()) {
			addDefaultUserPrivateLayouts(user);
		}

		boolean deleteDefaultUserPrivateLayouts = false;

		if (!PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED) {
			deleteDefaultUserPrivateLayouts = true;
		}
		else if (PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_POWER_USER_REQUIRED) {
			Role powerUserRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.POWER_USER);

			if (!UserLocalServiceUtil.hasRoleUser(
					powerUserRole.getRoleId(), user.getUserId())) {

				deleteDefaultUserPrivateLayouts = true;
			}
		}

		if (deleteDefaultUserPrivateLayouts && user.hasPrivateLayouts()) {
			deleteDefaultUserPrivateLayouts(user);
		}

		// Public pages

		boolean addDefaultUserPublicLayouts = false;

		if (PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED &&
			PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_AUTO_CREATE) {

			addDefaultUserPublicLayouts = true;

			if (PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED) {
				Role powerUserRole = RoleLocalServiceUtil.getRole(
					user.getCompanyId(), RoleConstants.POWER_USER);

				if (!UserLocalServiceUtil.hasRoleUser(
						powerUserRole.getRoleId(), user.getUserId())) {

					addDefaultUserPublicLayouts = false;
				}
			}
		}

		if (addDefaultUserPublicLayouts && !user.hasPublicLayouts()) {
			addDefaultUserPublicLayouts(user);
		}

		boolean deleteDefaultUserPublicLayouts = false;

		if (!PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {
			deleteDefaultUserPublicLayouts = true;
		}
		else if (PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED) {
			Role powerUserRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.POWER_USER);

			if (!UserLocalServiceUtil.hasRoleUser(
					powerUserRole.getRoleId(), user.getUserId())) {

				deleteDefaultUserPublicLayouts = true;
			}
		}

		if (deleteDefaultUserPublicLayouts && user.hasPublicLayouts()) {
			deleteDefaultUserPublicLayouts(user);
		}
	}

	protected File privateLARFile;
	protected File publicLARFile;

	private static final String _PATH_PORTAL_LAYOUT = "/portal/layout";

	private static Log _log = LogFactory.getLog(ServicePreAction.class);

}