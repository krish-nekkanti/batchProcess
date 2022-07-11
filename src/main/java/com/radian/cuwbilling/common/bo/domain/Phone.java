/**
 * @(#) Phone.java
 */

package com.radian.cuwbilling.common.bo.domain;

/**
 * This class represents a user-defined type for any type of telephone number,
 * including Phone, Fax, Mobile and Pager.
 * 
 * @author Rick Mohr, Bryan Carmen
 * @version 1.0
 */
public interface Phone
{

    /**
     * @Return The 10-digit Phone Number without display format.
     */
    public String getPhoneNo();

    /**
     * @Retun The Phone Extension for this Phone number.
     */
    public String getExtension();

    /**
     * @Return The default display value, (999) 999-9999, for the 10-digit Phone
     *         number.
     */
    public String getFormattedPhoneNo();

    /**
     * @Return The Phone Description for this Phone number.
     */
    public String getDescription();

    /**
     * Assign the 10-digit Phone Number.
     * 
     * @param phone
     *            10-digit Phone Number.
     */
    public void setPhoneNo(String phone);

    /**
     * Assigns the 4-digit Phone Extension
     * 
     * @param ext
     *            A phone extension for this Phone.
     */
    public void setExtension(String ext);

    /**
     * Assigns the Phone Description
     * 
     * @param description
     *            A phone description for this Phone.
     */
    public void setDescription(String description);

}