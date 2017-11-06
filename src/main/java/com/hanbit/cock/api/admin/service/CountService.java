package com.hanbit.cock.api.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.admin.dao.CountDAO;

@Service
public class CountService {
	
	@Autowired
	private CountDAO countDAO;
	
	public int adminCountRest() {
		return countDAO.selectCountRest();
	}
	
	public int adminCountArticle() {
		return countDAO.selectCountArticle();
	}
	
	public int adminCountMember() {
		return countDAO.selectCountMember();
	}

}
