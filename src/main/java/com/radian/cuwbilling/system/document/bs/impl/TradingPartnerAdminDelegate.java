/**
 * @(#) TradingPartnerAdminDelegate.java
 */

package com.radian.cuwbilling.system.document.bs.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;

import com.radian.cuwbilling.system.document.bo.codes.DeliveryPackageType;
import com.radian.cuwbilling.system.document.bo.dto.DeliveryServiceDTO;
import com.radian.cuwbilling.system.document.bs.DocumentException;
import com.radian.cuwbilling.system.document.bs.DocumentValidationException;
import com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration;
import com.radian.cuwbilling.system.document.bs.TradingPartnerSearchCriteria;
import com.radian.cuwbilling.system.document.bs.ejb.TradingPartnerAdminRemote;
import com.radian.cuwbilling.system.document.bs.ejb.TradingPartnerAdminRemoteHome;
import com.radian.foundation.bs.BaseService;
import com.radian.foundation.common.util.ServiceLocator;
import com.radian.foundation.common.util.ServiceLocatorException;

public class TradingPartnerAdminDelegate extends BaseService implements TradingPartnerAdministration
{
    public static final String TRADING_PARTNER_ADMIN_REMOTE_HOME_JNDI = "com/radian/cuwbilling/system/document/bs/ejb/TradingPartnerAdminRemoteHome";

    /**
     * The default constructor
     */
    public TradingPartnerAdminDelegate()
    {
        super();
    }

    /**
     * Helper method to handle initial context lookup and get a reference to the
     * EJB.
     * 
     * @return TradingPartnerAdminRemote a reference to the remote EJB object
     * @throws DocumentException
     */
    private TradingPartnerAdminRemote getTradingPartnerAdminRemote() throws DocumentException
    {
        try
        {
            TradingPartnerAdminRemoteHome home = (TradingPartnerAdminRemoteHome) ServiceLocator.getInstance().getEJBHome(TradingPartnerAdminRemoteHome.class,
                    TRADING_PARTNER_ADMIN_REMOTE_HOME_JNDI);
            return home.create();
        } catch (ServiceLocatorException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.serviceLocator", new Object[] { e.getMessage() });
        } catch (CreateException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.create", new Object[] { e.getMessage() });
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.remote", new Object[] { e.getMessage() });
        }
    }

    /**
     * Creates a new instance of TradingPartnerAdminDelegate
     * 
     * @return a new instance of TradingPartnerAdminDelegate
     */
    public static TradingPartnerAdministration instance()
    {
        return (TradingPartnerAdministration) instance(TradingPartnerAdminDelegate.class);
    }

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#create(DeliveryServiceDTO)
     */
    public Long create(DeliveryServiceDTO deliveryService) throws DocumentException, DocumentValidationException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            return ejb.create(deliveryService);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getByID(Long)
     */
    public DeliveryServiceDTO getByID(Long deliveryServiceID) throws DocumentException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            return ejb.getByID(deliveryServiceID);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getDeliveryServices(Long,Long)
     */
    public Collection getDeliveryServices(Long customerID, DeliveryPackageType deliveryPackageType) throws DocumentException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            return ejb.getDeliveryServices(customerID, deliveryPackageType);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getDeliveryServicesByPackageID(Long)
     */
    public Collection getDeliveryServicesByPackageID(Long packageDeliveryServiceID) throws DocumentException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            return ejb.getDeliveryServicesByPackageID(packageDeliveryServiceID);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }
    
    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getDeliveryServiceSummary(TradingPartnerSearchCriteria)
     */
    public Collection getDeliveryServiceSummary(TradingPartnerSearchCriteria criteria) throws DocumentException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            return ejb.getDeliveryServiceSummary(criteria);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#update(DeliveryServiceDTO)
     */
    public void update(DeliveryServiceDTO deliveryService) throws DocumentException, DocumentValidationException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            ejb.update(deliveryService);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }

    /**
     * @see com.radian.cuwbilling.system.document.bs.TradingPartnerAdministration#getAvailableDeliveryPackageTypes(Long)
     */
    public Collection getAvailableDeliveryPackageTypes(Long CustomerID) throws DocumentException
    {
        try
        {
            TradingPartnerAdminRemote ejb = getTradingPartnerAdminRemote();
            return ejb.getAvailableDeliveryPackageTypes(CustomerID);
        } catch (RemoteException e)
        {
            e.printStackTrace();
            throw new DocumentException("excep.general", e);
        }
    }
}
