package com.radian.cuwbilling.common.bs.util.DTOtoXML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * XMLDateHandler
 * 
 * @author KMadireddy XMLDateHandler handles date parsing and formatting. Set
 *         system property to control default date parsing.
 * @see java.text.SimpleDateFormat
 */
class XMLDateHandler
{
    final static String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss z";

    static DateFormat determineDateFormat()
    {
        // default format includes all necessary fields
        return new SimpleDateFormat(DATE_FORMAT);
        // Add system property (using if else..)
        // and return a DateFormat object to control default date parsing for
        // future use
    }
}
