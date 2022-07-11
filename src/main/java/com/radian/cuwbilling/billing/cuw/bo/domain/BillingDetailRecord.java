/**
 * @(#) BillingDetailRecord.java
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Collection;
import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.BDRBillingStatus;
import com.radian.cuwbilling.billing.common.bo.codes.BDRType;
import com.radian.cuwbilling.billing.common.bo.domain.BillingProfile;
import com.radian.cuwbilling.common.bo.codes.UnitOfMeasure;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface BillingDetailRecord extends BaseDomainObject
{
    int compareTo(Object obj);

    BDRType getBDRType();

    void setBDRType(BDRType bdrType);

    void addBDRCostCenterAllocation(BDRCostCenterAllocation bdrCostCenterAllocation);

    void removeBDRCostCenterAllocation(BDRCostCenterAllocation bdrCostCenterAllocation);

    Collection getBDRCostCenterAllocations();

    Collection getTrialInvoices();
    
    void addBDRGLAccountAllocation(BDRGLAccountAllocation bdrGLAccountAllocation);

    void removeBDRGLAccountAllocation(BDRGLAccountAllocation bdrGLAccountrAllocation);
    
    void addTrialInvoice(Invoice invoice);

    void removeTrialInvoice(Invoice invoice);

    Collection getBDRGLAccountAllocations();

    BDRBillingStatus getBillingStatus();

    Date getItemDate();

    String getItemDesc();

    Money getItemTotalPrice();

    Double getQuantity();

    UnitOfMeasure getUnits();

    void setBillingStatus(BDRBillingStatus status);

    void setItemDate(Date date);

    void setItemDesc(String des);

    void setItemTotalPrice(Money amt);

    void setQuantity(Double qty);

    void setUnits(UnitOfMeasure units);

    Invoice getInvoice();

    void setInvoice(Invoice invoice);

    String getBillingProfile();

    void setBillingProfile(String profileNum);

    Collection getAttachments();

    void addAttachment(InvoiceAttachment doc);
    
    String getRepID();
    
    String getUnderwriterFirstName();
    
    String getUnderwriterLastName();
    
    String getUnderwriterPosition();
    
    String getUnderwriterLocation();
    
    void setRepID(String repID);
    
    void setUnderwriterFirstName(String firstName);
    
    void setUnderwriterLastName(String lastName);
    
    void setUnderwriterPosition(String position);
    
    void setUnderwriterLocation(String location);
    
    

}
