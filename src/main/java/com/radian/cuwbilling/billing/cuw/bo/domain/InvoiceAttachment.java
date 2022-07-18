/*
 * Created on Jan 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.billing.common.bo.codes.InvoiceAttachmentType;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

/**
 * @author tzhou
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface InvoiceAttachment extends BaseDomainObject
{
    Invoice getInvoice();

    BillingDetailRecord getBDR();

    InvoiceAttachmentType getAttachmentType();

    String getFilename();

    DocumentDescriptor getDescriptor();

    void setInvoice(Invoice invoice);

    void setBDR(BillingDetailRecord bdr);

    void setAttachmentType(InvoiceAttachmentType type);

    void setFilename(String filename);

    void setDescriptor(DocumentDescriptor desc);
}
