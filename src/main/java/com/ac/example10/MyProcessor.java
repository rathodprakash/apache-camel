package com.ac.example10;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor{
	public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody(String.class));
    }
}
