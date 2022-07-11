/**
 * @(#) EmailAddress.java
 */

package com.radian.cuwbilling.common.bo.domain;

/**
 * This interface defines a simple EmailAddress.
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public interface EmailAddress extends BaseDomainObject
{

    /**
     * @Return The fully qualified email address for this Email.
     */
    public String getEmailAddress();

    /**
     * Assign the fully qualified email address for this EmailAddress.
     * 
     * @param emailAddr
     *            The fully qualified email address.
     */
    public void setEmailAddress(String emailAddr);
}
