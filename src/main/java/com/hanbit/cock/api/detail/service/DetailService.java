package com.hanbit.cock.api.detail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.detail.dao.DetailDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;

@Service
public class DetailService {

	@Autowired
	private DetailDAO detailDAO;

	public DetailVO getRest(int rid) {
		DetailVO detail = detailDAO.selectRest(rid);

		if (detail == null) {
			detail = new DetailVO();
		}

		detail.setArticles(getArticles(rid));

		return detail;
	}

	public List<ArticleVO> getArticles(int rid) {
		List<ArticleVO> articles = new ArrayList<>();
		articles = detailDAO.selectArticles(rid);

		System.out.println(articles);

		return articles;
	}

	public DetailVO getArticle(int rid, int articleId) {
		DetailVO detailVO = detailDAO.selectRest(rid);
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article = detailDAO.selectArticle(article);
		article.setImgs(detailDAO.selectImgs(article));
		article.setTags(detailDAO.selectTags(article));
		article.setMenus(detailDAO.selectMenus(article));

		detailVO.setArticles(new ArrayList<ArticleVO>());
		detailVO.getArticles().add(article);

		return detailVO;
	}

}
