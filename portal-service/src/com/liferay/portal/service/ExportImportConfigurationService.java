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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;

/**
 * Provides the remote service interface for ExportImportConfiguration. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportConfigurationServiceUtil
 * @see com.liferay.portal.service.base.ExportImportConfigurationServiceBaseImpl
 * @see com.liferay.portal.service.impl.ExportImportConfigurationServiceImpl
 * @generated
 */
@ProviderType
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ExportImportConfigurationService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExportImportConfigurationServiceUtil} to access the export import configuration remote service. Add custom service methods to {@link com.liferay.portal.service.impl.ExportImportConfigurationServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void deleteExportImportConfiguration(
		long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	public com.liferay.portal.model.ExportImportConfiguration moveExportImportConfigurationToTrash(
		long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.model.ExportImportConfiguration restoreExportImportConfigurationFromTrash(
		long exportImportConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);
}