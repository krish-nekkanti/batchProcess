/*
 * Created on Mar 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bo.dto.assembler;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.dto.BaseAssembler;
import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.system.batch.bo.domain.ImportExportMessage;
import com.radian.cuwbilling.system.batch.bo.domain.impl.ImportExportMessageImpl;
import com.radian.cuwbilling.system.batch.bo.dto.ImportExportMsgDTO;
import com.radian.foundation.bo.dto.BaseDTO;
import com.radian.foundation.common.exception.ValidationException;
import com.radian.foundation.os.persistence.spi.PersistenceProvider;
import com.radian.foundation.os.persistence.spi.PersistenceProviderException;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportExportMsgAssembler extends BaseAssembler
{

    /*
     * (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.common.bo.dto.BaseAssembler#createDTO(com.radian.cuwbilling.common.bo.domain.BaseDomainObject,
     *      com.radian.foundation.os.persistence.spi.PersistenceProvider)
     */
    public BaseDTO createDTO(BaseDomainObject obj, PersistenceProvider provider) throws PersistenceProviderException, ValidationException
    {
        ImportExportMsgDTO importExportMsgDTO = new ImportExportMsgDTO();
        ;
        if (obj != null)
        {
            ImportExportMessage importExportMsgDO = (ImportExportMessage) obj;

            importExportMsgDTO.setBatchNumber(importExportMsgDO.getBatchNumber());
            importExportMsgDTO.setImportExportMsgType(importExportMsgDO.getImportExportMsgType());
            importExportMsgDTO.setMessageID(importExportMsgDO.getID());
            importExportMsgDTO.setMessageDate(new DateDTO(importExportMsgDO.getCreatedDate()));
            importExportMsgDTO.setMessageText(importExportMsgDO.getMessage());
            importExportMsgDTO.setSuccessFlag(importExportMsgDO.isSuccess());
        }

        return importExportMsgDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.common.bo.dto.BaseAssembler#createDO(com.radian.foundation.bo.dto.BaseDTO,
     *      com.radian.foundation.os.persistence.spi.PersistenceProvider)
     */
    public BaseDomainObject createDO(BaseDTO dto, PersistenceProvider provider) throws PersistenceProviderException, ValidationException
    {
        final int MAX_MESSAGE_LENGTH = 500;
        ImportExportMessage importExportMsgDO = null;
        if (dto != null)
        {
            ImportExportMsgDTO importExportMsgDTO = (ImportExportMsgDTO) dto;
            importExportMsgDO = new ImportExportMessageImpl();
            importExportMsgDO.setBatchNumber(importExportMsgDTO.getBatchNumber());
            // save upto MAX_MESSAGE_LENGTH characters on the message text
            if (importExportMsgDTO.getMessageText() != null)
            {
                if (importExportMsgDTO.getMessageText().length() > MAX_MESSAGE_LENGTH)
                {
                    importExportMsgDO.setMessage(importExportMsgDTO.getMessageText().substring(0, (MAX_MESSAGE_LENGTH - 1)));
                } else
                {
                    importExportMsgDO.setMessage(importExportMsgDTO.getMessageText());
                }
            }
            importExportMsgDO.setImportExportMsgType(importExportMsgDTO.getImportExportMsgType());
            importExportMsgDO.setSuccess(importExportMsgDTO.getSuccessFlag());
        }

        return importExportMsgDO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.radian.cuwbilling.common.bo.dto.BaseAssembler#toDO(com.radian.foundation.bo.dto.BaseDTO,
     *      com.radian.cuwbilling.common.bo.domain.BaseDomainObject,
     *      com.radian.foundation.os.persistence.spi.PersistenceProvider)
     */
    public void toDO(BaseDTO dto, BaseDomainObject obj, PersistenceProvider provider) throws PersistenceProviderException, ValidationException
    {
        // TODO Auto-generated method stub

    }

}
