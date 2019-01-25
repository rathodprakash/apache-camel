package com.ac.example13;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

public class CamelQuartz {
	public static void main(String[] args) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
        ctx.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
				startPolicy.setRouteStartTime("0 0/1 * * * ?");

				from("file:input-box?noop=true").routePolicy(startPolicy).noAutoStartup().process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						System.out.println("Executing camel processor");						
					}
				})
				.to("file:output-box");
			}
		});
        ctx.start();
        System.out.println("Application context started");
        try {
            Thread.sleep(5 * 60 * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.stop();
    }
}
