package com.radian.cuwbilling.common.bo.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * TODO add support for internalization
 */
public class DateDTO extends GregorianCalendar implements java.io.Serializable
{
    /**
     * Converts <code>DateDTO</code> to <code>Date</code>.
     * 
     * @return axiom date as date. <code>null</code> if parameter ad is
     *         <code>null</code>.
     */
    public static Date toDate(DateDTO ad)
    {
        if (ad != null)
            return ad.getTime();
        else
            return null;
    }

    /**
     * Converts <code>Date</code> to <code>DateDTO</code>.
     * 
     * @return date as axiom date. <code>null</code> if parameter d is
     *         <code>null</code>.
     */
    public static DateDTO dateToDateDTO(Date d)
    {
        if (d != null)
            return new DateDTO(d);
        else
            return null;
    }

    public static boolean sameDay(Date one, Date two)
    {
        String strDateOne = formatter.format(one);
        String strDateTwo = formatter.format(two);

        boolean isEqual = strDateOne.equals(strDateTwo);

        return isEqual;
    }

    public static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public static SimpleDateFormat fullFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");

    // TODO vb should be removed when all UI conform to MM/dd/yyyy
    public static SimpleDateFormat formatterAlt = new SimpleDateFormat("MM-dd-yyyy");

    private Date d = null;

    public DateDTO(Date d)
    {
        setTimeInMillis(d.getTime());
        setLenient(false);
        ;
    }

    public DateDTO()
    {
        this(new Date());
    }

    public DateDTO(String d) throws ParseException
    {
        try
        {
            setTimeInMillis(formatter.parse(d).getTime());
            setLenient(false);
        } catch (ParseException pe)
        {
            // TODO vb should be removed when all UI conform to MM/dd/yyyy
            setTimeInMillis(formatterAlt.parse(d).getTime());
            setLenient(false);
        }
    }

    public DateDTO(String d, SimpleDateFormat f) throws ParseException
    {
        try
        {
            setTimeInMillis(f.parse(d).getTime());
        } catch (ParseException pe)
        {
            setTimeInMillis(formatter.parse(d).getTime());
        }
        setLenient(false);
    }

    public String getDisplayFormat()
    {
        return formatter.format(getTime());
    }

    public String getDisplayFullFormat()
    {
        return fullFormatter.format(getTime());
    }

    public String toString()
    {
        return getDisplayFormat();
    }

    public static void main(String[] args)
    {
        DateDTO date = new DateDTO(new Date());

        System.out.println(date);
    }
}
