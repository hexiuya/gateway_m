package com.blackjade.gateway.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blackjade.gateway.factory.NameList;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;

public class RequestUtil {
	public static void addClientId(RequestContext ctx , String customerDetail) throws Exception {
		try {
			Map map = JSON.parseObject(customerDetail);
			String id = (String) map.get("id");
			
			InputStream in = ctx.getRequest().getInputStream();
			String body = StreamUtils
					.copyToString(in, Charset.forName("UTF-8"));
			System.out.println("body:" + body);
			JSONObject json = JSONObject.parseObject(body);
			
			if (!id.equals(json.get("clientid"))){
				throw new Exception("clientid is illegal");
			}
			
			json.put("clientid", id);
//			json.put("poid", id);
			String newBody = json.toString();
			System.out.println("newBody:" + newBody);
			final byte[] reqBodyBytes = newBody.getBytes();
			HttpServletRequest request = ctx.getRequest();
			ctx.setRequest(new HttpServletRequestWrapper(request) {
				@Override
				public ServletInputStream getInputStream() throws IOException {
					return new ServletInputStreamWrapper(reqBodyBytes);
				}

				@Override
				public int getContentLength() {
					return reqBodyBytes.length;
				}

				@Override
				public long getContentLengthLong() {
					return reqBodyBytes.length;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static boolean checkPermission(String apiName , String customerDetail) {
		try {
			Map map = JSON.parseObject(customerDetail);
			String key = String.valueOf(map.get("managerid"));
			Long weight = NameList.getWeight(apiName);
			
			String tmp = String.valueOf(map.get("permissionCode"));
			Long permissionCode = Long.valueOf(tmp) ;
			
			Long result = weight & permissionCode;
			
			if (result == 0L){
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;

	}
	
	public static boolean checkUserStatus(RequestContext ctx , String customerDetail){
		Map map = JSON.parseObject(customerDetail);
		String manageidCache = String.valueOf(map.get("managerid")) ;
		String manageidParam = ctx.getRequest().getParameter("managerid");
		
		if(manageidCache.equals(manageidParam)){
			return true;
		}
		
		return false;
	}
}
