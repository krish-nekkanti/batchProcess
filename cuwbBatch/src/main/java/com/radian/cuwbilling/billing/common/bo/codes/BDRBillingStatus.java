package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is BDRBillingStatus, which indicates the status of a
 * given invoice item.
 * 
 * @author
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class BDRBillingStatus extends BaseEnumType
{

    /**
     * BDRBillingStatus: definition of possible values
     */
    public static final BDRBillingStatus NOT_BILLED = new BDRBillingStatus(new Long(210002), "Not Billed", "NB");

    public static final BDRBillingStatus BILLED = new BDRBillingStatus(new Long(210001), "Billed", "B");

    /**
     * System code domain
     */
    private static final BDRBillingStatus[] domain = { NOT_BILLED, BILLED };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private BDRBillingStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    private BDRBillingStatus()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BDRBillingStatus instance(BDRBillingStatus code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BDRBillingStatus
     * @return BDRBillingStatus for the enumerated type
     */
    public static final BDRBillingStatus decodeValue(Long codeID)
    {
        return (BDRBillingStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BDRBillingStatus
     * @return BDRBillingStatus for the enumerated type
     */
    public static final BDRBillingStatus decodeValue(String codeValue)
    {
        return (BDRBillingStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return BDRBillingStatus for the enumerated type
     */
    public static final BDRBillingStatus decodeLabel(String codeLabel)
    {
        return (BDRBillingStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
