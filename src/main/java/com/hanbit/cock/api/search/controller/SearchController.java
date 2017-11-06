package com.hanbit.cock.api.search.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.cock.api.search.service.SearchService;

@RestController
@RequestMapping("/api/cock/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = {"/get//", "/get/{text}/"})
	public Map getSearch(@PathVariable(value="text", required=false) String text) {
		Map map = new HashMap<>();
		
		if (text == null) {
			map.put("result", "null");
			return map;
		}
		String[] list = text.split(" ");
		
		map = searchService.getSearchService(list);
		
		return map;
	}
	
	@RequestMapping("/img/rest/{rid}/")
	public Map getImgs(@PathVariable("rid") int rid) {
		Map map = new HashMap<>();
		map = searchService.getSearchImgs(rid);	
		return map;
	}
	
	@RequestMapping("/img/article/{rid}/{articleId}/")
	public Map getImgs(@PathVariable("rid") int rid, @PathVariable("articleId") int articleId) {
		Map map = new HashMap<>();
		map = searchService.getSearchImgs(rid, articleId);	
		return map;
	}
	
}
