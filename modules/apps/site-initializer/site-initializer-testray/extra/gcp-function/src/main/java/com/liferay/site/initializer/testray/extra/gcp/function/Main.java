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

package com.liferay.site.initializer.testray.extra.gcp.function;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.liferay.petra.http.invoker.HttpInvoker;

import java.io.File;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Brian Wing Shun Chan
 */
public class Main {

	public static void main(String[] arguments) throws Exception {
		Properties properties = new Properties();

		try (InputStream inputStream = Main.class.getResourceAsStream(
				"/application.properties")) {

			properties.load(inputStream);
		}

		Main main = new Main(
			properties.getProperty("liferay.login"),
			properties.getProperty("liferay.password"),
			properties.getProperty("liferay.url"),
			properties.getProperty("s3.api.key.path"),
			properties.getProperty("s3.bucket.name"));

		main.uploadToTestray();
	}

	public Main(
		String liferayLogin, String liferayPassword, String liferayURL,
		String s3APIKeyPath, String s3BucketName) {

		_liferayLogin = liferayLogin;
		_liferayPassword = liferayPassword;
		_liferayURL = liferayURL;
		_s3APIKeyPath = s3APIKeyPath;
		_s3BucketName = s3BucketName;
	}

	public void uploadToTestray() throws Exception {
		Storage storage = StorageOptions.newBuilder(
		).setCredentials(
			GoogleCredentials.fromStream(
				Main.class.getResourceAsStream(_s3APIKeyPath))
		).build(
		).getService();

		Page<Blob> page = storage.list(
			_s3BucketName, Storage.BlobListOption.prefix("inbox/"));

		for (Blob blob : page.iterateAll()) {
			String name = blob.getName();

			if (name.equals("inbox/")) {
				continue;
			}

			_processArchive(blob.getContent());

			blob.copyTo(_s3BucketName, name.replaceFirst("inbox", "processed"));

			blob.delete();
		}
	}

	private String _getAttributeValue(String attributeName, Node node) {
		NamedNodeMap namedNodeMap = node.getAttributes();

		if (namedNodeMap == null) {
			return null;
		}

		Node attributeNode = namedNodeMap.getNamedItem(attributeName);

		if (attributeNode == null) {
			return null;
		}

		return attributeNode.getTextContent();
	}

	private long _getObjectEntryId(
			String name, String objectDefinitionShortName)
		throws Exception {

		Long objectEntryId = _objectEntryIds.get(
			objectDefinitionShortName + "#" + name);

		if (objectEntryId != null) {
			return objectEntryId;
		}

		HttpInvoker.HttpResponse httpResponse = _invoke(
			null, null, HttpInvoker.HttpMethod.GET, objectDefinitionShortName,
			HashMapBuilder.put(
				"fields", "id"
			).put(
				"filter", "name eq '" + name + "'"
			).build());

		JSONObject responseJSONObject = new JSONObject(
			httpResponse.getContent());

		JSONArray jsonArray = responseJSONObject.getJSONArray("items");

		if (jsonArray.isEmpty()) {
			return 0;
		}

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		objectEntryId = jsonObject.getLong("id");

		_objectEntryIds.put(
			objectDefinitionShortName + "#" + name, objectEntryId);

		return objectEntryId;
	}

	private Map<String, String> _getPropertiesMap(Element element) {
		Map<String, String> map = new HashMap<>();

		NodeList propertiesNodeList = element.getElementsByTagName(
			"properties");

		Node propertiesNode = propertiesNodeList.item(0);

		Element propertiesElement = (Element)propertiesNode;

		NodeList propertyNodeList = propertiesElement.getElementsByTagName(
			"property");

		for (int i = 0; i < propertyNodeList.getLength(); i++) {
			Node propertyNode = propertyNodeList.item(i);

			if (!propertyNode.hasAttributes()) {
				continue;
			}

			map.put(
				_getAttributeValue("name", propertyNode),
				_getAttributeValue("value", propertyNode));
		}

		return map;
	}

	private long _getTestrayProjectId(String testrayProjectName)
		throws Exception {

		long testrayProjectId = _getObjectEntryId(
			testrayProjectName, "projects");

		if (testrayProjectId != 0) {
			return testrayProjectId;
		}

		HttpInvoker.HttpResponse httpResponse = _invoke(
			_toJSON(
				HashMapBuilder.put(
					"name", testrayProjectName
				).build()),
			null, HttpInvoker.HttpMethod.POST, "projects", null);

		JSONObject responseJSONObject = new JSONObject(
			httpResponse.getContent());

		testrayProjectId = responseJSONObject.getLong("id");

		_objectEntryIds.put("projects#" + testrayProjectName, testrayProjectId);

		return testrayProjectId;
	}

	private HttpInvoker.HttpResponse _invoke(
			String body, Map<String, String> headers,
			HttpInvoker.HttpMethod httpMethod, String objectDefinitionShortName,
			Map<String, String> parameters)
		throws Exception {

		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(body, "application/json");

		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpInvoker.header(entry.getKey(), entry.getValue());
			}
		}

		httpInvoker.httpMethod(httpMethod);

		if (parameters != null) {
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}
		}

		httpInvoker.path(_liferayURL + "/o/c/" + objectDefinitionShortName);
		httpInvoker.userNameAndPassword(_liferayLogin + ":" + _liferayPassword);

		return httpInvoker.invoke();
	}

	private void _processArchive(byte[] bytes) throws Exception {
		Path tempDirectoryPath = null;
		Path tempFilePath = null;

		try {
			tempDirectoryPath = Files.createTempDirectory(null);

			tempFilePath = Files.createTempFile(null, null);

			Files.write(tempFilePath, bytes);

			Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");

			File tempDirectoryFile = tempDirectoryPath.toFile();

			archiver.extract(tempFilePath.toFile(), tempDirectoryFile);

			DocumentBuilderFactory documentBuilderFactory =
				DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder =
				documentBuilderFactory.newDocumentBuilder();

			for (File file : tempDirectoryFile.listFiles()) {
				try {
					Document document = documentBuilder.parse(file);

					_processDocument(document);
				}
				finally {
					file.delete();
				}
			}
		}
		finally {
			if (tempDirectoryPath != null) {
				Files.deleteIfExists(tempDirectoryPath);
			}

			if (tempFilePath != null) {
				Files.deleteIfExists(tempFilePath);
			}
		}
	}

	private void _processDocument(Document document) throws Exception {
		Element element = document.getDocumentElement();

		Map<String, String> propertiesMap = _getPropertiesMap(element);

		String testrayProjectName = propertiesMap.get("testray.project.name");

		_getTestrayProjectId(testrayProjectName);
	}

	private String _toJSON(Map<String, String> map) {
		JSONObject jsonObject = new JSONObject(map);

		return jsonObject.toString();
	}

	private final String _liferayLogin;
	private final String _liferayPassword;
	private final String _liferayURL;
	private final Map<String, Long> _objectEntryIds = new HashMap<>();
	private final String _s3APIKeyPath;
	private final String _s3BucketName;

}