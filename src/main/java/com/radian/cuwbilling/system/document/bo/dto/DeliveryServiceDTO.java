/*
 * DeliveryServiceDTO.java
 *
 * Created on November 14, 2003, 2:15 PM
 */

package com.radian.cuwbilling.system.document.bo.dto;

import java.util.Collection;

import com.radian.cuwbilling.common.bo.codes.ActivationStatus;
import com.radian.cuwbilling.common.bo.dto.CustomerSummaryDTO;
import com.radian.foundation.bo.codes.BaseEnumType;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * This class is a DTO used to communicate Delivery Service information between
 * the business service and the client.
 * 
 * @author jsumner
 */
public class DeliveryServiceDTO extends BaseDTO implements Comparable
{

    /** This is the unique identifier for the delivery service. */
    private Long deliveryServiceID;

    /**
     * This contains the customer information for a customer-specific delivery
     * service.
     */
    private CustomerSummaryDTO customer;

    /**
     * This flag is used to indicate whether the delivery service is generic or
     * specific to a particular customer.
     */
    private boolean customerSpecificFlag;

    /* This is the delivery method */
    private BaseEnumType deliveryMethod;

    /** This is the name of the trading partner delivery service. */
    private String serviceName;

    /** This is the activation status of the delivery service. */
    private ActivationStatus status;

    /**
     * This is a Collection of DeliveryPackageType enum objects that represent
     * the selected delivery package types for the delivery service.
     */
    private Collection deliveryPackageTypes;

    /** This is the the trading partner service contract ID. */
    private String serviceContractID;

    /** Creates a new instance of DeliveryServiceDTO */
    public DeliveryServiceDTO()
    {
    }

    /**
     * This method returns the delivery service ID.
     * 
     * @return the delivery service ID
     */
    public Long getDeliveryServiceID()
    {
        return this.deliveryServiceID;
    }

    /**
     * This method sets the delivery service ID
     * 
     * @param deliveryServiceID
     *            the new delivery service ID
     */
    public void setDeliveryServiceID(Long deliveryServiceID)
    {
        this.deliveryServiceID = deliveryServiceID;
    }

    /**
     * This method indicates whether the delivery service is generic or specific
     * to a particular customer.
     * 
     * @return true if the delivery service is specific to a customer, false
     *         otherwise.
     */
    public boolean isCustomerSpecific()
    {
        return this.customerSpecificFlag;
    }

    /**
     * This method sets the customer specific flag.
     * 
     * @param customerSpecificFlag
     *            The new value for the customer specific flag.
     */
    public void setCustomerSpecificFlag(boolean customerSpecificFlag)
    {
        this.customerSpecificFlag = customerSpecificFlag;
    }

    /**
     * This method returns the service name.
     * 
     * @return the service name.
     */
    public String getServiceName()
    {
        return this.serviceName;
    }

    /**
     * This method sets the service name.
     * 
     * @param serviceName
     *            the new value for the service name.
     */
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    /**
     * This method returns the activation status.
     * 
     * @return the activation status
     */
    public ActivationStatus getStatus()
    {
        return this.status;
    }

    /**
     * This method sets the activation status.
     * 
     * @param status
     *            the new value for the activation status
     */
    public void setStatus(ActivationStatus status)
    {
        this.status = status;
    }

    /**
     * This method returns a Collection of DeliveryPackageType enum objects
     * representing the delivery service's selected delivery package types.
     * 
     * @return the Collection of delivery package types
     */
    public Collection getDeliveryPackageTypes()
    {
        return this.deliveryPackageTypes;
    }

    /**
     * This method sets the collection of delivery package types.
     * 
     * @param deliveryPackageTypes
     *            the new Collection of delivery package types
     */
    public void setDeliveryPackageTypes(Collection deliveryPackageTypes)
    {
        this.deliveryPackageTypes = deliveryPackageTypes;
    }

    /**
     * This method returns the delivery service's customer
     * 
     * @return the delivery service's customer
     */
    public CustomerSummaryDTO getCustomer()
    {
        return this.customer;
    }

    /**
     * This method sets the delivery service's customer.
     * 
     * @param customer
     *            The customer to set.
     */
    public void setCustomer(CustomerSummaryDTO customer)
    {
        this.customer = customer;
    }

    /**
     * This method returns the delivery service's delivery method
     * 
     * @return the delivery method
     */
    public BaseEnumType getDeliveryMethod()
    {
        return this.deliveryMethod;
    }

    /**
     * This method sets the delivery method.
     * 
     * @param deliveryMethod
     *            the delivery method.
     */
    public void setDeliveryMethod(BaseEnumType deliveryMethod)
    {
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * This method determines if another object is equal to the current one. The
     * test performed compares the Delivery Service ID's, which uniquely
     * identify a delivery service.
     * 
     * @param obj
     *            the object to compare with
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if ((obj != null) && (getClass() == obj.getClass()))
        {
            DeliveryServiceDTO dto = (DeliveryServiceDTO) obj;
            if (deliveryServiceID.equals(dto.getDeliveryServiceID()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * This method is required by the comparable interface. It performs the
     * necessary comparisons between the current object and another one.
     * 
     * @param obj
     *            the object to compare
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    public int compareTo(Object obj)
    {
        DeliveryServiceDTO dto = (DeliveryServiceDTO) obj;
        int returnVal;

        // A TreeSet won't add an item if the compareTo function returns 0. So
        // if two
        // DTO's compare the same, then just allow the current one to be
        // considered less
        // than the supplied one so that the TreeSet will add it.

        if ((this.customer == null) && (dto.customer == null))
        {
            // if both are generic, then sort on service name
            returnVal = this.getServiceName().compareTo(dto.getServiceName());

            // If service name is inconclusive, then try sorting on delivery
            // method.
            if (returnVal == 0)
            {
                returnVal = this.getDeliveryMethod().getDisplayValue().compareTo(dto.getDeliveryMethod().getDisplayValue());
            }
        } else
        {
            // neither is generic, so sort on customer name, but make sure
            // nothing's null first
            if ((this.getCustomer() == null) || (this.getCustomer().getCustomerName() == null))
            {
                returnVal = -1;
            } else if ((dto.getCustomer() == null) || (dto.getCustomer().getCustomerName() == null))
            {
                returnVal = 1;
            } else
            {
                returnVal = this.getCustomer().getCustomerName().compareTo(dto.getCustomer().getCustomerName());

                // sort on service name now
                if (returnVal == 0)
                {
                    returnVal = this.getServiceName().compareTo(dto.getServiceName());
                }

                // If service name is inconclusive, then try sorting on delivery
                // method.
                if (returnVal == 0)
                {
                    returnVal = this.getDeliveryMethod().getDisplayValue().compareTo(dto.getDeliveryMethod().getDisplayValue());
                }
            }
        }

        // If the items are put into a TreeSet, then it will ignore duplicate
        // items (if the returnVal is 0). We don't want to lose these items.
        // If the customer and service name comparison isn't conclusive, then
        // just allow the current object to be considered "less" than the passed
        // in one.
        if (returnVal == 0)
        {
            returnVal = -1;
        }

        return returnVal;
    }

    /**
     * This methods returns the service contract ID of String type
     * 
     * @return service contract ID
     */
    public String getServiceContractID()
    {
        return serviceContractID;
    }

    /**
     * This methods sets the service contract ID of String type
     * 
     * @param serviceContractID
     *            of String type
     */
    public void setServiceContractID(String serviceContractID)
    {
        this.serviceContractID = serviceContractID;
    }

}
