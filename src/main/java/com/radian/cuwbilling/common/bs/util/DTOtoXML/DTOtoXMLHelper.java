package com.radian.cuwbilling.common.bs.util.DTOtoXML;

import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * DTOtoXMLHelper
 * 
 * @author KMadireddy Performs the actual task of writing a DTO as XML.
 */
class DTOtoXMLHelper
{
    public static final int WRITE_AS_ATTRIBUTE = 1;

    public static final int WRITE_AS_ELEMENT = 2;

    public static final int WRITE_AS_INDEXED_ELEMENT = 3;

    public static boolean includeEmptyFields = false;

    public static boolean includeFieldTypes = false;

    /**
     * 
     * The date formatter used to write out dates
     */
    protected DateFormat dateFormat = XMLDateHandler.determineDateFormat();

    protected boolean dateFormatIsDefault = true;

    /**
     * The dummy parameter block for invoking the getters for the bean
     */
    protected static Object[] getParams = new Object[0];

    protected Hashtable nameTranslation;

    protected Hashtable attrTranslation;

    protected Hashtable childTranslation;

    protected static Hashtable beanCache = new Hashtable();

    protected XMLOutput outputStream;

    protected boolean writeAttributes;

    protected DTOtoXMLHelper()
    {
    }

    /**
     * Creates a DTOtoXMLHelper
     * 
     * @param theStream,
     *            The stream where the XML will be written
     */
    public DTOtoXMLHelper(XMLOutput theStream, boolean writeAttributesFlag)
    {
        outputStream = theStream;

        writeAttributes = writeAttributesFlag;

        nameTranslation = null;
    }

    synchronized void setDateFormat(DateFormat fmt)
    {
        dateFormat = fmt;
        dateFormatIsDefault = false;
    }

    /**
     * Writes out an object as XML
     * 
     * @param rootName
     *            The tag name for this object (if this object is a property of
     *            a DTO(bean), the tag name will be the property name)
     * @param ob
     *            The object to write out
     * @throws IOException
     *             If there is an error while writing the object
     */
    public void writeObject(String rootName, Object ob, boolean emptyFields, boolean fieldTypes) throws IOException
    {
        includeEmptyFields = emptyFields;
        includeFieldTypes = fieldTypes;
        writeObject(rootName, ob);
    }

    /**
     * Writes out an object as XML
     * 
     * @param rootName
     *            The tag name for this object (if this object is a property of
     *            a DTO(bean), the tag name will be the property name)
     * @param ob
     *            The object to write out
     * @throws IOException
     *             If there is an error while writing the object
     */
    public void writeObject(String rootName, Object ob, boolean emptyFields) throws IOException
    {
        includeEmptyFields = emptyFields;
        writeObject(rootName, ob);
    }

    /**
     * Writes out an object as XML
     * 
     * @param rootName
     *            The tag name for this object (if this object is a property of
     *            a DTO(bean), the tag name will be the property name)
     * @param ob
     *            The object to write out
     * @throws IOException
     *             If there is an error while writing the object
     */
    public void writeObject(String rootName, Object ob) throws IOException
    {

        String tagName = getTranslatedEntityName(rootName);

        if (tagName == null)
            return;

        // Write out the opening tag
        outputStream.writeString("<");
        outputStream.writeString(tagName);

        if (includeFieldTypes)
        {
            outputStream.writeString(" ");
            outputStream.writeString("fieldType");
            outputStream.writeString("=\"");
            outputStream.writeString(formatRootElement(ob.getClass().getName()));
            outputStream.writeString("\"");
        }

        writeObjectFields(tagName, ob, WRITE_AS_ATTRIBUTE);

        outputStream.writeString(">");

        // Write out the properties of the object
        writeObjectFields(tagName, ob, WRITE_AS_ELEMENT);

        // Write out the closing tag
        outputStream.writeString("</");
        outputStream.writeString(tagName);
        outputStream.writeString(">");
    }

