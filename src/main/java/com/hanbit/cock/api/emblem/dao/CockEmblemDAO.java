package com.hanbit.cock.api.emblem.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.emblem.vo.EmblemVO;

@Repository
public class CockEmblemDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<EmblemVO> selectUidEmblem(String uid) {
		return sqlSession.selectList("emblem.selectUidEmblem", uid);
	}

}
