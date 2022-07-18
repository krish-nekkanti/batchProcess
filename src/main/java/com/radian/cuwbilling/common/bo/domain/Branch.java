/**
 * @(#) BranchI.java
 */

package com.radian.cuwbilling.common.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.domain.Location;

/**
 * This interface defins a Branch business domain object
 * 
 * A Branch represents an operating division/unit of a Customer.
 * 
 * The general intention is that it is accessed from within the context of a
 * database transaction "behind" an EJB Session facade tier.
 * 
 * Currently, many or all of it's attributes and methods are configured via
 * descriptor files to map to the persistent data store.
 * 
 * The Castor persistence framework is currently being used to "map" business
 * domain objects to the DB
 * 
 * @version 1.0
 */
public interface Branch extends BaseDomainObject
{

    /**
     * Gets the displayable id for the object.
     * 
     * @return the id as a string.
     */
    String getDisplayID();

    /**
     * Sets the displayable ID for the object.
     * 
     * @param id
     *            the id to set.
     */
    void setDisplayID(String id);

    /**
     * @Return A reference to the parent Customer for this Branch.
     */
    Customer getCustomer();

    /**
     * @Return Assigns a reference to the parent Customer for this Branch.
     * 
     * @param customer
     *            A reference to the Customer for this Branch.
     */
    void setCustomer(Customer customer);

    /**
     * Gets this branch location.
     * 
     * @return the location of the branch.
     */
    Location getLocation();

    /**
     * Sets location for this branch.
     * 
     * @param loc
     *            the location of the branch
     */
    void setLocation(Location loc);

}