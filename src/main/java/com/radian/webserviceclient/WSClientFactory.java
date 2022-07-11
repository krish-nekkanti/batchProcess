/**
 * @(#) WSClientFactory.java
 * Copyright 2008 Radian Group Inc.
 * All rights reserved.
 * @author John Stritzinger
 * @version 1.0
 */
package com.radian.webserviceclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.ilog.rules.decisionservice.DecisionServiceCuwrules;
import com.ilog.rules.decisionservice.DecisionServiceCuwrules_Service;
import com.ilog.rules.decisionservice.ObjectFactory;
import com.ilog.rules.decisionservice.DecisionServiceRequest;
import com.ilog.rules.decisionservice.DecisionServiceResponse;
import com.radian.foundation.common.config.Configuration;
import com.radian.foundation.common.util.ServiceLocator;
import com.radian.ws.client.CMDService_Impl;
import com.radian.ws.client.CUWService_Impl;
import com.radian.ws.client.DocumentService_Impl;
import com.radian.ws.client.PS_AR_Department_Impl;

/**
 * Provide web service clients for cuw billing
 */
public class WSClientFactory
{
	private static String WS_JRULES = "webservice-jrules";
	private static String WS_MIONLINE = "webservice-mionline";
	private static String WS_PEOPLESOFT = "webservice-peoplesoft";
	private static String WS_CMD = "webservice-cmd";
	private static String WS_SHAREPOINT = "webservice-sharepoint";
	private static String WS_SVCURL = "serviceurl";
	
	/**
	 * @return web service client to access JRules decision service
	 * @throws WSClientException 
	 */
	public static WSClientJrules getJrulesClient() throws WSClientException
	{
		WSClientJrules wsclient = new WSClientJrules();
		DecisionServiceCuwrules ws =  null;
		try
		{
			 	System.out.println("-----------------------getJrulesClient--Start-----------------"); 
			 	Configuration contentConfig = ServiceLocator.getInstance().getConfiguration("cuw-jrules-config");
		    	Properties prop = new Properties();
		    	InputStream istream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/"+contentConfig.getString("configFile"));
			    prop.load(istream);
			    istream.close();
			    String serviceWSDLURL = prop.getProperty("jrules.serviceWSDLURL").trim();
			    String serviceNameStr = prop.getProperty("jrules.serviceNameStr").trim();
			    String portNameStr = prop.getProperty("jrules.portNameStr").toString();
			    System.out.println("-------JRules props--serviceWSDLURL---"+serviceWSDLURL);
			    System.out.println("-------JRules props--serviceNameStr---"+serviceNameStr);
			    System.out.println("-------JRules props--portNameStr---"+portNameStr);
		     
		        if (serviceWSDLURL == null || serviceNameStr == null || portNameStr == null) {
		            System.out.println("Failed to set the WSDL, Service name, and/or Port");
		        } else {
		                    URL wsdlLocation;
							try {
		                              wsdlLocation = new URL(serviceWSDLURL);
		                        } catch (MalformedURLException e) {
		                        	  System.out.println("-------------------------MalformedURLException-----------------");
		                              e.printStackTrace();
		                              throw e;
		                        }
		                  QName serviceName = new QName("http://www.ilog.com/rules/DecisionService", serviceNameStr);
		                  DecisionServiceCuwrules_Service dspSvc = new DecisionServiceCuwrules_Service(wsdlLocation, serviceName);
		                  QName portQName = new QName("http://www.ilog.com/rules/DecisionService", portNameStr);
		                  ws = dspSvc.getPort(portQName, DecisionServiceCuwrules.class);
		                  wsclient.setWebservice(ws);
		        }  
		}
		catch (Exception e)
		{	//catch all exceps (e.g., unable to locate WSDL), including runtime, and rethrow
			System.out.println("-------------------------WSClientException-----------------");
			throw new WSClientException(e);
		}
		return wsclient;
	}

	/**
	 * @return web service client to access MIOnline web service
	 * @throws WSClientException 
	 */
	public static WSClientMionline getMionlineClient() throws WSClientException
	{
		WSClientMionline wsclient = new WSClientMionline();
		try
		{
			wsclient.setWebservice((new CUWService_Impl(getWebServiceURL(WS_MIONLINE))).getCUWServiceSoap());
		}
		catch (IOException e)
		{	//unable to locate web service
			throw new WSClientException(e);
		}
		catch (Exception e)
		{	//catch all exceps (e.g., unable to locate WSDL), including runtime, and rethrow
			throw new WSClientException(e);
		}
		return wsclient;
	}

	/**
	 * @return web service client to access PeopleSoft web service
	 * @throws WSClientException 
	 */
	public static WSClientPeoplesoft getPeoplesoftClient() throws WSClientException
	{
		WSClientPeoplesoft wsclient = new WSClientPeoplesoft();
		try
		{
			wsclient.setWebservice((new PS_AR_Department_Impl(getWebServiceURL(WS_PEOPLESOFT))).getCI_RE_AR_DEPARTMENT_Port());
		}
		catch (IOException e)
		{	//unable to locate web service
			throw new WSClientException(e);
		}
		catch (Exception e)
		{	//catch all exceps (e.g., unable to locate WSDL), including runtime, and rethrow
			throw new WSClientException(e);
		}
		return wsclient;
	}
	
	public static WSClientCMD getCMDClient() throws WSClientException
	{
		WSClientCMD wsclient = new WSClientCMD();
		try
		{
			wsclient.setWebservice((new CMDService_Impl(getWebServiceURL(WS_CMD))).getCMDServiceSoap());
		}
		catch (IOException e)
		{	//unable to locate web service
			throw new WSClientException(e);
		}
		catch (Exception e)
		{	//catch all exceps (e.g., unable to locate WSDL), including runtime, and rethrow
			throw new WSClientException(e);
		}
		return wsclient;
	}
	
	public static WSClientSharePoint getSharePointDClient(String username, String password) throws WSClientException
	{
		WSClientSharePoint wsclient = new WSClientSharePoint();
		try
		{
			wsclient.setWebservice((new DocumentService_Impl(getWebServiceURL(WS_SHAREPOINT))).getDocumentServiceSoap(username, password));
		}
		catch (IOException e)
		{	//unable to locate web service
			throw new WSClientException(e);
		}
		catch (Exception e)
		{	//catch all exceps (e.g., unable to locate WSDL), including runtime, and rethrow
			throw new WSClientException(e);
		}
		return wsclient;
	}
	
	/**
	 * @param configName identifies webservice in config file
	 * @return URL appropriate for environment using config data
	 */
	private static String getWebServiceURL(String configName) throws WSClientException
	{
        try
        {	//get environment-appropriate service URL from config file
            Configuration serviceConfig = ServiceLocator.getInstance().getConfiguration(configName);
            return serviceConfig.getString(WS_SVCURL);
        }
        catch (Exception e)
        {
            throw new WSClientException(e);
        }
	}
}
