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

/**
 * <code>Configuration</code> is a interface encapsulating a configuration node
 * used to retrieve configuration values.
 *
 * <p>
 * This is a "read only" interface preventing applications from modifying their
 * own configurations. Once it is created, the information never changes.
 * </p>
 * <h3>Data Model</h3>
 * <p>
 * The data model is a subset of XML's; a single-rooted hierarchical tree where each
 * node can contain multiple <em>attributes</em>, and leaf nodes can also
 * contain a <em>value</em>. Reflecting this, <code>Configuration</code>s are
 * usually built from an XML file by the {@link DefaultConfigurationBuilder}
 * class, or directly by a SAX parser using a {@link SAXConfigurationHandler} or
 * {@link NamespacedSAXConfigurationHandler} event handler.
 * </p>
 * <h4>Namespace support</h4>
 * <p>
 * Each <code>Configuration</code> node has a namespace
 * associated with it, in the form of a string, accessible through {@link
 * #getNamespace}. If no namespace is present, <code>getNamespace</code> will
 * return blank (""). See {@link DefaultConfigurationBuilder} for details on how
 * XML namespaces are mapped to <code>Configuration</code> namespaces.
 * </p>
 * <h3>Example</h3>
 * <p>
 * As an example, consider two  <code>Configuration</code>s (with and without
 * namespaces) built from this XML:
 * </p>
 * <pre>
 * &lt;my-system version="1.3" xmlns:doc="http://myco.com/documentation"&gt;
 *   &lt;doc:desc&gt;This is a highly fictitious config file&lt;/doc:desc&gt;
 *   &lt;widget name="fooWidget" initOrder="1" threadsafe="true"/&gt;
 * &lt;/my-system&gt;
 * </pre>
 * <p>If namespace support is enabled (eg through {@link
 * DefaultConfigurationBuilder#DefaultConfigurationBuilder(boolean) new
 * DefaultConfigurationBuilder(true)}), then the <code>xmlns:doc</code> element
 * will not translate into a Configuration attribute, and the
 * <code>doc:desc</code> element will become a <code>Configuration</code> node
 * with name "desc" and namespace "http://myco.com/documentation". The
 * <code>widget</code> element will have namespace "".
 * </p>
 * <p>If namespace support is disabled (the default for {@link
 * DefaultConfigurationBuilder}), the above XML will translate directly to
 * <code>Configuration</code> nodes. The <code>my-system</code> node will have
 * an attribute named "xmlns:doc", and a child called "doc:desc".
 * </p>
 * <p>
 * Assuming the <code>Configuration</code> object is named <code>conf</code>,
 * here is how the data could be retrieved:
 * </p>
 * <table border="1">
 * <tr align="center"><th>CodeType</th><th>No namespaces</th><th>With namespaces</th></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getName getName}()</code></td><td colspan="2">my-system</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getKeys getKeys}().length</code></td><td>2</td><td>1</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChildren getChildren}().length</code></td><td colspan="2">2</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getFloat getFloat}("version")</code></td><td colspan="2">1.3</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("widget").{@link #getString getString}("name")</code></td><td colspan="2">fooWidget</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("widget").{@link #getBoolean getBoolean}("threadsafe")</code></td><td colspan="2"><code>true</code></td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("widget").{@link #getLocation getLocation}()</code></td><td colspan="2">file:///home/jeff/tmp/java/avalon/src/java/new.xconf:4:60</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("desc").{@link #getName getName}()</code></td><td>desc (see {@link #getChild(String)})</td><td>desc</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("doc:desc").{@link #getName getName}()</code></td><td>doc:desc</td><td>doc:desc (see {@link #getChild(String)})</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("desc").{@link #getString getString}()</code></td><td>{@link ConfigurationException}</td><td>This is a highly fictitious config file</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("doc:desc").{@link #getString getString}()</code></td><td>This is a highly fictitious config file</td><td>{@link ConfigurationException}</td></tr>
 * <tr align="center"><td align="left"><code>conf.{@link #getChild getChild}("desc").{@link #getNamespace getNamespace}()</code></td><td>&nbsp;</td><td>http://myco.com/documentation"</td></tr>
 * </table>
 * </p>
 * <p>
 * Type-safe utility methods are provided for retrieving attribute and element
 * values as <code>String</code>, <code>int</code>, <code>long</code>,
 * <code>float</code> and <code>boolean</code>.
 * </p> 
 */
