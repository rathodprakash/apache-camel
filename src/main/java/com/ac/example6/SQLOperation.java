package com.ac.example6;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SQLOperation {
	public static void main(String[] args) throws Exception {
		
//		MysqlDataSource mysqlDataSource = new MysqlDataSource();
//		mysqlDataSource.setURL("jdbc:mysql://localhost:3306/immigrat_iss_demo_one");
//		mysqlDataSource.setPassword("root");
//		mysqlDataSource.setUser("root");
//		
//		SimpleRegistry registry = new SimpleRegistry();
//		registry.put("mySQLDataSource", mysqlDataSource);
		
		CamelContext camelContext = new DefaultCamelContext();
		
		 
		
		
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start")
				.to("jdbc:mySQLDataSource")
				.bean(new ResultHandler(),"printResult");
			}
		});
		camelContext.start();
		
		        
		        
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:start","select * from client_intake");
	}
}
