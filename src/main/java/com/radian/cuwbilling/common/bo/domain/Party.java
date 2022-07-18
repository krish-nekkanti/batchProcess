/**
 * @(#) PartyI.java
 */

package com.radian.cuwbilling.common.bo.domain;

import com.radian.cuwbilling.common.bo.domain.AxiomEntity;
import com.radian.cuwbilling.common.bo.domain.EmailAddress;
import com.radian.cuwbilling.common.bo.domain.Location;
import com.radian.cuwbilling.common.bo.domain.Phone;

/**
 * This interface defines the abstract Party business domain object.
 * 
 * The Party is the base class for people and organizations.
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public interface Party extends AxiomEntity
{
    /**
     * @return The primary <code>EmailAddress</code> for this Party.
     */
    EmailAddress getEmailAddress();

    /**
     * @Return The alternate <code>EmailAddress</code> for this Person.
     */
    public EmailAddress getAltEmailAddress();

    /**
     * @Return A reference to the <code>Location</code> for this party.
     */
    Location getLocation();

    /**
     * Assign a reference <code>Location</code> for this party.
     * 
     * @param location
     *            A reference to the <code>Location</code> for this party.
     */
    void setLocation(Location location);

    /**
     * @Return The 10-digit alternate phone number for this party.
     */
    Phone getAlternatePhone();

    /**
     * alt phone desc.
     */
    String getAlternatePhoneDesc();

    /**
     * set new alt phone desc.
     */
    void setAlternatePhoneDesc(String desc);

    /**
     * @Return The fax number for this party.
     */
    Phone getFax();

    /**
     * @Return The 10-digit primary phone number for this party.
     */
    Phone getPhone();

    /**
     * get the phone description.
     */
    String getPhoneDesc();

    /**
     * set the phone description.
     */
    void setPhoneDesc(String desc);

    /**
     * Assigns the <code>EmailAddress</code> to this Party.
     * 
     * @param emailAddress
     *            The <code>EmailAddress</code> for this Party.
     */
    void setEmailAddress(EmailAddress emailAddress);

    /**
     * Assigns the alternate <code>EmailAddress</code> to this Person.
     * 
     * @param emailAddress
     *            The alternate <code>EmailAddress</code> for this Person.
     */
    public void setAltEmailAddress(final EmailAddress emailAddress);

    /**
     * Assign the 10-digit alternate phone number for this party.
     * 
     * @param alternatephone
     *            10-digit alternate phone number
     */
    void setAlternatePhone(Phone alternatephone);

    /**
     * Assign the fax number for this party.
     * 
     * @param fax
     *            The primary fax number for this party.
     */
    void setFax(Phone fax);

    /**
     * Assign the 10-digit phone number for this party.
     * 
     * @param phone
     *            The 10-digit phone number for this party.
     */

    void setPhone(Phone phone);

    /**
     * @Return The phone extension for this party.
     */

    String getPhoneExtension();

    /**
     * Assign the 4-digit phone extension for this party.
     * 
     * set phone extension.
     */
    void setPhoneExtension(String phoneextension);

    /**
     * 
     * @Return The alt phone extension for this party.
     */
    String getAlternatePhoneExtension();

    /**
     * Assign the 4-digit alternate phone extension for this party.
     * 
     * set alternate phone extension.
     */
    void setAlternatePhoneExtension(String alternatephoneextension);

}
