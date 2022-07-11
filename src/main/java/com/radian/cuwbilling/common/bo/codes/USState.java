package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is BranchTypes, which is a domain of values to describe
 * the possible types of customer branches with which we do business. Branch
 * type is relevant to Customer Branches.
 * 
 * @author MSM
 * @see Type-Safe Enum Pattern
 */
public class USState extends BaseEnumType
{

    /**
     * USState: definition of possible values
     */
    // template is: public static final USState <USStatename> = new USState(new
    // Long(<ID>, "<desc>", "<abbr>");
    public static final USState ALABAMA = new USState(new Long(67001), "Alabama", "AL");

    public static final USState ALASKA = new USState(new Long(67002), "Alaska", "AK");

    public static final USState ARIZONA = new USState(new Long(67004), "Arizona", "AZ");

    public static final USState ARKANSAS = new USState(new Long(67005), "Arkansas", "AR");

    public static final USState CALIFORNIA = new USState(new Long(67006), "California", "CA");

    public static final USState COLORADO = new USState(new Long(67007), "Colorado", "CO");

    public static final USState CONNECTICUT = new USState(new Long(67008), "Connecticut", "CT");

    public static final USState DELAWARE = new USState(new Long(67009), "Delaware", "DE");

    public static final USState DISTRICT_OF_COLUMBIA = new USState(new Long(67010), "District of Columbia", "DC");

    public static final USState FLORIDA = new USState(new Long(67012), "Florida", "FL");

    public static final USState GEORGIA = new USState(new Long(67013), "Georgia", "GA");

    public static final USState GUAM = new USState(new Long(67014), "Guam", "GU");

    public static final USState HAWAII = new USState(new Long(67015), "Hawaii", "HI");

    public static final USState IDAHO = new USState(new Long(67016), "Idaho", "ID");

    public static final USState ILLINOIS = new USState(new Long(67017), "Illinois", "IL");

    public static final USState INDIANA = new USState(new Long(67018), "Indiana", "IN");

    public static final USState IOWA = new USState(new Long(67019), "Iowa", "IA");

    public static final USState KANSAS = new USState(new Long(67020), "Kansas", "KS");

    public static final USState KENTUCKY = new USState(new Long(67021), "Kentucky", "KY");

    public static final USState LOUISIANA = new USState(new Long(67022), "Louisiana", "LA");

    public static final USState MAINE = new USState(new Long(67023), "Maine", "ME");

    public static final USState MARYLAND = new USState(new Long(67025), "Maryland", "MD");

    public static final USState MASSACHUSETTS = new USState(new Long(67026), "Massachusetts", "MA");

    public static final USState MICHIGAN = new USState(new Long(67027), "Michigan", "MI");

    public static final USState MINNESOTA = new USState(new Long(67028), "Minnesota", "MN");

    public static final USState MISSISSIPPI = new USState(new Long(67029), "Mississippi", "MS");

    public static final USState MISSOURI = new USState(new Long(67030), "Missouri", "MO");

    public static final USState MONTANA = new USState(new Long(67031), "Montana", "MT");

    public static final USState NEBRASKA = new USState(new Long(67032), "Nebraska", "NE");

    public static final USState NEVADA = new USState(new Long(67033), "Nevada", "NV");

    public static final USState NEW_HAMPSHIRE = new USState(new Long(67034), "New Hampshire", "NH");

    public static final USState NEW_JERSEY = new USState(new Long(67035), "New Jersey", "NJ");

    public static final USState NEW_MEXICO = new USState(new Long(67036), "New Mexico", "NM");

    public static final USState NEW_YORK = new USState(new Long(67037), "New York", "NY");

    public static final USState NORTH_CAROLINA = new USState(new Long(67038), "North Carolina", "NC");

    public static final USState NORTH_DAKOTA = new USState(new Long(67039), "North Dakota", "ND");

    public static final USState OHIO = new USState(new Long(67041), "Ohio", "OH");

    public static final USState OKLAHOMA = new USState(new Long(67042), "Oklahoma", "OK");

    public static final USState OREGON = new USState(new Long(67043), "Oregon", "OR");

