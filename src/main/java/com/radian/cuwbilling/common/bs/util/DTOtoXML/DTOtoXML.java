package com.radian.cuwbilling.common.bs.util.DTOtoXML;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;

/**
 * DTOtoXML
 * 
 * @author KMadireddy An output stream filter that writes out a DTO as an XML
 *         document. The stream can write out basic Java types, their object
 *         equivalents, Collections, strings, dates and other DTOs. The XML tag
 *         names are the same as the DTO(bean) properties.
 */

public class DTOtoXML extends FilterOutputStream implements XMLOutput
{
    public static final String DEFAULT_ENCODING = "ISO-8859-1";

    protected DTOtoXMLHelper output;

    protected String encoding;

    /**
     * Create a new output stream around an existing stream
     * 
     * @param baseOutputStream
     *            The underlying output stream
     */
    public DTOtoXML(OutputStream baseOutputStream)
    {
        this(baseOutputStream, false, DEFAULT_ENCODING);
    }

    /**
     * Create a new output stream around an existing stream
     * 
     * @param baseOutputStream
     *            The underlying output stream
     * @param encoding
     *            The XML encoding for the output
     */
    public DTOtoXML(OutputStream baseOutputStream, String anEncoding)
    {
        this(baseOutputStream, false, anEncoding);
    }

    /**
     * Create a new output stream around an existing stream
     * 
     * @param baseOutputStream
     *            The underlying output stream
     * @param writeAttributes
     *            Indicates whether we should write simple properties as
     *            attributes
     */
    public DTOtoXML(OutputStream baseOutputStream, boolean writeAttributes)
    {
        this(baseOutputStream, writeAttributes, DEFAULT_ENCODING);
    }

    /**
     * Create a new output stream around an existing stream
     * 
     * @param baseOutputStream
     *            The underlying output stream
     * @param writeAttributes
     *            Indicates whether we should write simple properties as
     *            attributes
     * @param encoding
     *            The XML encoding for the output
     */
    public DTOtoXML(OutputStream baseOutputStream, boolean writeAttributes, String anEncoding)
    {
        super(baseOutputStream);
        encoding = anEncoding;
        output = new DTOtoXMLHelper(this, writeAttributes);
    }

    /**
     * Allow the default date format to be overridden by the caller.
     * 
     * @param fmt
     *            the date format to use when outputting dates
     */
    public void setDateFormat(DateFormat fmt)
    {
        output.setDateFormat(fmt);
    }

    /**
     * Writes a DTO as XML, using myDTO as the tag name for the document root.
     * Other tag names will come from the names of the DTO (bean) attributes.
     * 
     * @param dtoName
     *            The name of the document root
     * @param myDTO
     *            The DTO to convert to XML
     * @throws IOException
     *             If there is an error writing the object
     */
    public void getXML(String dtoName, Object myDTO) throws IOException
    {
        /*
         * // Write out the XML header writeString("<?xml version=\"1.0\"");
         * 
         * if (encoding != null) { writeString(" encoding=\""+encoding+"\""); }
         * writeString("?>");
         */
        // Write out the DTO as XML
        output.writeObject(dtoName, myDTO);
    }

    /**
     * Writes a DTO as XML, using myDTO as the tag name for the document root.
     * Other tag names will come from the names of the DTO (bean) attributes.
     * 
     * @param dtoName
     *            The name of the document root
     * @param myDTO
     *            The DTO to convert to XML
     * @param includeEmptyFields
     *            true if you want null DTO values to be converted into empty
     *            tags; false if you want null DTO values to be excluded
     * @throws IOException
     *             If there is an error writing the object
     */
    public void getXML(String dtoName, Object myDTO, boolean includeEmptyFields) throws IOException
    {
        // Write out the XML header
        writeString("<?xml version=\"1.0\"");

        if (encoding != null)
        {
            writeString(" encoding=\"" + encoding + "\"");
        }
        writeString("?>");

        // Write out the DTO as XML
        output.writeObject(dtoName, myDTO);
    }

    /**
     * Writes a DTO as XML, using myDTO as the tag name for the document root.
     * Other tag names will come from the names of the DTO (bean) attributes.
     * 
     * @param dtoName
     *            The name of the document root
     * @param myDTO
     *            The DTO to convert to XML
     * @param includeFieldTypes
     *            true if you want field types needs to be output as attribute
     * @throws IOException
     *             If there is an error writing the object
     */
    public void getXML(String dtoName, Object myDTO, boolean includeEmptyFields, boolean includeFieldTypes) throws IOException
    {
        // Write out the XML header
        writeString("<?xml version=\"1.0\"");

        if (encoding != null)
        {
            writeString(" encoding=\"" + encoding + "\"");
        }
        writeString("?>");

        // Write out the DTO as XML
        output.writeObject(dtoName, myDTO, includeEmptyFields, includeFieldTypes);
    }

    /**
     * Write a string to the output stream. This method is used by the output
     * utility to write a string to either an output stream or a writer.
     * 
     * @param str
     *            The string to write
     */
    public void writeString(String str) throws IOException
    {
        write(str.getBytes());
    }
}
