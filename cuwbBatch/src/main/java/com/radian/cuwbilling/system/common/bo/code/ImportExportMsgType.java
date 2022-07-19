package com.radian.cuwbilling.system.common.bo.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is ImportExportMsgType, which is a domain of values to
 * describe the possible Import or export type the message is for. This enum is
 * used for the ImportExportMessaging business service.
 * 
 * @author Drea Leed
 * 
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class ImportExportMsgType extends BaseEnumType
{

    /**
     * ImportExportMsgType: definition of possible values
     */
    public static final ImportExportMsgType IMPORT_TIMESHEETS = new ImportExportMsgType(new Long(231001), "Import Timesheets", "IT");

    public static final ImportExportMsgType IMPORT_CUW_PAYMENTS = new ImportExportMsgType(new Long(231002), "Import CUW Payments", "ICP");

    public static final ImportExportMsgType GENERATE_CUW_INVOICE = new ImportExportMsgType(new Long(231003), "Generate CUW Invoice", "GI");

    public static final ImportExportMsgType EXPORT_CUW_CUSTOMERS = new ImportExportMsgType(new Long(231004), "Export CUW Customers", "ECC");

    public static final ImportExportMsgType EXPORT_CUW_INVOICES = new ImportExportMsgType(new Long(231005), "Export CUW Invoices", "ECI");

    public static final ImportExportMsgType EXPORT_CUW_ADJUSTMENTS = new ImportExportMsgType(new Long(231006), "Export CUW Adjustments", "ECA");

    /**
     * System code domain
     */
    private static final ImportExportMsgType[] domain = { IMPORT_TIMESHEETS, IMPORT_CUW_PAYMENTS, GENERATE_CUW_INVOICE, EXPORT_CUW_CUSTOMERS,
            EXPORT_CUW_INVOICES, EXPORT_CUW_ADJUSTMENTS };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    private ImportExportMsgType(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    /**
     * Gets an instance of the code
     * 
     * @param code
     * @return
     */
    public static final ImportExportMsgType instance(ImportExportMsgType code)
    {
        return decodeValue(code.getID());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - ImportExportMsgType
     * @return ImportExportMsgType for the enumerated type
     */
    public static final ImportExportMsgType decodeValue(Long codeID)
    {
        return (ImportExportMsgType) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - ImportExportMsgType
     * @return ImportExportMsgType for the enumerated type
     */
    public static final ImportExportMsgType decodeValue(String codeValue)
    {
        return (ImportExportMsgType) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return ImportExportMsgType for the enumerated type
     */
    public static final ImportExportMsgType decodeLabel(String codeLabel)
    {
        return (ImportExportMsgType) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
