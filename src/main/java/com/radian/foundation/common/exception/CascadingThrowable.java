
package com.radian.foundation.common.exception;

/**
 * Interface which all cascadign throwables should implement.
 * Allows recording of nested exceptions.
 */
public interface CascadingThrowable
{
    /**
     * Retrieve cause of the exception.
     *
     * @return the root cause
     */
    Throwable getCause();

    /**
     * Retrieve root cause of the exception.
     *
     * @return the root cause
     */
    Throwable getRoot();

}