    /**
     * Writes out the properties of a DTO as XML
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param ob
     *            The DTO whose properties will be written
     * @param writeType
     *            The kind of fields being written (attribute or element)
     * @exception IOException
     *                If there is an error writing the object
     */
    public void writeObjectFields(String xmlTagName, Object ob, int writeType) throws IOException
    {
        // If the object is null, don't bother writing anything
        if (ob == null)
        {
            if (includeEmptyFields == false)
            {
                return;
            } else
            {
                String className = ob.getClass().getName();
                className = formatRootElement(className);
                outputStream.writeString("<");
                outputStream.writeString(className);
                outputStream.writeString("/>");
                return;
            }
        }
        /*
         * System.out.println(" name of ob = " + ob.getClass().getName()); if(
         * ob instanceof PersonDTO) { System.out.println(" Object is a PERSON
         * DTO"); }
         */

        // Check and process the object if it is a collection
        if ((WRITE_AS_ELEMENT == writeType) && (ob instanceof Collection))
        {
            // System.out.println(" Object is a Collection DTO");

            Iterator x = ((Collection) ob).iterator();
            while (x.hasNext())
            {
                Object obj = x.next();

                String className = obj.getClass().getName();
                className = formatRootElement(className);
                writeObject(className, obj);
            }
        } else
        {
            try
            {
                BeanInfo info = (BeanInfo) beanCache.get(ob.getClass());

                if (info == null)
                {
                    // System.out.println( " INFO is NULL ");
                    // Get the bean info for the DTO, but don't get the
                    // attributes from object
                    info = Introspector.getBeanInfo(ob.getClass(), Object.class);
                    beanCache.put(ob.getClass(), info);
                }

                // Get the list of properties for this DTO
                PropertyDescriptor[] props = info.getPropertyDescriptors();

                for (int i = 0; i < props.length; i++)
                {

                    // If the property is indexed (if it represents an array of
                    // props),
                    // use a different routine to write it out
                    if (props[i] instanceof IndexedPropertyDescriptor)
                    {
                        // System.out.println( "Prop[" + i + "] = " + props[i]);
                        if (writeType == WRITE_AS_ELEMENT)
                        {
                            writeIndexedProperty(xmlTagName, ob, (IndexedPropertyDescriptor) props[i]);
                        }
                    } else
                    {
                        // System.out.println( "else Prop[" + i + "] = " +
                        // props[i]);
                        writeProperty(xmlTagName, ob, props[i], writeType);
                    }
                }
            } catch (IntrospectionException exc)
            {
                throw new IOException("Got introspection exception " + exc.toString());
            }
        } // else if CollectionTest ENDs
    }

    /**
     * Writes out a non-indexed property
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param ob
     *            The object containing the property
     * @param prop
     *            The property descriptor of the property
     * @param writeType
     *            Should we try to write this property as an attribute? If not,
     *            don't write the object if writeAttributes is true and this
     *            object can be written as an attribute
     * @throws IOException
     *             If there is an error writing out the property
     */
    public void writeProperty(String xmlTagName, Object ob, PropertyDescriptor prop, int writeType) throws IOException
    {
        try
        {

            // If this property is a result of reflection mistakenly reading a
            // getter and assuming
            // is has an equivalent property value, ignore it
            if (((prop.getName().equals("amountAsString")) && (ob.getClass().getName().endsWith("MoneyDTO")))
                    || ((prop.getName().equals("amountAsStringWithCommas")) && (ob.getClass().getName().endsWith("MoneyDTO"))))
            {
                return;
            }

            // Get the method used to read the property
            Method reader = prop.getReadMethod();

            // If this property has no reader, don't bother reading it
            if (reader == null)
                return;

            // Get the property value
            Object propValue = reader.invoke(ob, getParams);
            // Customize the output
            if ((((reader.getName().equals("getAmount"))) && (ob.getClass().getName().endsWith("MoneyDTO")))
                    || (((reader.getName().equals("getAbbreviation"))) && (ob.getClass().getName().endsWith("USState")))
                    || (((reader.getName().equals("getDisplayFormat"))) && (ob.getClass().getName().endsWith("DateDTO"))))
            {
                if (writeType == WRITE_AS_ELEMENT)
                {
                    String mv = propValue == null ? "" : propValue.toString();
                    outputStream.writeString(mv);
                    // System.out.println ("########### " +
                    // ob.getClass().getName());
                }
            } else if ((((reader.getName().equals("getID")) || (reader.getName().equals("getDisplayValue")) || (reader.getName().equals("getValue"))) && (ob
                    .getClass().getName().endsWith("USState")))
                    || ((reader.getName().equals("getStateRaw")) && (ob.getClass().getName().endsWith("AddressDTO")))
                    || (((reader.getName().equals("getNickname")) || (reader.getName().equals("getDisplayName"))) && (ob.getClass().getName()
                            .endsWith("PersonDTO")))
                    || (((reader.getName().equals("getFirstDayOfWeek")) || (reader.getName().equals("isLenient"))
                            || (reader.getName().equals("getMinimalDaysInFirstWeek")) || (reader.getName().equals("getTimeZone"))
                            || (reader.getName().equals("getTime")) || (reader.getName().equals("getGregorianChange"))) && (ob.getClass().getName()
                            .endsWith("DateDTO"))))
            {
                // System.out.println ("$$$$$$$$$$$$$$ " +
                // ob.getClass().getName());
                // do nothing
            }

            /*
             * else if ( reader.getName().equals("isLenient") ) {
             * System.out.println ("========" + ob.getClass().getName()); }
             */
            else
            {
                // Write the property value
                writePropertyValue(xmlTagName, propValue, prop, writeType);
            }
        } catch (InvocationTargetException exc)
        {
            throw new IOException("Unable to read property " + prop.getName() + ": " + exc.toString());
        } catch (IllegalAccessException exc)
        {
            throw new IOException("Unable to read property " + prop.getName() + ": " + exc.toString());
        }
    }

