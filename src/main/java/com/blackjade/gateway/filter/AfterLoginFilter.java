package com.blackjade.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSONObject;
import com.blackjade.gateway.apis.MLoginAns;
import com.blackjade.gateway.factory.NameList;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AfterLoginFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AfterLoginFilter.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@Value("${httpSessionTimeOut}")
	private int httpSessionTimeOut;
	
	@Override
	public Object run() {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        
      //获取请求资源路径  
        String sevletPath = request.getServletPath();
        log.info("sevletPaht:" + sevletPath);
        
        if(!NameList.getLoginName().equals(sevletPath)){
        	return null;
        }
        
        String sessionId = request.getSession().getId();
        
        log.info("sessionId:"+sessionId);
        
        InputStream stream = ctx.getResponseDataStream();
        String body = null;
        MLoginAns mLoginAns = null;
		try {
			body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			mLoginAns = JSONObject.parseObject(body, MLoginAns.class);
			System.out.println(mLoginAns);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("internal error");
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            return null;
		}
        
        stringRedisTemplate.opsForValue().set(sessionId, body ,httpSessionTimeOut, TimeUnit.SECONDS);
        
//        redisTemplate.opsForValue().set(sessionId, obj, 180, TimeUnit.SECONDS);
        ctx.setResponseBody(body);
        
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

}
