package com.radian.cuwbilling.system.messaging.bo.dto;

/**
 * User: ayre Date: Oct 27, 2002 Time: 3:07:25 PM
 */
public class RequestInfo extends MessageHeader
{

    private SecurityInfo m_SecurityInfo;

    /** Creates a new instance of MessageInfo */
    public RequestInfo()
    {
    }

    /** SecurityInfo attr */
    public SecurityInfo getSecurityInfo()
    {
        return m_SecurityInfo;
    }

    public void setSecurityInfo(SecurityInfo p_Info)
    {
        m_SecurityInfo = p_Info;
    }

}
