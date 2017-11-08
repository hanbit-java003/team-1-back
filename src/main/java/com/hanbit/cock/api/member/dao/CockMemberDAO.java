package com.hanbit.cock.api.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.member.vo.CockMemberWroteVO;

@Repository
public class CockMemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//등록된 게시글 수.
	public int selectMemberCountArticle(String uid) {
		return sqlSession.selectOne("cockMember.selectMemberCountArticle",uid);
	}
	
	//등록된 게시글 가져오기
	public List<CockMemberWroteVO> selectMemberWrote(String uid) {
		
		return sqlSession.selectList("cockMember.selectMemberWrote", uid);
	}

}
