package com.radian.cuwbilling.system.messaging.bo.dto;

import java.io.Serializable;

/**
 * User: ayre Date: Oct 27, 2002 Time: 3:08:07 PM
 */
public class ResponseStatusInfo implements Serializable
{

    private String m_Code;

    private String m_Desc;

    private String m_Status;

    /** Creates a new instance of MessageInfo */
    public ResponseStatusInfo()
    {
    }

    /** Status attr */
    public String getStatus()
    {
        return m_Status;
    }

    public void setStatus(String p_Status)
    {
        m_Status = p_Status;
    }

    /** Code attr */
    public String getCode()
    {
        return m_Code;
    }

    public void setCode(String p_Code)
    {
        m_Code = p_Code;
    }

    /** Desc attr */
    public String getDesc()
    {
        return m_Desc;
    }

    public void setDesc(String p_Desc)
    {
        m_Desc = p_Desc;
    }

}
