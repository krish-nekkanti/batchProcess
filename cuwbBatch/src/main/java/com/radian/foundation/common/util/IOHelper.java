package com.radian.foundation.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Utility class for common IO operations.
 *
 * @author Giedrius Trumpickas
 */
public final class IOHelper
{
    /**
     * Default buffer size.
     */ 
    private final static int DEFAULT_BUF_SIZE = 4096;

    /**
     * Reads from input stream and writes it to given output stream.
     *
     * @param buffer a buffer for data transfering
     * @param src a source input stream
     * @exception IOException if io error occurs
     */
    public static void copyStream(byte[] buffer, InputStream src, OutputStream dst) throws IOException
    {               
        while ( true )
        {
            int count = src.read(buffer);           
            if ( count == -1 )
            {
                break;
            }
            dst.write(buffer, 0, count);
        }
    }

    /**
     * Reads from input stream and writes it to given output stream.
     *
     * @param size a buffer size
     * @param src a source input stream
     * @exception IOException if io error occurs
     */
    public static void copyStream(int size, InputStream src, OutputStream dst) throws IOException
    {       
        copyStream(new byte[size], src, dst);        
    }

    /**
     * Reads from input stream and writes it to given output stream.
     *	 
     * @param src a source input stream
     * @exception IOException if io error occurs
     */
    public static void copyStream(InputStream src, OutputStream dst) throws IOException
    {       
        copyStream(DEFAULT_BUF_SIZE, src, dst);
    }

    /**
     * Closes given input stream.
     *
     * @param in an input stream	 
     */
    public static void close(InputStream in)
    {       
        if ( in == null )
        {
            return;
        }
        try
        {
            in.close();
        }
        catch(java.io.IOException e)
        {
        }
    }

    /**
     * Closes given reader. Exceptions are ingored when closing.
     *
     * @param reader a reader
     */
    public static void close(Reader reader)
    {
        if(reader == null)
        {
            return;
        }
        try
        {
            reader.close();
        }
        catch(IOException e)
        {
        }
    }

    /**
     * Closes given writer. Exceptions are ingored when closing.
     *
     * @param writer a writer
     */
    public static void close(Writer writer)
    {
        if(writer == null)
        {
            return;
        }
        try
        {
            writer.close();
        }
        catch(IOException e)
        {
        }
    }

    /**
     * Closes given input stream.
     *
     * @param in an input stream	 
     */
    public static void close(OutputStream out)
    {       
        if ( out == null )
        {
            return;
        }
        try
        {
            out.close();
        }
        catch ( java.io.IOException e )
        {
        }
    }           
}