package com.hanbit.cock.api.detail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.cock.api.detail.dao.DetailDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;

@Service
public class DetailService {

	@Autowired
	private DetailDAO detailDAO;

	public DetailVO getRest(int rid, boolean sort) {
		DetailVO detail = detailDAO.selectRest(rid);

		if (detail == null) {
			detail = new DetailVO();
		}
		
		if (!sort) {
			detail.setArticles(getArticles(rid));
		}
		else {
			detail.setArticles(getArticlesByLikes(rid));
		}

		return detail;
	}

	public List<ArticleVO> getArticles(int rid) {
		List<ArticleVO> articles = new ArrayList<>();
		articles = detailDAO.selectArticles(rid);

		if (articles.size() == 0) {
			detailDAO.deleteRest(rid);
		}

		for (int i = 0; i < articles.size(); i++) {
			articles.get(i).setImgs(detailDAO.selectImgs(articles.get(i)));
			articles.get(i).setTags(detailDAO.selectTags(articles.get(i)));
			articles.get(i).setMenus(detailDAO.selectMenus(articles.get(i)));
			articles.get(i).setEmblems(detailDAO.selectEmblems(articles.get(i)));
		}

		return articles;
	}
	
	public List<ArticleVO> getArticlesByLikes(int rid) {
		List<ArticleVO> articles = new ArrayList<>();
		articles = detailDAO.selectArticlesByLikes(rid);
		
		if (articles.size() == 0) {
			detailDAO.deleteRest(rid);
		}
		
		for (int i = 0; i < articles.size(); i++) {
			articles.get(i).setImgs(detailDAO.selectImgs(articles.get(i)));
			articles.get(i).setTags(detailDAO.selectTags(articles.get(i)));
			articles.get(i).setMenus(detailDAO.selectMenus(articles.get(i)));
			articles.get(i).setEmblems(detailDAO.selectEmblems(articles.get(i)));
		}
		
		return articles;
	}

	@Transactional
	public void removeArticle(ArticleVO article) {
		detailDAO.deleteMenus(article);
		detailDAO.deleteImgs(article);
		detailDAO.deleteTags(article);
		detailDAO.deleteArticle(article);
		
		System.out.println("삭제");
	}

	public void increaseLikes(ArticleVO article) {
		detailDAO.updateLikesIncrease(article);
	}

	public void decreaseLikes(ArticleVO article) {
		detailDAO.updateLikesDecrease(article);
	}

}
