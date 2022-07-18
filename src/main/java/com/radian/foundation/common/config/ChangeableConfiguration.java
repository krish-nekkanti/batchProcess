/*
 * Created on Apr 9, 2003
 *
 */
package com.radian.foundation.common.config;

/**
 * <code>ChangeableConfiguration</code> provides interface for configuration
 * modifications.
 * 
 * @author Rolandas Valteris
 */
public interface ChangeableConfiguration extends Configuration
{
    /**
     * Adds a child <code>Configuration</code> to this configuration element.
     *
     * @param configuration new child <code>Configuration</code>.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    void addChild(ChangeableConfiguration configuration);

    /**
     * Removes a child <code>Configuration</code> to this configuration element.
     *
     * @param configuration child <code>Configuration</code> to be removed.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    void removeChild(ChangeableConfiguration configuration);

    /**
     * Adds an attribute to this configuration element, returning its old
     * value or <code>null</code> if there was no value before.
     *
     * @param key   attribute key name.
     * @param value attribute value.
     *
     * @return old attribute value.
     *
     * @throws IllegalStateException if object is marked as <em>read-only</em>.
     */
    String setAttribute(String key, String value);

    /**
     * Marks this <code>Configuration</code> object <em>read-only</em> to prevent
     * further modifications. Any attempt to modify the object afterwards will
     * result in <code>IllegalStateException</code> exceptions.
     */
    void markReadOnly();
}
