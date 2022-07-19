package com.radian.foundation.common.exception;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

import com.radian.foundation.common.resources.messages.MessageUtil;

/**
 * The intention of this Exception class is to expand upon the existing
 * Exception handling framework provided by Foundation by allowing
 * multiple (i.e. a map of) Exception messages for when/if a domain
 * wishes to set multiple messages as part of an exception that 
 * was thrown 
 * 
 * Currently - users of this class need to have the following build/deployment
 * options correctly set to allow the exception handling mechanism to localize
 * the error messages correctly from the system resource bundles:
 * <p>
 * The Axiom configuration file needs to be set correctly
 * The Axiom configuration file need to contain the location of the 
 * appropriate system ResourceBundle files
 * 
 * 
 * @author nines
 */
public class ValidationException extends ApplicationException {

	/**A collection of ValidationExceptionElement objects.  This list of
	 * values is a collection because they same error message may exist
	 * many times but with different message arguments
	 * */
	protected Collection exceptionElements = new ArrayList();	

	/**
	 * Adds a message to the list of error messages.  If a message with the same
	 * key already exists this method ignore the request
	 * 
	 * @param key a unique identifier for a resource bundle label
	 * 
	 */
	public void addMessage(String key)
	{
		if(key != null)
		{
			synchronized(exceptionElements)
			{
				exceptionElements.add(new ValidationExceptionElement(key,null));
			}
		}				
	}
		
	/**
	 * Adds a message with a single argument to the list of error messages.  If a message
	 * with the same key already exists the existing key/value will be removed and 
	 * replaced with this new key/value pair 
	 * 
	 * @param key a unique identifier for a resource bundle label
	 * @param value a single argument passed to a resource bundle label to place in the message
	 */
	public void addMessage(String key, Object value)
	{
		
		if(key != null)
		{		
			synchronized(exceptionElements)
			{
										
				Object[] o = new Object[1];
				o[0] = value;
				
				exceptionElements.add(new ValidationExceptionElement(key,o));
			}
		}
		
	}
	
	/**
	 * Adds a message with an array of argument to the list of error messages.  If a message
	 * with the same key already exists the existing key/value will be removed and 
	 * replaced with this new key/value(s) pair 
	 * 
	 * @param key a unique identifier for a resource bundle label
	 * @param values an array of arguments passed to a resource bundle label to place in the message
	 */		
	public void addMessage(String key, Object values[])
	{
		
		if(key != null)
		{		
			synchronized(exceptionElements)
			{				
				exceptionElements.add(new ValidationExceptionElement(key,values));
			}
		}
				
	}
	
	/**
	 * Removes a key/value pair from the list of error messages or ignores
	 * the request if the key does not exist
	 * 
	 * @param key
	 */
	public void removeMessage(String key){
		synchronized(exceptionElements){
			exceptionElements.remove(key);
		}
	}
	
	/**
	 * Returns a collection of String objects localized/ready for presentation 
	 * and/or logging purposes
	 * 
	 * @return a collection of localized String objects of error 
	 * messages pertaining to the cause of this exception 
	 */
	public Collection getMessages()
	{
		Collection localizedMsgs=null;
		boolean filledFirst = false;
		if(this.exceptionElements != null && this.exceptionElements.size() >0)
		{
			localizedMsgs = new ArrayList(this.exceptionElements.size() + 1);
			localizedMsgs.add(MessageUtil.formatMessage(super.key, super.values));
			filledFirst = true;
			Iterator it = this.exceptionElements.iterator();
			while(it.hasNext())
			{
				ValidationExceptionElement element = (ValidationExceptionElement)it.next();
				localizedMsgs.add(MessageUtil.formatMessage(element.getKey(), element.getValues()));	
			}
		}
		if(!filledFirst)
		{
			localizedMsgs = new ArrayList(1);
			localizedMsgs.add(MessageUtil.formatMessage(super.key, super.values));			
		}
		return localizedMsgs;				
	}
	
	/**
	 * @param key
	 */
	public ValidationException(String key) {
		super(key);
	}

	/**
	 * @param key
	 * @param value0
	 */
	public ValidationException(String key, Object value0) {
		super(key, value0);

	}

	/**
	 * @param cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param e
	 */
	public ValidationException(ApplicationException e) {
		super(e);

	}

	/**
	 * @param key
	 * @param cause
	 */
	public ValidationException(String key, Throwable cause) {
		super(key, cause);

	}

	/**
	 * Constructs a ValidationException from a unique key and an array of objects
	 * 
	 * @param key
	 * @param values
	 */
	public ValidationException(String key, Object[] values) {
		super(key, values);

	}
	
	/**
	 * Constructs a ValidationException from another ValidationException object 
	 * @param e 
	 */
	public ValidationException(ValidationException e){
		super(e);
		this.exceptionElements = e.exceptionElements;
	}

	/**
	 * Returns the "raw" non-localized list of exception messages
	 * 
	 * @return collection of ValidationExceptionElements objects
	 */
	public Collection getExceptionElements()
	{
		return exceptionElements;
	}

}
