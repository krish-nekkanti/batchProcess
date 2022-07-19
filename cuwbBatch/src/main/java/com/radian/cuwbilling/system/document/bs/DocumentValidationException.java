package com.radian.cuwbilling.system.document.bs;

import com.radian.foundation.common.exception.ValidationException;

/**
 * Exception class used to throw a data validation exception from the
 * system.document business service.
 * 
 * @author JSumner
 */
public class DocumentValidationException extends ValidationException
{
    /**
     * Constructor that creates a new DocumentValidationException based on
     * another throwable.
     * 
     * @param t
     *            The throwable used to create the DocumentValidationException
     */
    public DocumentValidationException(Throwable t)
    {
        super(t);
    }

    /**
     * Constructor that creates a new DocumentValidationException while
     * obtaining the exception's message from the specified resouce bundle key.
     * 
     * @param key
     *            The resource bundle key used to obtain the exception's
     *            message.
     */
    public DocumentValidationException(String key)
    {
        super(key);
    }

    /**
     * Constructor that creates a new DocumentValidationException while
     * obtaining the exception's message from the specified resouce bundle key
     * and an array of parameterized values.
     * 
     * @param key
     *            The resource bundle key used to obtain the exception's
     *            message.
     * @param values
     *            The array of parameterized values.
     */
    public DocumentValidationException(String key, Object[] values)
    {
        super(key, values);
    }

    /**
     * Constructor that creates a new DocumentValidationException while
     * obtaining the exception's message from the specified resouce bundle key.
     * The exception is based on the supplied throwable.
     * 
     * @param key
     *            The resource bundle key used to obtain the exception's
     *            message.
     * @param cause
     *            The throwable used to base this exception from.
     */
    public DocumentValidationException(String key, Throwable cause)
    {
        super(key, cause);
    }
}
