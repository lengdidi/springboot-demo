package com.ld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ld.entity.Bill;
import com.ld.service.BillService;

@Controller
public class BillController {
	@Autowired
	private BillService billService;

	@RequestMapping("/allbill")
	public String allBill(ModelMap modelMap) {
		List<Bill> findAll = billService.findAll();
		modelMap.addAttribute("billlist", findAll);
		return "bill";
	}
}
