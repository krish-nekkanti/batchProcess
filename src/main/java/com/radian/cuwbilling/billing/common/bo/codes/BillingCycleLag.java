/**
 * @(#) BillingCycleLag.java
 */

package com.radian.cuwbilling.billing.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class BillingCycleLag extends BaseEnumType
{
    public static final BillingCycleLag ONE = new BillingCycleLag(new Long(188001), "1", "");

    public static final BillingCycleLag TWO = new BillingCycleLag(new Long(188002), "2", "");

    public static final BillingCycleLag THREE = new BillingCycleLag(new Long(188003), "3", "");

    public static final BillingCycleLag FOUR = new BillingCycleLag(new Long(188004), "4", "");

    public static final BillingCycleLag FIVE = new BillingCycleLag(new Long(188005), "5", "");

    public static final BillingCycleLag SIX = new BillingCycleLag(new Long(188006), "6", "");

    public static final BillingCycleLag SEVEN = new BillingCycleLag(new Long(188007), "7", "");

    public static final BillingCycleLag EIGHT = new BillingCycleLag(new Long(188008), "8", "");

    public static final BillingCycleLag NINE = new BillingCycleLag(new Long(188009), "9", "");

    public static final BillingCycleLag TEN = new BillingCycleLag(new Long(188010), "10", "");

    public static final BillingCycleLag ELEVEN = new BillingCycleLag(new Long(188011), "11", "");

    public static final BillingCycleLag TWELVE = new BillingCycleLag(new Long(188012), "12", "");

    public static final BillingCycleLag THIRTEEN = new BillingCycleLag(new Long(188013), "13", "");

    public static final BillingCycleLag FOURTEEN = new BillingCycleLag(new Long(188014), "14", "");

    public static final BillingCycleLag FIFTEEN = new BillingCycleLag(new Long(188015), "15", "");

    public static final BillingCycleLag SIXTEEN = new BillingCycleLag(new Long(188016), "16", "");

    public static final BillingCycleLag SEVENTEEN = new BillingCycleLag(new Long(188017), "17", "");

    public static final BillingCycleLag EIGHTEEN = new BillingCycleLag(new Long(188018), "18", "");

    public static final BillingCycleLag NINETEEN = new BillingCycleLag(new Long(188019), "19", "");

    public static final BillingCycleLag TWENTY = new BillingCycleLag(new Long(188020), "20", "");

    public static final BillingCycleLag TWENTYONE = new BillingCycleLag(new Long(188021), "21", "");

    public static final BillingCycleLag TWENTYTWO = new BillingCycleLag(new Long(188022), "22", "");

    public static final BillingCycleLag TWENTYTHREE = new BillingCycleLag(new Long(188023), "23", "");

    public static final BillingCycleLag TWENTYFOUR = new BillingCycleLag(new Long(188024), "24", "");

    public static final BillingCycleLag TWENTYFIVE = new BillingCycleLag(new Long(188025), "25", "");

    public static final BillingCycleLag TWENTYSIX = new BillingCycleLag(new Long(188026), "26", "");

    public static final BillingCycleLag TWENTYSEVEN = new BillingCycleLag(new Long(188027), "27", "");

    public static final BillingCycleLag TWENTYEIGHT = new BillingCycleLag(new Long(188028), "28", "");

    public static final BillingCycleLag TWENTYNINE = new BillingCycleLag(new Long(188029), "29", "");

    public static final BillingCycleLag THIRTY = new BillingCycleLag(new Long(188030), "30", "");

    // public static final BillingCycleLag THIRTYONE = new BillingCycleLag(new
    // Long(31), "31","");

    private static final BillingCycleLag[] domain = { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN,
            SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY, TWENTYONE, TWENTYTWO, TWENTYTHREE, TWENTYFOUR, TWENTYFIVE, TWENTYSIX, TWENTYSEVEN, TWENTYEIGHT,
            TWENTYNINE, THIRTY };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public BillingCycleLag(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public BillingCycleLag()
    {
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final BillingCycleLag instance(BillingCycleLag code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - BillingCycleLag
     * 
     * @return BillingCycleLag for the enumerated type
     */
    public static final BillingCycleLag decodeValue(Long codeID)
    {
        return (BillingCycleLag) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - BillingCycleLag
     * 
     * @return BillingCycleLag for the enumerated type
     */
    public static final BillingCycleLag decodeValue(String codeValue)
    {
        return (BillingCycleLag) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * 
     * @return BillingCycleLag for the enumerated type
     */
    public static final BillingCycleLag decodeLabel(String codeLabel)
    {
        return (BillingCycleLag) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
