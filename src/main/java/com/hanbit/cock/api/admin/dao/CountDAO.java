package com.hanbit.cock.api.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectCountRest() {
		return sqlSession.selectOne("cockAdmin.selectCountRest");
	}
	
	public int selectCountArticle() {
		return sqlSession.selectOne("cockAdmin.selectCountArticle");
	}
	
	public int selectCountMember() {
		return sqlSession.selectOne("cockAdmin.selectCountMember");
	}

	public int selectCountAlertArticle() {
		return sqlSession.selectOne("cockAdmin.selectCountAlertArticle");
	}

}
