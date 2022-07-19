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

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAXConfigurationHandler helps build Configurations out of SAX events.
 */
public class SAXConfigurationHandler extends DefaultHandler
{
    private Configuration configuration;
    private DefaultConfiguration currentNode;
    private Locator         locator;

    public Configuration getConfiguration()
    {
        return configuration;
    }

    protected void setConfiguration (Configuration configuration)
    {
        this.configuration = configuration;
    }

    public void clear()
    {
        configuration = null;
        currentNode = null;
        locator = null;
    }

    public void setDocumentLocator(final Locator locator)
    {
        this.locator = locator;
    }

    public void startElement(final String namespaceURI,
                             final String localName,
                             final String rawName,
                             final Attributes attributes) throws SAXException
    {
        final DefaultConfiguration newNode =
            createConfiguration(rawName, getLocationString());
        if (currentNode != null)
        {
            currentNode.addChild(newNode);
        }
        currentNode = newNode;

        final int attributesSize = attributes.getLength();
        for (int i = 0; i < attributesSize; i++)
        {
            final String name = attributes.getQName(i);
            final String value = attributes.getValue(i);
            currentNode.setAttribute(name, value);
        }
    }

    public void endElement(final String namespaceURI,
                           final String localName,
                           final String rawName) throws SAXException
    {
        if (currentNode.getParent() == null)
        {
            setConfiguration (currentNode);
        }
        currentNode = (DefaultConfiguration)currentNode.getParent();
    }

    public void characters(final char[] ch, int start, int end) throws SAXException
    {
    }

    protected DefaultConfiguration createConfiguration(final String localName,
                                                       final String location)
    {
        return new DefaultConfiguration(localName, location);
    }

    /**
     * This just throws an exception on a parse error.
     */
    public void error(final SAXParseException exception) throws SAXException
    {
        throw exception;
    }

    /**
     * This just throws an exception on a parse error.
     */
    public void warning(final SAXParseException exception) throws SAXException
    {
        throw exception;
    }

    /**
     * This just throws an exception on a parse error.
     */
    public void fatalError(final SAXParseException exception) throws SAXException
    {
        throw exception;
    }

    protected String getLocationString()
    {
        if (locator == null)
        {
            return "Unknown";
        }
        else
        {
            return locator.getSystemId() + ":" +
                   locator.getLineNumber() + ":" +
                   locator.getColumnNumber();
        }
    }
}

