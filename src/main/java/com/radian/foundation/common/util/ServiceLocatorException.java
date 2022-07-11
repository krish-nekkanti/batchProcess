package com.radian.foundation.common.util;

import com.radian.foundation.common.exception.CascadingException;

/**
 * @author Valdas Sevelis
 */
public class ServiceLocatorException extends CascadingException
{
    /**
     * Constructs a new <code>ServiceLocatorException</code>
     * with specified detail message.
     *
     * @param message The error message.
     */
    public ServiceLocatorException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new <code>ServiceLocatorException</code>
     * with specified detail message and nested <code>Throwable</code>.
     *
     * @param message The error message.
     * @param cause   The exception or error that caused this exception
     *                 to be thrown
     */
    public ServiceLocatorException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructs a new <code>ServiceLocatorException</code>
     * with specified nested <code>Throwable</code>.
     *
     * @param cause The exception or error that caused this exception
     *               to be thrown
     */
    public ServiceLocatorException(Throwable cause)
    {
        super(cause);
    }
}
