package com.radian.cuwbilling.system.document.bs.ejb;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;

import com.radian.cuwbilling.system.document.bo.codes.DeliveryPackageType;
import com.radian.cuwbilling.system.document.bo.dto.DeliveryServiceDTO;
import com.radian.cuwbilling.system.document.bs.DocumentException;
import com.radian.cuwbilling.system.document.bs.DocumentValidationException;
import com.radian.cuwbilling.system.document.bs.TradingPartnerSearchCriteria;

/**
 * This is the Remote interface for the TradingPartnerAdmin EJB.
 * 
 * @author jsumner
 */
public interface TradingPartnerAdminRemote extends EJBObject
{
    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#create(DeliveryServiceDTO)
     */
    public Long create(DeliveryServiceDTO deliveryService) throws DocumentException, DocumentValidationException, RemoteException;

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getByID(Long)
     */
    public DeliveryServiceDTO getByID(Long deliveryServiceID) throws DocumentException, RemoteException;

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getDeliveryServices(Long,Long)
     */
    public Collection getDeliveryServices(Long customerID, DeliveryPackageType deliveryPackageType) throws DocumentException, RemoteException;

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getDeliveryServicesByPackageID(Long)
     */
    public Collection getDeliveryServicesByPackageID(Long packageDeliveryServiceID) throws DocumentException, RemoteException;
    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getDeliveryServiceSummary(TradingPartnerSearchCriteria)
     */
    public Collection getDeliveryServiceSummary(TradingPartnerSearchCriteria criteria) throws DocumentException, RemoteException;

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#update(DeliveryServiceDTO)
     */
    public void update(DeliveryServiceDTO deliveryService) throws DocumentException, DocumentValidationException, RemoteException;

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getAvailableDeliveryPackageTypes(Long)
     */
    public Collection getAvailableDeliveryPackageTypes(Long CustomerID) throws DocumentException, RemoteException;
}
