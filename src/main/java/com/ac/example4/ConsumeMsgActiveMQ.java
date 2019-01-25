package com.ac.example4;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ConsumeMsgActiveMQ {
	public static void main(String[] args) throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		camelContext.addComponent("jms", JmsComponent.jmsComponentTransacted(connectionFactory));
		
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("activemq:am_queue")
				.to("seda:end");
			}
		});
		camelContext.start();
		while(true){
			ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
			String msg = consumerTemplate.receiveBody("seda:end", String.class);
			System.out.println(msg);
		}
	}
}
