/*
 * Created on Jan 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * @author tzhou
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InvoiceAttachmentType extends BaseEnumType
{
    public static final InvoiceAttachmentType SYSTEM = new InvoiceAttachmentType(new Long(560001), "System", "System");

    public static final InvoiceAttachmentType BDR = new InvoiceAttachmentType(new Long(560002), "BDR", "BDR");

    public static final InvoiceAttachmentType INVOICE = new InvoiceAttachmentType(new Long(560003), "Invoice", "Invoice");

    /**
     * System code domain
     */
    private static final InvoiceAttachmentType[] domain = { SYSTEM, BDR, INVOICE };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private InvoiceAttachmentType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - InvoiceAttachmentType
     * @return InvoiceAttachmentType for the enumerated type
     */
    public static final InvoiceAttachmentType decodeValue(Long codeID)
    {
        return (InvoiceAttachmentType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - InvoiceAttachmentType
     * @return InvoiceAttachmentType for the enumerated type
     */
    public static final InvoiceAttachmentType decodeValue(String codeValue)
    {
        return (InvoiceAttachmentType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return InvoiceAttachmentType for the enumerated type
     */
    public static final InvoiceAttachmentType decodeLabel(String codeLabel)
    {
        return (InvoiceAttachmentType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
