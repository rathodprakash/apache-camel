package com.ac.example10;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CreateEmployeeProcessor implements Processor{
	public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody(String.class));
    	Employee emp = new Employee();
		emp.setName("camel-employee");
		emp.setDesignation("camel-manager");
		emp.setEmpId(111);
		emp.setSalary(30000);
		exchange.getIn().setBody(emp);
    }
}
