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

import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;

/**
 * A SAXConfigurationHandler helps build Configurations out of sax events,
 * including namespace information.
 */
public class NamespacedSAXConfigurationHandler extends SAXConfigurationHandler
{
    private final NamespaceSupport namespaceSupport = new NamespaceSupport();
    private final ArrayList        elements         = new ArrayList();
    private final ArrayList        prefixes         = new ArrayList();

    public void clear()
    {
        elements.clear();
/*
        Iterator i = prefixes.iterator();
        while ( i.hasNext() )
        {
            ( (ArrayList) i.next() ).clear();
        }
*/
        prefixes.clear();
        super.clear();
    }

    public void startDocument() throws SAXException
    {
        namespaceSupport.reset();
        super.startDocument();
    }

    public void endDocument() throws SAXException
    {
        super.endDocument();
        namespaceSupport.reset();
    }

    public void characters(final char[] ch, int start, int end) throws SAXException
    {
    }

    public void endElement(final String namespaceURI,
                           final String localName,
                           final String rawName) throws SAXException
    {
        final int location = elements.size() - 1;
        final Object object = elements.remove(location);
        final ArrayList lastPrefixes = (ArrayList)prefixes.remove(location);

        final Iterator i = lastPrefixes.iterator();
        while (i.hasNext())
        {
            endPrefixMapping((String) i.next());
        }
        lastPrefixes.clear();

        if (location == 0)
        {
            setConfiguration((Configuration)object);
        }

        namespaceSupport.popContext();
    }

    protected DefaultConfiguration createConfiguration(final String localName,
                                                       final String namespaceURI,
                                                       final String location)
    {
        String prefix = namespaceSupport.getPrefix(namespaceURI);
        if (prefix == null) prefix = "";
        return new DefaultConfiguration(localName, location, namespaceURI, prefix);
    }

    public void startElement(final String namespaceURI,
                             final String localName,
                             final String rawName,
                             final Attributes attributes) throws SAXException
    {
        namespaceSupport.pushContext();
        final ArrayList newPrefixes = new ArrayList();
        AttributesImpl componentAttr = new AttributesImpl();

        for (int i = 0; i < attributes.getLength(); i++)
        {
            if (attributes.getQName(i).startsWith("xmlns"))
            {
                newPrefixes.add(attributes.getLocalName(i));
                this.startPrefixMapping(attributes.getLocalName(i),
                                        attributes.getValue(i));
            }
            else
            {
                componentAttr.addAttribute(attributes.getURI(i),
                                           attributes.getLocalName(i),
                                           attributes.getQName(i),
                                           attributes.getType(i),
                                           attributes.getValue(i));
            }
        }

        final DefaultConfiguration newNode =
            createConfiguration(localName, namespaceURI, getLocationString());
        final int size = elements.size() - 1;

        if (size > -1)
        {
            final DefaultConfiguration parentNode =
                (DefaultConfiguration)elements.get(size);
            parentNode.addChild(newNode);
        }

        elements.add(newNode);
        prefixes.add(newPrefixes);

        final int attributesSize = componentAttr.getLength();

        for (int i = 0; i < attributesSize; i++)
        {
            final String name = componentAttr.getQName(i);
            final String value = componentAttr.getValue(i);
            newNode.setAttribute(name, value);
        }
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException
    {
        namespaceSupport.declarePrefix(prefix, uri);
        super.startPrefixMapping(prefix, uri);
    }
}

