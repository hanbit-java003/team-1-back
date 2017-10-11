package com.hanbit.cock.api.insert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.cock.api.insert.service.CockInsertService;
import com.hanbit.cock.api.vo.RestVO;

@RestController
@RequestMapping("/api/cock/insert")
public class CockInsertController {

	@Autowired
	private CockInsertService cockInsertService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping("/{rid}")
	public RestVO getRest(@PathVariable(value="rid") int rid) {
		return cockInsertService.getRest(rid);
	}
	
	@RequestMapping("/{rid}/{articleId}")
	public RestVO getRest(@PathVariable(value="rid") int rid, @PathVariable(value="articleId") int articleId) {
		return cockInsertService.getArticle(rid, articleId);
	}
}
