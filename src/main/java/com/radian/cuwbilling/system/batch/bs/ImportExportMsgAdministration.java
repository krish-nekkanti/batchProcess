/*
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bs;

import java.util.Collection;

import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ImportExportMsgAdministration
{

    /**
     * Creates a new ImportExport Message
     * 
     * @param ImportExportMsgDTO
     * @throws ImportExportMsgException
     */
    public Long create(ImportExportMsgDTO importExportMsgDTO) throws ImportExportMsgException;

    /**
     * Searches for ImportExport Messages by Criteria
     * 
     * @param ImportExportMsgSearchCriteria
     * @throws ImportExportMsgException
     */

    public Collection findByCriteria(ImportExportMsgSearchCriteria importExportMsgSearchCriteria) throws ImportExportMsgException;

    /**
     * Searches for ImportExport Messages by ID
     * 
     * @param importExportMsgID
     * @throws ImportExportMsgException
     */

    public ImportExportMsgDTO getByID(Long importExportMsgID) throws ImportExportMsgException;

}
