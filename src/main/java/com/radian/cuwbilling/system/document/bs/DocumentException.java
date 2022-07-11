package com.radian.cuwbilling.system.document.bs;

import com.radian.foundation.common.exception.ApplicationException;

/**
 * This class provides the general exception class used in the system.document
 * domain.
 * 
 * @author jsumner
 */
public class DocumentException extends ApplicationException
{
    /**
     * Constructor for DocumentException.
     * 
     * @param key
     *            The resource bundle key used to obtain the appropriate
     *            exception message.
     */
    public DocumentException(String key)
    {
        super(key);
    }

    /**
     * Constructor to make a DocumentException based on a resource bundle entry
     * and another throwable.
     * 
     * @param key
     *            The resource bundle key used to obtain the appropriate
     *            exception message.
     * @param t
     *            Another throwable to base this exception on.
     */
    public DocumentException(String key, Throwable t)
    {
        super(key, t);
    }

    /**
     * Constructor to make a DocumentException based on a resource bundle entry
     * and an array of parameterized values used to generate the message.
     * 
     * @param key
     *            The resource bundle key used to obtain the appropriate
     *            exception message.
     * @param values
     *            An array of parameterized values to use in generating the
     *            exception message.
     */
    public DocumentException(String key, Object[] values)
    {
        super(key, values);
    }

    /**
     * Constructor to make a DocumentException based on an ApplicationException.
     * 
     * @param e
     *            The ApplicationException to base this exception off of.
     */
    public DocumentException(ApplicationException e)
    {
        super(e);
    }
}
