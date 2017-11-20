package com.hanbit.cock.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.dao.MainDAO;
import com.hanbit.cock.api.vo.LocationVO;
import com.hanbit.cock.api.vo.MainVO;
import com.hanbit.cock.api.vo.TagVO;
import com.hanbit.cock.api.vo.TopFourVO;

@Service
public class MainService {
	
	@Autowired
	private MainDAO mainDAO;
	
	public List<MainVO> latestCockRest(LocationVO locationVO) {
		return mainDAO.selectLatestCockRest(locationVO);
	}
	
	public List<MainVO> articleCockRest(LocationVO locationVO) {
		return mainDAO.selectArticleCockRest(locationVO);
	}
	
	public List<TagVO> listRestTags() {
		return mainDAO.selectRestTags();
	}
	
	public List<TopFourVO> listCockTopFour() {
		return mainDAO.selectTopFour();
	}	
	
}
