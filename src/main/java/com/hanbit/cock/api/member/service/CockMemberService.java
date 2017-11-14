package com.hanbit.cock.api.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.member.dao.CockMemberDAO;
import com.hanbit.cock.api.member.vo.CockMemberWroteVO;
import com.hanbit.cock.api.member.vo.CockBookmarkVO;
import com.hanbit.cock.api.member.vo.CockMemberBookmarkVO;

@Service
public class CockMemberService {
	
	@Autowired
	private CockMemberDAO cockMemberDAO;
	
	//등록된 게시글 수.
	public int memberCountArticle(String uid) {
		
		return cockMemberDAO.selectMemberCountArticle(uid);
	}
	
	// 등록된 게시글 가져오기
/*	public List<CockMemberWroteVO> getMemberWrote(String uid) {
		
		return cockMemberDAO.selectMemberWrote(uid);
	}*/
	
	// 등록된 게시글 페이징 처리 하여 가져오기.
	public List<CockMemberWroteVO> getMemberWroteList(String uid, int page) {
		
		return cockMemberDAO.selectMemberWroteList(uid, page);
	}
	
	//즐겨찾기 한 맛집 수 .
	public int memberCountBookmark(String uid) {
		
		return cockMemberDAO.selectMemberCountBookmark(uid);
	}
	
	// 즐겨찾기 한 맛집 페이징 처리 하여 가져오기.
	public List<CockMemberBookmarkVO> getMemberBookmarkList(String uid, int page) {
		
		return cockMemberDAO.selectMemberBookmarkList(uid, page);
	}
	
	// 메인에 즐겨찾기 부분 카드에 뿌려주기.
	public List<CockBookmarkVO> getBookmark(String uid) {
		
		return cockMemberDAO.selectBookmark(uid);
	}

}
