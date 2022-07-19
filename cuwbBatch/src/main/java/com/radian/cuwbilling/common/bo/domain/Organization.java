/**
 * @(#) Organization.java
 */

package com.radian.cuwbilling.common.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.domain.JobPosition;

/**
 * This defines an Organization business domain object.
 *
 * The Organization is the base class for Customers and Branches.
 *
 * @author Rick Mohr
 * @version 1.0
 */
public interface Organization extends Party
{
    /**
     * Adds new contact to this organization.
     *
     * @param contact
     *            a new contact
     */
    //void addContact(ExternalContact contact);

    /**
     * @Return The alternate name for this Organization.
     */
    String getAltName();

    /**
     * Gets organization contacts.
     *
     * @return collection of organization contacts
     */
    Collection getContacts();

    /**
     * @Return The reason for creating this Organization.
     */
    String getCreateReasonText();

    /**
     * @Return The display version of this Organization identifier.
     */
    String getDisplayID();

    /**
     * @Return The URL to the website for this Organization.
     */
    String getURL();

    /**
     * Removes contact from this organization.
     *
     * @param contact
     */
    //void removeContact(ExternalContact contact);

    /**
     * Assigns an alternate name to this Organization.
     *
     * @param altName
     *            The alternate name for this Organization.
     */
    void setAltName(String altName);

    /**
     * Assigns the reason for creating this Organization.
     *
     * @param createReasonText
     *            The reason for creating this Organization.
     */
    void setCreateReasonText(String createReasonText);

    /**
     * Assigns the URL to the website for this Organization.
     *
     * @param uRL
     *            The URL to the website for this Organization.
     */
    void setURL(final String URL);

    /**
     * Adds new Jobposition .
     *
     * @param JobPosition
     *            The new position.
     */
    void addJobPosition(JobPosition position);

    /**
     * @Return collection of positions.
     */
    Collection getJobPositions();

    /**
     * Removes position from this JobPosition.
     *
     * @param JobPosition
     */
    void removeJobPosition(JobPosition position);

}
