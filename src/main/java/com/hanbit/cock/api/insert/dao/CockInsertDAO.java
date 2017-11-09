package com.hanbit.cock.api.insert.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.LocationVO;
import com.hanbit.cock.api.vo.MenuVO;
import com.hanbit.cock.api.vo.RestVO;
import com.hanbit.cock.api.vo.TagVO;

@Repository
public class CockInsertDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public RestVO selectRest(Integer rid) {
		return sqlSession.selectOne("cockInsert.selectRest", rid);
	}

	public List<ArticleVO> selectArticles(Integer rid) {
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

	public int articleIdGenerate(Integer rid) {
		return sqlSession.selectOne("cockInsert.generateMaxAid", rid);
	}

	public int insertRest(RestVO rest) {
		return sqlSession.insert("cockInsert.insertRest", rest);
	}
	
	public int modifyArticle(ArticleVO art) {
		return sqlSession.insert("cockInsert.replaceArticle", art);
	}

	public int removeMenus(ArticleVO article) {
		return sqlSession.delete("cockInsert.deleteMenus", article);
	}

	public int insertMenus(RestVO rest) {
		return sqlSession.insert("cockInsert.insertMenus", rest);
	}

	public int removeTags(ArticleVO article) {
		return sqlSession.delete("cockInsert.deleteTags", article);
	}

	public int insertTags(RestVO rest) {
		return sqlSession.insert("cockInsert.insertTags", rest);
	}

	public int removeImgs(ArticleVO art) {
		return sqlSession.delete("cockInsert.deleteImgs", art);
	}
	
	public int saveImgs(ArticleVO art) {
		return sqlSession.insert("cockInsert.insertImgs", art);

	}

	public List<LocationVO> selectLocations(LocationVO location) {
		return sqlSession.selectList("cockInsert.selectLocations", location);
	}

	public List<MenuVO> getMatchMenus(Map map) {
		return sqlSession.selectList("cockInsert.selectMatchMenus", map);
	}

	public int removeArticle(ArticleVO art) {
		return sqlSession.delete("cockInsert.deleteArticle", art);
	}
}
