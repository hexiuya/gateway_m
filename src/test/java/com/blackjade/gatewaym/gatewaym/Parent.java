package com.blackjade.gatewaym.gatewaym;

public class Parent {

	private int timeout = 400;
	
	public void print(){
		System.out.println("timeout:"+timeout);
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	
}