public interface Configuration
{
    /**
     * Returns the name of the node.
     *
     * @return name of the <code>Configuration</code> node.
     */
    String getName();

    /**
     * Returns a <code>String</code> describing location of
     * <code>Configuration</code>. Location can be different for different
     * mediums (i.e. "file:line" for normal XML files or "table:primary-key"
     * for DB based configurations).
     *
     * @return a string describing location of <code>Configuration</code>
     */
    String getLocation();

    /**
     * Returns a <code>String</code> indicating which namespace this
     * <code>Configuration</code> node belongs to.
     *
     * <p>
     * What this returns is dependent on the configuration file and the
     * Configuration builder. If the Configuration builder does not support
     * namespaces, this method will return a blank string.
     * </p>
     * <p>In the case of {@link DefaultConfigurationBuilder}, the namespace will
     * be the URI associated with the XML element. Eg.,:</p>
     * <pre>
     * &lt;foo xmlns:x="http://blah.com"&gt;
     *   &lt;x:bar/&gt;
     * &lt;/foo&gt;
     * </pre>
     * <p>The namespace of <code>foo</code> will be "", and the namespace of
     * <code>bar</code> will be "http://blah.com".</p>
     *
     * @return a <code>String</code> identifying the namespace of
     *         this <code>Configuration</code>.
     *
     * @throws ConfigurationException if the namespace is not
     *         present (<code>null</code>).
     */
    String getNamespace() throws ConfigurationException;

    /**
     * Return a new <code>Configuration</code> instance encapsulating the
     * specified child node.
     * <p>
     * If no such child node exists, an empty <code>Configuration</code> will be
     * returned, allowing constructs such as
     * <code>conf.getChild("foo").getChild("bar").getChild("baz").{@link
     * #getString(String) getString}("default");</code>.
     * </p>
     * <p>
     * If you wish to get a <code>null</code> return when no element is present,
     * use {@link #getChild(String, boolean) getChild("foo", <b>false</b>)}.
     * </p>
     *
     * @param child the name of the child node.
     *
     * @return configuration node with the specified name.
     *
     * @see #getChild(String, boolean)
     * @see #getConfiguration(String)
     */
    Configuration getChild(String child);

    /**
     * Return a <code>Configuration</code> instance encapsulating the specified
     * child node.
     *
     * @param child     the name of the child node.
     * @param createNew if <code>true</code>, a new <code>Configuration</code>
     *                  will be created and returned if the specified child
     *                  does not exist. If <code>false</code>, <code>null</code>
     *                  will be returned when the specified child doesn't exist.
     *
     * @return configuration node with the specified name
     *
     * @see #getChild(String)
     * @see #getConfiguration(String)
     * @see #nodeExists(String)
     */
    Configuration getChild(String child, boolean createNew);

    /**
     * Returns all children nodes for this <code>Configuration</code> node.
     * The array order will reflect the order in the source config file.
     *
     * @return all child nodes
     *
     * @see #getParent()
     * @see #getChildren()
     */
    Configuration[] getChildren();

    /**
     * Returns all children nodes with the specified name for this
     * <code>Configuration</code> node. The array order will reflect
     * the order in the source config file.
     *
     * @param name the name of the children to get.
     *
     * @return the child nodes with name <code>name</code>
     *
     * @see #getParent()
     * @see #getChildren()
     */
    Configuration[] getChildren(String name);

    /**
     * Returns the root configuration node.
     *
     * @return the root configuration node.
     */
    Configuration getRoot();

