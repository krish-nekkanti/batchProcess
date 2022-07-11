/*
 * BillingProfile.java
 *
 * Created on September 3, 2003, 1:57 PM
 */

package com.radian.cuwbilling.billing.common.bo.domain;

import java.util.Collection;

import com.radian.cuwbilling.billing.common.bo.codes.BillingProfileStatus;
import com.radian.cuwbilling.billing.cuw.bo.domain.BillingDetailRecord;
import com.radian.cuwbilling.common.bo.codes.USState;
import com.radian.cuwbilling.common.bo.domain.AxiomEntity;
import com.radian.cuwbilling.common.bo.domain.Customer;
import com.radian.cuwbilling.common.bo.domain.EmailAddress;
import com.radian.cuwbilling.common.bo.domain.Location;
import com.radian.cuwbilling.common.bo.domain.Phone;
import com.radian.cuwbilling.system.document.bo.domain.PackageDeliveryService;

public interface BillingProfile extends AxiomEntity
{
    public Customer getCustomer();

    public void setCustomer(Customer cust);

    public BillingProfileStatus getBillingProfileStatus();

    public void setBillingProfileStatus(BillingProfileStatus status);

    public Location getBillToLocation();

    public void setBillToLocation(Location billToLocation);

    public String getBillToAttention();

    public void setBillToAttention(String billToAttention);

    public Phone getBillToFax();

    public void setBillToFax(Phone billToFax);

    public String getBillToInvoiceName();

    public void setBillToInvoiceName(String billToInvoiceName);

    public Phone getBillToPhone();

    public void setBillToPhone(Phone billToPhone);

    public Collection getBillToEmails();

    public void addBillToEmail(EmailAddress email);

    public void removeBillToEmail(EmailAddress email);

    public void removeBillToEmails();

    public String getBillingProfileDisplayID();

    public void setBillingProfileDisplayID(String id);

    public Collection getBillingDetailRecords();

    public void addBillingDetailRecord(BillingDetailRecord bdr);

    public void removeBillingDetailRecord(BillingDetailRecord bdr);

    public PackageDeliveryService getPackageDeliveryService();

    public void setPackageDeliveryService(PackageDeliveryService packageDeliveryService);

    public Long getBillingProfileRootID();

    public void setBillingProfileRootID(Long billingProfileRootID);

    public Long getBillingProfileVersionID();

    public void setBillingProfileVersionID(Long billingProfileVersionID);

    public String getCity();

    public String getPostalFiveCode();

    public String getPostalFourCode();

    public USState getState();

    public String getStreetAddressLine1();

    public String getStreetAddressLine2();

    public String getStreetAddressLine3();

    public void setCity(final String city);

    public void setPostalFiveCode(final String postalfivecode);

    public void setPostalFourCode(final String postalfourcode);

    public void setState(final USState state);

    public void setStreetAddressLine1(final String streetaddressline1);

    public void setStreetAddressLine2(final String streetaddressline2);

    public void setStreetAddressLine3(final String streetaddressline3);
    
    public BillingProfileVersionInfo getVersionInfo();
    
    public Boolean getBillingProfileVersionIndicator();
}
