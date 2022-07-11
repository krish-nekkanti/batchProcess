/*
 * Created on Mar 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bs.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;

import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgException;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgSearchCriteria;
import com.radian.cuwbilling.system.batch.bs.ejb.ImportExportMsgAdminRemote;
import com.radian.cuwbilling.system.batch.bs.ejb.ImportExportMsgAdminRemoteHome;
import com.radian.foundation.bs.BaseService;
import com.radian.foundation.common.util.ServiceLocator;
import com.radian.foundation.common.util.ServiceLocatorException;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportExportMsgAdminDelegate extends BaseService implements ImportExportMsgAdministration
{

    public static final String IMPORT_EXPORT_MSG_ADMIN_REMOTE_HOME_JNDI = "com/radian/cuwbilling/system/batch/bs/ejb/ImportExportMsgAdminRemoteHome";

    /**
     * Helper method to handle initial context lookup and getting of reference
     * to EJB object interface
     * 
     * @return ImportExportMsgAdminRemote a reference to the remote EJB object
     * @throws ImportExportMsgException
     */
    private ImportExportMsgAdminRemote getImportExportMsgAdminRemote() throws ImportExportMsgException
    {
        try
        {
            ImportExportMsgAdminRemoteHome home = (ImportExportMsgAdminRemoteHome) ServiceLocator.getInstance().getEJBHome(
                    ImportExportMsgAdminRemoteHome.class, IMPORT_EXPORT_MSG_ADMIN_REMOTE_HOME_JNDI);
            return home.create();
        } catch (ServiceLocatorException e)
        {
            e.printStackTrace();
            throw new ImportExportMsgException("excep.serviceLocator", new Object[] { e.getMessage() });
        } catch (CreateException e)
        {
            e.printStackTrace();
            throw new ImportExportMsgException("excep.create", new Object[] { e.getMessage() });
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new ImportExportMsgException("excep.remote", new Object[] { e.getMessage() });
        }
    }

    public ImportExportMsgAdminDelegate()
    {
        super();
    }

    /**
     * Creates a new instance of ImportExportMsgAdminDelegate
     * 
     * @return New Object for ImportExportMsgAdminDelegate
     */
    public static ImportExportMsgAdministration instance()
    {
        return (ImportExportMsgAdministration) instance(ImportExportMsgAdminDelegate.class);
    }

    /*
     * create new Import/Export Message (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration#create(com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO)
     */
    public Long create(ImportExportMsgDTO importExportMsgDTO) throws ImportExportMsgException
    {
        try
        {
            ImportExportMsgAdminRemote ejb = getImportExportMsgAdminRemote();
            Long id = ejb.create(importExportMsgDTO);
            return id;
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new ImportExportMsgException("excep.remote", new Object[] { e.getMessage() });
        }
    }

    /*
     * Return Import/Export Message's Collection for the passed in Criteria
     * (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration#findByCriteria(com.radian.cuwbilling.system.batch.bs.ImportExportMsgSearchCriteria)
     */
    public Collection findByCriteria(ImportExportMsgSearchCriteria importExportMsgSearchCriteria) throws ImportExportMsgException
    {
        try
        {
            ImportExportMsgAdminRemote ejb = getImportExportMsgAdminRemote();
            Collection resultSummary = ejb.findByCriteria(importExportMsgSearchCriteria);
            return resultSummary;
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new ImportExportMsgException("excep.remote", new Object[] { e.getMessage() });
        }
    }

    /*
     * Return Import/Export Message by ID (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.system.batch.bs.ImportExportMsgAdministration#getByID(java.lang.Long)
     */
    public ImportExportMsgDTO getByID(Long importExportMsgID) throws ImportExportMsgException
    {
        try
        {
            ImportExportMsgAdminRemote ejb = getImportExportMsgAdminRemote();
            ImportExportMsgDTO resultDTO = ejb.getByID(importExportMsgID);
            return resultDTO;
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new ImportExportMsgException("excep.remote", new Object[] { e.getMessage() });
        }
    }

}
