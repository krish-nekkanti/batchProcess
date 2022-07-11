package com.radian.cuwbilling.common.bo.dto;

import com.radian.cuwbilling.common.bo.codes.Salutation;
import com.radian.cuwbilling.common.bo.codes.Suffix;
import com.radian.foundation.bo.dto.BaseDTO;

/**
 * Common attributes about a person
 * 
 */
public class PersonDTO extends BaseDTO
{

    private Long ID;

    private String firstName;

    private String lastName;

    private String middle;

    private Long prefix;

    private Long suffix;

    private String nickname;

    /**
     * Constructor for PersonDTO.
     */
    public PersonDTO()
    {
        super();
    }

    /**
     * Construct a person from itself(used by sub classes)
     * 
     * @param person
     */
    public PersonDTO(PersonDTO person)
    {
        super();
        if (person != null)
        {
            this.ID = person.ID;
            this.firstName = person.firstName;
            this.lastName = person.lastName;
            this.middle = person.middle;
            this.nickname = person.nickname;
            this.suffix = person.suffix;
        }
    }

    /**
     * Constructs a PersonDTO with minimal data
     * 
     * @param firstName
     * @param lastName
     * @param middle
     */
    public PersonDTO(Long ID, String firstName, String lastName, String middle)
    {
        super();
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middle = middle;
    }

    /**
     * Constructs a PersonDTO with minimal data
     * 
     * @param firstName
     * @param lastName
     * @param middle
     */
    public PersonDTO(String firstName, String lastName, String middle)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middle = middle;
    }

    /**
     * Constructs a PersonDTO with minimal data
     * 
     * @param firstName
     * @param lastName
     * @param middle
     */
    public PersonDTO(Long prefix, String firstName, String lastName, String middle, Long suffix)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middle = middle;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * Returns the firstName.
     * 
     * @return String
     */
    public String getFirstName()
    {
        if (firstName == null)
            return "";
        else
            return firstName;

    }

    /**
     * Returns the lastName.
     * 
     * @return String
     */
    public String getLastName()
    {
        if (lastName == null)
        {
            return "";
        } else
            return lastName;
    }

    /**
     * Returns the middle.
     * 
     * @return String
     */
    public String getMiddle()
    {
        if (middle == null)
            return "";
        else
            return middle;
    }

    /**
     * Returns the nickname.
     * 
     * @return String
     */
    public String getNickname()
    {
        if (nickname == null)
            return "";
        else
            return nickname;
    }

    /**
     * Returns the prefix.
     * 
     * @return Long
     */
    public Long getPrefix()
    {
        return prefix;
    }

    /**
     * Returns the suffix.
     * 
     * @return Long
     */
    public Long getSuffix()
    {
        return suffix;
    }

    /**
     * Returns the title.
     * 
     * @return String
     */
    public String getDisplayName()
    {
        return ((getPrefixStr() == null) ? "" : getPrefixStr() + " ") + ((getFirstName() == null) ? "" : getFirstName() + " ")
                + ((getFirstName() == null) ? "" : getMiddle() + " ") + ((getFirstName() == null) ? "" : getLastName() + " ")
                + ((getSuffixStr() == null) ? "" : getSuffixStr());
    }

    /**
     * Sets the firstName.
     * 
     * @param firstName
     *            The firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Sets the lastName.
     * 
     * @param lastName
     *            The lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Sets the middle.
     * 
     * @param middle
     *            The middle to set
     */
    public void setMiddle(String middle)
    {
        this.middle = middle;
    }

    /**
     * Sets the nickname.
     * 
     * @param nickname
     *            The nickname to set
     */
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    /**
     * Sets the prefix.
     * 
     * @param prefix
     *            The prefix to set
     */
    public void setPrefix(Long prefix)
    {
        this.prefix = prefix;
    }

    /**
     * Sets the suffix.
     * 
     * @param suffix
     *            The suffix to set
     */
    public void setSuffix(Long suffix)
    {
        this.suffix = suffix;
    }

    /**
     * Returns the prefixStr.
     * 
     * @return String
     */
    public String getPrefixStr()
    {
        return (prefix != null) ? Salutation.decodeValue(prefix).getValue() : null;

    }

    /**
     * Returns the suffixStr.
     * 
     * @return String
     */
    public String getSuffixStr()
    {
        if (suffix == null || Suffix.decodeValue(suffix) == null)
            return null;
        return Suffix.decodeValue(suffix).getValue();
    }

    /**
     * Sets the prefixStr.
     * 
     * @param prefixStr
     *            The prefixStr to set
     */
    public void setPrefixStr(String prefixStr)
    {
        if (prefixStr != null)
        {
            this.prefix = Salutation.decodeValue(prefixStr).getID();
        }
    }

    /**
     * Sets the suffixStr.
     * 
     * @param suffixStr
     *            The suffixStr to set
     */
    public void setSuffixStr(String suffixStr)
    {
        if (suffixStr != null)
        {
            this.suffix = Suffix.decodeValue(suffixStr).getID();
        }
    }

    /**
     * @return
     */
    public Long getID()
    {
        return ID;
    }

    /**
     * @param long1
     */
    public void setID(Long long1)
    {
        ID = long1;
    }

}
