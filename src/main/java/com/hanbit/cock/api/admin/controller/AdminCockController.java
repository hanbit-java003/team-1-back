package com.hanbit.cock.api.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.admin.service.AdminCockService;
import com.hanbit.cock.api.admin.vo.AdminRestVO;

@RestController
@RequestMapping("/api/cock/admin")
public class AdminCockController {
	
	@Autowired
	private AdminCockService adminCockService;
	
	@RequestMapping("/rest")
	public List<AdminRestVO> listAdminRest() {
		return adminCockService.listAdminRest();
	}
	
}
