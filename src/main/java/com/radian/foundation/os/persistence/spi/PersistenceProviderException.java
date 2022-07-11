
package com.radian.foundation.os.persistence.spi;

import com.radian.foundation.common.exception.CascadingException;

/**
 * Base class for persistence provider exceptions.
 * 
 * @author Giedrius Trumpickas
 */
public class PersistenceProviderException extends CascadingException
{
	/**
	 * Constructs provider exception with given message.
	 * 
	 * @param message an exception message
	 */
	public PersistenceProviderException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructs persistence provider exception with given throwable
	 * 
	 * @param nested a nested exception
	 */		
	public PersistenceProviderException(Throwable nested)
	{
		super(nested);	
	}
	
	/**
	 * Constructs provider exception with given message and nested exception
	 * 
	 * @param message an exception message
	 * @param nested a nested exception
	 */	
	public PersistenceProviderException(String message, Throwable nested)
	{
		super(message, nested);		
	}
}
