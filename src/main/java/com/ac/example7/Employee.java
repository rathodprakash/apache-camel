package com.ac.example7;

public class Employee {
	private boolean isResigning;
    private boolean isNew;
    private boolean isOnLeave;
    private boolean isPromoted;
    private String msg;
    private String name;
    public Employee(String name) {
        this.name = name;
    }
     
	public boolean isResigning() {
		return isResigning;
	}
	public void setResigning(boolean isResigning) {
		this.isResigning = isResigning;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public boolean isOnLeave() {
		return isOnLeave;
	}
	public void setOnLeave(boolean isOnLeave) {
		this.isOnLeave = isOnLeave;
	}
	public boolean isPromoted() {
		return isPromoted;
	}
	public void setPromoted(boolean isPromoted) {
		this.isPromoted = isPromoted;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
        return "Employee " + name + " " + msg;
    }
    
}
