package com.blackjade.gateway.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blackjade.gateway.bean.PermissionDefineEnum;

public class NameList {

	// 白名单
	private static List<String> whitelist = new ArrayList<String>();
	// 获取用户的缓存信息，该请求直接返回给前端，不再路由给后端服务
	private static String getUserInfo ;
	
	private static Map<String,PermissionDefineEnum> map = new HashMap<String,PermissionDefineEnum>();
	
	//不需要鉴权的url
	private static Map<String,Object> noPermissonUrl = new HashMap<String,Object>();
	
	private static final String PREFIX = "/crm/";
	
	private static final String LOGIN_NAME = "/crm/login";
	
	private static final String LOGOUT_NAME = "/crm/logout";
	
	static {
		whitelist.add("/crm/login");
		
		getUserInfo = "getUserInfo";
		
		noPermissonUrl.put(PREFIX + "changepw", true);
		
		map.put(PREFIX + "scanAllCustomers",PermissionDefineEnum.SCAN_ALL_CUSTOMERS);
		map.put(PREFIX + "scanCustomerPosition",PermissionDefineEnum.SCAN_CUSTOMER_POSITION);
		map.put(PREFIX + "createCustomer",PermissionDefineEnum.CREATE_CUSTOMER);
		map.put(PREFIX + "updateCustomer",PermissionDefineEnum.UPDATE_CUSTOMER);
		map.put(PREFIX + "limitCustomerTrade",PermissionDefineEnum.LIMIT_CUSTOMER_TRADE);
		map.put(PREFIX + "limitCustomerDepositWithdraw",PermissionDefineEnum.LIMIT_CUSTOMER_DEPOSIT_WITHDRAW);
		map.put(PREFIX + "updateCustomerOrders",PermissionDefineEnum.UPDATE_CUSTOMER_ORDERS);
		map.put(PREFIX + "createOrder",PermissionDefineEnum.CREATE_ORDER);
		map.put(PREFIX + "deleteOrder",PermissionDefineEnum.DELETE_ORDER);
		map.put(PREFIX + "scanOpratorLog",PermissionDefineEnum.SCAN_OPRATOR_LOG);
		
		map.put(PREFIX + "mGetAllUsers",PermissionDefineEnum.MANAGE_USER);
		map.put(PREFIX + "mGetAllUserPermission",PermissionDefineEnum.MANAGE_USER);
		map.put(PREFIX + "mAddPermission",PermissionDefineEnum.MANAGE_USER);
		map.put(PREFIX + "mUpdatePermission",PermissionDefineEnum.MANAGE_USER);
	}
	
	public static List<String> getWhitelist(){
		return whitelist;
	}
	
	public static String getUserInfo(){
		return getUserInfo;
	}
	
	public static Long getWeight (String key){
		PermissionDefineEnum permissionDefineEnum = map.get(key);
		Long weight = permissionDefineEnum.getWeight();
		return weight;
	}
	
	public static String getLoginName(){
		return LOGIN_NAME;
	}
	
	public static String getLogoutName(){
		return LOGOUT_NAME;
	}
	
	public static boolean noPermission(String apiName){
		Object obj = noPermissonUrl.get(apiName);
		if(obj == null){
			return false;
		}
		boolean flag = (boolean) obj;
		return flag ;
	}
}
