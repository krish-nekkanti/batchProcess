/**
 * @(#) BillingStatusUpdateDTO.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.foundation.bo.dto.BaseDTO;

/**
 * DTO for result holds info/totals on result of updating billing status in MIOnline for a list of loans
 */
public class BillingStatusUpdateDTO extends BaseDTO
{
	private int totLoansUpdated;
	private int totBilled;
	private int totAlreadyBilled;
	private int totNotFound;
	
	/**
	 * @return the totLoansUpdated
	 */
	public int getTotLoansUpdated()
	{
		return totLoansUpdated;
	}
	
	/**
	 * @return the totUpdatedToBilled
	 */
	public int getTotBilled()
	{
		return totBilled;
	}
	
	/**
	 * @return the totAlreadyBilled
	 */
	public int getTotAlreadyBilled()
	{
		return totAlreadyBilled;
	}
	
	/**
	 * @return the totNotFound
	 */
	public int getTotNotFound()
	{
		return totNotFound;
	}

	/**
	 * @param totLoansUpdated the totLoansUpdated to set
	 */
	public void setTotLoansUpdated(String totLoansUpdated)
	{
		this.totLoansUpdated = getNumber(totLoansUpdated);
	}

	/**
	 * @param totUpdatedToBilled the totBilled to set
	 */
	public void setTotBilled(String totUpdatedToBilled)
	{
		this.totBilled = getNumber(totUpdatedToBilled);
	}

	/**
	 * @param totAlreadyBilled the totAlreadyBilled to set
	 */
	public void setTotAlreadyBilled(String totAlreadyBilled)
	{
		this.totAlreadyBilled = getNumber(totAlreadyBilled);
	}

	/**
	 * @param totNotFound the totNotFound to set
	 */
	public void setTotNotFound(String totNotFound)
	{
		this.totNotFound = getNumber(totNotFound);
	}
	
	/**
	 * @param numText
	 * @return int
	 */
	private int getNumber(String numText)
	{
		try
		{	//convert string to int
			return Integer.parseInt(numText);
		}
		catch (NumberFormatException e)
		{	//if not a numeric total, default to 0
			return 0;
		}
	}
}
