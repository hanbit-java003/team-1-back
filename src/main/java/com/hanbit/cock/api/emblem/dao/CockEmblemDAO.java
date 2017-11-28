package com.hanbit.cock.api.emblem.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.data.DataCollection;
import com.hanbit.cock.api.emblem.vo.EmblemAchiveVO;
import com.hanbit.cock.api.emblem.vo.EmblemVO;

@Repository
public class CockEmblemDAO {

	@Autowired
	private SqlSession sqlSession;

	public EmblemAchiveVO selectEmblem(EmblemAchiveVO emblemAchive) {
		return sqlSession.selectOne("emblem.selectEmblem", emblemAchive);
	}
	
	public List<EmblemVO> selectUidEmblem(String uid) {
		return sqlSession.selectList("emblem.selectUidEmblem", uid);
	}

	public int signUpEmblemId(String uid) {
		return sqlSession.insert("emblem.insertEmblemId", uid);
	}
	
	public int updateArticleCount(DataCollection collection) {
		sqlSession.update("emblem.updateArticleCount", collection);
		return selectCollection(collection);
	}
	
	public int selectCollection(DataCollection collection) {
		return sqlSession.selectOne("emblem.selectCollection", collection);
	}
	
	public int increaseCollection(DataCollection collection) {
		sqlSession.update("emblem.increaseCollection", collection);
		return selectCollection(collection);
	}
	
	public int insertCollection(DataCollection collection) {
		return sqlSession.insert("emblem.insertCollection", collection);
	}

	public int decreaseCollection(DataCollection collection) {
		sqlSession.update("emblem.decreaseCollection", collection);
		return selectCollection(collection);
	}
}
