package com.ld.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ld.main.entity.Users;
import com.ld.main.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService UsersService;

	@RequestMapping("/list")
	@ResponseBody
	public String getAllUsers() {
		List<Users> findAll = UsersService.findAll();
		String jsonString = JSONObject.toJSONString(findAll);
		findAll.forEach(System.out::println);
		return jsonString;
	}
}
