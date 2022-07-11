/**
 * @(#) BaseDomainObject.java
 */

package com.radian.cuwbilling.common.bo.domain;

import java.util.Date;

import com.radian.cuwbilling.system.user.bo.domain.User;

/**
 * This interface defines the base interface for all Axiom Domain Objects;
 * 
 * The BaseDomainObject interface defines the following default behaviors;
 * 
 * <UL>
 * <LI>A unique identifier represented as a Long</LI>
 * <LI>Administrative attributes</LI>
 * </UL>
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public interface BaseDomainObject
{
    /**
     * @Return A reference to the <code>User</code> who created this business
     *         object.
     */
    public String getCreatedBy();

    /**
     * @return The <code>Date</code> this business object was created.
     */
    public Date getCreatedDate();

    /**
     * @Returns the persistence <code>ID</code> uniquely identifying this
     *          business Entity.
     */
    public Long getID();

    /**
     * @Return A reference to the <code>User</code> who last modified this
     *         object.
     */
    public String getModifiedBy();

    /**
     * @Return The <code>Date</code> this business object was last modified.
     */
    public Date getModifiedDate();

    /**
     * Assign the <code>User</code> who created this business object.
     * 
     * @param createdBy
     *            A reference to the User who created this business object.
     */
    public void setCreatedBy(final String createdBy);

    /**
     * Assigns the <code>Date</code> this business object was created.
     * 
     * @param createdDate
     *            The create <code>Date</code> for this business object.
     */
    public void setCreatedDate(final Date createdDate);

    /**
     * Assigns a unique persistence <code>ID</code> to this business Entity.
     * 
     * @param id
     *            A unique identifier used for the persistence key.
     */
    public void setID(Long id);

    /**
     * Assign the <code>User</code> who last modified this business object.
     * 
     * @param modifiedBy
     *            A reference to the <code>User</code> who last modified this
     *            business object.
     */
    public void setModifiedBy(final String modifiedBy);

    /**
     * Assign the <code>Date</code> the business object was last modified.
     * 
     * @param modifiedDate
     *            The <code>Date</code> the business object was last modified.
     */
    public void setModifiedDate(final Date modifiedDate);

}
