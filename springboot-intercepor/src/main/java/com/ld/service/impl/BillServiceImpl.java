package com.ld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.entity.Bill;
import com.ld.mapper.BillMapper;
import com.ld.service.BillService;


@Service
public class BillServiceImpl implements BillService {
	@Autowired
	private BillMapper billMapper;

	@Override
	public List<Bill> findAll() {
		return billMapper.findAll();
	}

}
