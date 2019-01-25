package com.ac.example10;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class SimpleRouteBuilder extends RouteBuilder{
	JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);
	
	@Override
	public void configure() throws Exception {

		// route for REST GET Call
//		from("file:input-box?noop=true").setHeader(Exchange.HTTP_METHOD, constant("GET"))
//				.to("http://localhost:9090/ISS/client/employee?id=1").process(new MyProcessor());

		from("activemq:am_queue").setHeader(Exchange.HTTP_METHOD, constant("GET"))
		.to("http://localhost:9090/ISS/client/employee?id=1").process(new MyProcessor());
		
		// route for REST POST Call
		/*from("direct:start").process(new CreateEmployeeProcessor()).marshal(jsonDataFormat)
				.setHeader(Exchange.HTTP_METHOD, simple("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json")).to("http://localhost:9090/ISS/client/employee")
				.process(new MyProcessor());*/
		
	}
}
