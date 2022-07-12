package com.radian.webserviceclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WSConnector extends WebServiceGatewaySupport {
	 
	  public Object callWebService(String url, Object request){
	    return getWebServiceTemplate().marshalSendAndReceive(url, request);
	  }
	}
