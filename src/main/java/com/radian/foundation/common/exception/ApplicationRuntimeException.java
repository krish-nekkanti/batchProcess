package com.radian.foundation.common.exception;

import java.io.Serializable;
import java.util.Locale;

import com.radian.foundation.common.resources.messages.MessageUtil;

public class ApplicationRuntimeException
    extends CascadingRuntimeException
    implements ApplicationThrowable, Serializable
{
    /**
     * The message key for this message.
     */
    private String key = null;

    /**
     * The replacement values for this mesasge.
     */
    private Object values[] = null;

    /**
     * Construct an action message with no replacement values.
     *
     * @param key Message key for this message
     */
    public ApplicationRuntimeException(String key)
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
    public ApplicationRuntimeException(String key, Object value0)
    {
        this.key = key;
        this.values = new Object[] { value0 };
    }

    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     * @param value1 Second replacement value
     */
    public ApplicationRuntimeException(String key, Object value0, Object value1)
    {
        this.key = key;
        this.values = new Object[] { value0, value1 };
    }

    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     * @param value1 Second replacement value
     * @param value2 Third replacement value
     */
    public ApplicationRuntimeException(
        String key,
        Object value0,
        Object value1,
        Object value2)
    {
        this.key = key;
        this.values = new Object[] { value0, value1, value2 };
    }

    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param value0 First replacement value
     * @param value1 Second replacement value
     * @param value2 Third replacement value
     * @param value3 Fourth replacement value
     */
    public ApplicationRuntimeException(
        String key,
        Object value0,
        Object value1,
        Object value2,
        Object value3)
    {
        this.key = key;
        this.values = new Object[] { value0, value1, value2, value3 };
    }

    /**
     * Construct an action message with the specified replacement values.
     *
     * @param key Message key for this message
     * @param values Array of replacement values
     */
    public ApplicationRuntimeException(String key, Object[] values)
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
        return MessageUtil.formatMessage(key, values);
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
}
