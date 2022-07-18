package com.radian.cuwbilling.system.messaging.bo.dto;

/**
 * User: ayre Date: Oct 27, 2002 Time: 3:05:49 PM
 */
public class SecurityInfo implements java.io.Serializable
{
    private java.lang.String password;

    private java.lang.String userName;

    public SecurityInfo()
    {
    }

    public java.lang.String getPassword()
    {
        return password;
    }

    public void setPassword(java.lang.String password)
    {
        this.password = password;
    }

    public java.lang.String getUserName()
    {
        return userName;
    }

    public void setUserName(java.lang.String userName)
    {
        this.userName = userName;
    }
}
