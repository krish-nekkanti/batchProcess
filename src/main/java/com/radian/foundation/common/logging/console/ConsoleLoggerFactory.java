
package com.radian.foundation.common.logging.console;

import java.util.HashMap;
import java.util.Map;

import com.radian.foundation.common.logging.Logger;
import com.radian.foundation.common.logging.LoggerFactory;


/**
 * Simple console logger factory.
 *
 * @author Giedrius Trumpickas
 */
public final class ConsoleLoggerFactory implements LoggerFactory
{
    /**
     *  Root logger.
     */ 
    private static Logger rootLogger = new ConsoleLogger("Console");

    /**
     * Loggers.
     */
    private static Map loggers = new HashMap();

    /**
     * Constructs cosnole loggers factory.
     *
     * @param configProperty config property value
     */
    public ConsoleLoggerFactory()
    {                       
    }

    /**
     * Creates logger for given name.
     *
     * @param name logger name
     * @return created logger for given name
     */
    public Logger createLogger(String name)
    {
        Logger logger = (Logger)loggers.get(name);
        if ( logger == null )
        {
            synchronized(loggers)
            {                
                logger = new ConsoleLogger(name);
                loggers.put(name, logger);              
            }
        }
        return logger;
    }

    /**
     * Gets root logger.
     *
     * @return root logger
     */
    public Logger createLogger()
    {
        return rootLogger;
    }    
}
