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

package com.liferay.blogs.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.exception.DuplicateBlogsEntryExternalReferenceCodeException;
import com.liferay.blogs.exception.NoSuchEntryException;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.blogs.service.persistence.BlogsEntryPersistence;
import com.liferay.blogs.service.persistence.BlogsEntryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class BlogsEntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.blogs.service"));

	@Before
	public void setUp() {
		_persistence = BlogsEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BlogsEntry> iterator = _blogsEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BlogsEntry blogsEntry = _persistence.create(pk);

		Assert.assertNotNull(blogsEntry);

		Assert.assertEquals(blogsEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		_persistence.remove(newBlogsEntry);

		BlogsEntry existingBlogsEntry = _persistence.fetchByPrimaryKey(
			newBlogsEntry.getPrimaryKey());

		Assert.assertNull(existingBlogsEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBlogsEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BlogsEntry newBlogsEntry = _persistence.create(pk);

		newBlogsEntry.setMvccVersion(RandomTestUtil.nextLong());

		newBlogsEntry.setCtCollectionId(RandomTestUtil.nextLong());

		newBlogsEntry.setUuid(RandomTestUtil.randomString());

		newBlogsEntry.setExternalReferenceCode(RandomTestUtil.randomString());

		newBlogsEntry.setGroupId(RandomTestUtil.nextLong());

		newBlogsEntry.setCompanyId(RandomTestUtil.nextLong());

		newBlogsEntry.setUserId(RandomTestUtil.nextLong());

		newBlogsEntry.setUserName(RandomTestUtil.randomString());

		newBlogsEntry.setCreateDate(RandomTestUtil.nextDate());

		newBlogsEntry.setModifiedDate(RandomTestUtil.nextDate());

		newBlogsEntry.setTitle(RandomTestUtil.randomString());

		newBlogsEntry.setSubtitle(RandomTestUtil.randomString());

		newBlogsEntry.setUrlTitle(RandomTestUtil.randomString());

		newBlogsEntry.setDescription(RandomTestUtil.randomString());

		newBlogsEntry.setContent(RandomTestUtil.randomString());

		newBlogsEntry.setDisplayDate(RandomTestUtil.nextDate());

		newBlogsEntry.setAllowPingbacks(RandomTestUtil.randomBoolean());

		newBlogsEntry.setAllowTrackbacks(RandomTestUtil.randomBoolean());

		newBlogsEntry.setTrackbacks(RandomTestUtil.randomString());

		newBlogsEntry.setCoverImageCaption(RandomTestUtil.randomString());

		newBlogsEntry.setCoverImageFileEntryId(RandomTestUtil.nextLong());

		newBlogsEntry.setCoverImageURL(RandomTestUtil.randomString());

		newBlogsEntry.setSmallImage(RandomTestUtil.randomBoolean());

		newBlogsEntry.setSmallImageFileEntryId(RandomTestUtil.nextLong());

		newBlogsEntry.setSmallImageId(RandomTestUtil.nextLong());

		newBlogsEntry.setSmallImageURL(RandomTestUtil.randomString());

		newBlogsEntry.setLastPublishDate(RandomTestUtil.nextDate());

		newBlogsEntry.setStatus(RandomTestUtil.nextInt());

		newBlogsEntry.setStatusByUserId(RandomTestUtil.nextLong());

		newBlogsEntry.setStatusByUserName(RandomTestUtil.randomString());

		newBlogsEntry.setStatusDate(RandomTestUtil.nextDate());

		_blogsEntries.add(_persistence.update(newBlogsEntry));

		BlogsEntry existingBlogsEntry = _persistence.findByPrimaryKey(
			newBlogsEntry.getPrimaryKey());

		Assert.assertEquals(
			existingBlogsEntry.getMvccVersion(),
			newBlogsEntry.getMvccVersion());
		Assert.assertEquals(
			existingBlogsEntry.getCtCollectionId(),
			newBlogsEntry.getCtCollectionId());
		Assert.assertEquals(
			existingBlogsEntry.getUuid(), newBlogsEntry.getUuid());
		Assert.assertEquals(
			existingBlogsEntry.getExternalReferenceCode(),
			newBlogsEntry.getExternalReferenceCode());
		Assert.assertEquals(
			existingBlogsEntry.getEntryId(), newBlogsEntry.getEntryId());
		Assert.assertEquals(
			existingBlogsEntry.getGroupId(), newBlogsEntry.getGroupId());
		Assert.assertEquals(
			existingBlogsEntry.getCompanyId(), newBlogsEntry.getCompanyId());
		Assert.assertEquals(
			existingBlogsEntry.getUserId(), newBlogsEntry.getUserId());
		Assert.assertEquals(
			existingBlogsEntry.getUserName(), newBlogsEntry.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBlogsEntry.getCreateDate()),
			Time.getShortTimestamp(newBlogsEntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingBlogsEntry.getModifiedDate()),
			Time.getShortTimestamp(newBlogsEntry.getModifiedDate()));
		Assert.assertEquals(
			existingBlogsEntry.getTitle(), newBlogsEntry.getTitle());
		Assert.assertEquals(
			existingBlogsEntry.getSubtitle(), newBlogsEntry.getSubtitle());
		Assert.assertEquals(
			existingBlogsEntry.getUrlTitle(), newBlogsEntry.getUrlTitle());
		Assert.assertEquals(
			existingBlogsEntry.getDescription(),
			newBlogsEntry.getDescription());
		Assert.assertEquals(
			existingBlogsEntry.getContent(), newBlogsEntry.getContent());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBlogsEntry.getDisplayDate()),
			Time.getShortTimestamp(newBlogsEntry.getDisplayDate()));
		Assert.assertEquals(
			existingBlogsEntry.isAllowPingbacks(),
			newBlogsEntry.isAllowPingbacks());
		Assert.assertEquals(
			existingBlogsEntry.isAllowTrackbacks(),
			newBlogsEntry.isAllowTrackbacks());
		Assert.assertEquals(
			existingBlogsEntry.getTrackbacks(), newBlogsEntry.getTrackbacks());
		Assert.assertEquals(
			existingBlogsEntry.getCoverImageCaption(),
			newBlogsEntry.getCoverImageCaption());
		Assert.assertEquals(
			existingBlogsEntry.getCoverImageFileEntryId(),
			newBlogsEntry.getCoverImageFileEntryId());
		Assert.assertEquals(
			existingBlogsEntry.getCoverImageURL(),
			newBlogsEntry.getCoverImageURL());
		Assert.assertEquals(
			existingBlogsEntry.isSmallImage(), newBlogsEntry.isSmallImage());
		Assert.assertEquals(
			existingBlogsEntry.getSmallImageFileEntryId(),
			newBlogsEntry.getSmallImageFileEntryId());
		Assert.assertEquals(
			existingBlogsEntry.getSmallImageId(),
			newBlogsEntry.getSmallImageId());
		Assert.assertEquals(
			existingBlogsEntry.getSmallImageURL(),
			newBlogsEntry.getSmallImageURL());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBlogsEntry.getLastPublishDate()),
			Time.getShortTimestamp(newBlogsEntry.getLastPublishDate()));
		Assert.assertEquals(
			existingBlogsEntry.getStatus(), newBlogsEntry.getStatus());
		Assert.assertEquals(
			existingBlogsEntry.getStatusByUserId(),
			newBlogsEntry.getStatusByUserId());
		Assert.assertEquals(
			existingBlogsEntry.getStatusByUserName(),
			newBlogsEntry.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBlogsEntry.getStatusDate()),
			Time.getShortTimestamp(newBlogsEntry.getStatusDate()));
	}

	@Test(expected = DuplicateBlogsEntryExternalReferenceCodeException.class)
	public void testUpdateWithExistingExternalReferenceCode() throws Exception {
		BlogsEntry blogsEntry = addBlogsEntry();

		BlogsEntry newBlogsEntry = addBlogsEntry();

		newBlogsEntry.setGroupId(blogsEntry.getGroupId());

		newBlogsEntry = _persistence.update(newBlogsEntry);

		Session session = _persistence.getCurrentSession();

		session.evict(newBlogsEntry);

		newBlogsEntry.setExternalReferenceCode(
			blogsEntry.getExternalReferenceCode());

		_persistence.update(newBlogsEntry);
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByG_UT() throws Exception {
		_persistence.countByG_UT(RandomTestUtil.nextLong(), "");

		_persistence.countByG_UT(0L, "null");

		_persistence.countByG_UT(0L, (String)null);
	}

	@Test
	public void testCountByG_LtD() throws Exception {
		_persistence.countByG_LtD(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate());

		_persistence.countByG_LtD(0L, RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByG_S() throws Exception {
		_persistence.countByG_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_S(0L, 0);
	}

	@Test
	public void testCountByG_NotS() throws Exception {
		_persistence.countByG_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_NotS(0L, 0);
	}

	@Test
	public void testCountByC_U() throws Exception {
		_persistence.countByC_U(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_U(0L, 0L);
	}

	@Test
	public void testCountByC_LtD() throws Exception {
		_persistence.countByC_LtD(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate());

		_persistence.countByC_LtD(0L, RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByC_S() throws Exception {
		_persistence.countByC_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByC_S(0L, 0);
	}

	@Test
	public void testCountByC_NotS() throws Exception {
		_persistence.countByC_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByC_NotS(0L, 0);
	}

	@Test
	public void testCountByLtD_S() throws Exception {
		_persistence.countByLtD_S(
			RandomTestUtil.nextDate(), RandomTestUtil.nextInt());

		_persistence.countByLtD_S(RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByG_U_LtD() throws Exception {
		_persistence.countByG_U_LtD(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextDate());

		_persistence.countByG_U_LtD(0L, 0L, RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByG_U_S() throws Exception {
		_persistence.countByG_U_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_U_S(0L, 0L, 0);
	}

	@Test
	public void testCountByG_U_SArrayable() throws Exception {
		_persistence.countByG_U_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			new int[] {RandomTestUtil.nextInt(), 0});
	}

	@Test
	public void testCountByG_U_NotS() throws Exception {
		_persistence.countByG_U_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_U_NotS(0L, 0L, 0);
	}

	@Test
	public void testCountByG_D_S() throws Exception {
		_persistence.countByG_D_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByG_D_S(0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByG_GtD_S() throws Exception {
		_persistence.countByG_GtD_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByG_GtD_S(0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByG_LtD_S() throws Exception {
		_persistence.countByG_LtD_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByG_LtD_S(0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByG_LtD_NotS() throws Exception {
		_persistence.countByG_LtD_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByG_LtD_NotS(0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByC_U_S() throws Exception {
		_persistence.countByC_U_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByC_U_S(0L, 0L, 0);
	}

	@Test
	public void testCountByC_U_NotS() throws Exception {
		_persistence.countByC_U_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByC_U_NotS(0L, 0L, 0);
	}

	@Test
	public void testCountByC_LtD_S() throws Exception {
		_persistence.countByC_LtD_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByC_LtD_S(0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByC_LtD_NotS() throws Exception {
		_persistence.countByC_LtD_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByC_LtD_NotS(0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByG_U_LtD_S() throws Exception {
		_persistence.countByG_U_LtD_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextDate(), RandomTestUtil.nextInt());

		_persistence.countByG_U_LtD_S(0L, 0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByG_U_LtD_NotS() throws Exception {
		_persistence.countByG_U_LtD_NotS(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextDate(), RandomTestUtil.nextInt());

		_persistence.countByG_U_LtD_NotS(0L, 0L, RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testCountByERC_G() throws Exception {
		_persistence.countByERC_G("", RandomTestUtil.nextLong());

		_persistence.countByERC_G("null", 0L);

		_persistence.countByERC_G((String)null, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		BlogsEntry existingBlogsEntry = _persistence.findByPrimaryKey(
			newBlogsEntry.getPrimaryKey());

		Assert.assertEquals(existingBlogsEntry, newBlogsEntry);
	}

	@Test(expected = NoSuchEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		_persistence.filterFindByGroupId(
			0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<BlogsEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"BlogsEntry", "mvccVersion", true, "ctCollectionId", true, "uuid",
			true, "externalReferenceCode", true, "entryId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "title", true, "subtitle",
			true, "urlTitle", true, "description", true, "displayDate", true,
			"allowPingbacks", true, "allowTrackbacks", true,
			"coverImageCaption", true, "coverImageFileEntryId", true,
			"coverImageURL", true, "smallImage", true, "smallImageFileEntryId",
			true, "smallImageId", true, "smallImageURL", true,
			"lastPublishDate", true, "status", true, "statusByUserId", true,
			"statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		BlogsEntry existingBlogsEntry = _persistence.fetchByPrimaryKey(
			newBlogsEntry.getPrimaryKey());

		Assert.assertEquals(existingBlogsEntry, newBlogsEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BlogsEntry missingBlogsEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBlogsEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		BlogsEntry newBlogsEntry1 = addBlogsEntry();
		BlogsEntry newBlogsEntry2 = addBlogsEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBlogsEntry1.getPrimaryKey());
		primaryKeys.add(newBlogsEntry2.getPrimaryKey());

		Map<Serializable, BlogsEntry> blogsEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, blogsEntries.size());
		Assert.assertEquals(
			newBlogsEntry1, blogsEntries.get(newBlogsEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newBlogsEntry2, blogsEntries.get(newBlogsEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BlogsEntry> blogsEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(blogsEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		BlogsEntry newBlogsEntry = addBlogsEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBlogsEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BlogsEntry> blogsEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, blogsEntries.size());
		Assert.assertEquals(
			newBlogsEntry, blogsEntries.get(newBlogsEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BlogsEntry> blogsEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(blogsEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBlogsEntry.getPrimaryKey());

		Map<Serializable, BlogsEntry> blogsEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, blogsEntries.size());
		Assert.assertEquals(
			newBlogsEntry, blogsEntries.get(newBlogsEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			BlogsEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<BlogsEntry>() {

				@Override
				public void performAction(BlogsEntry blogsEntry) {
					Assert.assertNotNull(blogsEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BlogsEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("entryId", newBlogsEntry.getEntryId()));

		List<BlogsEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		BlogsEntry existingBlogsEntry = result.get(0);

		Assert.assertEquals(existingBlogsEntry, newBlogsEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BlogsEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("entryId", RandomTestUtil.nextLong()));

		List<BlogsEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BlogsEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		Object newEntryId = newBlogsEntry.getEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("entryId", new Object[] {newEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEntryId = result.get(0);

		Assert.assertEquals(existingEntryId, newEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BlogsEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"entryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		BlogsEntry newBlogsEntry = addBlogsEntry();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newBlogsEntry.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		BlogsEntry newBlogsEntry = addBlogsEntry();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BlogsEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("entryId", newBlogsEntry.getEntryId()));

		List<BlogsEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(BlogsEntry blogsEntry) {
		Assert.assertEquals(
			blogsEntry.getUuid(),
			ReflectionTestUtil.invoke(
				blogsEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(blogsEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				blogsEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));

		Assert.assertEquals(
			Long.valueOf(blogsEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				blogsEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
		Assert.assertEquals(
			blogsEntry.getUrlTitle(),
			ReflectionTestUtil.invoke(
				blogsEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "urlTitle"));

		Assert.assertEquals(
			blogsEntry.getExternalReferenceCode(),
			ReflectionTestUtil.invoke(
				blogsEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "externalReferenceCode"));
		Assert.assertEquals(
			Long.valueOf(blogsEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				blogsEntry, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected BlogsEntry addBlogsEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BlogsEntry blogsEntry = _persistence.create(pk);

		blogsEntry.setMvccVersion(RandomTestUtil.nextLong());

		blogsEntry.setCtCollectionId(RandomTestUtil.nextLong());

		blogsEntry.setUuid(RandomTestUtil.randomString());

		blogsEntry.setExternalReferenceCode(RandomTestUtil.randomString());

		blogsEntry.setGroupId(RandomTestUtil.nextLong());

		blogsEntry.setCompanyId(RandomTestUtil.nextLong());

		blogsEntry.setUserId(RandomTestUtil.nextLong());

		blogsEntry.setUserName(RandomTestUtil.randomString());

		blogsEntry.setCreateDate(RandomTestUtil.nextDate());

		blogsEntry.setModifiedDate(RandomTestUtil.nextDate());

		blogsEntry.setTitle(RandomTestUtil.randomString());

		blogsEntry.setSubtitle(RandomTestUtil.randomString());

		blogsEntry.setUrlTitle(RandomTestUtil.randomString());

		blogsEntry.setDescription(RandomTestUtil.randomString());

		blogsEntry.setContent(RandomTestUtil.randomString());

		blogsEntry.setDisplayDate(RandomTestUtil.nextDate());

		blogsEntry.setAllowPingbacks(RandomTestUtil.randomBoolean());

		blogsEntry.setAllowTrackbacks(RandomTestUtil.randomBoolean());

		blogsEntry.setTrackbacks(RandomTestUtil.randomString());

		blogsEntry.setCoverImageCaption(RandomTestUtil.randomString());

		blogsEntry.setCoverImageFileEntryId(RandomTestUtil.nextLong());

		blogsEntry.setCoverImageURL(RandomTestUtil.randomString());

		blogsEntry.setSmallImage(RandomTestUtil.randomBoolean());

		blogsEntry.setSmallImageFileEntryId(RandomTestUtil.nextLong());

		blogsEntry.setSmallImageId(RandomTestUtil.nextLong());

		blogsEntry.setSmallImageURL(RandomTestUtil.randomString());

		blogsEntry.setLastPublishDate(RandomTestUtil.nextDate());

		blogsEntry.setStatus(RandomTestUtil.nextInt());

		blogsEntry.setStatusByUserId(RandomTestUtil.nextLong());

		blogsEntry.setStatusByUserName(RandomTestUtil.randomString());

		blogsEntry.setStatusDate(RandomTestUtil.nextDate());

		_blogsEntries.add(_persistence.update(blogsEntry));

		return blogsEntry;
	}

	private List<BlogsEntry> _blogsEntries = new ArrayList<BlogsEntry>();
	private BlogsEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}