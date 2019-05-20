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

package com.liferay.source.formatter.checks;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tools.ToolsUtil;
import com.liferay.source.formatter.BNDSettings;
import com.liferay.source.formatter.SourceFormatterExcludes;
import com.liferay.source.formatter.SourceFormatterMessage;
import com.liferay.source.formatter.checks.util.SourceUtil;
import com.liferay.source.formatter.util.CheckType;
import com.liferay.source.formatter.util.FileUtil;
import com.liferay.source.formatter.util.SourceFormatterUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author Hugo Huijser
 */
public abstract class BaseSourceCheck implements SourceCheck {

	@Override
	public String getAttributeValue(String attributeKey, String absolutePath) {
		return getAttributeValue(attributeKey, absolutePath, StringPool.BLANK);
	}

	@Override
	public String getAttributeValue(
		String attributeKey, String absolutePath, String defaultValue) {

		if (_attributesJSONObject == null) {
			return defaultValue;
		}

		String value = _attributeValueMap.get(attributeKey);

		if (value != null) {
			return value;
		}

		value = _attributeValueMap.get(absolutePath + ":" + attributeKey);

		if (value != null) {
			return value;
		}

		String closestPropertiesFileLocation = null;
		boolean hasSubdirectoryAttributeValue = false;

		Iterator<String> keys = _attributesJSONObject.keys();

		while (keys.hasNext()) {
			String fileLocation = keys.next();

			String curValue = _getAttributeValue(
				_attributesJSONObject.getJSONObject(fileLocation),
				attributeKey);

			if (curValue == null) {
				continue;
			}

			if (fileLocation.equals(
					SourceFormatterUtil.CONFIGURATION_FILE_LOCATION)) {

				if (value == null) {
					value = curValue;
				}

				continue;
			}

			String baseDirNameAbsolutePath = SourceUtil.getAbsolutePath(
				_baseDirName);

			if (fileLocation.length() > baseDirNameAbsolutePath.length()) {
				hasSubdirectoryAttributeValue = true;
			}

			if (!absolutePath.startsWith(fileLocation) &&
				!fileLocation.equals(baseDirNameAbsolutePath)) {

				continue;
			}

			if ((closestPropertiesFileLocation == null) ||
				(closestPropertiesFileLocation.length() <
					fileLocation.length())) {

				value = curValue;

				closestPropertiesFileLocation = fileLocation;
			}
		}

		if (value == null) {
			value = defaultValue;
		}

		_attributeValueMap.put(absolutePath + ":" + attributeKey, value);

		if (!hasSubdirectoryAttributeValue) {
			_attributeValueMap.put(attributeKey, value);
		}

		return value;
	}

	@Override
	public List<String> getAttributeValues(
		String attributeKey, String absolutePath) {

		if (_attributesJSONObject == null) {
			return Collections.emptyList();
		}

		List<String> attributeValues = _attributeValuesMap.get(attributeKey);

		if (attributeValues != null) {
			return attributeValues;
		}

		attributeValues = _attributeValuesMap.get(
			absolutePath + ":" + attributeKey);

		if (attributeValues != null) {
			return attributeValues;
		}

		attributeValues = new ArrayList<>();

		boolean hasSubdirectoryAttributeValues = false;

		Iterator<String> keys = _attributesJSONObject.keys();

		while (keys.hasNext()) {
			String fileLocation = keys.next();

			List<String> curAttributeValues = _getAttributeValues(
				_attributesJSONObject.getJSONObject(fileLocation),
				attributeKey);

			if (curAttributeValues.isEmpty()) {
				continue;
			}

			if (!fileLocation.equals(
					SourceFormatterUtil.CONFIGURATION_FILE_LOCATION)) {

				String baseDirNameAbsolutePath = SourceUtil.getAbsolutePath(
					_baseDirName);

				if (fileLocation.length() > baseDirNameAbsolutePath.length()) {
					hasSubdirectoryAttributeValues = true;
				}

				if (!absolutePath.startsWith(fileLocation)) {
					continue;
				}
			}

			attributeValues.addAll(curAttributeValues);
		}

		_attributeValuesMap.put(
			absolutePath + ":" + attributeKey, attributeValues);

		if (!hasSubdirectoryAttributeValues) {
			_attributeValuesMap.put(attributeKey, attributeValues);
		}

		return attributeValues;
	}

