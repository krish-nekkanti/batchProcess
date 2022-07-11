package com.radian.cuwbilling.common.bo.dto;

import com.radian.cuwbilling.common.bo.codes.ActivationStatus;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * @author nines
 *
 */
public class CustomerSummaryDTO extends BaseDTO
{
    private Long customerID;

    private String customerDisplayID;

    private String customerName;

    private String customerAltName;

    private ActivationStatus customerStatus; // statusCode

    /**
     * Constructor for CustomerSummaryDTO.
     */
    public CustomerSummaryDTO()
    {
        super();
    }

    public CustomerSummaryDTO(Long customerID)
    {
        super();
        this.customerID = customerID;
    }

    /**
     * Returns the customerAltName.
     *
     * @return String
     */
    public String getCustomerAltName()
    {
        return customerAltName;
    }

    /**
     * Returns the customerDisplayID.
     *
     * @return String
     */
    public String getCustomerDisplayID()
    {
        return customerDisplayID;
    }

    /**
     * Returns the customerID.
     *
     * @return Long
     */
    public Long getCustomerID()
    {
        return customerID;
    }

    /**
     * Returns the customerName.
     *
     * @return String
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Sets the customerAltName.
     *
     * @param customerAltName
     *            The customerAltName to set
     */
    public void setCustomerAltName(String customerAltName)
    {
        this.customerAltName = customerAltName;
    }

    /**
     * Sets the customerDisplayID.
     *
     * @param customerDisplayID
     *            The customerDisplayID to set
     */
    public void setCustomerDisplayID(String customerDisplayID)
    {
        this.customerDisplayID = customerDisplayID;
    }

    /**
     * Sets the customerID.
     *
     * @param customerID
     *            The customerID to set
     */
    public void setCustomerID(Long customerID)
    {
        this.customerID = customerID;
    }

    /**
     * Sets the customerName.
     *
     * @param customerName
     *            The customerName to set
     */
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String toString()
    {
        return this.customerDisplayID + ":" + this.customerName;
    }

    /**
     * Sets the customerStatus.
     *
     * @param customerStatus
     *            The customerStatus to set
     */
    public void setCustomerStatus(ActivationStatus customerStatus)
    {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerStatusRaw()
    {
        if (customerStatus == null)
            return null;

        return customerStatus.getID();
    }

    public void setCustomerStatusRaw(Long id)
    {
        this.customerStatus = ActivationStatus.decodeValue(id);
    }
}
