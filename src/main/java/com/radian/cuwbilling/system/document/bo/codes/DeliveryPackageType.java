/**
 * @(#) DeliveryPackageType.java
 */

package com.radian.cuwbilling.system.document.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class DeliveryPackageType extends BaseEnumType
{
    public static final DeliveryPackageType CUW_INVOICE_PACKAGE = new DeliveryPackageType(new Long(39001), "CUW Invoice Package", "IP");

    public static final DeliveryPackageType DECISION_PACKAGE = new DeliveryPackageType(new Long(39002), "Decision Package", "DP");

    private static final DeliveryPackageType[] domain = { CUW_INVOICE_PACKAGE, DECISION_PACKAGE };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private DeliveryPackageType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final DeliveryPackageType instance(DeliveryPackageType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - CustomerType
     * 
     * @return CustomerType for the enumerated type
     */
    public static final DeliveryPackageType decodeValue(Long codeID)
    {
        return (DeliveryPackageType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - CustomerType
     * 
     * @return CustomerType for the enumerated type
     */
    public static final DeliveryPackageType decodeValue(String codeValue)
    {
        return (DeliveryPackageType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return CustomerType for the enumerated type
     */
    public static final DeliveryPackageType decodeLabel(String codeLabel)
    {
        return (DeliveryPackageType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
