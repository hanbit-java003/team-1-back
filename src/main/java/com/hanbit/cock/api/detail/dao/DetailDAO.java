package com.hanbit.cock.api.detail.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.MenuVO;
import com.hanbit.cock.api.vo.TagVO;

@Repository
public class DetailDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public DetailVO selectRest(Integer rid) {
		return sqlSession.selectOne("cockDetail.selectRest", rid);
	}

	public List<ArticleVO> selectArticles(Integer rid) {
		return sqlSession.selectList("cockDetail.selectArticles", rid);
	}

	public ArticleVO selectArticle(ArticleVO article) {
		return sqlSession.selectOne("cockDetail.selectArticle", article);
	}
	
	public List<MenuVO> selectMenus(ArticleVO article) {
		return sqlSession.selectList("cockDetail.selectMenus", article);
	}

	public List<TagVO> selectTags(ArticleVO article) {
		return sqlSession.selectList("cockDetail.selectTags", article);
	}

	public List<ImgVO> selectImgs(ArticleVO article) {
		return sqlSession.selectList("cockDetail.selectImgs", article);
	}

}
