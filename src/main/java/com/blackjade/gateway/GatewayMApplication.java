package com.blackjade.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.blackjade.gateway.filter.AccessFilter;
import com.blackjade.gateway.filter.AfterLoginFilter;

@EnableZuulProxy
@SpringBootApplication
public class GatewayMApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMApplication.class, args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
	@Bean
	public AfterLoginFilter afterLoginFilter(){
		return new AfterLoginFilter();
	}
}
