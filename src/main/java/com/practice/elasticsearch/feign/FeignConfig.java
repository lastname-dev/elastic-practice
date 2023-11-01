package com.practice.elasticsearch.feign;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
@EnableFeignClients(clients ={ KakaoFeignClient.class})
public class FeignConfig {

	@Value(value = "${kakao.apiKey}")
	private String API_KEY;
	@Value(value = "${kakao.prefix}")
	private String PREFIX;

	@Bean
	Level feignLoggerLevel() {
		return Level.ALL; // log레벨 설정
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			requestTemplate.header("Content-Type", "application/json");
			requestTemplate.header("Accept", "application/json");
			requestTemplate.header("Authorization",PREFIX + API_KEY);
		};
	}
}