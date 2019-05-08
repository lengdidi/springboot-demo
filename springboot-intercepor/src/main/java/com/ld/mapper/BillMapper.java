package com.ld.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ld.entity.Bill;

@Repository
@Mapper
public interface BillMapper {
//	@Select("select * from smbms_bill")
	List<Bill> findAll();
}
