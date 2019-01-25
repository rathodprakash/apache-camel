package com.ac.example11;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.ac.example10.MyProcessor;

public class ActiveMQ_HTTP {
	public static void main(String[] args) throws Exception {
CamelContext camelContext = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		camelContext.addComponent("jms", JmsComponent.jmsComponentTransacted(connectionFactory));
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("activemq:am_queue")
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String msg = exchange.getIn().getBody(String.class);
						exchange.getOut().setBody(msg + " Change");
					}
				})
				.to("seda:end")
				.to("http://localhost:9090/ISS/client/employee?id=1").process(new MyProcessor());
			}
			
		});
		
		camelContext.start();
		
		ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
		String msg =  consumerTemplate.receiveBody("seda:end", String.class);
		System.out.println(msg);
	}
}
