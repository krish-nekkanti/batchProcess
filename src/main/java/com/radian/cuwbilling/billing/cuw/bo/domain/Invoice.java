/*
 * Invoice.java
 *
 * Created on September 5, 2003, 6:04 PM
 */

package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Collection;
import java.util.Date;

import com.radian.cuwbilling.billing.common.bo.codes.InvoiceStatus;
import com.radian.cuwbilling.billing.common.bo.codes.RevisionStatus;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;
import com.radian.cuwbilling.common.bo.domain.Money;

public interface Invoice extends BaseDomainObject
{
    void addARTrx(ARTrx artrx);

    void addBillingDetailRecord(BillingDetailRecord bdr);
    
    void addManualBDR(BillingDetailRecord bdr);

    void addInvoiceAttachment(InvoiceAttachment attachment);

    void addInvoiceTransmission(InvoiceTransmission transmission);

    void addRevisedInvoice(Invoice invoice);

    String getBillingProfile();

    Collection getARTransactions();

    Collection getBdrs();
    
    Collection getTrialManualBDRs();

    CUWBillingPeriod getBillingPeriod();

    Collection getInvoiceAttachments();

    Money getInvoiceAmount();

    Date getInvoiceDate();

    Date getInvoiceDueDate();

    String getInvoiceNo();

    Date getInvoiceSentDate();

    InvoiceStatus getInvoiceStatus();

    Collection getInvoiceTransmissions();

    Invoice getOriginalInvoice();

    Collection getRevisedInvoices();

    Integer getRevisionSeqNo();

    RevisionStatus getRevisionStatus();

    Money getTotalAdjustmentAmount();

    Money getTotalPaymentAmount();

    Boolean getPreloadedIndicator();
    
    String getStreetAddr1();
    
    String getStreetAddr2();
    
    String getStreetAddr3();
    
    String getCity();
    
    String getState();
    
    String getZip();
    
    String getZipPlus4();
    
    String getDeliveryService();
    
    void setDeliveryService(String deliveryService);

    void removeARTrx(ARTrx artrx);

    void removeBillingDetailRecord(BillingDetailRecord bdr);
    
    void removeManualBDR(BillingDetailRecord bdr);

    void removeDocument(InvoiceAttachment attachment);

    void removeInvoiceTransmission(InvoiceTransmission transmission);

    void removeRevisedInvoice(Invoice invoice);

    void setBillingProfile(String profile);

    void setBillingPeriod(CUWBillingPeriod period);

    void setInvoiceAmount(Money amount);

    void setInvoiceDate(Date invoiceDate);

    void setInvoiceDueDate(Date invoiceDueDate);

    void setInvoiceNo(String invoiceNumber);
    
    void setStreetAddr1(String addr1);
    
    void setStreetAddr2(String addr2);
    
    void setStreetAddr3(String addr3);
    
    void setCity(String city);
    
    void setState(String state);
    
    void setZip(String zip);
    
    void setZipPlus4(String zipPlus4);

    void setInvoiceSentDate(Date invoiceSentDate);

    void setInvoiceStatus(InvoiceStatus status);

    void setOriginalInvoice(Invoice invoice);

    void setRevisionSeqNo(Integer revisionSeqNo);

    void setRevisionStatus(RevisionStatus revisionStatus);

    void setTotalAdjustmentAmount(Money money);

    void setPreloadedIndicator(Boolean preloadedIndicator);

    Money getTotalOutstandingAmount();

    void setTotalOutstandingAmount(Money totalOutstandingAmount);

    Money getTotalPaymentDueAmount();

    void setTotalPaymentDueAmount(Money totalPaymentDueAmount);

    Money getPreviousBalance();

    void setPreviousBalance(Money previousBalance);

    public Collection getOvertimeBDRs();

    public Collection getPerLoanBDRs();

    public Collection getUWPerDiemBDRs();

    public Collection getOtherPerDiemBDRs();

    public Collection getExpenseBDRs();

    public Collection getAdjustmentBDRs();
    
String getCustNum();
    
    void setCustNum(String num);
    
    String getCustName();
    
    void setCustName(String name);
    
    String getBillToName();
    
    void setBillToName(String billTo);
    
    String getAttentionName();
    
    void setAttentionName(String name);
    
    String getBillToPhone();
    
    void setBillToPhone(String phone);
    
    String getPhoneExt();
    
    void setPhoneExt(String ext);
    
    String getNotes();
    
    void setNotes(String notes);
    
    Boolean getIncludeCSV();
    
    void setIncludeCSV(Boolean flag);
}
