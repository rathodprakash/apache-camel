package com.ac.example5;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CallingMethodUseingClassComponent2 {
	public static void main(String[] args) throws Exception {
		
		MyService myService = new MyService();
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("myService", myService);
		
		CamelContext camelContext = new DefaultCamelContext(registry);
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start")
				.to("bean:myService?method=doSomthing");
			}
		});
		camelContext.start();
		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start","this is the demo");
		
		System.out.println("Helloo = "+ myService.getDoSomthing());
	}
}
