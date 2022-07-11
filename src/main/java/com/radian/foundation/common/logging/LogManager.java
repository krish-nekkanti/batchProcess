package com.radian.foundation.common.logging;

import java.lang.reflect.Constructor;

import com.radian.foundation.common.logging.console.ConsoleLoggerFactory;

/**
 * Log manager. Note that this implementation of log manager expects configuation file uri
 * in the system properties it looks for property "com.radian.logging.config"
 *
 * @author Giedrius Trumpickas
 */
public final class LogManager
{
	/**
	 * Loggining configuration file property.
	 */
	private final static String LOG_CONFIG_PROPERTY = "com.radian.logging.config";

	/**
	 * Loggers factory property.
	 */
	private final static String LOG_FACTORY_PROPERTY = "com.radian.logging.LoggingFactory";
	
	/**
	 * Commons log4j config file location property.
	 */
	private final static String COMMONS_LOG4J_CONFIG_PROPERTY = "log4j.configuration";

	/**
	 * Tracks if log manager was initialized.
	 */
	private static boolean isInitialized = false;

	/**
	 * Loggers factory.
	 */
	private static LoggerFactory factory = null;

	/**
	 * Initialize loggining manager.
	 */
	/* NOTE: do not do static init here since application might want to initialize log manager explicitly
	static {
	    init();
	}*/

	/**
	 * Creates logger for given name.
	 *
	 * @param name a logger name
	 * @return created logger
	 */
	public static Logger getLogger(String name)
	{
		if (!isInitialized)
			init();

		return factory.createLogger(name);
	}

	/**
	 * Creates logger for given class.
	 *
	 * @param name a logger name
	 * @return created logger
	 */
	public static Logger getLogger(Class c)
	{
		if (!isInitialized)
			init();

		return factory.createLogger(c.getName());
	}

	/**
	 * Creates logger.
	 *	
	 * @return created logger
	 */
	public static Logger getLogger()
	{
		if (!isInitialized)
			init();

		return factory.createLogger();
	}

	/**
	 * Initializes logging manager.
	 * Performs default initialization if no explicit initialization was done.
	 * NOTE: implementation note: currently allows multiple initializations.
	 * 
	 * @see #init(String, String)
	 */
	private static void init()
	{
		String configValue = System.getProperty(LOG_CONFIG_PROPERTY);
		String logFactoryValue =
			System.getProperty(
				LOG_FACTORY_PROPERTY,
				"com.radian.foundation.common.logging.log4j.Log4jFactory");
		try
		{
			factory = createLoggerFactory(logFactoryValue, configValue);
		}
		catch (LoggingException e)
		{
			factory = new ConsoleLoggerFactory();
			factory.createLogger().error(
				"Can not create factory of class - " + logFactoryValue + " instance",
				e);
		}
		isInitialized = true;
	}

	/**
	 * Initializes logging manager with parameters.
	 * Provides means for application to explicitly init logging framework. 
	 * NOTE: implementation note: currently allows multiple initializations.
	 * 
	 * @param loggingFactory fully qualified logging factory class name
	 * @param loggingConfigFile logging configuration file name
	 */
	public static void init(String loggingFactory, String loggingConfigFile)
	{
		try
		{
			String log4jconfig = System.getProperty(COMMONS_LOG4J_CONFIG_PROPERTY);
			// prefere log4j standart configuration property over other stuff
			if(log4jconfig != null)
			{
				factory = createLoggerFactory(loggingFactory, log4jconfig);
				return;
			}
			System.setProperty(COMMONS_LOG4J_CONFIG_PROPERTY, loggingConfigFile);
			factory = createLoggerFactory(loggingFactory, loggingConfigFile);
		}
		catch (LoggingException e)
		{
			factory = new ConsoleLoggerFactory();
			factory.createLogger().error(
				"Can not create factory of class - " + loggingFactory + " instance",
				e);
		}

		isInitialized = true;
	}

	/**
	 * Creates instance of the logger factory by name.
	 *
	 * @param factoryClassName a logging factory class name
	 * @param configProperty a config system property value
	 * @return instance of logger factory
	 * @exception LoggingException
	 */
	private static LoggerFactory createLoggerFactory(
		String factoryClassName,
		String configProperty)
		throws LoggingException
	{
		try
		{
			Class factoryClass = Class.forName(factoryClassName);
			Class[] constructArgs = { String.class };
			Object[] factoryArgs = { configProperty };
			Constructor cons = factoryClass.getDeclaredConstructor(constructArgs);
			return (LoggerFactory) (cons.newInstance(factoryArgs));
		}
		catch (ClassCastException e)
		{
			throw new LoggingException("Logging factory property value is not logger factory");
		}
		catch (Exception e)
		{
			throw new LoggingException(e.getMessage());
		}
	}
}
