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

package com.liferay.osb.distributed.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link QueuedMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessageLocalService
 * @generated
 */
public class QueuedMessageLocalServiceWrapper
	implements QueuedMessageLocalService,
			   ServiceWrapper<QueuedMessageLocalService> {

	public QueuedMessageLocalServiceWrapper(
		QueuedMessageLocalService queuedMessageLocalService) {

		_queuedMessageLocalService = queuedMessageLocalService;
	}

	/**
	 * Adds the queued message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessage the queued message
	 * @return the queued message that was added
	 */
	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
		addQueuedMessage(
			com.liferay.osb.distributed.messaging.model.QueuedMessage
				queuedMessage) {

		return _queuedMessageLocalService.addQueuedMessage(queuedMessage);
	}

	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
			addQueuedMessage(
				String messageBrokerClassName, String topic,
				com.liferay.osb.distributed.messaging.Message message)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _queuedMessageLocalService.addQueuedMessage(
			messageBrokerClassName, topic, message);
	}

	/**
	 * Creates a new queued message with the primary key. Does not add the queued message to the database.
	 *
	 * @param queuedMessageId the primary key for the new queued message
	 * @return the new queued message
	 */
	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
		createQueuedMessage(long queuedMessageId) {

		return _queuedMessageLocalService.createQueuedMessage(queuedMessageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _queuedMessageLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the queued message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message that was removed
	 * @throws PortalException if a queued message with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
			deleteQueuedMessage(long queuedMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _queuedMessageLocalService.deleteQueuedMessage(queuedMessageId);
	}

	/**
	 * Deletes the queued message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessage the queued message
	 * @return the queued message that was removed
	 */
	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
		deleteQueuedMessage(
			com.liferay.osb.distributed.messaging.model.QueuedMessage
				queuedMessage) {

		return _queuedMessageLocalService.deleteQueuedMessage(queuedMessage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _queuedMessageLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _queuedMessageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _queuedMessageLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _queuedMessageLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _queuedMessageLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _queuedMessageLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
		fetchQueuedMessage(long queuedMessageId) {

		return _queuedMessageLocalService.fetchQueuedMessage(queuedMessageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _queuedMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _queuedMessageLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.osb.distributed.messaging.model.
		QueuedMessageMessageObjectBlobModel getMessageObjectBlobModel(
			java.io.Serializable primaryKey) {

		return _queuedMessageLocalService.getMessageObjectBlobModel(primaryKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _queuedMessageLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _queuedMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the queued message with the primary key.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message
	 * @throws PortalException if a queued message with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
			getQueuedMessage(long queuedMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _queuedMessageLocalService.getQueuedMessage(queuedMessageId);
	}

	/**
	 * Returns a range of all the queued messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @return the range of queued messages
	 */
	@Override
	public java.util.List
		<com.liferay.osb.distributed.messaging.model.QueuedMessage>
			getQueuedMessages(int start, int end) {

		return _queuedMessageLocalService.getQueuedMessages(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.distributed.messaging.model.QueuedMessage>
			getQueuedMessages(String messageBrokerClassName) {

		return _queuedMessageLocalService.getQueuedMessages(
			messageBrokerClassName);
	}

	/**
	 * Returns the number of queued messages.
	 *
	 * @return the number of queued messages
	 */
	@Override
	public int getQueuedMessagesCount() {
		return _queuedMessageLocalService.getQueuedMessagesCount();
	}

	@Override
	public java.io.InputStream openMessageObjectInputStream(
		long queuedMessageId) {

		return _queuedMessageLocalService.openMessageObjectInputStream(
			queuedMessageId);
	}

	/**
	 * Updates the queued message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect QueuedMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param queuedMessage the queued message
	 * @return the queued message that was updated
	 */
	@Override
	public com.liferay.osb.distributed.messaging.model.QueuedMessage
		updateQueuedMessage(
			com.liferay.osb.distributed.messaging.model.QueuedMessage
				queuedMessage) {

		return _queuedMessageLocalService.updateQueuedMessage(queuedMessage);
	}

	@Override
	public QueuedMessageLocalService getWrappedService() {
		return _queuedMessageLocalService;
	}

	@Override
	public void setWrappedService(
		QueuedMessageLocalService queuedMessageLocalService) {

		_queuedMessageLocalService = queuedMessageLocalService;
	}

	private QueuedMessageLocalService _queuedMessageLocalService;

}