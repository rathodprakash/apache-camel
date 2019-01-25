package com.ac.example7;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelRecipientListExample {
	public static void main(String[] args) throws Exception {
		JndiContext context = new JndiContext();
		context.bind("empRouter", new EmployeeRouter());
		CamelContext camelContext = new DefaultCamelContext(context);
        try {
            camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("direct:start")
					.recipientList(method("empRouter","routeEmployee"));
					
					from("direct:account")
					.log("-------> Account department notification '${body}'");
					
					from("direct:hr")
					.log("-------> HR department notification '${body}'");
					
					from("direct:manager")
					.log("-------> Manager department notification '${body}'");
				}
			});
            ProducerTemplate template = camelContext.createProducerTemplate();
            camelContext.start();
            Employee sam = new Employee("Sam");
            sam.setNew(true);
            sam.setMsg("Joined");
            template.sendBody("direct:start", sam);
            
            Employee jone = new Employee("Jone");
            jone.setOnLeave(true);
            jone.setMsg("Joined");
            template.sendBody("direct:start", jone);
            
            Employee roy = new Employee("Roy");
            roy.setPromoted(true);
            roy.setMsg("Joined");
            template.sendBody("direct:start", roy);
            
        } finally {
            camelContext.stop();
        }
	}
}
