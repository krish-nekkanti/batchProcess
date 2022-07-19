package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

public class AxiomEntityType extends BaseEnumType
{
    public static final AxiomEntityType CLIENT = new AxiomEntityType(new Long(45001), "Client", "Client");

    public static final AxiomEntityType BRANCH = new AxiomEntityType(new Long(45002), "Branch", "Branch");

    public static final AxiomEntityType URA = new AxiomEntityType(new Long(45003), "ura", "ura");

    public static final AxiomEntityType CLIENT_GROUP = new AxiomEntityType(new Long(45004), "Client Group", "Client Group");

    public static final AxiomEntityType MORTGAGE_BROKER_CLIENT = new AxiomEntityType(new Long(45005), "Mortgage Broker Client", "Mortgage Broker Client");

    public static final AxiomEntityType MORTGAGE_BROKER_BRANCH = new AxiomEntityType(new Long(45006), "Mortgage Broker Branch", "Mortgage Broker Branch");

    public static final AxiomEntityType CUW_EXPORT = new AxiomEntityType(new Long(45007), "CUW Export", "CUW Export");

    public static final AxiomEntityType CUW_INVOICE = new AxiomEntityType(new Long(45008), "CUW Invoice", "CUW Invoice");

    public static final AxiomEntityType PAYMENT = new AxiomEntityType(new Long(45009), "Payment Import", "Payment Import");

    public static final AxiomEntityType TIMESHEET = new AxiomEntityType(new Long(45010), "Timesheet", "Timesheet");

    public static final AxiomEntityType CREDIT_CERT = new AxiomEntityType(new Long(45011), "Credit at Certification", "Credit at Certification");

    /**
     * System code domain
     */
    private static final AxiomEntityType[] domain = { CLIENT, BRANCH, URA, CLIENT_GROUP, MORTGAGE_BROKER_CLIENT, MORTGAGE_BROKER_BRANCH, CUW_INVOICE,
            TIMESHEET, PAYMENT, CUW_EXPORT, CREDIT_CERT };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * System code domain
     */
    private static final AxiomEntityType[] sortedDomain = { BRANCH, CLIENT, CLIENT_GROUP, CREDIT_CERT, CUW_EXPORT, CUW_INVOICE, MORTGAGE_BROKER_BRANCH,
            MORTGAGE_BROKER_CLIENT, PAYMENT, TIMESHEET, URA };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List sortedDomainList = Collections.unmodifiableList(Arrays.asList(sortedDomain));

    private AxiomEntityType(Long id, String label, String codeLabel)
    {
        super(id, label, codeLabel);
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - LienPosition
     * @return AxiomEntityType for the enumerated type
     */
    public static final AxiomEntityType decodeValue(Long codeID)
    {
        return (AxiomEntityType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - LienPosition
     * @return AxiomEntityType for the enumerated type
     */
    public static final AxiomEntityType decodeValue(String codeValue)
    {
        return (AxiomEntityType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return AxiomEntityType for the enumerated type
     */
    public static final AxiomEntityType decodeLabel(String codeLabel)
    {
        return (AxiomEntityType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }

}
