package com.radian.cuwbilling.common.bo.dto;

import javax.activation.DataHandler;

/**
 * User: ayre Date: Sep 23, 2002 Time: 4:05:51 PM
 */
public class AttachmentWrapperDTO implements java.io.Serializable
{
    protected DataHandler attachment = null;

    protected String name = "";

    protected String type = "";

    public DataHandler getAttachment()
    {
        return attachment;
    }

    public void setAttachment(DataHandler attachment)
    {
        this.attachment = attachment;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

}
