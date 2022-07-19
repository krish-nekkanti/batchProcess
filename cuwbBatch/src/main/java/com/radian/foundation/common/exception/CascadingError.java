
package com.radian.foundation.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Class from which all exceptions should inherit.
 * Allows recording of nested exceptions.
 *
 * @author Giedrius Trumpickas
 */
public class CascadingError extends Error implements CascadingThrowable
{
    /**
     * Root cause.
     */
    private Throwable cause;

    /**
     * Construct a new <code>CascadingError</code> instance.
     *
     * @param message The detail message for this exception.
     * @param throwable the root cause of the exception
     */
    public CascadingError(String message, Throwable cause)
    {
        super(message);
        this.cause = cause;
    }

    /**
     * Retrieve root cause of the exception.
     *
     * @return the root cause
     */
    public final Throwable getCause()
    {
        return cause;
    }

    /**
     * Print the exception and the root cause to the standard error stream.
     */
    public final void printStackTrace()
    {
        super.printStackTrace();
        if(cause == null)
        {
            return;
        }
        cause.printStackTrace();
    }

    /**
     * Prints this exception and the root cause to the specified print stream.
     *
     * @param s the print stream to use for output.
     */
    public final void printStackTrace (PrintStream s)
    {
        super.printStackTrace(s);
        if(cause == null)
        {
            return;
        }
        cause.printStackTrace(s);
    }

    /**
     * Prints this exception and the root cause to the specified print writer.
     *
     * @param s the print writer to use for output.
     */
    public final void printStackTrace (PrintWriter s)
    {
        super.printStackTrace(s);
        if(cause == null)
        {
            return;
        }
        cause.printStackTrace(s);
    }

    /**
     * returns the original root exception
     */
    public final Throwable getRoot()
    {
        if(cause != null)
        {
            if(cause instanceof CascadingThrowable)
            {
                return ((CascadingThrowable)cause).getRoot();
            }
            else
            {
                return cause;
            }
        }

        return this;
    }
}
