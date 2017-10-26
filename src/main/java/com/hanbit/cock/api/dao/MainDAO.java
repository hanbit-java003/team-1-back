package com.hanbit.cock.api.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.vo.MainVO;
import com.hanbit.cock.api.vo.TagVO;
import com.hanbit.cock.api.vo.TopFourVO;

@Repository
public class MainDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<MainVO> selectLatestCockRest() {
		return sqlSession.selectList("cockMain.selectLatestRest");
	}
	
	public List<MainVO> selectArticleCockRest() {
		return sqlSession.selectList("cockMain.selectArticleRest");
	}
	
	public List<TagVO> selectRestTags() {
		return sqlSession.selectList("cockMain.selectTags");
	}
	
	public List<TopFourVO> selectTopFour() {
		return sqlSession.selectList("cockMain.selectTopFour");
	}
	
}
