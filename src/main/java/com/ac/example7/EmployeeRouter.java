package com.ac.example7;

public class EmployeeRouter {
	public String[] routeEmployee(Employee employee){
		if(employee.isResigning()){
			return new String[]{"direct:account","direct:hr"};
		}
		if(employee.isNew()){
			return new String[]{"direct:manager","direct:account","direct:hr"};
		}
		if(employee.isOnLeave()){
			return new String[]{"direct:manager","direct:hr"};
		}
		if(employee.isPromoted()){
			return new String[]{"direct:hr","direct:account"};
		}
		return new String[]{"direct:hr"};
	}
}
