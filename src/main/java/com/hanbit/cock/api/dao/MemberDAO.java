package com.hanbit.cock.api.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.vo.MemberVO;

@Repository
public class MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 회원추가
	public int insertMember(MemberVO memberVO) {
		return sqlSession.insert("member.insertMember",memberVO);
	}
	
	// 중복 email 확인
	public int countMember(String email) {
		return sqlSession.selectOne("member.countMember", email);
	}
	// 중복 nick 확인 
	public int countNick(String nick) {
		return sqlSession.selectOne("member.countNick", nick);
	}
	
	// detail(회원수정) 페이지
	public MemberVO selectMember(String email){
		
		return sqlSession.selectOne("member.selectMember", email);
	}
	
	// detail(회원수정) 페이지
	public MemberVO selectMemberDetail(String uid) {
		
		return sqlSession.selectOne("member.selectMemberDetail", uid);
	}
	
	// detail(회원수정) 페이지 수정단.
	public int insertMemberDetail(MemberVO memberVO) {
		return sqlSession.insert("member.insertMemberDetail",memberVO);
	}
	
	// detail(회원수정) nick 수정.
	public int updatetNick(MemberVO memberVO) {
		return sqlSession.update("member.updateNick",memberVO);
	}
	
	// 비밀번호 찾아오기.
	public String selectPassword(String uid) {
		
		return sqlSession.selectOne("member.selectPassword", uid);
	}
	
	// 비밀번호 수정.
	public int updatePassword(MemberVO memberVO) {
		
		return sqlSession.update("member.updatePassword", memberVO);
	}
	
	// 회원탈퇴
	public int deleteMember(MemberVO memberVO) {
		
		return sqlSession.update("member.deleteMember", memberVO);
	}
	
	
}
