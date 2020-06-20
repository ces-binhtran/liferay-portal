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

package com.liferay.portal.file.install.internal;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.file.install.internal.properties.InterpolationUtil;
import com.liferay.portal.file.install.listener.ArtifactInstaller;
import com.liferay.portal.file.install.listener.ArtifactListener;
import com.liferay.portal.file.install.listener.ArtifactTransformer;
import com.liferay.portal.file.install.listener.ArtifactUrlTransformer;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.FrameworkWiring;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.url.URLStreamHandlerService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Matthew Tambara
 */
public class FileInstall
	implements BundleActivator,
			   ServiceTrackerCustomizer<ArtifactListener, ArtifactListener> {

	public static void refresh(Bundle systemBundle, Collection<Bundle> bundles)
		throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(1);

		FrameworkWiring frameworkWiring = systemBundle.adapt(
			FrameworkWiring.class);

		frameworkWiring.refreshBundles(
			bundles, event -> countDownLatch.countDown());

		countDownLatch.await();
	}

	@Override
	public ArtifactListener addingService(
		ServiceReference<ArtifactListener> serviceReference) {

		ArtifactListener listener = _bundleContext.getService(serviceReference);

		_addListener(serviceReference, listener);

		return listener;
	}

	public void deleted(String pid) {
		DirectoryWatcher directoryWatcher = null;

		synchronized (_watchers) {
			directoryWatcher = _watchers.remove(pid);
		}

		if (directoryWatcher != null) {
			directoryWatcher.close();
		}
	}

	public List<ArtifactListener> getListeners() {
		synchronized (_listeners) {
			List<ArtifactListener> artifactListeners = new ArrayList<>(
				_listeners.values());

			Collections.reverse(artifactListeners);

			artifactListeners.add(_bundleTransformer);

			return artifactListeners;
		}
	}

	public Lock getReadLock() {
		return _readLock;
	}

	@Override
	public void modifiedService(
		ServiceReference<ArtifactListener> serviceReference,
		ArtifactListener artifactListener) {

		_removeListener(serviceReference, artifactListener);

		_addListener(serviceReference, artifactListener);
	}

	@Override
	public void removedService(
		ServiceReference<ArtifactListener> serviceReference,
		ArtifactListener artifactListener) {

		_removeListener(serviceReference, artifactListener);
	}

	public void start(BundleContext bundleContext) throws Exception {
		_bundleContext = bundleContext;

		_writeLock.lock();

		try {
			Dictionary<String, Object> properties = new HashMapDictionary<>();

			properties.put("url.handler.protocol", JarDirUrlHandler.PROTOCOL);

			_urlHandlerRegistration = bundleContext.registerService(
				URLStreamHandlerService.class.getName(), new JarDirUrlHandler(),
				properties);

			StringBundler sb = new StringBundler(15);

			sb.append("(|(");
			sb.append(Constants.OBJECTCLASS);
			sb.append(StringPool.EQUAL);
			sb.append(ArtifactInstaller.class.getName());
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(Constants.OBJECTCLASS);
			sb.append(StringPool.EQUAL);
			sb.append(ArtifactTransformer.class.getName());
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(Constants.OBJECTCLASS);
			sb.append(StringPool.EQUAL);
			sb.append(ArtifactUrlTransformer.class.getName());
			sb.append("))");

			_listenersTracker = new ServiceTracker(
				bundleContext, FrameworkUtil.createFilter(sb.toString()), this);

			_listenersTracker.open();

			try {
				_cmSupport = new ConfigAdminSupport(bundleContext, this);
			}
			catch (NoClassDefFoundError noClassDefFoundError) {
				Util.log(
					bundleContext, Util.Logger.LOG_DEBUG,
					"ConfigAdmin is not available, some features will be " +
						"disabled",
					noClassDefFoundError);
			}

			Map<String, String> map = new HashMap<>();

			_set(map, DirectoryWatcher.POLL);
			_set(map, DirectoryWatcher.DIR);
			_set(map, DirectoryWatcher.LOG_LEVEL);
			_set(map, DirectoryWatcher.LOG_DEFAULT);
			_set(map, DirectoryWatcher.FILTER);
			_set(map, DirectoryWatcher.TMPDIR);
			_set(map, DirectoryWatcher.START_NEW_BUNDLES);
			_set(map, DirectoryWatcher.USE_START_TRANSIENT);
			_set(map, DirectoryWatcher.USE_START_ACTIVATION_POLICY);
			_set(map, DirectoryWatcher.NO_INITIAL_DELAY);
			_set(map, DirectoryWatcher.DISABLE_CONFIG_SAVE);
			_set(map, DirectoryWatcher.ENABLE_CONFIG_SAVE);
			_set(map, DirectoryWatcher.CONFIG_ENCODING);
			_set(map, DirectoryWatcher.START_LEVEL);
			_set(map, DirectoryWatcher.ACTIVE_LEVEL);
			_set(map, DirectoryWatcher.UPDATE_WITH_LISTENERS);
			_set(map, DirectoryWatcher.OPTIONAL_SCOPE);
			_set(map, DirectoryWatcher.FRAGMENT_SCOPE);
			_set(map, DirectoryWatcher.SUBDIR_MODE);
			_set(map, DirectoryWatcher.WEB_START_LEVEL);

			String dirs = map.get(DirectoryWatcher.DIR);

			if ((dirs != null) && (dirs.indexOf(',') != -1)) {
				int index = 0;

				for (String dir : StringUtil.split(dirs, CharPool.COMMA)) {
					map.put(DirectoryWatcher.DIR, dir);

					String name = "initial";

					if (index > 0) {
						name = name + index;
					}

					updated(name, new HashMap<>(map));

					index++;
				}
			}
			else {
				updated("initial", map);
			}
		}
		finally {
			_writeLock.unlock();
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		_writeLock.lock();

		try {
			_urlHandlerRegistration.unregister();

			List<DirectoryWatcher> watchers = new ArrayList<>();

			synchronized (_watchers) {
				watchers.addAll(_watchers.values());

				_watchers.clear();
			}

			for (DirectoryWatcher directoryWatcher : watchers) {
				try {
					directoryWatcher.close();
				}
				catch (Exception exception) {
				}
			}

			if (_listenersTracker != null) {
				_listenersTracker.close();
			}

			if (_cmSupport != null) {
				_cmSupport.run();
			}
		}
		finally {
			_stopped = true;

			_writeLock.unlock();
		}
	}

	public void updateChecksum(File file) {
		List<DirectoryWatcher> toUpdate = new ArrayList<>();

		synchronized (_watchers) {
			toUpdate.addAll(_watchers.values());
		}

		for (DirectoryWatcher directoryWatcher : toUpdate) {
			Scanner scanner = directoryWatcher.scanner;

			scanner.updateChecksum(file);
		}
	}

	public void updated(String pid, Map<String, String> properties) {
		InterpolationUtil.performSubstitution(properties, _bundleContext);

		DirectoryWatcher directoryWatcher = null;

		synchronized (_watchers) {
			directoryWatcher = _watchers.get(pid);

			if ((directoryWatcher != null) &&
				Objects.equals(directoryWatcher.getProperties(), properties)) {

				return;
			}
		}

		if (directoryWatcher != null) {
			directoryWatcher.close();
		}

		directoryWatcher = new DirectoryWatcher(
			this, properties, _bundleContext);

		directoryWatcher.setDaemon(true);

		synchronized (_watchers) {
			_watchers.put(pid, directoryWatcher);
		}

		directoryWatcher.start();
	}

	private void _addListener(
		ServiceReference serviceReference, ArtifactListener artifactListener) {

		synchronized (_listeners) {
			_listeners.put(serviceReference, artifactListener);
		}

		Bundle bundle = serviceReference.getBundle();

		long currentStamp = bundle.getLastModified();

		List<DirectoryWatcher> toNotify = new ArrayList<>();

		synchronized (_watchers) {
			toNotify.addAll(_watchers.values());
		}

		for (DirectoryWatcher directoryWatcher : toNotify) {
			directoryWatcher.addListener(artifactListener, currentStamp);
		}
	}

	private void _removeListener(
		ServiceReference serviceReference, ArtifactListener artifactListener) {

		synchronized (_listeners) {
			_listeners.remove(serviceReference);
		}

		List<DirectoryWatcher> toNotify = new ArrayList<>();

		synchronized (_watchers) {
			toNotify.addAll(_watchers.values());
		}

		for (DirectoryWatcher directoryWatcher : toNotify) {
			directoryWatcher.removeListener(artifactListener);
		}
	}

	private void _set(Map<String, String> map, String key) {
		String property = _bundleContext.getProperty(key);

		if (property == null) {
			key = StringUtil.toUpperCase(key);

			property = System.getProperty(
				StringUtil.replace(key, CharPool.PERIOD, CharPool.UNDERLINE));

			if (property == null) {
				return;
			}
		}

		map.put(key, property);
	}

	private BundleContext _bundleContext;
	private final BundleTransformer _bundleTransformer =
		new BundleTransformer();
	private Runnable _cmSupport;
	private final Map<ServiceReference, ArtifactListener> _listeners =
		new TreeMap<>();
	private ServiceTracker _listenersTracker;
	private final ReadWriteLock _lock = new ReentrantReadWriteLock();
	private final Lock _readLock = _lock.readLock();
	private volatile boolean _stopped;
	private ServiceRegistration<?> _urlHandlerRegistration;
	private final Map<String, DirectoryWatcher> _watchers = new HashMap<>();
	private final Lock _writeLock = _lock.writeLock();

	private class ConfigAdminSupport implements Runnable {

		@Override
		public void run() {
			_tracker.close();
			_serviceRegistration.unregister();
		}

		private ConfigAdminSupport(
			BundleContext bundleContext, FileInstall fileInstall) {

			_tracker = new Tracker(bundleContext, fileInstall);

			Dictionary<String, Object> properties = new HashMapDictionary<>();

			properties.put(Constants.SERVICE_PID, _tracker.getName());

			_serviceRegistration = bundleContext.registerService(
				ManagedServiceFactory.class.getName(), _tracker, properties);

			_tracker.open();
		}

		private final ServiceRegistration<?> _serviceRegistration;
		private final Tracker _tracker;

		private class Tracker
			extends ServiceTracker<ConfigurationAdmin, ConfigurationAdmin>
			implements ManagedServiceFactory {

			@Override
			public ConfigurationAdmin addingService(
				ServiceReference<ConfigurationAdmin> serviceReference) {

				_writeLock.lock();

				try {
					if (_stopped) {
						return null;
					}

					ConfigurationAdmin configurationAdmin = super.addingService(
						serviceReference);

					long id = (Long)serviceReference.getProperty(
						Constants.SERVICE_ID);

					ConfigInstaller configInstaller = new ConfigInstaller(
						context, configurationAdmin, _fileInstall);

					configInstaller.init();

					_configInstallers.put(id, configInstaller);

					return configurationAdmin;
				}
				finally {
					_writeLock.unlock();
				}
			}

			@Override
			public void deleted(String string) {
				_configs.remove(string);

				_fileInstall.deleted(string);
			}

			@Override
			public String getName() {
				return "com.liferay.portal.file.install";
			}

			@Override
			public void removedService(
				ServiceReference<ConfigurationAdmin> serviceReference,
				ConfigurationAdmin configurationAdmin) {

				_writeLock.lock();

				try {
					if (_stopped) {
						return;
					}

					Iterator<String> iterator = _configs.iterator();

					while (iterator.hasNext()) {
						String string = iterator.next();

						_fileInstall.deleted(string);

						iterator.remove();
					}

					long id = (Long)serviceReference.getProperty(
						Constants.SERVICE_ID);

					ConfigInstaller configInstaller = _configInstallers.remove(
						id);

					if (configInstaller != null) {
						configInstaller.destroy();
					}

					super.removedService(serviceReference, configurationAdmin);
				}
				finally {
					_writeLock.unlock();
				}
			}

			@Override
			public void updated(String string, Dictionary<String, ?> dictionary)
				throws ConfigurationException {

				_configs.add(string);

				Map<String, String> props = new HashMap<>();

				Enumeration<String> enumeration = dictionary.keys();

				while (enumeration.hasMoreElements()) {
					String key = enumeration.nextElement();

					Object value = dictionary.get(key);

					props.put(key, value.toString());
				}

				_fileInstall.updated(string, props);
			}

			private Tracker(
				BundleContext bundleContext, FileInstall fileInstall) {

				super(bundleContext, ConfigurationAdmin.class.getName(), null);

				_fileInstall = fileInstall;
			}

			private final Map<Long, ConfigInstaller> _configInstallers =
				new HashMap<>();
			private final Set<String> _configs = Collections.synchronizedSet(
				new HashSet<>());
			private final FileInstall _fileInstall;

		}

	}

}