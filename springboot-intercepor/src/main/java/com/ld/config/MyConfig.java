package com.ld.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

	@Autowired
	private MyIntepoter myIntepoter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.err.println("进入拦截器配置类");
		InterceptorRegistration is = registry.addInterceptor(myIntepoter);
		is.excludePathPatterns("/login");
		is.excludePathPatterns("/tologin");
		is.excludePathPatterns("*.html");
		is.excludePathPatterns("/logout");
		is.addPathPatterns("/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
