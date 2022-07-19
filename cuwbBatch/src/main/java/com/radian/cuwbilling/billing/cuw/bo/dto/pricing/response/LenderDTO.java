/**
 * @(#) LenderDTO.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.foundation.bo.dto.BaseDTO;

/**
 * Holds info for Lender (from MIOnline)
 */
public class LenderDTO extends BaseDTO
{
	private String lenderMasterPolicyNumber;
	private String lenderName;
	private String lenderAddr1;
	private String lenderAddr2;
	private String lenderAddr3;
	private String lenderAddr4;
	private String lenderCity;
	private String lenderState;
	private String lenderZipcode;

	private static String EMPTY_STRING = "";

	/**
	 * @return the lenderMasterPolicyNumber
	 */
	public String getLenderMasterPolicyNumber()
	{
		return getProp(lenderMasterPolicyNumber);
	}

	/**
	 * @param lenderMasterPolicyNumber the lenderMasterPolicyNumber to set
	 */
	public void setLenderMasterPolicyNumber(String lenderMasterPolicyNumber)
	{
		this.lenderMasterPolicyNumber = lenderMasterPolicyNumber;
	}

	/**
	 * @return the lenderName
	 */
	public String getLenderName()
	{
		return getProp(lenderName);
	}

	/**
	 * @param lenderName the lenderName to set
	 */
	public void setLenderName(String lenderName)
	{
		this.lenderName = lenderName;
	}

	/**
	 * @return the lenderAddr1
	 */
	public String getLenderAddr1()
	{
		return getProp(lenderAddr1);
	}

	/**
	 * @param lenderAddr1 the lenderAddr1 to set
	 */
	public void setLenderAddr1(String lenderAddr1)
	{
		this.lenderAddr1 = lenderAddr1;
	}

	/**
	 * @return the lenderAddr2
	 */
	public String getLenderAddr2()
	{
		return getProp(lenderAddr2);
	}

	/**
	 * @param lenderAddr2 the lenderAddr2 to set
	 */
	public void setLenderAddr2(String lenderAddr2)
	{
		this.lenderAddr2 = lenderAddr2;
	}

	/**
	 * @return the lenderAddr3
	 */
	public String getLenderAddr3()
	{
		return getProp(lenderAddr3);
	}

	/**
	 * @param lenderAddr3 the lenderAddr3 to set
	 */
	public void setLenderAddr3(String lenderAddr3)
	{
		this.lenderAddr3 = lenderAddr3;
	}

	/**
	 * @return the lenderAddr4
	 */
	public String getLenderAddr4()
	{
		return getProp(lenderAddr4);
	}

	/**
	 * @param lenderAddr4 the lenderAddr4 to set
	 */
	public void setLenderAddr4(String lenderAddr4)
	{
		this.lenderAddr4 = lenderAddr4;
	}

	/**
	 * @return the lenderCity
	 */
	public String getLenderCity()
	{
		return getProp(lenderCity);
	}

	/**
	 * @param lenderCity the lenderCity to set
	 */
	public void setLenderCity(String lenderCity)
	{
		this.lenderCity = lenderCity;
	}

	/**
	 * @return the lenderState
	 */
	public String getLenderState()
	{
		return getProp(lenderState);
	}

	/**
	 * @param lenderState the lenderState to set
	 */
	public void setLenderState(String lenderState)
	{
		this.lenderState = lenderState;
	}

	/**
	 * @return the lenderZipcode
	 */
	public String getLenderZipcode()
	{
		return getProp(lenderZipcode);
	}

	/**
	 * @param lenderZipcode the lenderZipcode to set
	 */
	public void setLenderZipcode(String lenderZipcode)
	{
		this.lenderZipcode = lenderZipcode;
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
