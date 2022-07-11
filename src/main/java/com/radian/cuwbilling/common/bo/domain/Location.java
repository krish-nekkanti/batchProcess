/**
 * @(#) Location.java
 */

package com.radian.cuwbilling.common.bo.domain;

import com.radian.cuwbilling.common.bo.codes.Country;
import com.radian.cuwbilling.common.bo.codes.USState;

/**
 * This interface defines a Location business domain object.
 * 
 * A Location represents a postal address for a Party (i.e. Customer, Branch,
 * Person, etc.)
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public interface Location extends BaseDomainObject
{

    /**
     * @Return The City for this Location.
     */
    public String getCity();

    /**
     * @Return The County for this Location.
     */
    public String getCounty();

    /**
     * @Return The <code>Country</code> for this Location.
     */
    public Country getCountry();

    /**
     * @Return The five-digit postal code for this Location.
     */
    public String getPostalFiveCode();

    /**
     * @Return The zip+four postal code for ths Location.
     */
    public String getPostalFourCode();

    /**
     * @Return The <code>USState</code> for this Location.
     */
    public USState getState();

    /**
     * @Return The first line of the street address for this Location.
     */
    public String getStreetAddressLine1();

    /**
     * @Return The second line of the street address for this Location.
     */
    public String getStreetAddressLine2();

    /**
     * @Return The third line of the street address for this Location.
     */
    public String getStreetAddressLine3();

    /**
     * Assign the City for this Location.
     * 
     * @param city
     *            City
     */
    public void setCity(final String city);

    /**
     * Assign the County for this Location.
     * 
     * @param city
     *            County
     */
    public void setCounty(final String county);

    /**
     * Assign the <code>Country</code> for this Location.
     * 
     * @param country
     *            The Country for this Location.
     */
    public void setCountry(final Country country);

    /**
     * Assign the five-digit postal code for this location.
     * 
     * @param postalfivecode
     *            The five-digit postal code for this Location.
     */
    public void setPostalFiveCode(final String postalfivecode);

    /**
     * Assign the zip+four postal code for this Location.
     * 
     * @param postalfourcode
     *            The zip+4 postal code for this Location.
     */
    public void setPostalFourCode(final String postalfourcode);

    /**
     * Assign the <code>USState</code> for this Location.
     * 
     * @param state
     *            A reference to the <code>USState</code> for this Location.
     */
    public void setState(final USState state);

    /**
     * Assign the first line of the street address for this Location.
     * 
     * @param streetaddressline1
     *            The first line of the street address.
     */
    public void setStreetAddressLine1(final String streetaddressline1);

    /**
     * Assign the second line of the street address for this Location.
     * 
     * @param streetaddressline2
     *            The second line of the street address.
     */
    public void setStreetAddressLine2(final String streetaddressline2);

    /**
     * Assign the third line of the street address for this Location.
     * 
     * @param streetaddressline3
     *            The third line of the street address.
     */
    public void setStreetAddressLine3(final String streetaddressline3);

}
