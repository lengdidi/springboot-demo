package com.ld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.entity.Users;
import com.ld.mapper.UsersMapper;
import com.ld.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public List<Users> findAll() {
		return usersMapper.findAll();
	}

}
