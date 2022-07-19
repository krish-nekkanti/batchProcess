/**
 * @(#) BillingPeriodType.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class ARTransmissionStatus extends BaseEnumType
{
    public static final ARTransmissionStatus READY = new ARTransmissionStatus(new Long(211001), "Ready", "R");

    public static final ARTransmissionStatus SENT = new ARTransmissionStatus(new Long(211002), "Sent", "S");

    public static final ARTransmissionStatus ACKNOWLEDGED = new ARTransmissionStatus(new Long(211003), "Acknowledged", "A");

    private static final ARTransmissionStatus[] domain = { READY, SENT, ACKNOWLEDGED };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public ARTransmissionStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public ARTransmissionStatus()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final ARTransmissionStatus instance(ARTransmissionStatus code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingPeriodType
     * 
     * @return BillingPeriodType for the enumerated type
     */
    public static final ARTransmissionStatus decodeValue(Long codeID)
    {
        return (ARTransmissionStatus) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingPeriodType
     * 
     * @return BillingPeriodType for the enumerated type
     */
    public static final ARTransmissionStatus decodeValue(String codeValue)
    {
        return (ARTransmissionStatus) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return BillingPeriodType for the enumerated type
     */
    public static final ARTransmissionStatus decodeLabel(String codeLabel)
    {
        return (ARTransmissionStatus) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
