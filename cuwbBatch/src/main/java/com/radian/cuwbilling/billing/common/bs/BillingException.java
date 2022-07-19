package com.radian.cuwbilling.billing.common.bs;

import com.radian.foundation.common.exception.ApplicationException;

/**
 * @author nines
 * 
 */
public class BillingException extends ApplicationException
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor for CustomerException.
     * 
     * @param arg0
     */
    public BillingException(String key)
    {
        super(key);
    }

    public BillingException(String key, Throwable t)
    {
        super(key, t);
    }

    public BillingException(String key, Object[] values)
    {

        super(key, values);
    }

    public BillingException(Exception e)
    {
        super(e);
    }

}
