package com.radian.cuwb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, 
//		SecurityAutoConfiguration.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, 
		SecurityAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.radian.cuwb.controller", "com.radian.cuwb.service"})
//@EntityScan("com.radian.cuwb.rest.model")
@ComponentScan(basePackages = {"com.radian.cuwb"})
public class AppConfig extends SpringBootServletInitializer{
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(AppConfig.class);
	    }
}
