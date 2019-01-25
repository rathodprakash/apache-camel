package com.ac.example9;

public class Transormer {
	public String transformContent(String body) {
		System.out.println("invoking the transformContent method");
		String upperCaseContent=body.toUpperCase();
		return upperCaseContent;
	}
}
