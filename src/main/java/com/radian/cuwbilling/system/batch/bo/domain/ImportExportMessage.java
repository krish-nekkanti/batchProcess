package com.radian.cuwbilling.system.batch.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.system.common.bo.code.ImportExportMsgType;

/**
 * Date: Nov 24, 2003 Time: 10:33:24 AM
 */
public interface ImportExportMessage extends BaseDomainObject
{
    String getBatchNumber();

    void setBatchNumber(String batchNum);

    ImportExportMsgType getImportExportMsgType();

    void setImportExportMsgType(ImportExportMsgType type);

    String getMessage();

    void setMessage(String message);

    Boolean isSuccess();

    void setSuccess(Boolean success);

}
