package com.hanbit.cock.api.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.annotation.SignInRequired;
import com.hanbit.cock.api.controller.MemberController;
import com.hanbit.cock.api.member.service.CockMemberService;
import com.hanbit.cock.api.member.vo.CockMemberWroteVO;
import com.hanbit.cock.api.member.vo.PageVO;


import com.hanbit.cock.api.member.vo.CockBookmarkVO;
import com.hanbit.cock.api.member.vo.CockMemberBookmarkVO;

@RestController
@RequestMapping("/api/cock/member/")
public class CockMemberController {
	
	@Autowired
	private CockMemberService cockMemberService;
	
	@Autowired
	private MemberController memberController;
	
	// 내가 쓴 글 총 개수.
	@RequestMapping("/count/article")
	public int memberCountArticle(@RequestParam("uid") String uid) {
		return cockMemberService.memberCountArticle(uid);
	}
	
/*	@SignInRequired //로그인 했을때만 됨.
	@RequestMapping("/wrote")
	public List<CockMemberWroteVO> getMemberWrote(@RequestParam("uid") String uid) {
		
		return cockMemberService.getMemberWrote(uid);
	}*/
	
	// 내가 쓴 글 페이징 처리를 위한
	@RequestMapping("/total/article")
	public Map totalArticle(@RequestParam("uid") String uid) {
		int totalCount = cockMemberService.memberCountArticle(uid);
		
		Map result = new HashMap();
		result.put("total", totalCount);
		
		return result;
	}
	
	// 내가 등록한 맛집 페이징 처리해서 불러옴.
	@RequestMapping("/wrote/list")
	public List<CockMemberWroteVO> getMemberWroteList(
			@RequestParam(value="page", defaultValue="1") int page,
			HttpSession session) {

		String uid = (String) session.getAttribute("uid");
		
		return cockMemberService.getMemberWroteList(uid, page);
	}
	
	//즐겨찾기 한 맛집 수
	@RequestMapping("/count/bookmark")
	public int memberCountBookmark(HttpSession session){
		String uid = (String) session.getAttribute("uid");
		
		return cockMemberService.memberCountBookmark(uid);
	}
	
	// 즐겨찾기 한 맛집 페이징 처리를 위해서 total
	@RequestMapping("/total/bookmark")
	public Map totalBookmark(HttpSession session){
		String uid = (String) session.getAttribute("uid");
		
		int totalCount = cockMemberService.memberCountBookmark(uid);
		
		Map result = new HashMap();
		result.put("total", totalCount);
		
		return result;
		
	}
	
	// 즐겨찾기 한 맛집 페이징 처리 하여 가져오기.
	@RequestMapping("/bookmark/list")
	public List<CockMemberBookmarkVO> getMemberBookmarkList(
			@RequestParam(value="page", defaultValue="1") int page,
			HttpSession session){
		
		String uid = (String) session.getAttribute("uid");
		
		return cockMemberService.getMemberBookmarkList(uid, page);
		
	}
	
	// 즐겨찾기 메인 색깔.
	@RequestMapping("/bookmark")
	public List<CockBookmarkVO> getBookmark(HttpSession session) {
		String uid = (String) session.getAttribute("uid");
		
		CockBookmarkVO bookmarkVO = new CockBookmarkVO();
		
		bookmarkVO.setUid(uid);
		
		return cockMemberService.getBookmark(uid);
	}
	
	// 맛집 즐겨찾기
	@RequestMapping("/bookmark/save")
	public Map saveBookmark(@RequestParam("rid") int rid,
			HttpSession session){
		String uid = (String) session.getAttribute("uid");
		
		CockBookmarkVO bookmarkVO = new CockBookmarkVO();
		bookmarkVO.setRid(rid);
		bookmarkVO.setUid(uid);
		
		cockMemberService.saveBookmark(bookmarkVO);
		
		Map result = new HashMap();
		result.put("status", "ok");
		
		return result;
		
	}
	
	
}
