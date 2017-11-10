package com.hanbit.cock.api.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.member.vo.CockMemberWroteVO;
import com.hanbit.cock.api.member.vo.PageVO;
import com.hanbit.cock.api.member.vo.CockMemberBookmarkVO;

@Repository
public class CockMemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//등록된 게시글 수.
	public int selectMemberCountArticle(String uid) {
		return sqlSession.selectOne("cockMember.selectMemberCountArticle",uid);
	}
	
	//등록된 게시글 가져오기
/*	public List<CockMemberWroteVO> selectMemberWrote(String uid) {
		
		return sqlSession.selectList("cockMember.selectMemberWrote", uid);
	}*/
	
	//등록된 게시물 페이지 처리하기 위해
	public List<CockMemberWroteVO> selectMemberWroteList(String uid, int page) {
		int rowsPerPage = 10;
		int firstIndex = (page-1)*rowsPerPage; // 첫번째 페이지 구하는 방법
		
		PageVO pageVO = new PageVO();
		pageVO.setFirstIndex(firstIndex);
		pageVO.setRowsPerPage(rowsPerPage);
		pageVO.setUid(uid);
		
		return sqlSession.selectList("cockMember.selectMemberWroteList", pageVO);
		
	}
	
	//즐겨찾기 한 맛집 수 
	public int selectMemberCountBookmark(String uid) {
		return sqlSession.selectOne("cockMember.selectMemberCountBookmark",uid);
	}
	
	// 즐겨찾기 한 맛집 페이지 처리해서 가져오기
	public List<CockMemberBookmarkVO> selectMemberBookmarkList(String uid, int page) {
		int rowsPerPage = 10;
		int firstIndex = (page-1) * rowsPerPage; // 첫번째 페이지 구현..
		
		PageVO pageVO = new PageVO();
		pageVO.setFirstIndex(firstIndex);
		pageVO.setRowsPerPage(rowsPerPage);
		pageVO.setUid(uid);
		
		return sqlSession.selectList("cockMember.selectMemberBookmarkList", pageVO);
	}

}
