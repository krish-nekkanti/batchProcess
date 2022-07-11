package com.radian.cuwbilling.common.bo.domain;

import java.util.Collection;
import java.util.Date;

import com.radian.cuwbilling.common.bo.codes.USState;

/**
 * This interface defines Customer business domain object
 *
 * A Customer is an organization with whom Radian interacts as part of their
 * business operations. Examples of Customers are Lenders, Banks, Credit Unions
 * and Brokers.
 *
 * @version 1.0
 */
public interface Customer extends BaseDomainObject
{
    /**
     * @Return The alternate name for this Customer.
     */
    String getAltName();

    /**
     * Get the displayable id for the object.
     *
     * @return the id as a string
     */
    public String getDisplayID();

    /**
     * Set the displayable ID for the object.
     *
     * @param id
     *            the id to set
     */
    public void setDisplayID(String id);

    public String getName();

    public void setName(String name);
    
    /**
     * Assigns an alternate name to this Customer.
     * 
     * @param altName
     *            The alternate name for this Customer.
     */
    void setAltName(String altName);
}
