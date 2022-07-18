/**
 * @(#) WSMionlineClientSaxHandler.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Base class to handle common elements for MIOnline web service responses
 * This class is extended to handle specifics for each web service response
 * Note using this Sax parser approach because there is no schema for mionline responses (i.e., no JAXB binding possible)
 * There are no attributes used in mionline-style responses--all content in elements
 */
public abstract class WSMionlineClientSaxHandler extends DefaultHandler
{
	private static final Object WSMIO_RETURNSTATUS = "call_returnstatus";
	private static final Object WSMIO_ERRCODE = "errorcode";
	private static final Object WSMIO_ERRDESC = "errordesc";
	private static final Object WSMIO_ERRSRC = "errorsrc";
	
	private StringBuffer elementContent;
	
	private boolean inReturnStatus = false;

	private WSMionlineStatus status;
	
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
		status = new WSMionlineStatus();
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
		if (rawName.equals(WSMIO_RETURNSTATUS))
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
		{	//handle return status common to all mionline web service responses
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
	public WSMionlineStatus getStatus()
	{
		return status;
	}
	
	private void setReturnStatus(String elementName, String theContent)
	{
		if (elementName.equals(this.WSMIO_ERRCODE))
		{
			status.setErrorcode(theContent);
		}
		else if (elementName.equals(this.WSMIO_ERRDESC))
		{
			status.setErrordesc(theContent);
		}
		else if (elementName.equals(this.WSMIO_ERRSRC))
		{
			status.setErrorsrc(theContent);
		}
		else if (elementName.equals(WSMIO_RETURNSTATUS))
		{	//ind end of return status element
			inReturnStatus = false;
		}
		//else ignore element, should not happen
	}
}
