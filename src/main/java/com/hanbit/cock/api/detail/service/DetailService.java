package com.hanbit.cock.api.detail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.cock.api.detail.dao.DetailDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.LikeVO;
import com.hanbit.cock.api.vo.MenuVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@Service
public class DetailService {

	@Autowired
	private DetailDAO detailDAO;

	public DetailVO getRest(int rid, boolean sort) {
		DetailVO detail = detailDAO.selectRest(rid);

		if (detail == null) {
			detail = new DetailVO();
		}

		// 최신순 / 좋아요순 정렬
		if (!sort) {
			detail.setArticles(getArticles(rid));
		} else {
			detail.setArticles(getArticlesByLikes(rid));
		}

		return detail;
	}
	
	public RestDetailVO getRestDetail(int rid) {
		RestDetailVO rd = detailDAO.selectRestDetail(rid);
		return rd;
	}

	public List<ArticleVO> getArticles(int rid) {
		List<ArticleVO> articles = new ArrayList<>();
		articles = detailDAO.selectArticles(rid);

		if (articles.size() == 0) {
			detailDAO.deleteRestDetail(rid);
			detailDAO.deleteRest(rid);
		}

		for (int i = 0; i < articles.size(); i++) {
			articles.get(i).setImgs(getImgs(articles.get(i)));
			articles.get(i).setTags(detailDAO.selectTags(articles.get(i)));
			articles.get(i).setEmblems(detailDAO.selectEmblems(articles.get(i)));
		}

		return articles;
	}

	public List<ArticleVO> getArticlesByLikes(int rid) {
		List<ArticleVO> articles = new ArrayList<>();
		articles = detailDAO.selectArticlesByLikes(rid);

		if (articles.size() == 0) {
			detailDAO.deleteRestDetail(rid);
			detailDAO.deleteRest(rid);
		}

		for (int i = 0; i < articles.size(); i++) {
			articles.get(i).setImgs(getImgs(articles.get(i)));
			articles.get(i).setTags(detailDAO.selectTags(articles.get(i)));
			articles.get(i).setEmblems(detailDAO.selectEmblems(articles.get(i)));
		}

		return articles;
	}

	public List<ImgVO> getImgs(ArticleVO article) {
		List<ImgVO> imgs = new ArrayList<>();
		imgs = detailDAO.selectImgs(article);

		for (int i = 0; i < imgs.size(); i++) {
			List<MenuVO> menus = new ArrayList<>();
			menus = detailDAO.selectMenus(imgs.get(i));
			imgs.get(i).setMenus(menus);
		}

		return imgs;
	}

	@Transactional
	public void removeArticle(ArticleVO article) {
		detailDAO.deleteMenus(article);
		detailDAO.deleteImgs(article);
		detailDAO.deleteTags(article);
		detailDAO.deleteArticle(article);

		System.out.println("삭제");
	}
	
	public List<LikeVO> getLikes(int rid) {
		return detailDAO.selectLikes(rid);
	}

	public void increaseLikes(ArticleVO article) {
		LikeVO like = new LikeVO();
		like.setRid(article.getRid());
		like.setArticleId(article.getArticleId());
		like.setUid(article.getUid());
		
		detailDAO.insertLike(like);
		detailDAO.updateLikesIncrease(article);
	}
	
	public int getLikeCount(ArticleVO article) {
		return detailDAO.selectLikeCount(article);
	}
	
	public void decreaseLikes(ArticleVO article) {
		LikeVO like = new LikeVO();
		like.setRid(article.getRid());
		like.setArticleId(article.getArticleId());
		like.setUid(article.getUid());
		
		detailDAO.deleteLike(like);
		detailDAO.updateLikesDecrease(article);
	}

}
