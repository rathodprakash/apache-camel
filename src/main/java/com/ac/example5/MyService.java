package com.ac.example5;

public class MyService {
	String msg;
	public  void doSomthing(String msg){
		this.msg = msg;
	}
	
	public  String getDoSomthing(){
		return msg;
	}
}
