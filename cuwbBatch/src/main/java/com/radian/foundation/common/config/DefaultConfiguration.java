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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the default <code>Configuration</code> implementation.
 */
public class DefaultConfiguration  implements ChangeableConfiguration, Serializable
{
    /**
     * Constant for an empty configuration array.
     */
    protected static final Configuration[] EMPTY_CONFIG_ARRAY = new Configuration[0];

    /**
     * Parent <code>Configuration</code>. node.
     */
    private Configuration parentNode;

    /**
     * Location in the XML file for <code>Configuration</code>.
     */
    private final String location;

    /**
     * XML namespace location for this <code>Configuration</code> node.
     */
    private final String namespace;

    /**
     * XML namespace prefix for this <code>Configuration</code> node.
     */
    private final String prefix;

    /**
     * The name of this <code>Configuration</code> node.
     */
    private final String name;

    /**
     * List of child nodes.
     */
    private ArrayList childNodes;

    /**
     * Attribute list.
     */
    private HashMap attributes;

    /**
     * Read-only indicator. If configuration is marked as read-only
     * any modification will result in <code>IllegalStateException</code>.
     */
    private boolean readOnly;

    /**
     * Create a new <code>DefaultConfiguration</code> instance.
     */
    public DefaultConfiguration(final String name, final String location)
    {
        this(name, location, "", "");
    }

    /**
     * Create a new <code>DefaultConfiguration</code> instance.
     * @param name     config node name.
     * @param location builder-specific locator string.
     * @param ns       namespace string (typically a URI). Should not be
     *                 <code>null</code>; use <code>""</code>, if no namespace.
     * @param prefix   a short string prefixed to element names, associating
     *                 elements with a longer namespace string. Should not be
     *                 <code>null</code>; use <code>""</code>, if no namespace.
     */
    public DefaultConfiguration(final String name, final String location,
                                final String ns, final String prefix)
    {
        this.name = name;
        this.location = location;
        this.namespace = ns;
        this.prefix = prefix;  // only used as a serialization hint. Cannot be null
    }

    /**
     * Returns the name of the node.
     *
     * @return name of the <code>Configuration</code> node.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns a <code>String</code> indicating which namespace this
     * <code>Configuration</code> node belongs to.
     *
     * @return a <code>String</code> identifying the namespace of
     *         this <code>Configuration</code>.
     *
     * @throws ConfigurationException if the namespace is not
     *         present (<code>null</code>).
     */
    public String getNamespace() throws ConfigurationException
    {
        if (namespace == null)
        {
            throw new ConfigurationException("No namespace (not even default \"\") is associated with the "+
                                             "configuration element \"" + getName() +
                                             "\" at " + getLocation());
        }

        return namespace;
    }

    /**
     * Returns the prefix of the namespace for this <code>Configuration</code>.
     * node.
     *
     * @return the prefix of the namespace.
     *
     * @throws ConfigurationException if prefix is not present
     *         (<code>null</code>).
     */
    protected String getPrefix() throws ConfigurationException
    {
        if (prefix == null)
        {
            throw new ConfigurationException("No prefix (not even default \"\") is associated with the "+
                                             "configuration element \"" + getName() +
                                             "\" at " + getLocation());
        }

        return prefix;
    }

    /**
     * Returns a <code>String</code> describing location of
     * <code>Configuration</code>.
     *
     * @return a string describing location of <code>Configuration</code> node.
     */
    public String getLocation()
    {
        return (location == null) ? "" : location;
    }

    /**
     * Returns the parent of this <code>Configuration</code> node,
     * or <code>null</code> if this is the root.
     *
     * @return the parent of this configuration node.
     */
    public Configuration getParent()
    {
        return parentNode;
    }

    /**
     * Returns an array of all key names that are associated with this
     * <code>Configuration</code> node.
     * <p>
     * <em>The order of keys in this array can not be relied on.</em> As
     * with XML, a <code>Configuration</code>'s attributes are an
     * <em>unordered</em> set. If your code relies on order, eg
     * <tt>conf.getKeys()[0]</tt>, then it is liable to break if a
     * different XML parser is used.
     * </p>
     *
     * @return an array containing key names for this configuration node.
     */
    public String[] getKeys()
    {
        if (attributes == null)
        {
            return new String[0];
        }
        else
        {
            return(String[])attributes.keySet().toArray(new String[0]);
        }
    }

    /**
     * Returns all children nodes for this <code>Configuration</code> node.
     * The array order will reflect the order in the source config file.
     *
     * @return all child nodes
     */
    public Configuration[] getChildren()
    {
        if (childNodes == null)
        {
            return EMPTY_CONFIG_ARRAY;
        }
        else
        {
            return(Configuration[])childNodes.toArray(EMPTY_CONFIG_ARRAY);
        }
    }

