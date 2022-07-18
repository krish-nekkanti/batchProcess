/*
 * InvoiceTransmission.java
 *
 * Created on September 5, 2003, 6:04 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.billing.common.bo.codes.RevisionStatus;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Location;

/**
 * 
 * @author DLeed
 */
public interface InvoiceTransmission extends BaseDomainObject
{
    void addOverrideEmail(InvoiceEmail email);

    String getAttentionOf();

    String getDeliveryService();

    Location getOverrideAddress();

    Collection getOverrideEmails();

    String getOverridePhone();

    String getOverridePhoneExt();

    String getTrackingID();

    Invoice getInvoice();

    RevisionStatus getInvoiceStatus();

    void removeOverrideEmail(InvoiceEmail email);

    void setAttentionOf(String attentionOf);

    void setOverrideAddress(Location address);

    void setOverridePhone(String phone);

    void setOverridePhoneExt(String ext);

    void setTrackingID(String trackingID);

    void setInvoice(Invoice invoice);

    void setDeliveryService(String deliveryService);

    String getOverrideFax();

    void setOverrideFax(String overrideFax);

    Boolean isIncludeDetailFlag();

    void setIncludeDetailFlag(Boolean includeDetail);

    String getNameOnInvoice();

    void setNameOnInvoice(String name);

    void setInvoiceStatus(RevisionStatus status);

}
