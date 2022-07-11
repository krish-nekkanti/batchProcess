/*
 * Created on Apr 8, 2003
 *
 */
package com.radian.foundation.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import com.radian.foundation.common.util.JNDIHelper;

/**
 * <code>AxiomConfigurationLocator</code>is responsible for locating AXIOM configuration.
 *
 * NOTE: current version supports r/o configuration for standalone server.
 * <code>AxiomConfigurationLocator</code> tries to resolve configuration file in 3 steps:<ul>
 *   <li>check <code>axiom.config.path</code> system property;</li>
 *   <li>if property not found check <code>java:comp/env/axiomConfigPath</code> ;</li>
 *   <li>if property not found try to use default path;</li>
 *   <li>if file not found at default path try to load configuration file as a resource</li>
 * </ul>
 *
 * @author Rolandas Valteris
 */
public class AxiomConfigurationLocator
{
    // NOTE: cannot use logger here since configuration should be initialized prior logger
    // intialization. since logger uses config framework for init
    //private static Logger logger = LogManager.getLogger(AxiomConfigurationLocator.class);

    /** JNDI name that holds path to configuration file. */
    private static final String CONFIG_PATH_JNDI_NAME = "java:comp/env/axiomConfigPath";

    /** Name of the system property that holds path to configuration file. */
    private static final String CONFIG_PATH_PROPERTY = "axiom.config.path";

    /** Default configuration file path. Used in case {@link #CONFIG_PATH_PROPERTY} is not specified. */
    private static final String DEFAULT_CONFIG_PATH = "applications/axiom-config.xml";

    /** Configuration resource path to be loaded in case configuration file is not found. */
    private static final String CONFIG_RESOURCE_PATH = "application/default-axiom-config.xml";

    /** Name of configuration object in JNDI tree. */
    private static final String CONFIG_JNDI_NAME = "axiomConfig";

    /** Lazy init singleton locator instance. */
    private static AxiomConfigurationLocator inst = null;

    /** Factory method. Currently returns singleton instance. */
    public static final AxiomConfigurationLocator getInstance()
    {
        if (inst == null)
        {
            inst = new AxiomConfigurationLocator();
        }

        return inst;
    }

    /** Locally cached configuration object. */
    private ChangeableConfiguration config = null;

    /** configuration storage source as resolved by {@link #resolveConfigSource()}. Used for testing. */
    private String configSource = "unresolved";

    /**
     * Default c-tor.
     */
    private AxiomConfigurationLocator()
    {
        super();
    }

    /**
     * Returns r/o configuration implementation.
     *
     * @return r/o configuration implementation.
     */
    public Configuration getConfiguration() throws ConfigurationException
    {
        return getChangeableConfiguration();
    }

    /**
     * Returns changeable configuration implementation.
     *
     * @return changeable configuration implementation.
     */
    public ChangeableConfiguration getChangeableConfiguration() throws ConfigurationException
    {
        //System.out.println("ENTERING AxiomConfigurationLocator.getChangeableConfiguration()");

        // first check if we have config in the directory
        ChangeableConfiguration configInJndi = null;

        try
        {
            configInJndi = (ChangeableConfiguration) JNDIHelper.lookup(CONFIG_JNDI_NAME);
            configSource = "jndi object";
            //System.out.println("configuration successfully retrieved from JNDI");
            //System.out.println("LEAVING AxiomConfigurationLocator.getChangeableConfiguration()");
            return configInJndi;
        }
        catch (NamingException ignore)
        { // config not found in directory
        }

        System.out.println("configuration not found in JNDI");

        // lazy loading. check local cache
        if (config == null)
        {
            System.out.println("configuration is not locally cached, trying to load it...");
            configInJndi = load();
            if (configInJndi != null)
                System.out.println("configuration loaded from " + getConfigSourceStr());
            else
                System.out.println("unable to load configuration");
        }
        else
        {
            configSource = "local object";
            configInJndi = config;
            System.out.println("using locally cached configuration object");
        }

        // try to place configuration object into directory
        try
        {
            System.out.println("Trying to bind configuration to JNDI...");
            JNDIHelper.bindNonReplicatable(CONFIG_JNDI_NAME, configInJndi);
            System.out.println("configuration successfully bound to JNDI");
        }
        catch(NameAlreadyBoundException e){
        }
        catch (NamingException e)
        {
            // unsuccessful attempt. just cache config object locally
            System.out.println("unable to bind configuration to JNDI");
            if (config == null)
            {
                System.out.println("configuration stored in local cache");
                config = configInJndi;
            }
        }

//        System.out.println("LEAVING AxiomConfigurationLocator.getChangeableConfiguration()");
        return configInJndi;
    }

    /**
     * Loads configuration from persistent storage.
     */
    private ChangeableConfiguration load() throws ConfigurationException
    {
        DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();

        return (ChangeableConfiguration) builder.build(resolveConfigSource());
    }

    /**
     * Resolves configuration persistent source.
     *
     * @return
     */
    private InputStream resolveConfigSource() throws ConfigurationException
    {
        InputStream result = null;
        // first try to get path from sys properties
        configSource = "system property";
        String fileName = System.getProperty(CONFIG_PATH_PROPERTY);

        // unsuccessfull
        if (fileName == null)
        {
            // try JNDI
            try
            {
                configSource = "jndi name";
                fileName = (String) JNDIHelper.lookup(CONFIG_PATH_JNDI_NAME);
            }
            catch (NamingException ignore)
            {
            }
        }

        // fall back to default file name
        if (fileName == null)
        {
            configSource = "default path";
            fileName = DEFAULT_CONFIG_PATH;
        }

        File file = new File(fileName);

        // if file is not found, try to load as a resource
        if (!file.exists())
        {
            configSource = "resource";
            result =
                Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            // as a last resourt, fall back to the default resource path
            if (result == null)
            {
                result =
                    Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        CONFIG_RESOURCE_PATH);
            }
        }
        else
        {
            try
            {
                result = new FileInputStream(file);
            }
            catch (FileNotFoundException ignore)
            { // safe to ignore exception since we already checked if the file exists
            }
        }

        if (result == null)
        {
            configSource = "not found";
            throw new ConfigurationException("Configuration not found. Check glove compartment, you might have left it there");
        }

        return result;
    }

    /**
     * Helper method for testing.
     */
    public String getConfigSourceStr()
    {
        return configSource;
    }
}
