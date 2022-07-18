/**
 * @(#) AxiomEntityImpl.java
 */

package com.radian.cuwbilling.common.bo.domain.impl;

import java.util.Date;

import com.radian.cuwbilling.common.bo.codes.ActivationStatus;
import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.common.bo.domain.AxiomEntity;
import com.radian.cuwbilling.system.user.bo.domain.User;

/**
 * This class represents the abstract Entity class used by all Axiom reference
 * domain objects.
 * 
 * The Entity class provides the basic behavoir shared by all Axiom reference
 * domain objects including, but not limited to Customer, Branch, Contract and
 * Person.
 * 
 * The default behavior includes:
 * <UL>
 * <LI>Ability to activate/deactive the business entity</LI>
 * <LI>Ability to maintain a version history for the business entity.</LI>
 * </UL>
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public abstract class AxiomEntityImpl extends BaseDomainObjectImpl implements AxiomEntity
{
    /**
     * The date this business entity was activated.
     */
    private Date activationDate;

    /**
     * Identifies the current activation status for this Business Entity.
     */
    private ActivationStatus activeStatus = ActivationStatus.ACTIVE; // default
                                                                        // to
                                                                        // active
                                                                        // to
                                                                        // avoid
                                                                        // situation
                                                                        // when
                                                                        // activation
                                                                        // status
                                                                        // ==
                                                                        // null

    /**
     * The primary comment for this Business Entity.
     */
    private String comment;

    /**
     * The deactivation comment for this business entity.
     */
    private String deactivationComment;

    /**
     * The date the business entity was deactivated
     */
    private Date deactivationDate;

    /**
     * Identifies the description of this Business Entity.
     */
    private String desc;

    /**
     * Identifies the name for this Business Entity.
     */
    private String name;

    /**
     * axiom entity type. we may not need this as a memeber, as each entity
     * class may return same instance
     */
    private AxiomEntityType entityType;

    /**
     * Identifies the <code>User</code> whom activated the business entity.
     */
    private User activatedBy;

    /**
     * Identifies the <code>User</code> whom deactivated the business entity.
     */
    private User deactivatedBy;

    /**
     * @Return The <code>Date</code> the business entity was activated.
     */
    public Date getActivationDate()
    {
        return this.activationDate;
    }

    /**
     * @Return The <code>ActivationStatus</code> for this business entity.
     */
    public ActivationStatus getActiveStatus()
    {
        return this.activeStatus;
    }

    /**
     * @Return The primary comment for this Business Entity.
     */
    public String getComment()
    {
        return this.comment;
    }

    /**
     * @Return The reason for deactivating this business entity.
     */
    public String getDeactivationComment()
    {
        return this.deactivationComment;
    }

    /**
     * @Return The <code>Date</code> the business entity was deactivated.
     */
    public Date getDeactivationDate()
    {
        return this.deactivationDate;
    }

    /**
     * @Return The description of this business entity.
     */
    public String getDesc()
    {
        return this.desc;
    }

    /**
     * @Return The name for this business entity.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Assign the <code>Date</code> this business entity was activated. If the
     * Business Entity was previously Deactivated, reset the Deactivation Date
     * and DeactivationComment to null values
     * 
     * @param Date
     *            The <code>Date</code> the business entity was activated.
     */
    public void setActivationDate(final Date date)
    {
        this.activationDate = date;
    }

    /**
     * Assign the primary comment for this business entity.
     * 
     * @param comment
     *            The comment for this business entity.
     */
    public void setComment(final String comment)
    {
        this.comment = comment;
    }

    /**
     * Assign the deactivation comment for this business entity.
     * 
     * @param comment
     *            The deactivation comment for this business entity.
     */
    public void setDeactivationComment(final String comment)
    {
        this.deactivationComment = comment;
    }

    /**
     * Assign the <code>Date</code> this business entity was deactivated.
     * 
     * @param date
     *            The <code>Date</code> this business entity was deactivated.
     */
    public void setDeactivationDate(final Date date)
    {
        this.deactivationDate = date;
    }

    /**
     * Assign the description of this business entity.
     * 
     * @param desc
     *            The description for this Product.
     */
    public void setDesc(final String desc)
    {
        this.desc = desc;
    }

    /**
     * Assign the name for this business entity.
     * 
     * @param name
     *            The name for this business entity.
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * Activates this Business Entity making it available for use in
     * transactions. This method sets the activationDate to the current date,
     * assigns the requesting <code>User</code> as the activatedBy, and
     * assigns the activationStatus to <code>ActivationStatus.ACTIVE</code>.
     * 
     * If the Business Entity was previously deactivated, the deactivationDate
     * is set to null, and deactivationComment is set to empty string.
     * 
     * @param user
     *            A reference to the requesting <code>User</code>
     * 
     * @ActivationException An exception occurred while activating the Business
     *                      Entity.
     */
    public void activateEntity(User user)
    {
        this.activeStatus = ActivationStatus.ACTIVE;
        this.activationDate = new Date();
        this.activatedBy = user;
    }

    /**
     * Deactivates the Business Entity restricting it from being used in future
     * transactions. This method sets the deactivationDate to the current date,
     * assigns the requesting <code>User</code> as the deactivatedBy, and
     * assigns the activationStatus to <code>ActivationStatus.INACTIVE</code>.
     * 
     * @param comment
     *            The reason for deactivating the business entity.
     * @param user
     *            A reference to the requesting <code>User</code>.
     * 
     * @ActivationException An exception occurred while deactivating the
     *                      Business Entity.
     */
    public void deactivateEntity(String comment, User user)
    {

        this.comment = comment;
        this.activeStatus = ActivationStatus.INACTIVE;
        this.deactivationDate = new Date();
        this.deactivatedBy = user;
    }

    /**
     * @Return name for the business entity.
     */
    public String toString()
    {
        return this.getName();
    }

    public Long getActiveStatusRaw()
    {
        if (activeStatus == null)
            return null;

        return activeStatus.getID();
    }

    public void setActiveStatusRaw(Long id)
    {
        this.activeStatus = ActivationStatus.decodeValue(id);
    }

    public AxiomEntityType getAxiomEntityType()
    {
        // todo:: each class could return its type, so we wouldn't need
        // setter...
        return entityType;
    }

    public void setAxiomEntityType(AxiomEntityType entityType)
    {
        // todo:: this could be class method...
        this.entityType = entityType;
    }

    /**
     * Getter for property activatedBy.
     * 
     * @return Value of property activatedBy.
     */
    public User getActivatedBy()
    {
        return activatedBy;
    }

    /**
     * Setter for property activatedBy.
     * 
     * @param activatedBy
     *            New value of property activatedBy.
     */
    public void setActivatedBy(User activatedBy)
    {
        this.activatedBy = activatedBy;
    }

    /**
     * Getter for property deactivatedBy.
     * 
     * @return Value of property deactivatedBy.
     */
    public User getDeactivatedBy()
    {
        return deactivatedBy;
    }

    /**
     * Setter for property deactivatedBy.
     * 
     * @param deactivatedBy
     *            New value of property deactivatedBy.
     */
    public void setDeactivatedBy(User deactivatedBy)
    {
        this.deactivatedBy = deactivatedBy;
    }

}
