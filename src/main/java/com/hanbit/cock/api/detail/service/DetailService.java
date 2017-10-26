package com.hanbit.cock.api.detail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.dao.MemberDAO;
import com.hanbit.cock.api.detail.dao.DetailDAO;
import com.hanbit.cock.api.emblem.dao.CockEmblemDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;
import com.hanbit.cock.api.vo.MemberDetailVO;
import com.hanbit.cock.api.vo.MemberVO;

@Service
public class DetailService {

	@Autowired
	private DetailDAO detailDAO;
	
	@Autowired
	private MemberDAO memberDAO; // chi
	
	@Autowired
	private CockEmblemDAO cockEmblemDAO; // chi 

	public DetailVO getRest(int rid) {
		DetailVO detail = detailDAO.selectRest(rid);

		if (detail == null) {
			detail = new DetailVO();
		}

		getArticles(rid);

		return detail;
	}

	public DetailVO getArticles(int rid) {
		DetailVO detail = detailDAO.selectRest(rid);

		if (detail == null) {
			detail = new DetailVO();
		}

		List<ArticleVO> articles = new ArrayList<>();

		detail.setArticles(detailDAO.selectArticles(rid));

		for (int i = 0; i < detail.getArticles().size(); i++) {
			articles.add(getArticle(rid, i));
		}

		detail.setArticles(articles);

		return detail;
	}

	public ArticleVO getArticle(int rid, int articleId) {
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article = detailDAO.selectArticle(article);
		article.setImgs(detailDAO.selectImgs(article));
		article.setTags(detailDAO.selectTags(article));
		article.setMenus(detailDAO.selectMenus(article));
		
		/*********************** chi *************************/ 
		MemberVO memberVO = memberDAO.selectMemberDetail(article.getUid());
		MemberDetailVO memberDetailVO = memberVO.getDetail(); 
		article.setEmail(memberVO.getEmail());
		article.setAvatar(memberDetailVO.getAvatar());
		article.setEmblems(cockEmblemDAO.selectUidEmblem(article.getUid()));

		return article;
	}

}