    /**
     * Writes out an indexed property (an array of properties)
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param ob
     *            The object containing the property
     * @param prop
     *            The descriptor for the property as an attribute? If not, don't
     *            write the object if writeAttributes is true and this object
     *            can be written as an attribute
     * @throws IOException
     *             If there is an error writing the properties
     */
    public void writeIndexedProperty(String xmlTagName, Object ob, IndexedPropertyDescriptor prop) throws IOException
    {
        try
        {
            // Get the method used to read the property values
            Method reader = prop.getReadMethod();

            // If this property has no reader, don't bother reading it
            if (reader == null)
                return;

            // Get the entire array of property values
            Object[] propValues = (Object[]) reader.invoke(ob, getParams);

            // Write out each property value as its own element
            writePropertyValue(xmlTagName, propValues, prop, WRITE_AS_INDEXED_ELEMENT);
        } catch (InvocationTargetException exc)
        {
            throw new IOException("Unable to read property " + prop.getName() + ": " + exc.toString());
        } catch (IllegalAccessException exc)
        {
            throw new IOException("Unable to read property " + prop.getName() + ": " + exc.toString());
        }
    }

    /**
     * Writes out a property value based on the property type
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param propValue
     *            The value to write
     * @param prop
     *            The property descriptor of the property
     * @param writeType
     *            Should we try to write this property as an attribute? If not,
     *            don't write the object if writeAttributes is true and this
     *            object can be written as an attribute
     * @throws IOException
     *             If there is an error while writing the property
     */
    public void writePropertyValue(String xmlTagName, Object propValue, PropertyDescriptor prop, int writeType) throws IOException
    {
        // See what kind of object it is
        Class type = prop.getPropertyType();

        // If the object is an array, find out what the base type is
        if (type.isArray() && (propValue != null))
        {
            type = type.getComponentType();

            int len = Array.getLength(propValue);

            for (int i = 0; i < len; i++)
            {
                Object realValue = Array.get(propValue, i);

                writePropertyValueByType(xmlTagName, realValue, prop, writeType, type);
            }
        } else
        {
            writePropertyValueByType(xmlTagName, propValue, prop, writeType, type);
        }
    }

