/**
 * @(#) PackageDeliveryService.java
 */

package com.radian.cuwbilling.system.document.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

public interface PackageDeliveryService extends BaseDomainObject
{
    /**
     * @return
     */
    String getDeliveryAddress();

    /**
     * @return
     */
    TradingPartnerDeliveryService getTradingPartnerDeliveryService();

    /**
     * @param address
     */
    void setDeliveryAddress(String address);

    /**
     * @param service
     */
    void setTradingPartnerDeliveryService(TradingPartnerDeliveryService service);
    
    /**
     * 
     * @param id
     */
    void setDeliveryItemProfileID( Long id);
    
    /**
     *
     */
    Long getDeliveryItemProfileID();

}
