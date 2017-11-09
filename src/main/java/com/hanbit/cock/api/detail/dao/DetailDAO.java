package com.hanbit.cock.api.detail.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.emblem.vo.EmblemVO;
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
	
	public List<ArticleVO> selectArticlesByLikes(Integer rid) {
		return sqlSession.selectList("cockDetail.selectArticlesByLikes", rid);
	}

	public List<MenuVO> selectMenus(ImgVO img) {
		return sqlSession.selectList("cockDetail.selectMenus", img);
	}

	public List<TagVO> selectTags(ArticleVO article) {
		return sqlSession.selectList("cockDetail.selectTags", article);
	}

	public List<ImgVO> selectImgs(ArticleVO article) {
		return sqlSession.selectList("cockDetail.selectImgs", article);
	}

	public List<EmblemVO> selectEmblems(ArticleVO article) {
		return sqlSession.selectList("cockDetail.selectEmblems", article);
	}

	public int deleteArticle(ArticleVO article) {
		return sqlSession.delete("cockDetail.deleteArticle", article);
	}

	public int deleteMenus(ArticleVO article) {
		return sqlSession.delete("cockDetail.deleteMenus", article);
	}

	public int deleteTags(ArticleVO article) {
		return sqlSession.delete("cockDetail.deleteTags", article);
	}

	public int deleteImgs(ArticleVO article) {
		return sqlSession.delete("cockDetail.deleteImgs", article);
	}
	
	public int deleteRest(int rid) {
		return sqlSession.delete("cockDetail.deleteRest", rid);
	}

	public int deleteRestDetail(int rid) {
		return sqlSession.delete("cockDetail.deleteRestDetail", rid);
	}

	public int updateLikesIncrease(ArticleVO article) {
		return sqlSession.update("cockDetail.increaseLikes", article);
	}

	public int updateLikesDecrease(ArticleVO article) {
		return sqlSession.update("cockDetail.decreaseLikes", article);
	}

}
