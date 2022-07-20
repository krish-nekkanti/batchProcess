package com.radian.webserviceclient.cmd.endPoints;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.SOAPException;

import static javax.xml.soap.SOAPConstants.SOAP_1_2_PROTOCOL;

public class CMDWSConnector extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request) {

        SaajSoapMessageFactory messageFactory = null;
        try {
            messageFactory = new SaajSoapMessageFactory(javax.xml.soap.MessageFactory.newInstance(SOAP_1_2_PROTOCOL));
        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }
        messageFactory.setSoapVersion(SoapVersion.SOAP_12);
        messageFactory.afterPropertiesSet();

        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
        webServiceTemplate.setMessageFactory(messageFactory);

        return webServiceTemplate.marshalSendAndReceive(url, request);
    }
}
