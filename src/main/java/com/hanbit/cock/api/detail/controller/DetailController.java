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

	@RequestMapping("/{rid}/{likes}")
	public DetailVO getRest(@PathVariable(value = "rid") int rid,
							@PathVariable(value = "likes") boolean likes) {
		return detailService.getRest(rid, likes);
	}

	@DeleteMapping("/{rid}/{articleId}")
	public Map<String, Boolean> removeArticle(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId) {
		
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		
		detailService.removeArticle(article);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("ok", true);
		
		return result;
	}
	
	@RequestMapping("/inc/{rid}/{articleId}")
	public Map<String, Boolean> increaseLikes(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId) {
		
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		
		detailService.increaseLikes(article);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("ok", true);
		
		return result;
	}
	
	@RequestMapping("/dec/{rid}/{articleId}")
	public Map<String, Boolean> decreaseLikes(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId) {
		
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		
		detailService.decreaseLikes(article);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("ok", true);
		
		return result;
	}
}
