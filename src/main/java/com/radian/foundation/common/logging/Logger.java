
package com.radian.foundation.common.logging;

/**
 * Logger interface.
 *
 * @author Giedrius Trumpickas
 */
public interface Logger
{
    /**
     * Logs debug message.
     *
     * @param message a debug message
     */
    void debug(String message);

    /**
     * Logs debug message and given throwable
     *
     * @param message a debug message
     * @param throwable a throwable
     */
    void debug(String message, Throwable throwable);

    /**
     * Logs info message
     *
     * @param message a info message	 
     */
    void info(String message);

    /**
     * Logs info message and given throwable
     *
     * @param message an error message
     * @param throwable a throwable
     */
    void info(String message, Throwable throwable);

    /**
     * Logs warrning message
     *
     * @param message a warrning message	 
     */
    void warn(String message);

    /**
     * Logs warnning message and given throwable
     *
     * @param message a warrning message
     * @param throwable a throwable
     */
    void warn(String message, Throwable throwable);

    /**
     * Logs error message
     *
     * @param message a error message	 
     */
    void error(String message);

    /**
     * Logs error message and given throwable
     *
     * @param message an error message
     * @param throwable a throwable
     */
    void error(String message, Throwable throwable);

    /**
     * Logs fatal error message
     *
     * @param message a fatal error message	 
     */
    void fatal(String message);

    /**
     * Logs fatal error message and given throwable
     *
     * @param message an fatal error message
     * @param throwable a throwable
     */
    void fatal(String message, Throwable throwable);

    /**
     * Logs message with given given level.
     *
     * @param level a loggining level
     * @param message a log message
     */
    void logLevel(Level level, String message);

    /**
     * Logs message and throwable with given level.
     *
     * @param level a loggining level
     * @param message a log message
     * @param throwable a throwable
     */
    void logLevel(Level level, String message, Throwable throwable);

    /**
     * Tests if given level is enabled.
     *
     * @param level a loggining level
     * @return <code>true</code> if given level is enabled
     */
    boolean isLevelEnabled(Level level);

    /**
     * Tests if info loggining level is enabled for this logger
     *
     * @return <code>true</code> if info level is enabled
     */         
    boolean isInfoEnabled();

    /**
     * Tests if info loggining level is enabled for this logger
     *
     * @return <code>true</code> if info level is enabled
     */
    boolean isDebugEnabled();

    /**
     * Tests if error loggining level is enabled for this logger
     *
     * @return <code>true</code> if error level is enabled
     */
    boolean isErrorEnabled();

    /**
     * Tests if warrning loggining level is enabled for this logger.
     *
     * @return <code>true</code> if warrning level is enabled
     */
    boolean isWarnEnabled();

    /**
     * Tests if fatal error loggining level is enabled for this logger
     *
     * @return <code>true</code> if fatal error level is enabled
     */
    boolean isFatalEnabled();

    /**
     * Logs a method entry. 
     * This is a convenience method that can be used to log entry to a method.
     *
     * @param sourceClass a source class
     * @param sourceMethod a source method
     */
    void entering(Class sourceClass, String sourceMethod);

    /**
     * Logs a method return.
     * This is a convenience method that can be used to log a method return.	 
     *
     * @param sourceClass a source class
     * @param sourceMethod a source method
     */ 
    void exiting(Class sourceClass, String sourceMethod);

    /**
     * Logs throwing an exception. 
     * This is a convenience method to log that a method is terminating by throwing an exception.
     *
     * @param sourceClass a source class
     * @param sourceMethod a source method
     * @param throwable a source method
     */ 
    void throwing(Class sourceClass, String sourceMethod, Throwable throwable);         
}
