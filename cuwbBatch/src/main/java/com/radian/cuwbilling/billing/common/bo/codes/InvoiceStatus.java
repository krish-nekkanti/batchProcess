package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is InvoiceStatus, which indicates the status of a given
 * invoice item.
 * 
 * @author
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class InvoiceStatus extends BaseEnumType
{

    /**
     * InvoiceStatus: definition of possible values
     */
    public static final InvoiceStatus PAID_IN_FULL = new InvoiceStatus(new Long(208001), "Paid in full", "P");

    public static final InvoiceStatus OPEN = new InvoiceStatus(new Long(208002), "Open", "O");

    /*
     * Sunil Are 07/28/04 3:30PM commented out as per the use case change. No
     * longer using this invoice status
     */
    // public static final InvoiceStatus CLOSED = new InvoiceStatus(new
    // Long(208003), "Closed", "C");
    public static final InvoiceStatus CREATED = new InvoiceStatus(new Long(208004), "Created", "CR");

    public static final InvoiceStatus DOCUMENT_GENERATED = new InvoiceStatus(new Long(208005), "Document Generated", "DG");

    public static final InvoiceStatus DOCUMENT_SENT = new InvoiceStatus(new Long(208006), "Document Sent", "DS");

    public static final InvoiceStatus EXPORTED_TO_AR = new InvoiceStatus(new Long(208007), "Exported To AR", "AR");

    /**
     * System code domain
     */
    private static final InvoiceStatus[] domain = { PAID_IN_FULL, OPEN, CREATED, DOCUMENT_GENERATED, DOCUMENT_SENT, EXPORTED_TO_AR };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private InvoiceStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public InvoiceStatus()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final InvoiceStatus instance(InvoiceStatus code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - InvoiceStatus
     * @return InvoiceStatus for the enumerated type
     */
    public static final InvoiceStatus decodeValue(Long codeID)
    {
        return (InvoiceStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - InvoiceStatus
     * @return InvoiceStatus for the enumerated type
     */
    public static final InvoiceStatus decodeValue(String codeValue)
    {
        return (InvoiceStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return InvoiceStatus for the enumerated type
     */
    public static final InvoiceStatus decodeLabel(String codeLabel)
    {
        return (InvoiceStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
