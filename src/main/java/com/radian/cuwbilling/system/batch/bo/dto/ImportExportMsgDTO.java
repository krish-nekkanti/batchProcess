/*
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.system.batch.bo.dto;

import com.radian.cuwbilling.common.bo.dto.DateDTO;
import com.radian.cuwbilling.system.common.bo.code.ImportExportMsgType;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * @author NKode
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImportExportMsgDTO extends BaseDTO
{
    // Attributes
    private ImportExportMsgType importExportMsgType;

    private DateDTO messageDate;

    private Long messageID;

    private String messageText;

    private Boolean successFlag;

    private String batchNumber;

    /**
     * @return importExportMsgType
     */
    public ImportExportMsgType getImportExportMsgType()
    {
        return importExportMsgType;
    }

    /**
     * @return messageDate
     */
    public DateDTO getMessageDate()
    {
        return messageDate;
    }

    /**
     * @return messageID
     */
    public Long getMessageID()
    {
        return messageID;
    }

    /**
     * @return messageText
     */
    public String getMessageText()
    {
        return messageText;
    }

    /**
     * @return successFlag
     */
    public Boolean getSuccessFlag()
    {
        return successFlag;
    }

    /**
     * @param importExportMsgType
     */
    public void setImportExportMsgType(ImportExportMsgType importExportMsgType)
    {
        this.importExportMsgType = importExportMsgType;
    }

    /**
     * @param messageDate
     */
    public void setMessageDate(DateDTO messageDate)
    {
        this.messageDate = messageDate;
    }

    /**
     * @param messageID
     */
    public void setMessageID(Long messageID)
    {
        this.messageID = messageID;
    }

    /**
     * @param messageText
     */
    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    /**
     * @param successFlag
     */
    public void setSuccessFlag(Boolean successFlag)
    {
        this.successFlag = successFlag;
    }

    /**
     * @return batchNumber
     */
    public String getBatchNumber()
    {
        return batchNumber;
    }

    /**
     * @param bacthNumber
     */
    public void setBatchNumber(String bacthNumber)
    {
        this.batchNumber = bacthNumber;
    }

}
