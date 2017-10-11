package com.hanbit.cock.api.insert.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.insert.dao.CockInsertDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.RestVO;

@Service
public class CockInsertService {

	@Autowired
	private CockInsertDAO cockInsertDAO; 
	
	public RestVO getRest(int rid) {
		RestVO restVO = cockInsertDAO.selectRest(rid);
		if (restVO == null) { // restVO가 null 이면 List를 받을 수가 없다.
			restVO = new RestVO();
		}
		
		return restVO;
	}
	
	public RestVO getArticle(int rid, int articleId) {
		RestVO restVO = cockInsertDAO.selectRest(rid);
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article = cockInsertDAO.selectArticle(article);
		article.setImgs(cockInsertDAO.selectImgs(article));
		
		restVO.setArticles(new ArrayList<ArticleVO>());
		restVO.getArticles().add(article);
		restVO.setMenus(cockInsertDAO.selectMenus(article));
		restVO.setTags(cockInsertDAO.selectTags(article));
		
		return restVO;
	}
}
