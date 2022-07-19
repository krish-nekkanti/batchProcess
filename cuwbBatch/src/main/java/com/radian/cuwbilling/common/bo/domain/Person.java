/**
 * @(#) Person.java
 */

package com.radian.cuwbilling.common.bo.domain;

import com.radian.cuwbilling.common.bo.codes.Salutation;

/**
 * This interface defines a Person.
 * 
 * The Person is used to describe all kinds of People participating in the Axiom
 * application as a Contact, Staff Member, User or LeaseBack employee.
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public interface Person extends Party
{
    /**
     * @Return The first name for this Person.
     */
    public String getFirstName();

    /**
     * @Return The last name for this Person.
     */
    public String getLastName();

    /**
     * @Return The Middle Initial for this Person.
     */
    public String getMiddleInitial();

    /**
     * @Return The Nickname for this Person.
     */
    public String getNickname();

    /**
     * @Return The <code>Saluation</code> for this Person.
     */
    public Salutation getSalutation();

    /**
     * Assigns the first name to this Person.
     * 
     * @param firstName
     *            The first name for this Person.
     */
    public void setFirstName(final String firstName);

    /**
     * Assigns the last name for this Person.
     * 
     * @param lastName
     *            The last name for this Person.
     */
    public void setLastName(final String lastName);

    /**
     * Assigns the middle initial for this Person.
     * 
     * @param middleInitial
     *            The middle initial for this Person.
     */
    public void setMiddleInitial(final String middleInitial);

    /**
     * Assigns the nickname for this Person.
     * 
     * @param nickname
     *            The nickname for this Person.
     */
    public void setNickname(final String nickname);

    /**
     * Assigns the <code>Salutation</code> for this Person.
     * 
     * @param salutation
     *            The <code>Salutation</code> for this Person.
     */
    public void setSalutation(final Salutation salutation);

}
