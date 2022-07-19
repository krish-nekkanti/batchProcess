package com.radian.cuwbilling.system.batch.bo.domain.impl;

import com.radian.cuwbilling.common.bo.domain.impl.BaseDomainObjectImpl;
import com.radian.cuwbilling.system.batch.bo.domain.ImportExportMessage;
import com.radian.cuwbilling.system.common.bo.code.ImportExportMsgType;

/**
 * Date: Nov 24, 2003 Time: 10:33:24 AM
 */
public class ImportExportMessageImpl extends BaseDomainObjectImpl implements ImportExportMessage
{
    private String batchNumber;

    private ImportExportMsgType msgType;

    private Boolean success;

    private String message;

    public String getBatchNumber()
    {
        return batchNumber;
    }

    public ImportExportMsgType getImportExportMsgType()
    {
        return msgType;
    }

    public String getMessage()
    {
        return message;
    }

    public Boolean isSuccess()
    {
        return success;
    }

    public void setBatchNumber(String batchNum)
    {
        this.batchNumber = batchNum;
    }

    public void setImportExportMsgType(ImportExportMsgType type)
    {
        this.msgType = type;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setSuccess(Boolean success)
    {
        this.success = success;
    }

    public void setMsgTypeRaw(Long msgTypeID)
    {
        if (msgTypeID != null)
        {
            this.msgType = ImportExportMsgType.decodeValue(msgTypeID);
        }
    }

    public Long getMsgTypeRaw()
    {
        if (msgType != null)
        {
            return msgType.getID();
        } else
            return null;
    }

}
