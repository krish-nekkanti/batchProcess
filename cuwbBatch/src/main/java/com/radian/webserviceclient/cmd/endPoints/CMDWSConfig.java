package com.radian.webserviceclient.cmd.endPoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Configuration class to inject Web service related model classes 
 * and default URL to return wsdl file.
 * @author krishna.prasad
 *
 */
@Configuration
public class CMDWSConfig {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.radian.webserviceclient.cmd.model");
		return marshaller;
	}
	@Bean
	  public CMDWSConnector wsConnector(Jaxb2Marshaller marshaller) {
	    CMDWSConnector client = new CMDWSConnector();
	    client.setDefaultUri("http://localhost:9090/BookService");
	    client.setMarshaller(marshaller);
	    client.setUnmarshaller(marshaller);
	    return client;
	  }
}
