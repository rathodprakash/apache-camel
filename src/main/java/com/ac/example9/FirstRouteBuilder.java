package com.ac.example9;

import org.apache.camel.builder.RouteBuilder;

public class FirstRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:F:/camel/input?noop=true")
		.process(new LogProcessor())
		.bean(new Transormer(),"transformContent")
		.to("file:F:/camel/output");	
	}

}
