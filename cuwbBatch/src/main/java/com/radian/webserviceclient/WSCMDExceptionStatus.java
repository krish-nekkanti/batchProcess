package com.radian.webserviceclient;

import com.radian.foundation.common.exception.ApplicationException;

/**
 * Exception provides access to MIOnline webservice status returned from call
 */
public class WSCMDExceptionStatus extends ApplicationException
{
	private WSCMDStatus status;

	/**
	 * Constructor to create exception messge from web service return and provide access to status obj
	 * @param status
	 */
	public WSCMDExceptionStatus(WSCMDStatus status)
	{
		super(status.getExceptionMsg(status));
		setStatus(status);
	}

	/**
	 * @return the status
	 */
	public WSCMDStatus getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	private void setStatus(WSCMDStatus status)
	{
		this.status = status;
	}
}