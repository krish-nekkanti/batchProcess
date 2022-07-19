/**
 * @(#) MsgKey.java
 */

package com.radian.foundation.common.resources.messages;

import java.util.Locale;

/**
 * A compound message key. Contains information that uniquely identifies message resource.
 * 
 * @author Rolandas Valteris
 */
public class MsgKey
{
    /**
     * Message category. <code>null</code> denotes all categories.
     */
    private String category;

    /**
     * Message key that uniquely identifies message within category.
     */
    private String key;

    /**
     * Message locale. <code>null</code> specifies default locale.
     */
    private Locale locale;

    /**
     * Checks wether the <code>key</code> is null key. A message key is considered null key
     * if the object itself is <code>null</code> or its <code>key</code> property is
     * <code>null</code>.
     * 
     * @param key the key.
     * @return true if the key is considered a null key.
     */
    static public boolean isNull(MsgKey key)
    {
        return (key == null || key.getKey() == null);
    }

    /**
     * @param key
     */
    public MsgKey(String key)
    {
        this(null, key, null);
    }

    /**
     * @param category
     * @param key
     */
    public MsgKey(String category, String key)
    {
        this(category, key, null);
    }

    /**
     * @param key
     * @param locale
     */
    public MsgKey(String key, Locale locale)
    {
        this(null, key, locale);
    }

    /**
     * @param category
     * @param key
     * @param locale
     */
    public MsgKey(String category, String key, Locale locale)
    {
        // allow null for key
        //if (key == null)
        //    throw new NullPointerException("Parameter 'key' is null.");

        this.category = category;
        this.key = key;
        this.locale = locale;
    }

    /**
     * Returns message category. <code>null</code> means all categories.
     * 
     * @return message category.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Returns message key that uniquely identifies message within category.
     * 
     * @return message key within category.
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Returns message locale. <code>null</code> means default locale.
     * 
     * @return message locale.
     */
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return (category != null ? (category + ":") : "") + key
            + (locale != null ? ("_" + locale) : "");
    }

}