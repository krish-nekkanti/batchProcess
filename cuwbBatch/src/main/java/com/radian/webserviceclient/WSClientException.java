/**
 * @(#) WSClientException.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import com.radian.foundation.common.exception.ApplicationException;

/**
 * Exception wraps lower level exceptions for web service client
 */
public class WSClientException extends ApplicationException
{
	/**
	 * @param key
	 */
	public WSClientException(String key)
	{
		super(key);
	}

	/**
	 * @param cause
	 */
	public WSClientException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param e
	 */
	public WSClientException(ApplicationException e)
	{
		super(e);
	}

	/**
	 * @param key
	 * @param value0
	 */
	public WSClientException(String key, Object value0)
	{
		super(key, value0);
	}

	/**
	 * @param key
	 * @param cause
	 */
	public WSClientException(String key, Throwable cause)
	{
		super(key, cause);
	}

	/**
	 * @param key
	 * @param values
	 */
	public WSClientException(String key, Object[] values)
	{
		super(key, values);
	}
}
