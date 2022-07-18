package com.radian.foundation.common.exception;

import java.io.Serializable;
import java.util.Locale;

import com.radian.foundation.common.resources.messages.MessageUtil;

public class ApplicationException
    extends CascadingException
    implements ApplicationThrowable, CascadingThrowable, Serializable
{
    /**
     * The message key for this message.
     */
    protected String key = null; 

    /**
     * The replacement values for this mesasge.
     */
    protected Object values[] = null;
    
    /**Flag that can be used to designate that this exception has been logged*/
    public boolean logged = false;

    /**
     * Construct an action message with no replacement values.
     *
     * @param key Message key for this message
     */
    public ApplicationException(String key)
    {
        this.key = key;
        this.values = null;
    }

    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     */
    public ApplicationException(String key, Object value0)
    {
        this.key = key;
        this.values = new Object[] { value0 };
    }

    public ApplicationException(Throwable cause)
    {
        super(cause);
    }

    public ApplicationException(ApplicationException e)
    {
        super(e.getCause());
        this.key = e.key;
        this.values = e.values;
    }

    /**
     * Construct a new <code>CascadingException</code> instance.
     *
     * @param message The detail message for this exception.
     * @param throwable the root cause of the exception
     */
    public ApplicationException(String key, Throwable cause)
    {
        super(cause);
        this.key = key;
    }

    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param values Array of replacement values
     */
    public ApplicationException(String key, Object[] values)
    {
        this.key = key;
        this.values = values;
    }

    /**
     * Get the message key for this message.
     */
    public String getKey()
    {

        return (this.key);

    }

    /**
     * Get the replacement values for this message.
     */
    public Object[] getValues()
    {
        return (this.values);
    }

    /**
     * Returns the error message string of this throwable object.
     *
     * @return error message for the default locale
     */
    public String getMessage()
    {
        String msg =  MessageUtil.formatMessage(key, values);        
        if(msg == null)
        	return key;
        else
        	return msg;
    }

    /**
     * Retrieves a localized messaged based on the key value that
     * was set by the object/service that throw this exception.
     * 
     * If a localized message cannot be determined then the 
     * (potentially) non-localized key/values that were set when this
     * exception was thrown should be returned to the client.
     * 
     * @param locale the locale to use
     * @return localized error message
     */
    public String getMessage(Locale locale)
    {
        return MessageUtil.formatMessage(key, locale, values);
    }

    /**
     * Sets the cause.
     * @param cause The cause to set
     */
//    public void setCause(Throwable cause)
//    {
//        this.cause = cause;
//    }

    /**
     * Sets the key.
     * @param key The key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * Sets the values.
     * @param values The values to set
     */
    public void setValues(Object[] values)
    {
        this.values = values;
    }

	/**
	 * @return
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @param b
	 */
	public void setLogged(boolean b) {
		logged = b;
	}

}
