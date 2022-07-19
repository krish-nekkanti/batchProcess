/**
 * @(#) WSClientExceptionStatus.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import com.radian.foundation.common.exception.ApplicationException;

/**
 * Exception provides access to MIOnline webservice status returned from call
 */
public class WSClientExceptionStatus extends ApplicationException
{
	private WSMionlineStatus status;

	/**
	 * Constructor to create exception messge from web service return and provide access to status obj
	 * @param status
	 */
	public WSClientExceptionStatus(WSMionlineStatus status)
	{
		super(status.getExceptionMsg(status));
		setStatus(status);
	}

	/**
	 * @return the status
	 */
	public WSMionlineStatus getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	private void setStatus(WSMionlineStatus status)
	{
		this.status = status;
	}
}
