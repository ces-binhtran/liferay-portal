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

package com.liferay.commerce.product.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPDefinitionPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CPDefinitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPDefinitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPDefinition> iterator = _cpDefinitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinition cpDefinition = _persistence.create(pk);

		Assert.assertNotNull(cpDefinition);

		Assert.assertEquals(cpDefinition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		_persistence.remove(newCPDefinition);

		CPDefinition existingCPDefinition = _persistence.fetchByPrimaryKey(newCPDefinition.getPrimaryKey());

		Assert.assertNull(existingCPDefinition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPDefinition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinition newCPDefinition = _persistence.create(pk);

		newCPDefinition.setUuid(RandomTestUtil.randomString());

		newCPDefinition.setGroupId(RandomTestUtil.nextLong());

		newCPDefinition.setCompanyId(RandomTestUtil.nextLong());

		newCPDefinition.setUserId(RandomTestUtil.nextLong());

		newCPDefinition.setUserName(RandomTestUtil.randomString());

		newCPDefinition.setCreateDate(RandomTestUtil.nextDate());

		newCPDefinition.setModifiedDate(RandomTestUtil.nextDate());

		newCPDefinition.setBaseSKU(RandomTestUtil.randomString());

		newCPDefinition.setProductTypeName(RandomTestUtil.randomString());

		newCPDefinition.setGtin(RandomTestUtil.randomString());

		newCPDefinition.setManufacturerPartNumber(RandomTestUtil.randomString());

		newCPDefinition.setAvailableIndividually(RandomTestUtil.randomBoolean());

		newCPDefinition.setMinCartQuantity(RandomTestUtil.nextInt());

		newCPDefinition.setMaxCartQuantity(RandomTestUtil.nextInt());

		newCPDefinition.setAllowedCartQuantities(RandomTestUtil.randomString());

		newCPDefinition.setMultipleCartQuantity(RandomTestUtil.nextInt());

		newCPDefinition.setDDMStructureKey(RandomTestUtil.randomString());

		newCPDefinition.setDisplayDate(RandomTestUtil.nextDate());

		newCPDefinition.setExpirationDate(RandomTestUtil.nextDate());

		newCPDefinition.setLastPublishDate(RandomTestUtil.nextDate());

		newCPDefinition.setStatus(RandomTestUtil.nextInt());

		newCPDefinition.setStatusByUserId(RandomTestUtil.nextLong());

		newCPDefinition.setStatusByUserName(RandomTestUtil.randomString());

		newCPDefinition.setStatusDate(RandomTestUtil.nextDate());

		newCPDefinition.setDefaultLanguageId(RandomTestUtil.randomString());

		_cpDefinitions.add(_persistence.update(newCPDefinition));

		CPDefinition existingCPDefinition = _persistence.findByPrimaryKey(newCPDefinition.getPrimaryKey());

		Assert.assertEquals(existingCPDefinition.getUuid(),
			newCPDefinition.getUuid());
		Assert.assertEquals(existingCPDefinition.getCPDefinitionId(),
			newCPDefinition.getCPDefinitionId());
		Assert.assertEquals(existingCPDefinition.getGroupId(),
			newCPDefinition.getGroupId());
		Assert.assertEquals(existingCPDefinition.getCompanyId(),
			newCPDefinition.getCompanyId());
		Assert.assertEquals(existingCPDefinition.getUserId(),
			newCPDefinition.getUserId());
		Assert.assertEquals(existingCPDefinition.getUserName(),
			newCPDefinition.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinition.getCreateDate()),
			Time.getShortTimestamp(newCPDefinition.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinition.getModifiedDate()),
			Time.getShortTimestamp(newCPDefinition.getModifiedDate()));
		Assert.assertEquals(existingCPDefinition.getBaseSKU(),
			newCPDefinition.getBaseSKU());
		Assert.assertEquals(existingCPDefinition.getProductTypeName(),
			newCPDefinition.getProductTypeName());
		Assert.assertEquals(existingCPDefinition.getGtin(),
			newCPDefinition.getGtin());
		Assert.assertEquals(existingCPDefinition.getManufacturerPartNumber(),
			newCPDefinition.getManufacturerPartNumber());
		Assert.assertEquals(existingCPDefinition.getAvailableIndividually(),
			newCPDefinition.getAvailableIndividually());
		Assert.assertEquals(existingCPDefinition.getMinCartQuantity(),
			newCPDefinition.getMinCartQuantity());
		Assert.assertEquals(existingCPDefinition.getMaxCartQuantity(),
			newCPDefinition.getMaxCartQuantity());
		Assert.assertEquals(existingCPDefinition.getAllowedCartQuantities(),
			newCPDefinition.getAllowedCartQuantities());
		Assert.assertEquals(existingCPDefinition.getMultipleCartQuantity(),
			newCPDefinition.getMultipleCartQuantity());
		Assert.assertEquals(existingCPDefinition.getDDMStructureKey(),
			newCPDefinition.getDDMStructureKey());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinition.getDisplayDate()),
			Time.getShortTimestamp(newCPDefinition.getDisplayDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinition.getExpirationDate()),
			Time.getShortTimestamp(newCPDefinition.getExpirationDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinition.getLastPublishDate()),
			Time.getShortTimestamp(newCPDefinition.getLastPublishDate()));
		Assert.assertEquals(existingCPDefinition.getStatus(),
			newCPDefinition.getStatus());
		Assert.assertEquals(existingCPDefinition.getStatusByUserId(),
			newCPDefinition.getStatusByUserId());
		Assert.assertEquals(existingCPDefinition.getStatusByUserName(),
			newCPDefinition.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinition.getStatusDate()),
			Time.getShortTimestamp(newCPDefinition.getStatusDate()));
		Assert.assertEquals(existingCPDefinition.getDefaultLanguageId(),
			newCPDefinition.getDefaultLanguageId());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUUID_G(StringPool.NULL, 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

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
	public void testCountByG_S() throws Exception {
		_persistence.countByG_S(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_S(0L, 0);
	}

	@Test
	public void testCountByG_NotS() throws Exception {
		_persistence.countByG_NotS(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByG_NotS(0L, 0);
	}

	@Test
	public void testCountByLtD_S() throws Exception {
		_persistence.countByLtD_S(RandomTestUtil.nextDate(),
			RandomTestUtil.nextInt());

		_persistence.countByLtD_S(RandomTestUtil.nextDate(), 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		CPDefinition existingCPDefinition = _persistence.findByPrimaryKey(newCPDefinition.getPrimaryKey());

		Assert.assertEquals(existingCPDefinition, newCPDefinition);
	}

	@Test(expected = NoSuchCPDefinitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CPDefinition> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPDefinition", "uuid",
			true, "CPDefinitionId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "baseSKU", true, "productTypeName", true,
			"gtin", true, "manufacturerPartNumber", true,
			"availableIndividually", true, "minCartQuantity", true,
			"maxCartQuantity", true, "allowedCartQuantities", true,
			"multipleCartQuantity", true, "DDMStructureKey", true,
			"displayDate", true, "expirationDate", true, "lastPublishDate",
			true, "status", true, "statusByUserId", true, "statusByUserName",
			true, "statusDate", true, "defaultLanguageId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		CPDefinition existingCPDefinition = _persistence.fetchByPrimaryKey(newCPDefinition.getPrimaryKey());

		Assert.assertEquals(existingCPDefinition, newCPDefinition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinition missingCPDefinition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPDefinition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPDefinition newCPDefinition1 = addCPDefinition();
		CPDefinition newCPDefinition2 = addCPDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDefinition1.getPrimaryKey());
		primaryKeys.add(newCPDefinition2.getPrimaryKey());

		Map<Serializable, CPDefinition> cpDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpDefinitions.size());
		Assert.assertEquals(newCPDefinition1,
			cpDefinitions.get(newCPDefinition1.getPrimaryKey()));
		Assert.assertEquals(newCPDefinition2,
			cpDefinitions.get(newCPDefinition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPDefinition> cpDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDefinition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPDefinition> cpDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpDefinitions.size());
		Assert.assertEquals(newCPDefinition,
			cpDefinitions.get(newCPDefinition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPDefinition> cpDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDefinition.getPrimaryKey());

		Map<Serializable, CPDefinition> cpDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpDefinitions.size());
		Assert.assertEquals(newCPDefinition,
			cpDefinitions.get(newCPDefinition.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPDefinitionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPDefinition>() {
				@Override
				public void performAction(CPDefinition cpDefinition) {
					Assert.assertNotNull(cpDefinition);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPDefinitionId",
				newCPDefinition.getCPDefinitionId()));

		List<CPDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPDefinition existingCPDefinition = result.get(0);

		Assert.assertEquals(existingCPDefinition, newCPDefinition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPDefinitionId",
				RandomTestUtil.nextLong()));

		List<CPDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPDefinitionId"));

		Object newCPDefinitionId = newCPDefinition.getCPDefinitionId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPDefinitionId",
				new Object[] { newCPDefinitionId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPDefinitionId = result.get(0);

		Assert.assertEquals(existingCPDefinitionId, newCPDefinitionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPDefinitionId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPDefinitionId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CPDefinition newCPDefinition = addCPDefinition();

		_persistence.clearCache();

		CPDefinition existingCPDefinition = _persistence.findByPrimaryKey(newCPDefinition.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingCPDefinition.getUuid(),
				ReflectionTestUtil.invoke(existingCPDefinition,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingCPDefinition.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCPDefinition,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected CPDefinition addCPDefinition() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinition cpDefinition = _persistence.create(pk);

		cpDefinition.setUuid(RandomTestUtil.randomString());

		cpDefinition.setGroupId(RandomTestUtil.nextLong());

		cpDefinition.setCompanyId(RandomTestUtil.nextLong());

		cpDefinition.setUserId(RandomTestUtil.nextLong());

		cpDefinition.setUserName(RandomTestUtil.randomString());

		cpDefinition.setCreateDate(RandomTestUtil.nextDate());

		cpDefinition.setModifiedDate(RandomTestUtil.nextDate());

		cpDefinition.setBaseSKU(RandomTestUtil.randomString());

		cpDefinition.setProductTypeName(RandomTestUtil.randomString());

		cpDefinition.setGtin(RandomTestUtil.randomString());

		cpDefinition.setManufacturerPartNumber(RandomTestUtil.randomString());

		cpDefinition.setAvailableIndividually(RandomTestUtil.randomBoolean());

		cpDefinition.setMinCartQuantity(RandomTestUtil.nextInt());

		cpDefinition.setMaxCartQuantity(RandomTestUtil.nextInt());

		cpDefinition.setAllowedCartQuantities(RandomTestUtil.randomString());

		cpDefinition.setMultipleCartQuantity(RandomTestUtil.nextInt());

		cpDefinition.setDDMStructureKey(RandomTestUtil.randomString());

		cpDefinition.setDisplayDate(RandomTestUtil.nextDate());

		cpDefinition.setExpirationDate(RandomTestUtil.nextDate());

		cpDefinition.setLastPublishDate(RandomTestUtil.nextDate());

		cpDefinition.setStatus(RandomTestUtil.nextInt());

		cpDefinition.setStatusByUserId(RandomTestUtil.nextLong());

		cpDefinition.setStatusByUserName(RandomTestUtil.randomString());

		cpDefinition.setStatusDate(RandomTestUtil.nextDate());

		cpDefinition.setDefaultLanguageId(RandomTestUtil.randomString());

		_cpDefinitions.add(_persistence.update(cpDefinition));

		return cpDefinition;
	}

	private List<CPDefinition> _cpDefinitions = new ArrayList<CPDefinition>();
	private CPDefinitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}