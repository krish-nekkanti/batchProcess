
package com.radian.foundation.common.logging;

/**
 * Logger factory.
 *
 * @author Giedrius Trumpickas
 */
public interface LoggerFactory
{
    /**
     * Creates root logger.
     *	 
     * @return roo logger
     */
    Logger createLogger();

    /**
     * Creates logger for given name.
     *
     * @param name logger name
     * @return created logger for given name
     */
    Logger createLogger(String name);
}
