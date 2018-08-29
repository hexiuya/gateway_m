package com.blackjade.gatewaym.gatewaym;

import javax.annotation.PostConstruct;


public class Child extends Parent{
	
	
	@PostConstruct
	public void myInit(){
		System.out.println("post construct .....");
		super.setTimeout(100);
	}
	
	public Child (){
		System.out.println("construct ....");
		super.setTimeout(100);
	}
	
	public static void main(String[] args) {
		Child child = new Child();
		
		child.print();
	}
}
