/*
 * BillingException.java
 *
 * Created on August 1, 2003, 4:27 PM
 */

package com.radian.cuwbilling.billing.cuw.bs;

import com.radian.foundation.common.exception.ApplicationException;
import com.radian.foundation.common.exception.ValidationException;

/**
 * 
 * @author tzhou
 */
public class CUWBillingException extends ValidationException
{
    /**
     * Constructor for BillingException.
     * 
     * @param arg0
     */
    public CUWBillingException(String key)
    {
        super(key);
    }

    public CUWBillingException(String key, Throwable t)
    {
        super(key, t);
    }

    public CUWBillingException(String key, Object[] values)
    {

        super(key, values);
    }

    public CUWBillingException(ApplicationException e)
    {
        super(e);
    }
}
