package com.radian.webserviceclient;

import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.radian.cuwbilling.common.bo.dto.DateDTO;

/**
 * Base class to handle common elements for CMD web service responses
 * This class is extended to handle specifics for each web service response
 * Note using this Sax parser approach because there is no schema for CMD responses (i.e., no JAXB binding possible)
 * There are no attributes used in CMDnline-style responses--all content in elements
 */
public abstract class WSCMDClientSaxHandler extends DefaultHandler
{
	private static final Object WSCMD_RETURNSTATUS = "call_returnstatus";
	private static final Object WSCMD_ERRCODE = "errorcode";
	private static final Object WSCMD_ERRDESC = "errordesc";
	private static final Object WSCMD_ERRSRC = "errorsrc";
	
	private StringBuffer elementContent;
	
	private boolean inReturnStatus = false;

	private WSCMDStatus status;
	
	/**
	 * @param elementName identifes element
	 */
	public abstract void startElementBinding(String elementName);
	
	/**
	 * @param elementName identifies element
	 * @param theElement is content between start and end element tags
	 */
	public abstract void bindElementContent(String elementName, String theElement);

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public void startDocument()
	{
		//init to handle elements in response doc
		elementContent = new StringBuffer();
		status = new WSCMDStatus();
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	public void endDocument()
	{		
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String namespaceURI, String localName, String rawName, Attributes attributes) throws SAXException
	{
		//reset to get content for next element
		elementContent.setLength(0);
		if (rawName.equals(WSCMD_RETURNSTATUS))
		{	//ind start of return status element
			inReturnStatus = true;
		}
		startElementBinding(rawName);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String namespaceURI, String localName, String rawName) throws SAXException
	{
		//move element content to java obj
		if (inReturnStatus)
		{	//handle return status common to all CMDnline web service responses
			setReturnStatus(rawName, elementContent.toString().trim());
		}
		else
		{	//handle element in subclass
			bindElementContent(rawName, elementContent.toString().trim());
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] buffer, int start, int length) throws SAXException
	{
		//get all content between start and end element tags
		elementContent.append(buffer, start, length);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
	 */
	public void warning(SAXParseException exception) throws SAXException
	{
		throw (exception);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	public void error(SAXParseException exception) throws SAXException
	{
		throw (exception);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException exception) throws SAXException
	{
		throw (exception);
	}

	/**
	 * @return the status
	 */
	public WSCMDStatus getStatus()
	{
		return status;
	}
	
	private void setReturnStatus(String elementName, String theContent)
	{
		if (elementName.equals(this.WSCMD_ERRCODE))
		{
			status.setErrorcode(theContent);
		}
		else if (elementName.equals(this.WSCMD_ERRDESC))
		{
			status.setErrordesc(theContent);
		}
		else if (elementName.equals(this.WSCMD_ERRSRC))
		{
			status.setErrorsrc(theContent);
		}
		else if (elementName.equals(WSCMD_RETURNSTATUS))
		{	//ind end of return status element
			inReturnStatus = false;
		}
		//else ignore element, should not happen
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	protected Boolean getBooleanFromString(String value, String trueValue)
	{
		if ("Y".equalsIgnoreCase(value) ||
			"Yes".equalsIgnoreCase(value) ||
			trueValue.equalsIgnoreCase(value))
		{
			return Boolean.TRUE;
		} else
		{
			return Boolean.FALSE;
		}
	}
	protected DateDTO getFilterDate( String dateString )
	{
		try {
			if (dateString == null)
			{
				return null;
			} else {
				DateDTO date = new DateDTO(dateString.substring(0,10), new SimpleDateFormat("yyyy-MM-dd"));
				if(date.before(new DateDTO("1900-01-01"))) {
					return null;
				} else {
					return date;
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	protected String getFormattedPhoneNumber(String phoneNumber)
	{
		if( phoneNumber == null || phoneNumber.trim().length() != 10)
		{
			return phoneNumber.trim();
		} else {
			return "(" + phoneNumber.trim().substring(0, 3) + ")" + 
					phoneNumber.trim().substring(3, 6) + "-"+
					phoneNumber.trim().substring(6, 10);
		}
	}
	
	protected String getFormattedZipcode (String zipcode)
	{
		if( zipcode == null || zipcode.trim().length() != 9)
		{
			return zipcode.trim();
		} else {
			return zipcode.trim().substring(0, 5) + "-" + 
					zipcode.trim().substring(5, 9) ;
		}
	}
}
