package com.radian.foundation.common.config;

import com.radian.foundation.common.exception.CascadingException;

/**
 * Thrown when a <code>Configurable</code> component cannot be configured
 * properly, or if a value cannot be retrieved properly. 
 */
public class ConfigurationException extends CascadingException
{
    /**
     * Constructs a new <code>ConfigurationException</code> with specified
     * error message.
     *
     * @param message The detail message for this exception.
     */
    public ConfigurationException(final String message)
    {
        super(message);
    }

    /**
     * Constructs a new <code>ConfigurationException</code> with specified
     * <code>Throwable</code> cause.
     * 
     * @param cause
     */
    public ConfigurationException(final Throwable cause)
    {
        super(cause);
    }
    
    /**
     * Constructs a new <code>ConfigurationException</code> with specified
     * error message and <code>Throwable</code> cause.
     *
     * @param message The detail message for this exception.
     * @param throwable the root cause of the exception
     */
    public ConfigurationException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
