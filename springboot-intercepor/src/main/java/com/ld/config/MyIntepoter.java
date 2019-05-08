package com.ld.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;

@Repository
public class MyIntepoter implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userSession = request.getSession().getAttribute("userSession");
		if (userSession != null) {
			System.out.println("用户登录了，继续访问");
			return true;
		}
		System.out.println("拦截了");
		response.sendRedirect("/login");
		return false;

	}
}
