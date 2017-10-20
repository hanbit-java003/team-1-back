package com.hanbit.cock.api.detail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.detail.dao.DetailDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.RestVO;

@Service
public class DetailService {

	@Autowired
	private DetailDAO detailDAO;

	public RestVO getRest(int rid) {
		RestVO restVO = detailDAO.selectRest(rid);
		
		if (restVO == null) {
			restVO = new RestVO();
		}

		return restVO;
	}
	
	public RestVO getArticles(int rid) {
		RestVO restVO = detailDAO.selectRest(rid);
		List<ArticleVO> articles = new ArrayList<>();
		
		articles = detailDAO.selectArticles(rid);
		
		restVO.setArticles(articles);
		
		return restVO;
	}
	
	public RestVO getArticle(int rid, int articleId) {
		RestVO restVO = detailDAO.selectRest(rid);
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article = detailDAO.selectArticle(article);
		article.setImgs(detailDAO.selectImgs(article));
		article.setTags(detailDAO.selectTags(article));
		article.setMenus(detailDAO.selectMenus(article));
		
		restVO.setArticles(new ArrayList<ArticleVO>());
		restVO.getArticles().add(article);
		
		return restVO;
	}

}