    /**
     * Returns all children nodes with the specified name for this
     * <code>Configuration</code> node. The array order will reflect
     * the order in the source config file.
     *
     * @param childName the name of the children to get.
     *
     * @return the child nodes with name <code>childName</code>
     */
    public Configuration[] getChildren(final String childName)
    {
        if (childNodes == null)
        {
            return EMPTY_CONFIG_ARRAY;
        }
        else
        {
            final int size = childNodes.size();
            final ArrayList children = new ArrayList();

            for ( int i = 0; i < size; i++ )
            {
                final Configuration configuration = (Configuration)childNodes.get(i);
                if (childName.equals(configuration.getName()))
                {
                    children.add(configuration);
                }
            }

            return (Configuration[])children.toArray(EMPTY_CONFIG_ARRAY);
        }
    }

    /**
     * Returns the value associated with the specified <code>key</code>
     * for this configuration node.
     *
     * @param key the name of the key whose associated value is to be returned.
     *
     * @return the <code>String</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists.
     */
    public String getString(String key) throws ConfigurationException
    {
        final String value = (attributes != null)
                               ? (String)attributes.get(key) : null;

        if (value == null)
        {
            throw new ConfigurationException("No attribute named \"" + key + "\" is " +
                                             "associated with the configuration element \"" +
                                             getName() + "\" at " + getLocation());
        }

        return value;
    }

    /**
     * Return a <code>Configuration</code> instance encapsulating the specified
     * child node.
     *
     * @param childName the name of the child node.
     * @param createNew if <code>true</code>, a new <code>Configuration</code>
     *                  will be created and returned if the specified child
     *                  does not exist. If <code>false</code>, <code>null</code>
     *                  will be returned when the specified child doesn't exist.
     *
     * @return configuration node with the specified name
     */
    public Configuration getChild(String childName, boolean createNew)
    {
        if (childNodes != null)
        {
            final int size = childNodes.size();
            for (int i = 0; i < size; i++)
            {
                final Configuration configuration = (Configuration)childNodes.get(i);
                if (childName.equals(configuration.getName()))
                {
                    return configuration;
                }
            }
        }

        return (createNew)
                  ? new DefaultConfiguration (childName, "-")
                  : null;
    }

    /**
     * Adds a child <code>Configuration</code> to this configuration element.
     *
     * @param configuration new child <code>Configuration</code>.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    public void addChild(final DefaultConfiguration configuration)
    {
        checkWriteable();

        if (childNodes == null)
        {
            childNodes = new ArrayList();
        }

        childNodes.add(configuration);
        configuration.setParent(this);
    }

    /**
     * Removes a child <code>Configuration</code> to this configuration element.
     *
     * @param configuration child <code>Configuration</code> to be removed.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    public void removeChild(final DefaultConfiguration configuration)
    {
        checkWriteable();

        if (childNodes == null)
        {
            return;
        }
        childNodes.remove(configuration);
        configuration.setParent(null);
    }

    /**
     * Sets parent for this configuration node.
     *
     * @param node new parent <code>Configuration</code> node.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    protected void setParent(Configuration node)
    {
        checkWriteable();

        parentNode = node;
    }

    /**
     * Returns count of children <code>Configuration</code> nodes.
     *
     * @return count of children nodes.
     */
    public int getChildCount()
    {
        return (childNodes == null) ? 0 : childNodes.size();
    }

    /**
     * Checks if this <code>Configuration</code> object is writable.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    protected final void checkWriteable() throws IllegalStateException
    {
        if (readOnly)
        {
            throw new IllegalStateException("Configuration is read-only and can not be modified");
        }
    }

    /**
     * @see com.radian.shared.mw.config.ChangeableConfiguration#addChild(com.radian.shared.mw.config.ChangeableConfiguration)
     */
    public void addChild(ChangeableConfiguration configuration)
    {
        addChild((DefaultConfiguration)configuration);
    }

    /**
     * @see com.radian.shared.mw.config.ChangeableConfiguration#removeChild(com.radian.shared.mw.config.ChangeableConfiguration)
     */
    public void removeChild(ChangeableConfiguration configuration)
    {
        removeChild((DefaultConfiguration)configuration);
    }

    /**
     * @see com.radian.shared.mw.config.ChangeableConfiguration#setAttribute(String, String)
     */
    public String setAttribute(final String key, String value)
    {
        checkWriteable();

        if (attributes == null)
        {
            attributes = new HashMap();
        }

        return(String) attributes.put(key, value);
    }

    /**
     * @see com.radian.shared.mw.config.ChangeableConfiguration#markReadOnly()
     */
    public void markReadOnly()
    {
        readOnly = true;
    }

	@Override
	public Configuration getChild(String child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration getConfiguration(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nodeExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getString(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInt(String key) throws ConfigurationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String key) throws ConfigurationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String key, long defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(String key) throws ConfigurationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(String key, float defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String key) throws ConfigurationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String key, double defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getBoolean(String key) throws ConfigurationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] getByteArray(String key) throws ConfigurationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getByteArray(String key, byte[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}
}

