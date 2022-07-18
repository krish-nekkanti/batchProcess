/* 
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */

package com.radian.foundation.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

/**
 * A DefaultConfigurationBuilder builds <code>Configuration</code>s from XML,
 * via a SAX2 compliant parser.
 *
 * <p>
 * XML namespace support is optional, and disabled by default to preserve
 * backwards-compatibility. To enable it, pass the {@link
 * #DefaultConfigurationBuilder(boolean)} constructor the flag <code>true</code>.
 * </p>
 * <p>
 * The mapping from XML namespaces to {@link Configuration} namespaces is pretty
 * straightforward, with one caveat: attribute namespaces are (deliberately) not
 * supported. Enabling namespace processing has the following effects:</p>
 * <ul>
 *  <li>Attributes starting with <code>xmlns:</code> are interpreted as
 *  declaring a prefix:namespaceURI mapping, and won't result in the creation of
 *  <code>xmlns</code>-prefixed attributes in the <code>Configuration</code>.
 *  </li>
 *  <li>
 *  Prefixed XML elements, like <tt>&lt;doc:title xmlns:doc="http://foo.com"&gt;,</tt>
 *  will result in a <code>Configuration</code> with <code>{@link
 *  Configuration#getName getName()}.equals("title")</code> and <code>{@link
 *  Configuration#getNamespace getNamespace()}.equals("http://foo.com")</code>.
 *  </li>
 * </ul>
 */
public class DefaultConfigurationBuilder
{
    private SAXParser parser;
    private SAXConfigurationHandler parseHandler;
    private boolean enableNamespaces;

    /**
     * Create a Configuration Builder with a default XMLReader that ignores
     * namespaces.  In order to enable namespaces, use either the constructor
     * that has a boolean or that allows you to pass in your own
     * namespace-enabled XMLReader.
     */
    public DefaultConfigurationBuilder() throws ConfigurationException
    {
        this(false);
    }

    /**
     * Create a Configuration Builder, specifying a flag that determines
     * namespace support.
     *
     * @param enableNamespaces If <code>true</code>,  a namespace-aware
     *                         <code>SAXParser</code> is used. If
     *                         <code>false</code>, the default JAXP
     *                         <code>SAXParser</code> (without namespace
     *                         support) is used.
     */
    public DefaultConfigurationBuilder(final boolean enableNamespaces) throws ConfigurationException
    {
        this.enableNamespaces = enableNamespaces;
        parser = getParser (enableNamespaces);
        setHandler(getDefaultHandler());
    }

    /**
     * Create a Configuration Builder, specifying a XML schema for configuration validation.
     *
     * @param schemaFile a path to schema
     */
    public DefaultConfigurationBuilder(String schemaFile) throws ConfigurationException
    {        
        parser = getParser(schemaFile);
        setHandler(getDefaultHandler());
    }

    /**
     * Create a Configuration Builder with your own
     * <code>SAXConfigurationHandler</code>.
     *
     * @param enableNamespaces If <code>true</code>,  a namespace-aware
     *                         <code>SAXParser</code> is used. If
     *                         <code>false</code>, the default JAXP
     *                         <code>SAXParser</code> (without namespace
     *                         support) is used.
     * @param handler          custom <code>SAXConfigurationHandler</code>.
     *
     * @throws ConfigurationException if SAX parser can not be created.
     */
    public DefaultConfigurationBuilder(final boolean enableNamespaces, SAXConfigurationHandler handler) throws ConfigurationException
    {
        this(enableNamespaces);
        setHandler(handler);
    }

    /**
     * Sets up the configuration handler.
     */
    protected void setHandler(SAXConfigurationHandler handler)
    {
        this.parseHandler = handler;
    }

    /**
     * Gets current configuration handler.
     */
    protected SAXConfigurationHandler getHandler()
    {
        return parseHandler;
    }

    /**
     * Gets a default SAXConfigurationHandler for your configuration reading.
     */
    protected SAXConfigurationHandler getDefaultHandler()
    {
        try
        {
            if (parser.getXMLReader().getFeature("http://xml.org/sax/features/namespaces"))
            {
                return new NamespacedSAXConfigurationHandler();
            }
        }
        catch (Exception e)
        {
            // ignore error and fall through to the non-namespaced version
        }

        return new SAXConfigurationHandler();
    }

    /**
     * Gets a SAX parser with or without namespace support.
     *
     * @param enableNamespaces
     */
    protected SAXParser getParser (final boolean enableNamespaces) throws ConfigurationException
    {
        try
        {
            final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            if (enableNamespaces)
            {
                saxParserFactory.setNamespaceAware(true);
            }

            return saxParserFactory.newSAXParser();
        }
        catch (Exception se)
        {
            throw new ConfigurationException("Unable to setup SAX parser", se);
        }
    } 

    /**
     * Gets a SAX parser with schema validation turned on
     *
     * @param schemaLocation a schema URI
     */
    protected SAXParser getParser (String schemaLocation) throws ConfigurationException
    {
        try
        {
            final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.getXMLReader().setFeature("http://xml.org/sax/features/validation", true);        
            saxParser.getXMLReader().setFeature( "http://xml.org/sax/features/validation", true);
            saxParser.getXMLReader().setFeature( "http://xml.org/sax/features/namespaces", true );
            saxParser.getXMLReader().setFeature("http://apache.org/xml/features/validation/schema", true);
            saxParser.getXMLReader().setFeature( "http://apache.org/xml/features/validation/schema", true );
            saxParser.getXMLReader().setFeature( "http://apache.org/xml/features/nonvalidating/load-external-dtd", true );
            saxParser.getXMLReader().setFeature( "http://apache.org/xml/features/validation/schema-full-checking", true );
            saxParser.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", schemaLocation);
            return saxParser;
        }
        catch (Exception se)
        {
            throw new ConfigurationException("Unable to setup SAX parser", se);
        }
    }    

    /**
     * Build a configuration object from a file using a filename.
     */
    public Configuration buildFromFile(final String filename) throws ConfigurationException
    {
        return buildFromFile (new File(filename));
    }

    /**
     * Build a configuration object from a file using a File object.
     */
    public Configuration buildFromFile(final File file) throws ConfigurationException
    {
        try
        {
            return build (new FileInputStream(file));
        }
        catch (FileNotFoundException fnf)
        {
            throw new ConfigurationException (fnf.getMessage (), fnf);
        }
    }

    /**
     * Build a configuration object using an URI
     */
    public Configuration build(final String uri) throws ConfigurationException
    {
        return build(new InputSource(uri));
    }

    /**
     * Build a configuration object using an InputStream.
     */
    public Configuration build(final InputStream inputStream) throws ConfigurationException
    {
        return build(new InputSource(inputStream));
    }

    /**
     * Build a configuration object using an XML InputSource object
     */
    public Configuration build(final InputSource input) throws ConfigurationException
    {
        try
        {
            synchronized(this)
            {
                parseHandler.clear();
                parser.parse(input, parseHandler);
                return parseHandler.getConfiguration();
            }
        }
        catch (Exception e)
        {
            throw new ConfigurationException(e.getMessage(), e);
        }
    }

}
