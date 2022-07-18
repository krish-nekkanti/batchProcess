package com.radian.cuwbilling.common.bo.dto;

import com.radian.foundation.bo.dto.BaseDTO;

/**
 * PhoneDTO.class
 * 
 * @author nines, carmen
 */
public class PhoneDTO extends BaseDTO
{
    private static final String _HYPHEN = "-";

    private static final String _EXT = " ext. ";

    private String number;

    private String extension;

    private String description;

    /**
     * Constructor for PhoneDTO.
     */
    public PhoneDTO()
    {
        super();
    }

    public PhoneDTO(String number)
    {
        super();
        this.number = number;
    }

    public PhoneDTO(String number, String extension)
    {
        super();
        this.number = number;
        this.extension = extension;
    }

    public PhoneDTO(String number, String extension, String description)
    {
        super();
        this.number = number;
        this.extension = extension;
        this.description = description;
    }

    /**
     * Converts <code>String</code> to <code>PhoneDTO</code>.
     * 
     * @return strings as axiom Phone. <code>null</code> if parameter number
     *         is <code>null</code>.
     */
    public static PhoneDTO stringsToPhoneDTO(String number, String extension)
    {
        if (number == null)
        {
            return null;
        }

        if (extension == null)
        {
            return new PhoneDTO(number);
        }

        return new PhoneDTO(number, extension);
    }

    /**
     * Converts <code>PhoneDTO</code> to <code>String</code>.
     * 
     * @return axiom phone number as String. <code>null</code> if parameter is
     *         <code>null</code>.
     */
    public static String toNumber(PhoneDTO phone)
    {
        if (phone == null)
        {
            return null;
        }

        return phone.getNumber();
    }

    /**
     * Converts <code>PhoneDTO</code> to <code>String</code>.
     * 
     * @return axiom phone extension as String. <code>null</code> if parameter
     *         is <code>null</code>.
     */
    public static String toExtension(PhoneDTO phone)
    {
        if (phone == null)
        {
            return null;
        }

        return phone.getExtension();
    }

    /**
     * Returns the extension.
     * 
     * @return String
     */
    public String getExtension()
    {
        return extension;
    }

    /**
     * Returns the number.
     * 
     * @return String
     */
    public String getNumber()
    {
        return number;
    }

    /**
     * Returns the description.
     * 
     * @return String
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the extension.
     * 
     * @param extension
     *            The extension to set
     */
    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    /**
     * Sets the number.
     * 
     * @param number
     *            The number to set
     */
    public void setNumber(String number)
    {
        this.number = number;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            The description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Consistent phone number representation aaa-xxx-nnnn
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        if (getNumber() != null)
        {
            sb.append(getNumber());
        }

        if (getExtension() != null)
        {
            sb.append(_EXT);
            sb.append(getExtension());
        }

        return sb.toString();
    }
}
