package com.hanbit.cock.api.detail.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.detail.service.DetailService;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;

@RestController
@RequestMapping("/api/cock/detail")
public class DetailController {

	@Autowired
	private DetailService detailService;

	@RequestMapping("/{rid}")
	public DetailVO getRest(@PathVariable(value = "rid") int rid) {
		return detailService.getArticles(rid);
	}
	
	@RequestMapping("/{rid}/{articleId}")
	public ArticleVO getRest(@PathVariable(value = "rid") int rid,
						     @PathVariable(value = "articleId") int articleId) {
		return detailService.getArticle(rid, articleId);
	}
	
	/*
	 * 삭제, 수정 해야함, 유저정보도 불러와야함...
	 * */
	
	@DeleteMapping("/{rid}/{articleId}")
	public Map<String, Boolean> removeArticle(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId) {
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("ok", true);
		
		return result;
	}
}