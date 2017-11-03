package com.hanbit.cock.api.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.admin.service.CountService;

@RestController
@RequestMapping("/api/cock/admin/count")
public class CountController {
	
	@Autowired
	private CountService countService;
	
	@RequestMapping("/rest")
	public int adminCountRest() {
		return countService.adminCountRest();
	}
	
	@RequestMapping("/article")
	public int adminCountArticle() {
		return countService.adminCountArticle();
	}
	
	@RequestMapping("/member")
	public int adminCountMember() {
		return countService.adminCountMember();
	}

}
