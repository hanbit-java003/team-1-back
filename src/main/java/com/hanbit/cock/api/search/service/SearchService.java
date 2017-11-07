package com.hanbit.cock.api.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.cock.api.insert.dao.CockInsertDAO;
import com.hanbit.cock.api.search.dao.SearchDAO;
import com.hanbit.cock.api.search.vo.SearchArticleVO;
import com.hanbit.cock.api.search.vo.SearchImgVO;
import com.hanbit.cock.api.search.vo.SearchRestVO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.MenuVO;
import com.hanbit.cock.api.vo.RestVO;
import com.hanbit.cock.api.vo.TagVO;

@Service
public class SearchService {

	@Autowired
	private SearchDAO searchDAO;
	
	@Transactional
	public Map getSearchService(String[] list) {
		Map map = new HashMap<>();
		
		map.put("rests", searchRests(list));
		map.put("articles", searchArticles(list));
		
		return map;
	}
	
	private List<SearchRestVO> searchRests(String[] list) {
		List<SearchRestVO> rests = searchDAO.searchRests(list);
		
		if (rests.size() == 0) {
			return null;
		}
		
		for (SearchRestVO rest : rests) {
			rest.setArticleCnt(searchDAO.countArticles(rest.getRid()));
			rest.setFavoCnt(0);
			rest.setLikeCnt(searchDAO.countMaxLikes(rest.getRid()));
		}
		
		return rests;
	}
	
	private List<SearchArticleVO> searchArticles(String[] list) {
		List<SearchArticleVO> articles = searchDAO.searchArticles(list);
		
		if (articles.size() == 0) {
			return null;
		}
		
		for (int i=0; i<articles.size(); i++) {
			SearchArticleVO article = articles.get(i); 
			articles.set(i, searchDAO.selectArticle(article));
			List<String> str = searchDAO.searchMenus(article);
			articles.get(i).setMenus(str.toArray(new String[str.size()]));
			str = searchDAO.searchTags(article);
			articles.get(i).setTags(str.toArray(new String[str.size()]));
		}
		
		return articles;
	}

	public Map getSearchImgs(int rid) {
		Map map = new HashMap<>();
		
		List<String> imgs = searchDAO.searchImgs(rid);
		
		map.put("imgs", imgs);
		return map;
	}

	public Map getSearchImgs(int rid, int articleId) {
		Map map = new HashMap<>();
		
		SearchImgVO img = new SearchImgVO();
		img.setRid(rid);
		img.setArticleId(articleId);
		
		List<String> imgs = searchDAO.searchImgs(img);
		
		map.put("imgs", imgs);
		return map;
	}
	
}