	@Override
	public Set<SourceFormatterMessage> getSourceFormatterMessages(
		String fileName) {

		if (_sourceFormatterMessagesMap.containsKey(fileName)) {
			return _sourceFormatterMessagesMap.get(fileName);
		}

		return Collections.emptySet();
	}

	@Override
	public boolean isAttributeValue(String attributeKey, String absolutePath) {
		return GetterUtil.getBoolean(
			getAttributeValue(attributeKey, absolutePath));
	}

	@Override
	public boolean isAttributeValue(
		String attributeKey, String absolutePath, boolean defaultValue) {

		String attributeValue = getAttributeValue(attributeKey, absolutePath);

		if (Validator.isNull(attributeValue)) {
			return defaultValue;
		}

		return GetterUtil.getBoolean(attributeValue);
	}

	@Override
	public boolean isEnabled(String absolutePath) {
		return isAttributeValue("enabled", absolutePath, true);
	}

	@Override
	public boolean isModulesCheck() {
		return false;
	}

	@Override
	public boolean isPortalCheck() {
		return false;
	}

	@Override
	public void setAllFileNames(List<String> allFileNames) {
	}

	@Override
	public void setAttributes(String attributes) throws JSONException {
		_attributesJSONObject = new JSONObjectImpl(attributes);
	}

	@Override
	public void setBaseDirName(String baseDirName) {
		_baseDirName = baseDirName;
	}

	@Override
	public void setFileExtensions(List<String> fileExtensions) {
		_fileExtensions = fileExtensions;
	}

	@Override
	public void setMaxLineLength(int maxLineLength) {
		_maxLineLength = maxLineLength;
	}

	@Override
	public void setPluginsInsideModulesDirectoryNames(
		List<String> pluginsInsideModulesDirectoryNames) {

		_pluginsInsideModulesDirectoryNames =
			pluginsInsideModulesDirectoryNames;
	}

	@Override
	public void setPortalSource(boolean portalSource) {
		_portalSource = portalSource;
	}

	@Override
	public void setProjectPathPrefix(String projectPathPrefix) {
		_projectPathPrefix = projectPathPrefix;
	}

	@Override
	public void setPropertiesMap(Map<String, Properties> propertiesMap) {
		_propertiesMap = propertiesMap;
	}

	@Override
	public void setSourceFormatterExcludes(
		SourceFormatterExcludes sourceFormatterExcludes) {

		_sourceFormatterExcludes = sourceFormatterExcludes;
	}

	@Override
	public void setSubrepository(boolean subrepository) {
		_subrepository = subrepository;
	}

	protected void addMessage(String fileName, String message) {
		addMessage(fileName, message, -1);
	}

	protected void addMessage(String fileName, String message, int lineNumber) {
		addMessage(fileName, message, null, lineNumber);
	}

	protected void addMessage(
		String fileName, String message, String markdownFileName) {

		addMessage(fileName, message, markdownFileName, -1);
	}

	protected void addMessage(
		String fileName, String message, String markdownFileName,
		int lineNumber) {

		Set<SourceFormatterMessage> sourceFormatterMessages =
			_sourceFormatterMessagesMap.get(fileName);

		if (sourceFormatterMessages == null) {
			sourceFormatterMessages = new TreeSet<>();
		}

		Class<?> clazz = getClass();

		sourceFormatterMessages.add(
			new SourceFormatterMessage(
				fileName, message, CheckType.SOURCE_CHECK,
				clazz.getSimpleName(), markdownFileName, lineNumber));

		_sourceFormatterMessagesMap.put(fileName, sourceFormatterMessages);
	}

