package com.soapService.demo.api.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.*;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SoapServiceConfig {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> 
	getMessageDispatcherServlet(ApplicationContext context){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet,"/ws/*");
	}
	
	@Bean(name="eligibilityCheck")
	public DefaultWsdl11Definition getDefaultWsdl11Definition(XsdSchema schema) {
		
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		
		wsdl.setPortTypeName("EligibilityEndpoint");
		wsdl.setTargetNamespace("http://www.soapService.com/demo/api/eligibilityCheck");
		wsdl.setLocationUri("/ws");
		wsdl.setSchema(schema);
		return wsdl;
	}
	
	@Bean
	public XsdSchema getXsdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("eligibilityCheck.xsd"));
	}
}
