package com.hanbit.cock.api.emblem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.emblem.service.CockEmblemService;
import com.hanbit.cock.api.emblem.vo.EmblemAchiveVO;

@RestController
@RequestMapping("/api/emblem")
public class CockEmblemController {
	
	@Autowired
	private CockEmblemService cockEmblemService;
	
	@RequestMapping("/get/{uid}")
	public EmblemAchiveVO getEmblem(@PathVariable(value="uid") String uid) {
		return cockEmblemService.getEmblem(uid);
	}
	
}
