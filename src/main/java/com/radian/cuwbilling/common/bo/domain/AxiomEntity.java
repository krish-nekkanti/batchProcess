/**
 * @(#) AxiomEntity.java
 */

package com.radian.cuwbilling.common.bo.domain;

import java.util.Date;

import com.radian.cuwbilling.common.bo.codes.ActivationStatus;
import com.radian.cuwbilling.common.bo.codes.AxiomEntityType;
import com.radian.cuwbilling.system.user.bo.domain.User;

/**
 * This interface defines the behavior for Axiom Business Entities, such as
 * Customer, Branch, Contract and Billing Profile.
 * 
 * The interface defines the following default beahavior:
 * <UL>
 * <LI>A unique identifier represented as a Long</LI>
 * <LI>Ability to activate/deactive the business entity</LI>
 * <LI>Ability to maintain a version history for the business entity.</LI>
 * </UL>
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public interface AxiomEntity extends BaseDomainObject
{
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
    public void activateEntity(User user);

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
    public void deactivateEntity(String comment, User user);

    /**
     * @Return The <code>Date</code> the business entity was activated.
     */
    public Date getActivationDate();

    /**
     * @Return The <code>Date</code> the business entity was deactivated.
     */
    public Date getDeactivationDate();

    /**
     * @Return The reason for deactivating this business entity.
     */
    public String getDeactivationComment();

    /**
     * @Return The <code>ActivationStatus</code> for this business entity.
     */
    public ActivationStatus getActiveStatus();

    /**
     * @Return The primary comment for this Business Entity.
     */
    public String getComment();

    /**
     * @Return The description of this business entity.
     */
    public String getDesc();

    /**
     * @Return The name for this business entity.
     */
    public String getName();

    /**
     * Assign the <code>Date</code> this business entity was activated.
     * 
     * @param Date
     *            The <code>Date</code> the business entity was activated.
     */
    public void setActivationDate(final Date date);

    /**
     * Assign the primary comment for this business entity.
     * 
     * @param comment
     *            The comment for this business entity.
     */
    public void setComment(final String comment);

    /**
     * Assign the description of this business entity.
     * 
     * @param desc
     *            The description for this Product.
     */
    public void setDesc(final String desc);

    /**
     * Assign the name for this business entity.
     * 
     * @param name
     *            The name for this business entity.
     */
    public void setName(final String name);

    /**
     * Assign the deactivation comment for this business entity.
     * 
     * @param comment
     *            The deactivation comment for this business entity.
     */
    public void setDeactivationComment(final String comment);

    /**
     * Assign the <code>Date</code> this business entity was deactivated.
     * 
     * @param date
     *            The <code>Date</code> this business entity was deactivated.
     */
    public void setDeactivationDate(final Date date);

    /**
     * @Return name for the business entity.
     */
    public String toString();

    /**
     * @return axiom entity type
     */
    public AxiomEntityType getAxiomEntityType();

    /**
     * @param axiom
     *            entity type
     */
    public void setAxiomEntityType(AxiomEntityType entityType);

    /**
     * Getter for property activatedBy.
     * 
     * @return Value of property activatedBy.
     */
    public User getActivatedBy();

    /**
     * Setter for property activatedBy.
     * 
     * @param activatedBy
     *            New value of property activatedBy.
     */
    public void setActivatedBy(User activatedBy);

    /**
     * Getter for property deactivatedBy.
     * 
     * @return Value of property deactivatedBy.
     */
    public User getDeactivatedBy();

    /**
     * Setter for property deactivatedBy.
     * 
     * @param deactivatedBy
     *            New value of property deactivatedBy.
     */
    public void setDeactivatedBy(User deactivatedBy);

}
