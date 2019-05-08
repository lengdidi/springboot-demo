package com.ld.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ld.entity.Users;
import com.ld.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService UsersService;

	@RequestMapping("list")
	@ResponseBody
	public String getAllUsers() {
		List<Users> findAll = UsersService.findAll();
		String jsonString = JSONObject.toJSONString(findAll);
		findAll.forEach(System.out::println);
		return jsonString;
	}

	@RequestMapping("/tball")
	public String tball(Model model) {
		List<Users> findAll = UsersService.findAll();
		model.addAttribute("userlist", findAll);
		return "user";
		// http://blog.sina.com.cn/webzmy
	}
	
	@ResponseBody
	@RequestMapping("/aaa")
	public String aaa() {
		return "aaa";
	}

	@ResponseBody
	@RequestMapping("/login")
	public String login() {
		return "登录页面";
	}

	@ResponseBody
	@RequestMapping("/tologin")
	public String toLogin(HttpSession session, String username) {
		System.out.println(username);
		if (username != null) {
			session.setAttribute("userSession", username);
			System.out.println(username);
		}
		return "登录成功";
	}

	@RequestMapping("/one")
	public String one() {
		return "one";
	}

}
