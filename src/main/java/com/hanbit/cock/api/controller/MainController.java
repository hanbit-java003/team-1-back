package com.hanbit.cock.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.service.MainService;
import com.hanbit.cock.api.vo.LocationVO;
import com.hanbit.cock.api.vo.MainVO;
import com.hanbit.cock.api.vo.TagVO;
import com.hanbit.cock.api.vo.TopFourVO;

@RestController
@RequestMapping("/api/cock/rest")
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/latest/{lat},{lng}/")
	public List<MainVO> latestCockRest(@PathVariable("lat") double lat, @PathVariable("lng") double lng) {		
		LocationVO locationVO = new LocationVO();
		
		locationVO.setLat(lat);
		locationVO.setLng(lng);		
		
		return mainService.latestCockRest(locationVO);
	}
	
	@RequestMapping("/article/{lat},{lng}/")
	public List<MainVO> articleCockRest(@PathVariable("lat") double lat, @PathVariable("lng") double lng) {		
		LocationVO locationVO = new LocationVO();
		
		locationVO.setLat(lat);
		locationVO.setLng(lng);
		
		return mainService.articleCockRest(locationVO);
	}
	
	@RequestMapping("/tags")
	public List<TagVO> listRestTags() {
		return mainService.listRestTags();
	}
	
	@RequestMapping("/top4")
	public List<TopFourVO> listCockTopFour() {
		return mainService.listCockTopFour();
	}

}
