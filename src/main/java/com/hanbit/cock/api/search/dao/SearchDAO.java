package com.hanbit.cock.api.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.search.vo.SearchArticleVO;
import com.hanbit.cock.api.search.vo.SearchImgVO;
import com.hanbit.cock.api.search.vo.SearchRestVO;
import com.hanbit.cock.api.vo.ArticleVO;

@Repository
public class SearchDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<SearchRestVO> searchRests(String[] list) {
		return sqlSession.selectList("search.selectMatchRests", list);
	}

	public int countArticles(int rid) {
		return sqlSession.selectOne("search.countArticles", rid);
	}

	public int countMaxLikes(int rid) {
		return sqlSession.selectOne("search.countMaxLikes", rid);
	}

	public List<SearchArticleVO> searchArticles(String[] list) {
		return sqlSession.selectList("search.searchMatchArticles", list);
	}

	public SearchArticleVO selectArticle(SearchArticleVO article) {
		return sqlSession.selectOne("search.selectArticle", article);
	}

	public List<String> searchMenus(SearchArticleVO article) {
		return sqlSession.selectList("search.selectMatchMenus", article);
	}

	public List<String> searchTags(SearchArticleVO article) {
		return sqlSession.selectList("search.selectMatchTags", article);
	}

	public List<String> searchImgs(int rid) {
		return sqlSession.selectList("search.selectRestImgs", rid);
	}
	
	public List<String> searchImgs(SearchImgVO img) {
		return sqlSession.selectList("search.selectArticleImgs", img);
	}
}