	protected void clearSourceFormatterMessages(String fileName) {
		_sourceFormatterMessagesMap.remove(fileName);
	}

	protected String getBaseDirName() {
		return _baseDirName;
	}

	protected BNDSettings getBNDSettings(String fileName) throws IOException {
		String bndFileLocation = fileName;

		while (true) {
			int pos = bndFileLocation.lastIndexOf(StringPool.SLASH);

			if (pos == -1) {
				return null;
			}

			bndFileLocation = bndFileLocation.substring(0, pos + 1);

			BNDSettings bndSettings = _bndSettingsMap.get(bndFileLocation);

			if (bndSettings != null) {
				return bndSettings;
			}

			File file = new File(bndFileLocation + "bnd.bnd");

			if (file.exists()) {
				bndSettings = new BNDSettings(
					bndFileLocation + "bnd.bnd", FileUtil.read(file));

				_bndSettingsMap.put(bndFileLocation, bndSettings);

				return bndSettings;
			}

			bndFileLocation = StringUtil.replaceLast(
				bndFileLocation, CharPool.SLASH, StringPool.BLANK);
		}
	}

	protected String getContent(String fileName, int level) throws IOException {
		File file = getFile(fileName, level);

		if (file != null) {
			String content = FileUtil.read(file);

			if (Validator.isNotNull(content)) {
				return content;
			}
		}

		return StringPool.BLANK;
	}

	protected Document getCustomSQLDocument(
			String fileName, String absolutePath,
			Document portalCustomSQLDocument)
		throws DocumentException {

		if (isPortalSource() && !isModulesFile(absolutePath)) {
			return portalCustomSQLDocument;
		}

		int i = fileName.lastIndexOf("/src/");

		if (i == -1) {
			return null;
		}

		File customSQLFile = new File(
			fileName.substring(0, i) + "/src/custom-sql/default.xml");

		if (!customSQLFile.exists()) {
			customSQLFile = new File(
				fileName.substring(0, i) +
					"/src/main/resources/META-INF/custom-sql/default.xml");
		}

		if (!customSQLFile.exists()) {
			customSQLFile = new File(
				fileName.substring(0, i) +
					"/src/main/resources/custom-sql/default.xml");
		}

		if (!customSQLFile.exists()) {
			return null;
		}

		return SourceUtil.readXML(customSQLFile);
	}

	protected File getFile(String fileName, int level) {
		return SourceFormatterUtil.getFile(_baseDirName, fileName, level);
	}

	protected List<String> getFileExtensions() {
		return _fileExtensions;
	}

	protected List<String> getFileNames(
			String baseDirName, String[] excludes, String[] includes)
		throws IOException {

		return SourceFormatterUtil.scanForFiles(
			baseDirName, excludes, includes, _sourceFormatterExcludes, true);
	}

	protected int getLeadingTabCount(String line) {
		int leadingTabCount = 0;

		while (line.startsWith(StringPool.TAB)) {
			line = line.substring(1);

			leadingTabCount++;
		}

		return leadingTabCount;
	}

	protected int getLevel(String s) {
		return ToolsUtil.getLevel(s);
	}

	protected int getLevel(
		String s, String increaseLevelString, String decreaseLevelString) {

		return ToolsUtil.getLevel(s, increaseLevelString, decreaseLevelString);
	}

	protected int getLevel(
		String s, String[] increaseLevelStrings,
		String[] decreaseLevelStrings) {

		return ToolsUtil.getLevel(
			s, increaseLevelStrings, decreaseLevelStrings);
	}

	protected int getLevel(
		String s, String[] increaseLevelStrings, String[] decreaseLevelStrings,
		int startLevel) {

		return ToolsUtil.getLevel(
			s, increaseLevelStrings, decreaseLevelStrings, startLevel);
	}

	protected String getLine(String content, int lineNumber) {
		return SourceUtil.getLine(content, lineNumber);
	}

