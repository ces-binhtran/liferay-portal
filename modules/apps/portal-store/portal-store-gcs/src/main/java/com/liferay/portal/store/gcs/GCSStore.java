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

package com.liferay.portal.store.gcs;

import com.google.api.gax.paging.Page;
import com.google.api.gax.retrying.RetrySettings;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.document.library.kernel.util.comparator.VersionNumberComparator;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.store.gcs.configuration.GCSStoreConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import org.threeten.bp.Duration;

/**
 * @author Shanon Mathai
 * @author Alicia García
 */
@Component(
	configurationPid = "com.liferay.portal.store.gcs.configuration.GCSStoreConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = "store.type=com.liferay.portal.store.gcs.GCSStore",
	service = Store.class
)
public class GCSStore implements Store {

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream inputStream)
		throws PortalException {

		if (hasFile(companyId, repositoryId, fileName, versionLabel)) {
			deleteFile(companyId, repositoryId, fileName, versionLabel);
		}

		String fileVersionKey = _keyTransformer.getFileVersionKey(
			companyId, repositoryId, fileName, versionLabel);

		BlobInfo blobInfo = BlobInfo.newBuilder(
			_getBucketInfo(), fileVersionKey
		).build();

		try (WriteChannel writer = _getWriter(blobInfo)) {
			_writeInputStream(inputStream, writer);
		}
		catch (IOException ioException) {
			throw new PortalException(
				"Unable to write out to buffer", ioException);
		}
	}

	@Override
	public void deleteDirectory(
		long companyId, long repositoryId, String dirName) {

		String path = _keyTransformer.getDirectoryKey(
			companyId, repositoryId, dirName);

		Page<Blob> blobPage = _gcsStore.list(
			_gcsStoreConfiguration.bucketName(),
			Storage.BlobListOption.prefix(path));

		Iterable<Blob> blobs = blobPage.iterateAll();

		blobs.forEach(this::_deleteBlob);
	}

	@Override
	public void deleteFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		_deleteFile(
			_getHeadVersionLabel(
				companyId, repositoryId, fileName, versionLabel));
	}

	@Override
	public InputStream getFileAsStream(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		String pathName = _getHeadVersionLabel(
			companyId, repositoryId, fileName, versionLabel);

		Blob blob = _gcsStore.get(
			BlobId.of(_gcsStoreConfiguration.bucketName(), pathName));

		return Channels.newInputStream(_getReader(blob));
	}

	@Override
	public String[] getFileNames(
		long companyId, long repositoryId, String dirName) {

		String path = null;

		if (Validator.isNull(dirName) ||
			dirName.equals(StringPool.FORWARD_SLASH)) {

			path = _keyTransformer.getRepositoryKey(companyId, repositoryId);
		}
		else {
			path = _keyTransformer.getDirectoryKey(
				companyId, repositoryId, dirName);
		}

		Bucket bucket = _gcsStore.get(_gcsStoreConfiguration.bucketName());

		Page<Blob> blobPage = bucket.list(Storage.BlobListOption.prefix(path));

		Iterable<Blob> blobs = blobPage.iterateAll();

		Stream<Blob> blobStream = StreamSupport.stream(
			blobs.spliterator(), false);

		return blobStream.map(
			BlobInfo::getName
		).toArray(
			String[]::new
		);
	}

	@Override
	public long getFileSize(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException {

		String pathName = _getHeadVersionLabel(
			companyId, repositoryId, fileName, versionLabel);

		Blob blob = _gcsStore.get(
			BlobId.of(_gcsStoreConfiguration.bucketName(), pathName));

		if (blob == null) {
			throw new PortalException("No such file store entry: " + pathName);
		}

		return blob.getSize();
	}

	@Override
	public String[] getFileVersions(
		long companyId, long repositoryId, String fileName) {

		return getFileNames(
			companyId, repositoryId,
			_keyTransformer.getFileKey(companyId, repositoryId, fileName));
	}

	@Override
	public boolean hasFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		String path = _keyTransformer.getFileVersionKey(
			companyId, repositoryId, fileName, versionLabel);

		Page<Blob> blobPage = _gcsStore.list(
			_gcsStoreConfiguration.bucketName(),
			Storage.BlobListOption.pageSize(1),
			Storage.BlobListOption.prefix(path));

		Iterable<Blob> filesFoundIterable = blobPage.getValues();

		Iterator<Blob> filesFoundIterator = filesFoundIterable.iterator();

		return filesFoundIterator.hasNext();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		try {
			_gcsStoreConfiguration = ConfigurableUtil.createConfigurable(
				GCSStoreConfiguration.class, properties);

			_gcsStore = null;

			_setupEncryptedCommunication();

			_setGcsStore();
		}
		catch (PortalException portalException) {
			throw new IllegalStateException(
				"Unable to initialize GCS store", portalException);
		}
	}

	private RetrySettings _buildRetrySettings(
		int maxAttempts, int initialRetryDelay, int maxRetryDelay,
		double retryDelayMultiplier, int maxRpcTimeout, int initRpcTimout,
		double rpcTimeoutMultiplier, boolean jittered) {

		RetrySettings.Builder builder = RetrySettings.newBuilder();

		builder.setInitialRetryDelay(Duration.ofMillis(initialRetryDelay));
		builder.setInitialRpcTimeout(Duration.ofMillis(initRpcTimout));
		builder.setJittered(jittered);
		builder.setMaxAttempts(maxAttempts);
		builder.setMaxRetryDelay(Duration.ofMillis(maxRetryDelay));
		builder.setMaxRpcTimeout(Duration.ofMillis(maxRpcTimeout));
		builder.setRetryDelayMultiplier(retryDelayMultiplier);
		builder.setRpcTimeoutMultiplier(rpcTimeoutMultiplier);

		return builder.build();
	}

	private StorageOptions _buildStorage(
		RetrySettings retrySettings, GoogleCredentials googleCredentials) {

		StorageOptions.Builder builder = StorageOptions.newBuilder();

		builder.setCredentials(googleCredentials);
		builder.setRetrySettings(retrySettings);

		return builder.build();
	}

	private void _deleteBlob(Blob blob) {
		if (_blobDecryptSourceOption == null) {
			blob.delete();
		}

		blob.delete(_blobDecryptSourceOption);
	}

	private void _deleteFile(String filePath) {
		boolean deleted = _gcsStore.delete(
			BlobId.of(_gcsStoreConfiguration.bucketName(), filePath));

		if (!deleted && _log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Unable to delete \"", filePath, "\" from file store"));
		}
	}

	private BucketInfo _getBucketInfo() {
		if (_bucketInfo == null) {
			_bucketInfo = BucketInfo.newBuilder(
				_gcsStoreConfiguration.bucketName()
			).build();
		}

		return _bucketInfo;
	}

	private String _getHeadVersionLabel(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		if (Validator.isNotNull(versionLabel)) {
			return _keyTransformer.getFileVersionKey(
				companyId, repositoryId, fileName, versionLabel);
		}

		String key = _keyTransformer.getFileKey(
			companyId, repositoryId, fileName);

		String[] names = getFileNames(companyId, repositoryId, key);

		if ((names == null) || (names.length == 0)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to determine available versions for: ", key,
						" using default version: ", VERSION_DEFAULT));
			}

			return _keyTransformer.getFileVersionKey(
				companyId, repositoryId, fileName, VERSION_DEFAULT);
		}

		List<String> fileNames = Arrays.asList(names);

		fileNames.sort(new VersionNumberComparator());

		return fileNames.get(fileNames.size() - 1);
	}

	private ReadChannel _getReader(Blob blob) {
		if (_blobDecryptSourceOption == null) {
			return blob.reader();
		}

		return blob.reader(_blobDecryptSourceOption);
	}

	private WriteChannel _getWriter(BlobInfo blobInfo) {
		if (_blobEncryptWriteOption == null) {
			return _gcsStore.writer(blobInfo);
		}

		return _gcsStore.writer(blobInfo, _blobEncryptWriteOption);
	}

	private void _setCredentials() throws PortalException {
		try (InputStream inputStream = new FileInputStream(
				_gcsStoreConfiguration.authFileLocation())) {

			_googleCredentials = ServiceAccountCredentials.fromStream(
				inputStream);
		}
		catch (IOException ioException) {
			throw new PortalException(
				"Unable to authenticate with authentication file", ioException);
		}
	}

	private void _setGcsStore() throws PortalException {
		if (_gcsStore == null) {
			_setCredentials();

			RetrySettings retrySettings = _buildRetrySettings(
				_gcsStoreConfiguration.maxRetryAttempts(),
				_gcsStoreConfiguration.initialRetryDelay(),
				_gcsStoreConfiguration.maxRetryDelay(),
				_gcsStoreConfiguration.retryDelayMultiplier(),
				_gcsStoreConfiguration.maxRpcTimeout(),
				_gcsStoreConfiguration.initialRpcTimeout(),
				_gcsStoreConfiguration.rpcTimeoutMultiplier(),
				_gcsStoreConfiguration.retryJitter());

			StorageOptions storageOptions = _buildStorage(
				retrySettings, _googleCredentials);

			_gcsStore = storageOptions.getService();
		}
	}

	private void _setupEncryptedCommunication() {
		String keyValue = PropsUtil.get(_DL_STORE_GCS_AES_256_KEY);

		if ((keyValue == null) || keyValue.equals(StringPool.BLANK)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Property \"dl.store.gcs.aes256.key\" should be set to " +
						"encrypt stored files. Using default storage. The " +
							"key must be AES 256bit key, encoded in Base64.");
			}

			_blobDecryptSourceOption = null;

			_blobEncryptWriteOption = null;
		}
		else {
			_blobDecryptSourceOption = Blob.BlobSourceOption.decryptionKey(
				keyValue);

			_blobEncryptWriteOption = Storage.BlobWriteOption.encryptionKey(
				keyValue);
		}
	}

	private void _writeInputStream(InputStream inputStream, WriteChannel writer)
		throws IOException, PortalException {

		byte[] buffer = new byte[_WRITE_BUFFER_SIZE];
		int limit = -1;

		while ((limit = inputStream.read(buffer)) >= 0) {
			try {
				writer.write(ByteBuffer.wrap(buffer, 0, limit));
			}
			catch (IOException ioException) {
				throw new PortalException(ioException);
			}
		}
	}

	private static final String _DL_STORE_GCS_AES_256_KEY =
		"dl.store.gcs.aes256.key";

	private static final int _WRITE_BUFFER_SIZE = 1024;

	private static final Log _log = LogFactoryUtil.getLog(GCSStore.class);

	private Blob.BlobSourceOption _blobDecryptSourceOption;
	private Storage.BlobWriteOption _blobEncryptWriteOption;
	private BucketInfo _bucketInfo;
	private Storage _gcsStore;
	private GCSStoreConfiguration _gcsStoreConfiguration;
	private GoogleCredentials _googleCredentials;
	private final GCSKeyTransformer _keyTransformer = new GCSKeyTransformer();

}