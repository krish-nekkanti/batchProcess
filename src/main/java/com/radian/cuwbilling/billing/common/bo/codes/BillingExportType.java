/*
 * BillingExportType.java
 *
 * Created on March 3, 2004, 11:01 AM
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * 
 * @author sguddimath
 */
public class BillingExportType extends BaseEnumType
{

    public static final BillingExportType INVOICE_EXPORT = new BillingExportType(new Long(200001), "InvoiceExport", "IE");

    public static final BillingExportType ADJUSTMENT_EXPORT = new BillingExportType(new Long(200002), "AdjustmentExport", "AE");

    public BillingExportType()
    {
    }

    private BillingExportType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    /**
     * System code domain
     */
    private static final BillingExportType[] domain = { INVOICE_EXPORT, ADJUSTMENT_EXPORT };

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingExportType instance(BillingExportType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingExportType
     * @return BillingExportType for the enumerated type
     */
    public static final BillingExportType decodeValue(Long codeID)
    {
        return (BillingExportType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingExportType
     * @return InvoiceItemStatus for the enumerated type
     */
    public static final BillingExportType decodeValue(String codeValue)
    {
        return (BillingExportType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return BillingExportType for the enumerated type
     */
    public static final BillingExportType decodeLabel(String codeLabel)
    {
        return (BillingExportType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
