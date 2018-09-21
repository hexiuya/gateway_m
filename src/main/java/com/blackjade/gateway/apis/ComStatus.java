package com.blackjade.gateway.apis;

public class ComStatus {

	public static enum getUserInfo {
		FAILED,
		SUCCESS
	}
	
	public static enum commonStatic {
		NO_PERMISSION,
		PLEASE_LOGIN,
		CLIENT_ID_ILLEGAL
	}
	
	public static enum LoginStatus {
		SUCCESS, 
		WRONG_MSGID,
		REQID_ERROR,
		ILLEGAL_USER,
		PASSWD_LENGTH_ERR,
		VARCODE_LENGTH_ERR,
		USER_FORMAT_ERR,
		USERID_FORMAT_ERR,
		EMAIL_FORMAT_ERR,
		PASSWD_FORMAT_ERR,
		VARCODE_FORMAT_ERR,
		PASSWD_USER_NO_MATCH,
		UNKNOWN
	}
}
