/**
 * @(#) BaseDomainObjectImpl.java
 */

package com.radian.cuwbilling.common.bo.domain.impl;

import java.util.Date;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.system.user.bo.domain.User;

/**
 * This class represents the abstract business domain object.
 * 
 * The BaseDomainObject is the base class Business Domain Objects. This base
 * class provides the following default behaviors:
 * 
 * <UL>
 * <LI>A unique identifier represented as a Long</LI>
 * <LI>Administrative attributes</LI>
 * </UL>
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public abstract class BaseDomainObjectImpl implements BaseDomainObject
{
    private static int MAGIC_HASH = 1;

    /**
     * Identifies the <code>User</code> whom initially created the business
     * entity.
     */
    private String createdBy;

    /**
     * The date this business object was created.
     */
    private Date createdDate = new java.util.Date();

    /**
     * A unique key identifying a Axiom business entity.
     */
    private Long ID;

    /**
     * Identifies the <code>User</code> who last modified the business entity.
     */
    private String modifiedBy;

    /**
     * The date this business object was last modified.
     */
    private Date modifiedDate;

    /**
     * Guarantees all equal business entities are equal based on their
     * persistence key.
     */
    public boolean equals(Object other)
    {
        if (other == this)
            return true;
        if (other instanceof BaseDomainObject)
        {
            BaseDomainObject otherObject = (BaseDomainObject) other;
            if (otherObject.getID() != null && getID() != null)
            {
                return otherObject.getID().equals(getID());
            }
        }
        return false;
    }

    /**
     * @Return A reference to the <code>User</code> who created this business
     *         object.
     */
    public String getCreatedBy()
    {
        return this.createdBy;
    }

    /**
     * @return The <code>Date</code> this business object was created.
     */
    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    /**
     * @Returns the persistence <code>ID</code> uniquely identifying this
     *          business Entity.
     */
    public Long getID()
    {
        return this.ID;
    }

    /**
     * @Return A reference to the <code>User</code> who last modified this
     *         object.
     */
    public String getModifiedBy()
    {
        return this.modifiedBy;
    }

    /**
     * @Return The <code>Date</code> this business object was last modified.
     */
    public Date getModifiedDate()
    {
        return this.modifiedDate;
    }

    /**
     * Assign the <code>User</code> who created this business object.
     * 
     * @param createdBy
     *            A reference to the User who created this business object.
     */
    public void setCreatedBy(final String createdBy)
    {
        this.createdBy = createdBy;
    }

    /**
     * Assigns the <code>Date</code> this business object was created.
     * 
     * @param createdDate
     *            The create <code>Date</code> for this business object.
     */
    public void setCreatedDate(final Date createdDate)
    {
        this.createdDate = createdDate;
    }

    /**
     * Assigns a unique persistence <code>ID</code> to this business Entity.
     * 
     * @param id
     *            A unique identifier used for the persistence key.
     */
    public void setID(Long id)
    {
        this.ID = id;
    }

    /**
     * Assign the <code>User</code> who last modified this business object.
     * 
     * @param modifiedBy
     *            A reference to the <code>User</code> who last modified this
     *            business object.
     */
    public void setModifiedBy(final String modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Assign the <code>Date</code> the business object was last modified.
     * 
     * @param modifiedDate
     *            The <code>Date</code> the business object was last modified.
     */
    public void setModifiedDate(final Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return MAGIC_HASH;
    }

}
