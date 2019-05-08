package com.ld.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.main.entity.Users;
import com.ld.main.mapper.UsersMapper;
import com.ld.main.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;

	@Override
	public List<Users> findAll() {
		return usersMapper.findAll();
	}

}
