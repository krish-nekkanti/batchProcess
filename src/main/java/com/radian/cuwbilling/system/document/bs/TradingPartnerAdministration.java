/*
 * TradingPartnerAdministration.java
 *
 * Created on November 14, 2003
 */

package com.radian.cuwbilling.system.document.bs;

import java.rmi.RemoteException;
import java.util.Collection;

import com.radian.cuwbilling.system.document.bo.codes.DeliveryPackageType;
import com.radian.cuwbilling.system.document.bo.dto.DeliveryServiceDTO;

/**
 * This interface specifies the public business service interface for the
 * trading partner administration service. All clients using this service should
 * do so through this API.
 * 
 * @author jsumner
 */
public interface TradingPartnerAdministration
{
    /**
     * This method creates a new trading partner delivery service using data
     * from the supplied DeliveryServiceDTO. The method returns the Long ID of
     * the newly created delivery service.
     * 
     * @return A Long representing the ID of the newly created delivery service.
     * @param deliveryService
     *            A DeliveryServiceDTO object containing the data for the new
     *            delivery service.
     * @throws DocumentException
     *             Thrown if a processing error occurs.
     * @throws DocumentValidationException
     *             Thrown if a data validation error occurs with the data
     *             provided in the DTO.
     */
    public Long create(DeliveryServiceDTO deliveryService) throws DocumentException, DocumentValidationException;

    /**
     * This method returns a DeliveryServiceDTO representing the delivery
     * service with the supplied ID.
     * 
     * @return A DeliveryServiceDTO representing the requested delivery service.
     * @param deliveryServiceID
     *            The ID of the delivery service that is to be returned.
     * @throws DocumentException
     *             Thrown if a processing error occurs.
     */
    public DeliveryServiceDTO getByID(Long deliveryServiceID) throws DocumentException;

    /**
     * This method returns a Collection of DeliveryServiceDTO objects
     * representing all delivery services applicable to the specified customer
     * and delivery package type. NOTE: delivery services specific to the
     * supplied customer as well as generic (non-customer specific) delivery
     * services are returned, all of which match the specified delivery package
     * type. If customerID is null, then all generic delivery services matching
     * the specified delivery package type are returned.
     * 
     * @return A Collection of DeliveryServiceDTO objects.
     * @param customerID
     *            The ID of the customer whose delivery services (plus generic
     *            ones) are to be returned.
     * @param deliveryPackageType
     *            The delivery package type used to determine which delivery
     *            services to return.
     * @throws DocumentException
     *             Thrown if a processing error occurs.
     */
    public Collection getDeliveryServices(Long customerID, DeliveryPackageType deliveryPackageType) throws DocumentException;

    /**
     * 
     * @param packageDeliveryServiceID
     * @return
     * @throws DocumentException
     * @throws RemoteException
     */
    public Collection getDeliveryServicesByPackageID(Long packageDeliveryServiceID) throws DocumentException, RemoteException;
    
    /**
     * This method returns a Collection of DeliveryServiceDTO objects
     * representing all delivery services that match the specified search
     * criteria.
     * 
     * @return A Collection of DeliveryServiceDTO objects matching the specified
     *         search criteria.
     * @param criteria
     *            the criteria to use for the search.
     * @throws DocumentException
     *             Thrown if a processing error occurs.
     */
    public Collection getDeliveryServiceSummary(TradingPartnerSearchCriteria criteria) throws DocumentException;

    /**
     * This method updates a delivery service with new information provided by a
     * DeliveryServiceDTO.
     * 
     * @param deliveryService
     *            The DeliveryServiceDTO containing the new information for the
     *            delivery service.
     * @throws DocumentException
     *             Thrown if a processing error occurs.
     * @throws DocumentValidationException
     */
    public void update(DeliveryServiceDTO deliveryService) throws DocumentException, DocumentValidationException;

    /**
     * This method is used to determine the possible delivery package types
     * based on a customerID. The customerID is combined with each package type
     * and a determination is made to see if there is at least one available
     * delivery service. If there is, then the package type is included in the
     * returned Collection.
     * 
     * @param CustomerID
     *            The ID of the customer to examine
     * @return A Collection of DeliveryPackageType enum objects containing all
     *         available delivery package types for the specified customer.
     * @throws DocumentException
     *             Thrown if a processing error occurs.
     */
    public Collection getAvailableDeliveryPackageTypes(Long CustomerID) throws DocumentException;
}
