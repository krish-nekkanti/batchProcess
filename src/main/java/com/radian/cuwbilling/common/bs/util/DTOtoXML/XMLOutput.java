package com.radian.cuwbilling.common.bs.util.DTOtoXML;

/**
 * XMLOutput
 * 
 * @author KMadireddy An interface used by DTOtoXML and DTOtoXMLHelper classes
 *         to write XML as output stream. This interface is implemented by
 *         DTOtoXML public class.
 */

interface XMLOutput
{
    public void writeString(String str) throws java.io.IOException;
}
