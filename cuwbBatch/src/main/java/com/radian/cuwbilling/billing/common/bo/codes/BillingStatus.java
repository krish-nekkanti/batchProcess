package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * Billing status codes
 * 
 * @author nines
 */
public class BillingStatus extends BaseEnumType
{

    /**
     * BillingStatus: definition of possible values
     */
    public static final BillingStatus NOT_BILLED = new BillingStatus(new Long(150001), "Not Billed", "NB");

    public static final BillingStatus BILLED = new BillingStatus(new Long(150002), "Billed", "B");

    public static final BillingStatus NOT_PRICED = new BillingStatus(new Long(150003), "Not Priced", "NP");

    /**
     * System code domain
     */
    private static final BillingStatus[] domain = { NOT_BILLED, BILLED, NOT_PRICED };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private BillingStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingStatus()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingStatus instance(BillingStatus code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingStatus
     * @return BillingStatus for the enumerated type
     */
    public static final BillingStatus decodeValue(Long codeID)
    {
        return (BillingStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingStatus
     * @return BillingStatus for the enumerated type
     */
    public static final BillingStatus decodeValue(String codeValue)
    {
        return (BillingStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return BillingStatus for the enumerated type
     */
    public static final BillingStatus decodeLabel(String codeLabel)
    {
        return (BillingStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
