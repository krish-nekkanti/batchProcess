
package com.radian.foundation.common.exception;

public interface ApplicationThrowable
{
    /**
     * Get the message key for this message.
     */
    public String getKey();

    /**
     * Get the replacement values for this message.
     */
    public Object[] getValues();
}