	protected int getLineNumber(String content, int pos) {
		return SourceUtil.getLineNumber(content, pos);
	}

	protected int getLineStartPos(String content, int lineNumber) {
		return SourceUtil.getLineStartPos(content, lineNumber);
	}

	protected int getMaxLineLength() {
		return _maxLineLength;
	}

	protected List<String> getPluginsInsideModulesDirectoryNames() {
		return _pluginsInsideModulesDirectoryNames;
	}

	protected String getPortalContent(String fileName) throws IOException {
		return getPortalContent(fileName, false);
	}

	protected String getPortalContent(
			String fileName, boolean forceRetrieveFromGit)
		throws IOException {

		String portalBranchName = _getPortalBranchName(!forceRetrieveFromGit);

		return getPortalContent(fileName, portalBranchName);
	}

	protected String getPortalContent(String fileName, String portalBranchName)
		throws IOException {

		if (Validator.isNull(portalBranchName)) {
			String content = getContent(
				fileName, ToolsUtil.PORTAL_MAX_DIR_LEVEL);

			if (Validator.isNotNull(content)) {
				return content;
			}
		}

		URL url = _getPortalGitURL(fileName, portalBranchName);

		if (url == null) {
			return null;
		}

		try {
			return StringUtil.read(url.openStream());
		}
		catch (IOException ioe) {
			return null;
		}
	}

	protected synchronized Document getPortalCustomSQLDocument()
		throws DocumentException, IOException {

		if (_portalCustomSQLDocument != null) {
			return _portalCustomSQLDocument;
		}

		_portalCustomSQLDocument = DocumentHelper.createDocument();

		if (!isPortalSource()) {
			return _portalCustomSQLDocument;
		}

		String portalCustomSQLDefaultContent = getPortalContent(
			"portal-impl/src/custom-sql/default.xml");

		if (portalCustomSQLDefaultContent == null) {
			return _portalCustomSQLDocument;
		}

		Element rootElement = _portalCustomSQLDocument.addElement("custom-sql");

		Document customSQLDefaultDocument = SourceUtil.readXML(
			portalCustomSQLDefaultContent);

		Element customSQLDefaultRootElement =
			customSQLDefaultDocument.getRootElement();

		for (Element sqlElement :
				(List<Element>)customSQLDefaultRootElement.elements("sql")) {

			String customSQLFileContent = getPortalContent(
				"portal-impl/src/" + sqlElement.attributeValue("file"));

			if (customSQLFileContent == null) {
				continue;
			}

			Document customSQLDocument = SourceUtil.readXML(
				customSQLFileContent);

			Element customSQLRootElement = customSQLDocument.getRootElement();

			for (Element customSQLElement :
					(List<Element>)customSQLRootElement.elements("sql")) {

				rootElement.add(customSQLElement.detach());
			}
		}

		return _portalCustomSQLDocument;
	}

	protected File getPortalDir() {
		File portalImplDir = SourceFormatterUtil.getFile(
			getBaseDirName(), "portal-impl", ToolsUtil.PORTAL_MAX_DIR_LEVEL);

		if (portalImplDir == null) {
			return null;
		}

		return portalImplDir.getParentFile();
	}

	protected InputStream getPortalInputStream(String fileName)
		throws IOException {

		return getPortalInputStream(fileName, _getPortalBranchName(true));
	}

	protected InputStream getPortalInputStream(
			String fileName, String portalBranchName)
		throws IOException {

		File file = getFile(fileName, ToolsUtil.PORTAL_MAX_DIR_LEVEL);

		if (file != null) {
			return new FileInputStream(file);
		}

		URL url = _getPortalGitURL(fileName, portalBranchName);

		if (url != null) {
			return url.openStream();
		}

		return null;
	}

