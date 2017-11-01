package com.hanbit.cock.api.emblem.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.data.DataCollection;
import com.hanbit.cock.api.emblem.vo.EmblemVO;

@Repository
public class CockEmblemDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<EmblemVO> selectUidEmblem(String uid) {
		return sqlSession.selectList("emblem.selectUidEmblem", uid);
	}

	public int signUpEmblemId(String uid) {
		return sqlSession.insert("emblem.insertEmblemId", uid);
	}
	
	public int increaseRestCount(String uid) {
		sqlSession.update("emblem.increaseRestCount", uid);
		return selectRestCount(uid);
	}
	
	public int selectRestCount(String uid) {
		return sqlSession.selectOne("emblem.selectInsertRestCount", uid);
	}
	
	public int achiveFirstRest(String uid) {
		return sqlSession.insert("emblem.insertFirstRest", uid);
	}

	public int achiveHundredRest(String uid) {
		return sqlSession.insert("emblem.insertHundredRest", uid);
	}

	public int increaseCollection(DataCollection collection) {
		sqlSession.update("emblem.increaseCollection", collection);
		return selectCollection(collection);
	}
	
	public int selectCollection(DataCollection collection) {
		return sqlSession.selectOne("emblem.selectCollection", collection);
	}
	
	public int insertCollection(DataCollection collection) {
		return sqlSession.insert("emblem.insertCollection", collection);
	}
}