    public void writePropertyValueByType(String xmlTagName, Object propValue, PropertyDescriptor prop, int writeType, Class type) throws IOException
    {
        // If the object is a native type or the object wrapper for a native
        // type,
        // or if it is a string, use the basic output routine
        if (type.equals(Integer.TYPE) || type.equals(Long.TYPE) || type.equals(Short.TYPE) || type.equals(Byte.TYPE) || type.equals(Boolean.TYPE)
                || type.equals(Float.TYPE) || type.equals(Double.TYPE) || Integer.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)
                || Short.class.isAssignableFrom(type) || Byte.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type)
                || Float.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type))
        {
            if (propValue != null)
            {
                if (includeFieldTypes)
                {
                    writeBasicType(xmlTagName, prop.getName(), propValue, writeType, type.getName());
                } else
                {
                    writeBasicType(xmlTagName, prop.getName(), propValue, writeType);
                }
            }
        } else if (String.class.isAssignableFrom(type))
        {
            if (propValue != null)
            {
                writeString(xmlTagName, prop.getName(), propValue, writeType);
            }
        } else if (java.util.Date.class.isAssignableFrom(type))
        {
            if (propValue != null)
            {
                // otherwise if it's a date, use the date formatter
                writeDate(xmlTagName, prop.getName(), propValue, writeType);
            }
        } else
        {
            // if none of the above, assume it's a DTO and write out a nested
            // object using the property name as the tag for the nested object
            if ((writeType == WRITE_AS_ELEMENT) || (writeType == WRITE_AS_INDEXED_ELEMENT))
            {
                if (propValue != null)
                {
                    writeObject(prop.getName(), propValue);
                }
            }
        }
    }

    /**
     * Writes out a basic type including the opening and closing tags.
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param propName
     *            The name of the property to write
     * @param propValue
     *            The value of the property
     * @throws IOException
     *             If there is an error while writing the property
     */
    public void writeBasicType(String xmlTagName, String propName, Object propValue, int writeType) throws IOException
    {
        // Get the translated name of the property
        String tagName = getTranslatedChildName(xmlTagName, propName);

        // If the property doesn't exist, don't write anything
        if (tagName == null)
            return;

        if ((writeAttributes || writeAsAttribute(xmlTagName, tagName)) && (writeType != WRITE_AS_INDEXED_ELEMENT))
        {
            if (writeType == WRITE_AS_ATTRIBUTE)
            {
                outputStream.writeString(" ");
                outputStream.writeString(tagName);
                outputStream.writeString("=\"");
                outputStream.writeString(propValue.toString());
                outputStream.writeString("\"");
            } else
            {
                // Don't write basic types as elements if they
                // should have been written as attributes
                return;
            }
        } else if (writeType != WRITE_AS_ATTRIBUTE)
        {
            outputStream.writeString("<");
            outputStream.writeString(tagName);
            outputStream.writeString(">");

            outputStream.writeString(propValue.toString());

            outputStream.writeString("</");
            outputStream.writeString(tagName);
            outputStream.writeString(">");
        }
    }

    /**
     * Writes out a basic type including the opening and closing tags.
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param propName
     *            The name of the property to write
     * @param propValue
     *            The value of the property
     * @param writeType
     *            The type of output
     * @param fieldType
     *            The type of the field (eg. Integer, Double etc)
     * @throws IOException
     *             If there is an error while writing the property
     */
    public void writeBasicType(String xmlTagName, String propName, Object propValue, int writeType, String fieldType) throws IOException
    {
        // Get the translated name of the property
        String tagName = getTranslatedChildName(xmlTagName, propName);

        // If the property doesn't exist, don't write anything
        if (tagName == null)
            return;

        if ((writeAttributes || writeAsAttribute(xmlTagName, tagName)) && (writeType != WRITE_AS_INDEXED_ELEMENT))
        {
            if (writeType == WRITE_AS_ATTRIBUTE)
            {
                outputStream.writeString(" ");
                outputStream.writeString(tagName);
                outputStream.writeString("=\"");
                outputStream.writeString(propValue.toString());
                outputStream.writeString("\"");

                // Add Field Type as an attribute
                outputStream.writeString(" ");
                outputStream.writeString("fieldType");
                outputStream.writeString("=\"");
                outputStream.writeString(formatRootElement(fieldType));
                outputStream.writeString("\"");
            } else
            {
                // Don't write basic types as elements if they
                // should have been written as attributes
                return;
            }
        } else if (writeType != WRITE_AS_ATTRIBUTE)
        {
            outputStream.writeString("<");
            outputStream.writeString(tagName);

            // Add Field Type as an attribute
            outputStream.writeString(" ");
            outputStream.writeString("fieldType");
            outputStream.writeString("=\"");
            outputStream.writeString(formatRootElement(fieldType));
            outputStream.writeString("\"");

            outputStream.writeString(">");

            outputStream.writeString(propValue.toString());

            outputStream.writeString("</");
            outputStream.writeString(tagName);
            outputStream.writeString(">");
        }
    }

    /**
     * Writes out a string, using CDATA if the string contains < or &
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param propName
     *            The name of the property to write
     * @param propValue
     *            The value of the property
     * @param outputStream
     *            The output stream or writer where the value is to be written
     * @throws IOException
     *             If there is an error while writing the property
     */
    public void writeString(String xmlTagName, String propName, Object propValue, int writeType) throws IOException
    {
        // Get the translated name of the property
        String tagName = getTranslatedChildName(xmlTagName, propName);

        // If the property doesn't exist, don't write anything
        if (tagName == null)
            return;

        if ((writeAttributes || writeAsAttribute(xmlTagName, tagName)) && (writeType != WRITE_AS_INDEXED_ELEMENT))
        {
            if (writeType == WRITE_AS_ATTRIBUTE)
            {
                outputStream.writeString(" ");
                outputStream.writeString(tagName);
                outputStream.writeString("=\"");
                outputStream.writeString((String) propValue);
                outputStream.writeString("\"");

                // Add Field Type as an attribute
                if (includeFieldTypes)
                {
                    outputStream.writeString(" ");
                    outputStream.writeString("fieldType");
                    outputStream.writeString("=\"");
                    outputStream.writeString("String");
                    outputStream.writeString("\"");
                }
            } else
            {
                // Don't write strings as elements if they
                // should have been written as attributes
                return;
            }
        } else if (writeType != WRITE_AS_ATTRIBUTE)
        {
            outputStream.writeString("<");
            outputStream.writeString(tagName);

            // Add Field Type as an attribute
            if (includeFieldTypes)
            {
                outputStream.writeString(" ");
                outputStream.writeString("fieldType");
                outputStream.writeString("=\"");
                outputStream.writeString("String");
                outputStream.writeString("\"");
            }
            outputStream.writeString(">");

            String propString = (String) propValue;

            if ((propString.indexOf('<') >= 0) || (propString.indexOf('>') >= 0) || (propString.indexOf('&') >= 0))
            {
                outputStream.writeString("<![CDATA[");
                outputStream.writeString(propString);
                outputStream.writeString("]]>");
            } else
            {
                outputStream.writeString(propString);
            }

            outputStream.writeString("</");
            outputStream.writeString(tagName);
            outputStream.writeString(">");
        }
    }

    /**
     * Writes out a date including the opening and closing tags
     * 
     * @param xmlTagName
     *            The XML tag name of the DTO being written
     * @param propName
     *            The name of the property to write
     * @param propValue
     *            The value of the property
     * @param outputStream
     *            The output stream or writer where the value is to be written
     * @throws IOException
     *             If there is an error while writing the property
     */
    public void writeDate(String xmlTagName, String propName, Object propValue, int writeType) throws IOException
    {
        // Get the translated name of the property
        String tagName = getTranslatedChildName(xmlTagName, propName);

        // If the property doesn't exist, don't write anything (this only
        // happens when there is a DTD and there is no match in the DTD for
        // this property
        if (tagName == null)
            return;

        if ((writeAttributes || writeAsAttribute(xmlTagName, tagName)) && (writeType != WRITE_AS_INDEXED_ELEMENT))
        {
            if (writeType == WRITE_AS_ATTRIBUTE)
            {
                outputStream.writeString(" ");
                outputStream.writeString(tagName);
                outputStream.writeString("=\"");

                outputStream.writeString(dateFormat.format((java.util.Date) propValue));
                outputStream.writeString("\"");

                // Add Field Type as an attribute
                if (includeFieldTypes)
                {
                    outputStream.writeString(" ");
                    outputStream.writeString("fieldType");
                    outputStream.writeString("=\"");
                    outputStream.writeString("Date");
                    outputStream.writeString("\"");
                }
            } else
            {
                // Don't write date values as elements
                // if they should have been written as an attribute
            }
        } else if (writeType != WRITE_AS_ATTRIBUTE)
        {
            outputStream.writeString("<");
            outputStream.writeString(tagName);
            if (!dateFormatIsDefault)
            {
                try
                {
                    SimpleDateFormat fmt = (SimpleDateFormat) dateFormat;
                    outputStream.writeString(" format=\"");
                    outputStream.writeString(fmt.toPattern());
                    outputStream.writeString("\"");
                } catch (ClassCastException e)
                {
                    // if it's not a SimpleDateFormat, just don't
                    // output the format attribute
                }
            }

            // Add Field Type as an attribute
            if (includeFieldTypes)
            {
                outputStream.writeString(" ");
                outputStream.writeString("fieldType");
                outputStream.writeString("=\"");
                outputStream.writeString("Date");
                outputStream.writeString("\"");
            }

            outputStream.writeString(">");

            outputStream.writeString(dateFormat.format((java.util.Date) propValue));

            outputStream.writeString("</");
            outputStream.writeString(tagName);
            outputStream.writeString(">");
        }
    }

    /**
     * Converts a name to lower case and removes any '-' or '_' characters
     * 
     * @param name
     *            The name to strip
     * @return The stripped version of the name
     */
    public String stripName(String name)
    {
        // Create a buffer to hold the stripped name
        StringBuffer stripped = new StringBuffer();

        int len = name.length();

        // Loop through all the characters in the name
        for (int i = 0; i < len; i++)
        {
            // Convert each character to lower case
            char ch = Character.toLowerCase(name.charAt(i));

            // Skip '-' and '_'
            if ((ch == '-') || (ch == '_') || (ch == '.') || (ch == ':'))
                continue;

            stripped.append(ch);
        }
        return stripped.toString();
    }

    /**
     * Finds the correct format of a name
     * 
     * @param The
     *            name to translate
     * @return The translated name or null if no translation exists
     */
    public String getTranslatedEntityName(String name)
    {
        if (nameTranslation == null)
            return name;

        String trans = (String) nameTranslation.get(stripName(name));

        return trans;
    }

    public String getTranslatedChildName(String elementName, String childName)
    {
        if (nameTranslation == null)
            return childName;

        String stripped = stripName(elementName);

        Hashtable childTrans = (Hashtable) childTranslation.get(stripped);

        Hashtable attTrans = (Hashtable) attrTranslation.get(stripped);

        if ((childTrans == null) && (attTrans == null))
        {
            return getTranslatedEntityName(childName);
        }

        String childStripped = stripName(childName);

        if (attTrans != null)
        {
            String trans = (String) attTrans.get(childStripped);
            if (trans != null)
                return trans;
        }

        if (childTrans != null)
        {
            String trans = (String) childTrans.get(childStripped);
            if (trans != null)
                return trans;
        }

        return null;
    }

    /**
     * Returns true if a tag name for a particular object should be written as
     * an attribute
     * 
     * @param elementName
     *            The element whose tag is being written
     * @param attributeName
     *            The potential attribute name
     * @return True if the tag should be written as an attribute
     */
    public boolean writeAsAttribute(String elementName, String attributeName)
    {
        if (nameTranslation == null)
            return false;

        Hashtable attrTrans = (Hashtable) attrTranslation.get(stripName(elementName));

        if (attrTrans == null)
            return false;

        if (attrTrans.get(stripName(attributeName)) != null)
        {
            return true;
        }
        return false;
    }

    public static String formatRootElement(String fullPath)
    {
        StringTokenizer tokens;
        String finalRootName = fullPath;

        tokens = new StringTokenizer(fullPath, ".");
        while (tokens.hasMoreTokens())
        {
            finalRootName = tokens.nextToken();
        }

        tokens = new StringTokenizer(finalRootName, "$");
        while (tokens.hasMoreTokens())
        {
            finalRootName = tokens.nextToken();
        }

        StringBuffer name = new StringBuffer(finalRootName);

        if (name.substring(name.length() - 3, name.length() - 0).equals("DTO"))
        {
            name.replace(name.length() - 3, name.length() - 0, "");
        }
        finalRootName = name.toString();
        return finalRootName;
    }
}