	protected String getProjectName() {
		if (_projectName != null) {
			return _projectName;
		}

		if (Validator.isNull(_projectPathPrefix) ||
			!_projectPathPrefix.contains(StringPool.COLON)) {

			_projectName = StringPool.BLANK;

			return _projectName;
		}

		int pos = _projectPathPrefix.lastIndexOf(StringPool.COLON);

		_projectName = _projectPathPrefix.substring(pos + 1);

		return _projectName;
	}

	protected String getProjectPathPrefix() {
		return _projectPathPrefix;
	}

	protected SourceFormatterExcludes getSourceFormatterExcludes() {
		return _sourceFormatterExcludes;
	}

	protected String getVariableTypeName(
		String content, String fileContent, String variableName) {

		if (variableName == null) {
			return null;
		}

		Pattern pattern = Pattern.compile(
			"\\W(\\w+)\\s+" + variableName + "\\W");

		Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			return matcher.group(1);
		}

		matcher = pattern.matcher(fileContent);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return null;
	}

	protected boolean isExcludedPath(
		Properties properties, String key, String path, int lineNumber,
		String parameter) {

		List<String> excludes = ListUtil.fromString(
			GetterUtil.getString(properties.getProperty(key)),
			StringPool.COMMA);

		if (ListUtil.isEmpty(excludes)) {
			return false;
		}

		String pathWithParameter = null;

		if (Validator.isNotNull(parameter)) {
			pathWithParameter = path + StringPool.AT + parameter;
		}

		String pathWithLineNumber = null;

		if (lineNumber > 0) {
			pathWithLineNumber = path + StringPool.AT + lineNumber;
		}

		for (String exclude : excludes) {
			if (Validator.isNull(exclude)) {
				continue;
			}

			if (exclude.startsWith("**")) {
				exclude = exclude.substring(2);
			}

			if (exclude.endsWith("**")) {
				exclude = exclude.substring(0, exclude.length() - 2);

				if (path.contains(exclude)) {
					return true;
				}

				continue;
			}

			if (path.endsWith(exclude) ||
				((pathWithParameter != null) &&
				 pathWithParameter.endsWith(exclude)) ||
				((pathWithLineNumber != null) &&
				 pathWithLineNumber.endsWith(exclude))) {

				return true;
			}
		}

		return false;
	}

	protected boolean isExcludedPath(String key, String path) {
		return isExcludedPath(key, path, -1);
	}

	protected boolean isExcludedPath(String key, String path, int lineNumber) {
		return isExcludedPath(key, path, lineNumber, null);
	}

	protected boolean isExcludedPath(
		String key, String path, int lineNumber, String parameter) {

		for (Map.Entry<String, Properties> entry : _propertiesMap.entrySet()) {
			String propertiesFileLocation = entry.getKey();

			if (path.startsWith(propertiesFileLocation) &&
				isExcludedPath(
					entry.getValue(), key, path, lineNumber, parameter)) {

				return true;
			}
		}

		return false;
	}

	protected boolean isExcludedPath(
		String key, String path, String parameter) {

		return isExcludedPath(key, path, -1, parameter);
	}

	protected boolean isModulesApp(String absolutePath, boolean privateOnly) {
		if (absolutePath.contains("/modules/private/apps/") ||
			(!privateOnly && absolutePath.contains("/modules/apps/"))) {

			return true;
		}

		if (_projectPathPrefix == null) {
			return false;
		}

		if (_projectPathPrefix.startsWith(":private:apps") ||
			(!privateOnly && _projectPathPrefix.startsWith(":apps:"))) {

			return true;
		}

		return false;
	}

	protected boolean isModulesFile(String absolutePath) {
		return isModulesFile(absolutePath, null);
	}

	protected boolean isModulesFile(
		String absolutePath, List<String> pluginsInsideModulesDirectoryNames) {

		if (_subrepository) {
			return true;
		}

		if (pluginsInsideModulesDirectoryNames == null) {
			return absolutePath.contains("/modules/");
		}

		try {
			for (String directoryName : pluginsInsideModulesDirectoryNames) {
				if (absolutePath.contains(directoryName)) {
					return false;
				}
			}
		}
		catch (Exception e) {
		}

		return absolutePath.contains("/modules/");
	}

	protected boolean isPortalSource() {
		return _portalSource;
	}

	protected boolean isSubrepository() {
		return _subrepository;
	}

	protected String stripQuotes(String s) {
		return stripQuotes(s, CharPool.APOSTROPHE, CharPool.QUOTE);
	}

	protected String stripQuotes(String s, char... delimeters) {
		List<Character> delimetersList = ListUtil.toList(delimeters);

		char delimeter = CharPool.SPACE;
		boolean insideQuotes = false;

		StringBundler sb = new StringBundler();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (insideQuotes) {
				if (c == delimeter) {
					int precedingBackSlashCount = 0;

					for (int j = i - 1; j >= 0; j--) {
						if (s.charAt(j) == CharPool.BACK_SLASH) {
							precedingBackSlashCount += 1;
						}
						else {
							break;
						}
					}

					if ((precedingBackSlashCount == 0) ||
						((precedingBackSlashCount % 2) == 0)) {

						insideQuotes = false;
					}
				}
			}
			else if (delimetersList.contains(c)) {
				delimeter = c;
				insideQuotes = true;
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	protected static final String RUN_OUTSIDE_PORTAL_EXCLUDES =
		"run.outside.portal.excludes";

	private String _getAttributeValue(
		JSONObject jsonObject, String attributeKey) {

		JSONArray jsonArray = jsonObject.getJSONArray(attributeKey);

		if ((jsonArray == null) || (jsonArray.length() != 1)) {
			return null;
		}

		return jsonArray.getString(0);
	}

	private List<String> _getAttributeValues(
		JSONObject jsonObject, String attributeKey) {

		List<String> attributeValues = new ArrayList<>();

		JSONArray jsonArray = jsonObject.getJSONArray(attributeKey);

		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				attributeValues.add(jsonArray.getString(i));
			}
		}

		return attributeValues;
	}

	private String _getPortalBranchName(
		boolean excludePortalRootPropertiesFile) {

		String portalRootLocation = null;

		if (_portalSource && excludePortalRootPropertiesFile) {
			portalRootLocation = SourceUtil.getAbsolutePath(
				SourceFormatterUtil.getPortalDir(_baseDirName));
		}

		String value = SourceFormatterUtil.getPropertyValue(
			SourceFormatterUtil.GIT_LIFERAY_PORTAL_BRANCH, _propertiesMap,
			portalRootLocation);

		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		if (!value.contains(StringPool.COMMA)) {
			return value;
		}

		String[] portalBranchNames = StringUtil.split(value);

		return portalBranchNames[0];
	}

	private URL _getPortalGitURL(String fileName, String portalBranchName) {
		if (Validator.isNull(portalBranchName)) {
			return null;
		}

		try {
			return new URL(
				StringBundler.concat(
					SourceFormatterUtil.GIT_LIFERAY_PORTAL_URL,
					portalBranchName, StringPool.SLASH, fileName));
		}
		catch (Exception e) {
			return null;
		}
	}

	private JSONObject _attributesJSONObject;
	private final Map<String, String> _attributeValueMap =
		new ConcurrentHashMap<>();
	private final Map<String, List<String>> _attributeValuesMap =
		new ConcurrentHashMap<>();
	private String _baseDirName;
	private final Map<String, BNDSettings> _bndSettingsMap =
		new ConcurrentHashMap<>();
	private List<String> _fileExtensions;
	private int _maxLineLength;
	private List<String> _pluginsInsideModulesDirectoryNames;
	private Document _portalCustomSQLDocument;
	private boolean _portalSource;
	private String _projectName;
	private String _projectPathPrefix;
	private Map<String, Properties> _propertiesMap;
	private SourceFormatterExcludes _sourceFormatterExcludes;
	private final Map<String, Set<SourceFormatterMessage>>
		_sourceFormatterMessagesMap = new ConcurrentHashMap<>();
	private boolean _subrepository;

}