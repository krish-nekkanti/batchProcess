package com.radian.cuwbilling.system.messaging.bo.dto;

import java.io.Serializable;

/**
 * User: ayre Date: Oct 27, 2002 Time: 3:01:50 PM
 */
public abstract class DeliveryMethod implements Serializable
{
    public static final String RESPONSE_REQUIRED = "RESPONSE_REQUIRED";

    public static final String RESPONSE_ON_ERROR = "RESPONSE_ON_ERROR";

    public static final String NO_RESPONSE = "NO_RESPONSE";

    public static final String EMAIL = "EMAIL";

    public static final String HTTP = "HTTP";

    public static final String FTP = "FTP";

    public static final String PRINT = "PRINT";

    public static final String FAX = "FAX";

    private String m_Type;

    private String m_ResponseType;

    private String m_TPContractId; // link from Axiom to TP ContractID
                                    // (Sterling Delivery Method) TODO: Need to
                                    // investigate

    // database changes to support appropriate maintenance/synchronization
    // of TP Profile data in SI with DeliveryItemProfile info in Axiom.

    /** Creates a new instance of MessageInfo */
    public DeliveryMethod()
    {
    }

    /** type attr */
    public String getType()
    {
        return m_Type;
    }

    public void setType(String p_Type)
    {
        m_Type = p_Type;
    }

    public String getTPContractId()
    {
        return m_TPContractId;
    }

    public void setTPContractId(String p_Id)
    {
        m_TPContractId = p_Id;
    }

    /** ResponseType */
    public String getResponseType()
    {
        return m_ResponseType;
    }

    public void setResponseType(String p_Type)
    {
        m_ResponseType = p_Type;
    }

}
