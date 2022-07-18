/**
 * @(#) BorrowerDTO.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.foundation.bo.dto.BaseDTO;

/**
 * Holds info for a borrower (from MIOnline)
 */
public class BorrowerDTO extends BaseDTO
{
	private String ssn;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fico;

	private static String EMPTY_STRING = "";
	private static String NAME_SEP = " ";

	/**
	 * @return the ssn
	 */
	public String getSsn()
	{
		return getProp(ssn);
	}
	
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return getProp(firstName);
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * @return the middleName
	 */
	public String getMiddleName()
	{
		return getProp(middleName);
	}
	
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return getProp(lastName);
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * @return the name as First Middle Last
	 */
	public String getName()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getFirstName());
		sb.append(NAME_SEP);
		sb.append(getMiddleName());
		sb.append(NAME_SEP);
		sb.append(getLastName());
		return sb.toString().trim();
	}
	
	/**
	 * @return the fico
	 */
	public String getFico()
	{
		return getProp(fico);
	}
	
	/**
	 * @param fico the fico to set
	 */
	public void setFico(String fico)
	{
		this.fico = fico;
	}
	
	private String getProp(String prop)
	{
		if (prop != null)
		{
			return prop;
		}
		return EMPTY_STRING;
	}
}