    public static final USState PENNSYLVANIA = new USState(new Long(67045), "Pennsylvania", "PA");

    public static final USState PUERTO_RICO = new USState(new Long(67046), "Puerto Rico", "PR");

    public static final USState RHODE_ISLAND = new USState(new Long(67047), "Rhode Island", "RI");

    public static final USState SOUTH_CAROLINA = new USState(new Long(67048), "South Carolina", "SC");

    public static final USState SOUTH_DAKOTA = new USState(new Long(67049), "South Dakota", "SD");

    public static final USState TENNESSEE = new USState(new Long(67050), "Tennessee", "TN");

    public static final USState TEXAS = new USState(new Long(67051), "Texas", "TX");

    public static final USState UTAH = new USState(new Long(67052), "Utah", "UT");

    public static final USState VERMONT = new USState(new Long(67053), "Vermont", "VT");

    public static final USState VIRGINIA = new USState(new Long(67055), "Virginia", "VA");

    public static final USState WASHINGTON = new USState(new Long(67056), "Washington", "WA");

    public static final USState WEST_VIRGINIA = new USState(new Long(67057), "West Virginia", "WV");

    public static final USState WISCONSIN = new USState(new Long(67058), "Wisconsin", "WI");

    public static final USState WYOMING = new USState(new Long(67059), "Wyoming", "WY");

    /**
     * System code domain
     */
    private static final USState[] domain = { ALABAMA, ALASKA, ARIZONA, ARKANSAS, CALIFORNIA, COLORADO, CONNECTICUT, DELAWARE, DISTRICT_OF_COLUMBIA, FLORIDA,
            GEORGIA, GUAM, HAWAII, IDAHO, ILLINOIS, INDIANA, IOWA, KANSAS, KENTUCKY, LOUISIANA, MAINE, MARYLAND, MASSACHUSETTS, MICHIGAN, MINNESOTA,
            MISSISSIPPI, MISSOURI, MONTANA, NEBRASKA, NEVADA, NEW_HAMPSHIRE, NEW_JERSEY, NEW_MEXICO, NEW_YORK, NORTH_CAROLINA, NORTH_DAKOTA, OHIO, OKLAHOMA,
            OREGON, PENNSYLVANIA, PUERTO_RICO, RHODE_ISLAND, SOUTH_CAROLINA, SOUTH_DAKOTA, TENNESSEE, TEXAS, UTAH, VERMONT, VIRGINIA, WASHINGTON,
            WEST_VIRGINIA, WISCONSIN, WYOMING };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public USState(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public USState()
    {
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - USState
     * @return USState for the enumerated type
     */
    public static final USState decodeValue(Long codeID)
    {
        return (USState) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - USState
     * @return USState for the enumerated type
     */
    public static final USState decodeValue(String codeValue)
    {
        return (USState) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return USState for the enumerated type
     */
    public static final USState decodeLabel(String codeLabel)
    {
        return (USState) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }

    /**
     * For some reason we can't decode the abbreviations on all o the Enums...:(
     * 
     * @param abr
     * @return
     */
    public static final USState decodeAbbreviation(String abr)
    {
        USState state = null;

        for (Iterator it = domainList.iterator(); it.hasNext();)
        {
            USState iState = (USState) it.next();
            if (iState.getAbbreviation().equals(abr))
            {
                state = iState;
                break;
            }
        }

        return state;

    }

    /**
     * Return the specific USState for the respective enumerated type value.
     * 
     * @param codeValue
     *            Enumerated type code value - PQARating
     * @param it
     *            Iterator to domain list of values
     * 
     * @return USState for the enumerated type
     */
    public static final USState decodeValueIgnoreCase(String codeValue)
    {
        Iterator it = domainList.iterator();
        BaseEnumType code = (BaseEnumType) null;
        BaseEnumType found = (BaseEnumType) null;

        while (it.hasNext())
        {
            code = (BaseEnumType) it.next();
            if (code.getDisplayValue().equalsIgnoreCase(codeValue))
            {
                found = code;
                break;
            }
        }
        return (USState) found;
    }
}
