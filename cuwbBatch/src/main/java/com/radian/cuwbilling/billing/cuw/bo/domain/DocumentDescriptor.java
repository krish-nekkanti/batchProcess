package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

/**
 * TODO - add java doc comment
 * 
 * 
 */
public interface DocumentDescriptor extends BaseDomainObject
{
    public void setDocumentTitle(String title);

    public String getDocumentTitle();

    public void setEntityID(String entityID);

    public String getEntityID();

}
