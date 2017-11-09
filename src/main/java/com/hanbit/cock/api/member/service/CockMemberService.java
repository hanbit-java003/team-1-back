package com.hanbit.cock.api.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.member.dao.CockMemberDAO;
import com.hanbit.cock.api.member.vo.CockMemberWroteVO;

@Service
public class CockMemberService {
	
	@Autowired
	private CockMemberDAO cockMemberDAO;
	
	//등록된 게시글 수.
	public int memberCountArticle(String uid) {
		
		return cockMemberDAO.selectMemberCountArticle(uid);
	}
	
	// 등록된 게시글 가져오기
	public List<CockMemberWroteVO> getMemberWrote(String uid) {
		
		return cockMemberDAO.selectMemberWrote(uid);
	}
	
	public List<CockMemberWroteVO> getMemberWroteList(int page) {
		
		return cockMemberDAO.selectMemberWroteList(page);
	}

}