    /**
     * Returns the parent of this configuration node,  or <code>null</code>
     * if this is the root.
     *
     * @return the parent of this configuration node.
     *
     * @see #getRoot()
     */
    Configuration getParent();

    /**
     * Returns the configuration node with the given path name that
     * exists in the same tree as this node. Relative path names
     * (which do not begin with the slash character ('/')) are interpreted
     * relative to this preference node.
     * <p>
     * If the node did not exist prior to this call, new empty path will be
     * created.
     * </p>
     *
     * @param path the path name of the node whose existence is to be checked.
     *
     * @return configuration node with the specified path.
     */
    Configuration getConfiguration(String path);

    /**
     * Returns true if the configuration node exists in the same tree as
     * this node. Relative path names (which do not begin with the slash
     * character ('/')) are interpreted relative to this preference node.
     *
     * @param path the path name of the node whose existence is to be checked.
     *
     * @return <code>true</code> if the specified node exists, returns
     *         <code>false</code> otherwise.
     */
    boolean nodeExists(String path);

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
    String[] getKeys();

    /**
     * Returns the value associated with the specified <code>key</code>
     * for this configuration node.
     *
     * @param key the name of the key whose associated value is to be returned.
     *
     * @return the <code>String</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists.
     *
     * @see #getString(String, String)
     */
    String getString(String key) throws ConfigurationException;

    /**
     * Returns the value associated with the specified <code>key</code>
     * for this configuration node. Returns the specified default value
     * if there is no value associated with the key.
     *
     * @param key          the name of the key whose associated value is to
     *                     be returned.
     * @param defaultValue the default value to be retuned, if the key does not
     *                     exist in this configuration node.
     *
     * @return the <code>String</code> value associated with <code>key</code>, or
     *         <code>defaultValue</code> if the associated value does not exist.
     *
     * @see #getString(String)
     */
    String getString(String key, String defaultValue);

    /**
     * Return the <code>int</code> value associated with the specified key
     * contained in this node.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as <code>int</code>.
     *
     * @return the <code>int</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists,
     *            or the value can not be interpreted as an <code>int</code>.
     *
     * @see #getString(String)
     * @see #getInt(String, int)
     */
    int getInt(String key) throws ConfigurationException;

    /**
     * Return the <code>int</code> value associated with the specified key
     * contained in this node. Returns the specified default value
     * if there is no value associated with the key.
     *
     * @param key          the name of the key whose associated value is to
     *                     be returned as <code>int</code>.
     * @param defaultValue the default value to be retuned, if the key does not
     *                     exist in this configuration node.
     *
     * @return the <code>int</code> value of associated <code>key</code>.
     *
     * @see #getString(String)
     * @see #getInt(String)
     */
    int getInt(String key, int defaultValue);

    /**
     * Return the <code>long</code> value associated with the specified key
     * contained in this node.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as <code>long</code>.
     *
     * @return the <code>long</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists,
     *            or the value can not be interpreted as a <code>long</code>.
     *
     * @see #getString(String)
     * @see #getLong(String, long)
     */
    long getLong(String key) throws ConfigurationException;

    /**
     * Return the <code>long</code> value associated with the specified key
     * contained in this node. Returns the specified default
     * if there is no value associated with the key.
     *
     * @param key          the name of the key whose associated value is to
     *                     be returned as <code>long</code>.
     * @param defaultValue the default value to be retuned, if the key does not
     *                     exist in this configuration node.
     *
     * @return the <code>long</code> value of associated <code>key</code>.
     *
     * @see #getString(String)
     * @see #getLong(String)
     */
    long getLong(String key, long defaultValue);

    /**
     * Return the <code>float</code> value associated with the specified key
     * contained in this node.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as <code>float</code>.
     *
     * @return the <code>float</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists,
     *            or the value can not be interpreted as a <code>float</code>.
     *
     * @see #getString(String)
     * @see #getFloat(String, float)
     */
    float getFloat(String key) throws ConfigurationException;

