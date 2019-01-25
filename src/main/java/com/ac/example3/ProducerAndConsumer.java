package com.ac.example3;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerAndConsumer {
	public static void main(String[] args) throws Exception {
		String msg;
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start")
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String msg = exchange.getIn().getBody(String.class);
						msg += "Chnage String Object";
						exchange.getOut().setBody(msg);
					}
				})
				.to("seda:end");
			}
		});
		camelContext.start();
		
		ProducerTemplate template = camelContext.createProducerTemplate();
		template.sendBody("direct:start","Hello Every one ");
		
		ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
		msg =  consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println(msg);
		
	}
	
}
