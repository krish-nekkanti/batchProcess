package com.radian.cuwbilling.system.messaging.bo.dto;

/**
 * User: ayre Date: Oct 27, 2002 Time: 3:00:44 PM
 */
public class TradingPartnerInfo implements java.io.Serializable
{

    private String m_Id; // TODO: this is the ID from TradingPartner object

    private String m_Name; // TODO: Branch name, Customer name, AUS Name?

    protected String transformationId = "";

    // TODO: this needs to be cross-referenced to the domain schema

    /** Creates a new instance of TradingPartnerInfo */
    public TradingPartnerInfo()
    {
    }

    /** Id attr */
    public String getId()
    {
        return m_Id;
    }

    public void setId(String p_Id)
    {
        m_Id = p_Id;
    }

    /** Name attr */
    public String getName()
    {
        return m_Name;
    }

    public void setName(String p_Name)
    {
        m_Name = p_Name;
    }

    public String getTransformationId()
    {
        return transformationId;
    }

    public void setTransformationId(String transformationId)
    {
        this.transformationId = transformationId;
    }

}