    /**
     * Return the <code>float</code> value associated with the specified key
     * contained in this node.  Returns the specified default value
     * if there is no value associated with the key.
     *
     * @param key          the name of the key whose associated value is to
     *                     be returned as <code>float</code>.
     * @param defaultValue the default value to be retuned, if the key does not
     *                     exist in this configuration node.
     *
     * @return the <code>float</code> value of associated <code>key</code>.
     *
     * @see #getString(String)
     * @see #getFloat(String)
     */
    float getFloat(String key, float defaultValue);

    /**
     * Return the <code>double</code> value associated with the specified key
     * contained in this node.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as <code>double</code>.
     *
     * @return the <code>double</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists,
     *            or the value can not be interpreted as a <code>double</code>.
     *
     * @see #getString(String)
     * @see #getDouble(String, double)
     */
    double getDouble(String key) throws ConfigurationException;

    /**
     * Return the <code>double</code> value associated with the specified key
     * containedin this node. Returns the specified default value
     * if there is no value associated with the key.
     *
     * @param key          the name of the key whose associated value is to
     *                     be returned as <code>double</code>.
     * @param defaultValue the default value to be retuned, if the key does not
     *                     exist in this configuration node, or the associated
     *                     value can not be interpreted as <code>boolean</code>.
     *
     * @return the <code>double</code> value of associated <code>key</code>.
     *
     * @see #getString(String)
     * @see #getDouble(String)
     */
    double getDouble(String key, double defaultValue);

    /**
     * Return the <code>boolean</code> value associated with the specified key
     * contained in this node.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as <code>boolean</code>.
     *
     * @return the <code>boolean</code> value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists,
     *            or the value can not be interpreted as a <code>boolean</code>.
     *
     * @see #getString(String)
     * @see #getBoolean(String, boolean)
     */
    boolean getBoolean(String key) throws ConfigurationException;

    /**
     * Return the <code>boolean</code> value associated with the specified key
     * contained in this node. Valid strings are <code>"true"</code>, which
     * represents <code>true</code>, and <code>"false"</code>, which represents
     * <code>false</code>. Returns the specified default if there is no value
     * associated with the key, or if the associated value is something other
     * than <code>"true"</code> or <code>"false"</code>, ignoring case.
     *
     * @param key          the name of the key whose associated value is to
     *                     be returned as <code>boolean</code>.
     * @param defaultValue the default value to be retuned, if the key does not
     *                     exist in this configuration node, or the associated
     *                     value can not be interpreted as <code>boolean</code>.
     *
     * @return the <code>boolean</code> value of associated <code>key</code>.
     *
     * @see #getString(String)
     * @see #getBoolean(String)
     */
    boolean getBoolean(String key, boolean defaultValue);

    /**
     * Returns the byte array value represented by the string associated
     * with the specified key in this configuration node. Valid strings
     * are <em>Base64</em> encoded binary data, as defined in
     * <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>, Section 6.8,
     * with one minor change: the string must consist solely of characters from
     * the <em>Base64 Alphabet</em>; no newline characters or extraneous
     * characters are permitted.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as a byte array.
     *
     * @return the byte array value of associated <code>key</code>.
     *
     * @exception ConfigurationException if no value with that key exists,
     *            or the value can not be interpreted as a byte array.
     * @see #getString(String)
     * @see #getByteArray(String, byte[])
     */
    byte[] getByteArray(String key) throws ConfigurationException;

    /**
     * Returns the byte array value represented by the string associated
     * with the specified key in this configuration node.
     *
     * @param key the name of the key whose associated value is to be returned
     *            as <code>boolean</code>.
     *
     * @return the byte array value of associated <code>key</code>.
     *
     * @see #getString(String)
     * @see #getByteArray(String)
     */
    byte[] getByteArray(String key, byte [] defaultValue);
}

