package com.ld.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ld.entity.Users;

@Repository
@Mapper
public interface UsersMapper {
//	@Select("select * from smbms_user")
	List<Users> findAll();
}
