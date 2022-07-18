package com.radian.webserviceclient;

import java.io.Serializable;

/**
 * Holds result of call_returnstatus element, common to all mionline web service responses
 */
public class WSCMDStatus implements Serializable
{
	private String errorcode;
	private String errordesc;
	private String errorsrc;
	
	private static String EMPTY_STRING = "";
    private static String WS_MSG_STATUS = "WSCMD webservice call failed: errorcode.desc.src=";
    private static String WS_MSGSEP = "..";
    private static String STATUS_NO_PROFILE_FOR_NUM = "203";
    private static String STATUS_PROFILE_NOT_FOUND = "206";

	/**
	 * default constructor
	 */
	public WSCMDStatus()
	{
	}
	
	/**
	 * @param status
	 * @return msg
	 */
	public String getExceptionMsg(WSCMDStatus status)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(WS_MSG_STATUS);
		sb.append(status.getErrorcode());
		sb.append(WS_MSGSEP);
		sb.append(status.getErrordesc());
		sb.append(WS_MSGSEP);
		sb.append(status.getErrorsrc());
		return sb.toString();
	}

	/**
	 * @return the errorcode
	 */
	public String getErrorcode()
	{
		return getProperty(errorcode);
	}

	/**
	 * @param errorcode the errorcode to set
	 */
	public void setErrorcode(String errorcode)
	{
		this.errorcode = errorcode;
	}

	/**
	 * @return the errordesc
	 */
	public String getErrordesc()
	{
		return getProperty(errordesc);
	}

	/**
	 * @param errordesc the errordesc to set
	 */
	public void setErrordesc(String errordesc)
	{
		this.errordesc = errordesc;
	}

	/**
	 * @return the errorsrc
	 */
	public String getErrorsrc()
	{
		return getProperty(errorsrc);
	}

	/**
	 * @param errorsrc the errorsrc to set
	 */
	public void setErrorsrc(String errorsrc)
	{
		this.errorsrc = errorsrc;
	}
	
	private String getProperty(String prop)
	{
		if (prop == null)
		{	//return empty string if no value
			return EMPTY_STRING;
		}
		return prop;
	}
	
	/**
	 * @return true if error status is that the repID was not found for GetProfiileInfo call
	 */
	public boolean isProfileNotFound()
	{
		if (getErrorcode().length() > 0)
		{
			if (getErrorcode().equals(STATUS_NO_PROFILE_FOR_NUM) ||
				getErrorcode().equals(STATUS_PROFILE_NOT_FOUND))
			{	//error because repID, or associated user data, was not found
				return true;
			}
		}
		//error for some other reason
		return false;
	}
}

