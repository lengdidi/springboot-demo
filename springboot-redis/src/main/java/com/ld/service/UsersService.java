package com.ld.service;

import java.util.List;

import com.ld.entity.Users;

public interface UsersService {
	List<Users> findAll();

	Users findById(Integer id);

	int updateUser(Users users);

	int deleteUser(Integer id);
}
