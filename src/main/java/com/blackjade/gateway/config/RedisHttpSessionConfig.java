package com.blackjade.gateway.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;


@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfig extends RedisHttpSessionConfiguration {
	
	@Value("${httpSessionTimeOut}")
	private Integer httpSessionTimeOut;
	
	@PostConstruct
	public void myInit (){
		super.setMaxInactiveIntervalInSeconds(httpSessionTimeOut);
	}
	
//	@Bean
//	public RedisOperationsSessionRepository sessionRepository() {
//		RedisTemplate<Object, Object> redisTemplate = createRedisTemplate();
//		RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(
//				redisTemplate);
//		sessionRepository.setApplicationEventPublisher(this.applicationEventPublisher);
//		if (this.defaultRedisSerializer != null) {
//			sessionRepository.setDefaultSerializer(this.defaultRedisSerializer);
//		}
//		sessionRepository
//				.setDefaultMaxInactiveInterval(this.maxInactiveIntervalInSeconds);
//		if (StringUtils.hasText(this.redisNamespace)) {
//			sessionRepository.setRedisKeyNamespace(this.redisNamespace);
//		}
//		sessionRepository.setRedisFlushMode(this.redisFlushMode);
//		return sessionRepository;
//	}
	
	
}
