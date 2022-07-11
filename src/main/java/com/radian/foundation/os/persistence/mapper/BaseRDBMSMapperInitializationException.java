package com.radian.foundation.os.persistence.mapper;

import com.radian.foundation.common.exception.CascadingRuntimeException;

/**
 * Base exception class for domain mappers.
 * 
 * @author Giedrius Trumpickas
 */
public class BaseRDBMSMapperInitializationException extends CascadingRuntimeException
{
	
	/**
	 * @param message
	 */
	public BaseRDBMSMapperInitializationException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public BaseRDBMSMapperInitializationException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseRDBMSMapperInitializationException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
