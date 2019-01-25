package com.ac.example12;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:input-box?noop=true").split().tokenize("\n").process(new Processor() {
			public void process(Exchange exchange) {
				String body = exchange.getIn().getBody().toString();
				String response;
				if (body.contains("javainuse1")) {
					// the following routes will be called sequentially
					response = "direct:route1,direct:route2,direct:route3";
				} else
					// the following routes will be called sequentially
					response = "direct:route3,direct:route2,direct:route1";
					//set the route slip message in the header
				exchange.getIn().setHeader("myRoutingSlipHeader", response);
			}
		}).routingSlip(header("myRoutingSlipHeader"));

		from("direct:route1").process(new Processor() {
			public void process(Exchange exchange) {
				String body = exchange.getIn().getBody().toString();
				body = body + " in route 1";
				System.out.println(body);
				exchange.getOut().setBody(body);
			}
		});

		from("direct:route2").process(new Processor() {
			public void process(Exchange exchange) {
				String body = exchange.getIn().getBody().toString();
				body = body + " in route 2";
				System.out.println(body);
				exchange.getOut().setBody(body);
			}
		});

		from("direct:route3").process(new Processor() {
			public void process(Exchange exchange) {
				String body = exchange.getIn().getBody().toString();
				body = body + " in route 3";
				exchange.getOut().setBody(body);
				System.out.println(body);
			}
		});
	}

}
