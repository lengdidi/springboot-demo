package com.ld.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ld.entity.Users;
import com.ld.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;

	@RequestMapping("list")
	@ResponseBody
	public String getAllUsers() {
		List<Users> findAll = usersService.findAll();
		String jsonString = JSONObject.toJSONString(findAll);
		findAll.forEach(System.out::println);
		return jsonString;
	}

	@RequestMapping("/tball")
	public String tball(Model model) {
		List<Users> findAll = usersService.findAll();
		model.addAttribute("userlist", findAll);
		return "user";
		// http://blog.sina.com.cn/webzmy
	}

	@ResponseBody
	@RequestMapping("/login")
	public String login() {
		return "登录页面";
	}

	@ResponseBody
	@RequestMapping("/tologin/{username}")
	public String toLogin(HttpSession session, @PathVariable String username) {
		System.out.println(username);
		if (username != null) {
			System.out.println(username);
			session.setAttribute("userSession", username);
		}
		return "登录成功";
	}

	@RequestMapping("select/{id}")
	@ResponseBody
	public Object select(@PathVariable String id) {
		Users user = usersService.findById(Integer.valueOf(id));
		System.out.println(user);
		return user;
	}

	@RequestMapping("update")
	@ResponseBody
	public Object update(Users user) {
//		user.setUserName("冷冷冷");
		usersService.updateUser(user);
		return user;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String id) {
		usersService.deleteUser(Integer.valueOf(id));
		return null;
	}

}
