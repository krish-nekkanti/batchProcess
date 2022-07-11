package com.radian.foundation.common.resources.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.radian.foundation.common.exception.CascadingRuntimeException;

/**
 * This class is a wrapper around the resource bundles used within Axiom. It should be used in
 * conjunction with the MsgKey class for message key constants.
 * 
 * @author todd spurlock
 * @author Rolandas Valteris
 */
public class MessageUtil
{
    /**
     * Format a message with any number of arguments. An argument may be an object or it may
     * also be a "key" to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param argSrcArray an array of Objects to replace in the message. <B>Any item in the
     *        array may also be another key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key, Object argSrcArray[])
    {
        return formatMessage(new MsgKey(key), argSrcArray);
    }

    /**
     * Format a message with no arguments
     * 
     * @param messageKey the key of the message to look up.
     * @param locale the locale to use.
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key, Locale locale)
    {
        return getMessageString(new MsgKey(key, locale));
    }

    /**
     * Format a message with any number of arguments. An argument may be an object or it may
     * also be a "key" to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param argSrcArray an array of Objects to replace in the message. <B>Any item in the
     *        array may also be another key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key, Locale locale, Object argSrcArray[])
    {
        return formatMessage(new MsgKey(key, locale), argSrcArray);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key, Locale locale, Object arg0)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        return formatMessage(new MsgKey(key, locale), args);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg1 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key, Locale locale, Object arg0, Object arg1)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        return formatMessage(key, locale, args);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg1 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg2 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key,
                                       Locale locale,
                                       Object arg0,
                                       Object arg1,
                                       Object arg2)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        return formatMessage(key, locale, args);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg1 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg2 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg3 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String key,
                                       Locale locale,
                                       Object arg0,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        return formatMessage(key, locale, args);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Format a message with any number of arguments. An argument may be an object or it may
     * also be a "key" to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param argSrcArray an array of Objects to replace in the message. <B>Any item in the
     *        array may also be another key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category, String key, Object argSrcArray[])
    {
        return formatMessage(new MsgKey(category, key), argSrcArray);
    }

    /**
     * Format a message with no arguments
     * 
     * @param messageKey the key of the message to look up.
     * @param locale the locale to use.
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category, String key, Locale locale)
    {
        return getMessageString(new MsgKey(category, key, locale));
    }

    /**
     * Format a message with any number of arguments. An argument may be an object or it may
     * also be a "key" to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param argSrcArray an array of Objects to replace in the message. <B>Any item in the
     *        array may also be another key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category,
                                       String key,
                                       Locale locale,
                                       Object argSrcArray[])
    {
        return formatMessage(new MsgKey(category, key, locale), argSrcArray);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category, String key, Locale locale, Object arg0)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        return formatMessage(new MsgKey(category, key, locale), args);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg1 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category,
                                       String key,
                                       Locale locale,
                                       Object arg0,
                                       Object arg1)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        return formatMessage(category, key, locale, args);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg1 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg2 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category,
                                       String key,
                                       Locale locale,
                                       Object arg0,
                                       Object arg1,
                                       Object arg2)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        return formatMessage(category, key, locale, args);
    }

    /**
     * Format a message with arguments. An argument may be an object or it may also be a "key"
     * to look up. the system will figure out which it is.
     * 
     * @param key the key of the message to look up.
     * @param locale the locale to use.
     * @param arg0 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg1 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg2 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @param arg3 the argument to replace in the message. <B>An argument may also be another
     *        key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(String category,
                                       String key,
                                       Locale locale,
                                       Object arg0,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        return formatMessage(category, key, locale, args);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Format a message with any number of arguments. An argument may be an object or it may
     * also be a "key" to look up. the system will figure out which it is.
     * 
     * @param msgKey the key of the message to look up.
     * @param argSrcArray an array of Objects to replace in the message. <B>Any item in the
     *        array may also be another key </B>
     * @return the message retrieved from the bundle
     */
    public static String formatMessage(MsgKey msgKey, Object argSrcArray[])
    {
        Object[] locSrcArray = argSrcArray;
        if (locSrcArray == null)
        {
            // The user has called us with a null array,
            // assume they are passing a String that is null,
            // and call the appropriate function
            String tmp = null;
            locSrcArray = new Object[1];
            locSrcArray[0] = tmp;
        }

        try
        {

            MessageFormat mf = new MessageFormat(getMessageString(msgKey));
            if (msgKey.getLocale() != null)
            {
                mf.setLocale(msgKey.getLocale());
            }

            Object[] args = new Object[locSrcArray.length];
            for (int i = 0; i < args.length; i++)
            {
                args[i] = substituteKey(locSrcArray[i], msgKey.getCategory(), msgKey
                    .getLocale());
            }

            return mf.format(args);

        }
        catch (MissingResourceException e)
        {
            System.out
                .println("Caught a MissingResourceException attempting to get Resource bundle file and entry.  Could not find:"
                    + msgKey);
        }
        catch (CascadingRuntimeException e)
        {
            System.out
                .println("Caught a CascadingRuntimeException attempting to get Resource bundle file and entry.  Msg:"
                    + e.getMessage());
        }

        return msgKey.getKey();

    }

    /**
     * Retrieve a message from the key passed.
     * 
     * @param msgKey the key of the message to look up.
     * @return the message retrieved from the bundle
     */
    private static String getMessageString(MsgKey msgKey) throws MissingResourceException
    {
        if (MsgKey.isNull(msgKey))
        {
            return "";
        }

        return MessageResourceManager.getInstance().getMessageString(msgKey);
    }

    /**
     * This function is for args. If the argument is a key, then it is looked up and replaced.
     * If not, it is just returned intact.
     * 
     * @param rawArg the argument to check.
     * @param category the category to use.
     * @param locale the locale to use.
     * @return if the argument is a key, then the text from the bundle, otherwise just the
     *         argument
     */
    private static Object substituteKey(Object rawArg, String category, Locale locale)
    {
        Object result = rawArg;

        if (isKey(rawArg))
        {
            try
            {
                result = getMessageString(new MsgKey(category, (String)rawArg, locale));
            }
            catch (MissingResourceException e)
            {
                // if there is no key in the bundle, then it must be just a string
                // so return it.
            }
        }

        return result;
    }

    /**
     * Determines if the argument is a key.
     * 
     * @param rawArg the argument to check
     * @return true if the argument is a key, otherwise false
     */
    private static boolean isKey(Object rawArg)
    {
        if ((rawArg != null) && (rawArg instanceof String))
        {
            String key = (String)rawArg;

            // see if there is a '.' within the string, but not at the end
            int ln = key.length();
            int index = key.indexOf('.');
            if ((index != -1) && (index < (ln - 1)))
            {
                return true;
            }
        }

        return false;
    }
}