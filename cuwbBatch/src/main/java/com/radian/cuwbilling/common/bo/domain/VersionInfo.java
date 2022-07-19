/*
 * VersionInfo.java
 *
 * Created on December 23, 2003, 5:10 PM
 */

package com.radian.cuwbilling.common.bo.domain;

import java.util.Date;

import com.radian.cuwbilling.system.user.bo.domain.User;

/**
 * 
 * @author DLeed
 */
public interface VersionInfo extends BaseDomainObject
{

    public Long getObjectID();

    public void setObjectID(Long objectID);

    public User getVersionUser();

    public void setVersionUser(User user);

    public Date getVersionDateTime();

    public void setVersionDateTime(Date date);

    public String getLongDesc();

    public void setLongDesc(String desc);

    public String getShortDesc();

    public void setShortDesc(String desc);

    public Long getRootID();

    public void setRootID(Long id);

    public String getVersionComment();

    public void setVersionComment(String comment);

    public String getVersionNumber();

    public void setVersionNumber(String number);

}
