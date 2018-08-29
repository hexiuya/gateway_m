package com.blackjade.gateway.bean;

public enum PermissionDefineEnum {

	SCAN_ALL_CUSTOMERS(1L),
	SCAN_CUSTOMER_POSITION(2L),
	CREATE_CUSTOMER(4L),
	UPDATE_CUSTOMER(8L),
	LIMIT_CUSTOMER_TRADE(16L),
	LIMIT_CUSTOMER_DEPOSIT_WITHDRAW(32L),
	UPDATE_CUSTOMER_ORDERS(64L),
	CREATE_ORDER(128L),
	DELETE_ORDER(256L),
	SCAN_OPRATOR_LOG(512L),
	MANAGE_USER(1024L);
	
	private PermissionDefineEnum (Long weight){
		this.weight = weight;
	}
	
	private Long weight;

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	
}

