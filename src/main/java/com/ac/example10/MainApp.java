package com.ac.example10;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class MainApp {
	public static void main(String[] args) {
		CamelContext ctx = new DefaultCamelContext();
		try {
			ctx.addRoutes(new SimpleRouteBuilder());
			ctx.start();
			try {
	            Thread.sleep(5 * 60 * 1000);
	        }
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        ctx.stop();
		} catch(Exception  e){
			System.out.println(e.getMessage());
		}
	}
}
