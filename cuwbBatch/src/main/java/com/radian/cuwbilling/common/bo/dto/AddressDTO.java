package com.radian.cuwbilling.common.bo.dto;

import com.radian.cuwbilling.common.bo.codes.Country;
import com.radian.cuwbilling.common.bo.codes.USState;
import com.radian.foundation.bo.dto.BaseDTO;

public class AddressDTO extends BaseDTO
{
    private String street1;

    private String street2;

    private String street3;

    private String city;

    private String county;

    private USState state;

    private Country country;

    private String zipCode;

    private String zipCodePlusFour;

    /**
     * Constructor for AddressDTO.
     */
    public AddressDTO()
    {
        super();
    }

    /**
     * Creates an address with minimal data
     * 
     * @param street1
     * @param city
     * @param state
     */
    public AddressDTO(String street1, String city, USState state)
    {
        super();
        this.street1 = street1;
        this.city = city;
        this.state = state;
    }

    public AddressDTO(String street1, String city, USState state, String zipCode, String zipCodePlusFour)
    {
        super();
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.zipCodePlusFour = zipCodePlusFour;
    }

    public AddressDTO(String street1, String city, USState state, String zipCode, String zipCodePlusFour, Country country)
    {
        super();
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.zipCodePlusFour = zipCodePlusFour;
        this.country = country;
    }

    /**
     * Returns the city.
     * 
     * @return String
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Returns the county.
     * 
     * @return String
     */
    public String getCounty()
    {
        return county;
    }

    /**
     * Returns the street1.
     * 
     * @return String
     */
    public String getStreet1()
    {
        return street1;
    }

    /**
     * Returns the street2.
     * 
     * @return String
     */
    public String getStreet2()
    {
        return street2;
    }

    /**
     * Returns the zipCode.
     * 
     * @return String
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * Returns the zipCode.
     * 
     * @return String
     */
    public String getFullZipCode()
    {
        if (zipCodePlusFour != null)
        {
            return getZipCode() + "-" + getZipCodePlusFour();
        } else
        {
            return getZipCode();
        }
    }

    /**
     * Sets the city.
     * 
     * @param city
     *            The city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Sets the county.
     * 
     * @param county
     *            The county to set
     */
    public void setCounty(String county)
    {
        this.county = county;
    }

    /**
     * Sets the street1.
     * 
     * @param street1
     *            The street1 to set
     */
    public void setStreet1(String street1)
    {
        this.street1 = street1;
    }

    /**
     * Sets the street2.
     * 
     * @param street2
     *            The street2 to set
     */
    public void setStreet2(String street2)
    {
        this.street2 = street2;
    }

    /**
     * Sets the zipCode.
     * 
     * @param zipCode
     *            The zipCode to set
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * Returns the street3.
     * 
     * @return String
     */
    public String getStreet3()
    {
        return street3;
    }

    /**
     * Returns the zipCodePlusFour.
     * 
     * @return String
     */
    public String getZipCodePlusFour()
    {
        return zipCodePlusFour;
    }

    /**
     * Sets the street3.
     * 
     * @param street3
     *            The street3 to set
     */
    public void setStreet3(String street3)
    {
        this.street3 = street3;
    }

    /**
     * Sets the zipCodePlusFour.
     * 
     * @param zipCodePlusFour
     *            The zipCodePlusFour to set
     */
    public void setZipCodePlusFour(String zipCodePlusFour)
    {
        this.zipCodePlusFour = zipCodePlusFour;
    }

    /**
     * Returns the country.
     * 
     * @return Country
     */
    public Country getCountry()
    {
        return country;
    }

    /**
     * Returns the state.
     * 
     * @return USState
     */
    public USState getState()
    {
        return state;
    }
    
    public String getStateLable()
    {
    	if (this.state != null)
    	{
    		return this.state.getAbbreviation();
    	}
    	else
    	{
    		return null;
    	}
    }

    /**
     * Sets the country.
     * 
     * @param country
     *            The country to set
     */
    public void setCountry(Country country)
    {
        this.country = country;
    }

    /**
     * Sets the state.
     * 
     * @param state
     *            The state to set
     */
    public void setState(USState state)
    {
        this.state = state;
    }

    public Long getStateRaw()
    {
        if (state == null)
            return null;

        return state.getID();
    }

    public void setStateRaw(Long id)
    {
        this.state = USState.decodeValue(id);
    }

    public Long getCountryRaw()
    {
        if (country == null)
            return null;

        return country.getID();
    }

    public void setCountryRaw(Long id)
    {
        this.country = Country.decodeValue(id);
    }
}
