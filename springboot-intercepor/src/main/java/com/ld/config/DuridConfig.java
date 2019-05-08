package com.ld.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DuridConfig {
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// 添加IP白名单
		servletRegistrationBean.addInitParameter("allow", "192.168.25.125,127.0.0.1");
		// 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
		servletRegistrationBean.addInitParameter("deny", "192.168.25.125");
		// 添加控制台管理用户
		servletRegistrationBean.addInitParameter("loginUsername", "root");
		servletRegistrationBean.addInitParameter("loginPassword", "root");
		// 是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;

	}

	@Bean
	public FilterRegistrationBean statFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		// 忽略过滤格式
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
		return filterRegistrationBean;
	}
}
