package com.hanbit.cock.api.insert.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.MenuVO;
import com.hanbit.cock.api.vo.RestVO;
import com.hanbit.cock.api.vo.TagVO;

@Repository
public class CockInsertDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public RestVO selectRest(int rid) {
		return sqlSession.selectOne("cockInsert.selectRest", rid);
	}

	public List<ArticleVO> selectArticles(int rid) {
		return sqlSession.selectOne("cockInsert.selectArticles", rid);
	}
	
	public ArticleVO selectArticle(ArticleVO article) {
		return sqlSession.selectOne("cockInsert.selectArticle", article);
	}

	public List<MenuVO> selectMenus(ArticleVO article) {
		return sqlSession.selectList("cockInsert.selectMenus", article);
	}

	public List<TagVO> selectTags(ArticleVO article) {
		return sqlSession.selectList("cockInsert.selectTags", article);
	}

	public List<ImgVO> selectImgs(ArticleVO article) {
		return sqlSession.selectList("cockInsert.selectImgs", article);
	}

	public int ridGenerate() {
		return sqlSession.selectOne("cockInsert.generateMaxRid");
	}

	public int articleIdGenerate(int rid) {
		return sqlSession.selectOne("cockInsert.generateMaxAid", rid);
	}

	public int insertRest(RestVO rest) {
		return sqlSession.insert("cockInsert.insertRest", rest);
	}

	public int saveArticle(ArticleVO art) {
		return sqlSession.insert("cockInsert.replaceArticle", art);
	}

	public int saveImgs(ArticleVO art) {
		return sqlSession.insert("cockInsert.insertImgs", art);
	}
	
}
