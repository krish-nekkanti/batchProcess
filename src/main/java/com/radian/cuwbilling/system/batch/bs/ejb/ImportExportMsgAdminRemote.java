/*
 * Created on Mar 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bs.ejb;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;

import com.radian.cuwbilling.billing.cuw.bs.CUWBillingException;
import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgException;
import com.radian.cuwbilling.system.batch.bs.ImportExportMsgSearchCriteria;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ImportExportMsgAdminRemote extends EJBObject
{
    /**
     * Creates a new Import/Export message
     * 
     * @param ImportExportMsgDTO
     * @throws ImportExportMsgException
     */
    public Long create(ImportExportMsgDTO importExportMsgDTO) throws ImportExportMsgException, RemoteException;

    /**
     * Get Import/Export Message by ID
     * 
     * @param imporExportMsgID
     * @throws ImportExportMsgException,
     *             RemoteException
     */
    public ImportExportMsgDTO getByID(Long imporExportMsgID) throws ImportExportMsgException, RemoteException;

    /**
     * retrieves a Collection of
     * {@link com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO}
     * 
     * @param importExportMsgSearchCriteria
     * @throws CUWBillingException,
     *             RemoteException
     */
    public Collection findByCriteria(ImportExportMsgSearchCriteria importExportMsgSearchCriteria) throws ImportExportMsgException, RemoteException;
}
