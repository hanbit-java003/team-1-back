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

@RestController
@RequestMapping("/api/cock/member/")
public class CockMemberController {
	
	@Autowired
	private CockMemberService cockMemberService;
	
	@Autowired
	private MemberController memberController;
	
	// 총 개수.
	@RequestMapping("/count/article")
	public int memberCountArticle(@RequestParam("uid") String uid) {
		return cockMemberService.memberCountArticle(uid);
	}
	
	@SignInRequired //로그인 했을때만 됨.
	@RequestMapping("/wrote")
	public List<CockMemberWroteVO> getMemberWrote(@RequestParam("uid") String uid) {
		
		return cockMemberService.getMemberWrote(uid);
	}
	
	//페이징 처리를 위한
	@RequestMapping("/total")
	public Map total(@RequestParam("uid") String uid) {
		int totalCount = cockMemberService.memberCountArticle(uid);
		
		Map result = new HashMap();
		result.put("total", totalCount);
		
		return result;
	}
	
	@RequestMapping("/list")
	public List<CockMemberWroteVO> getMemberWroteList(@RequestParam(value="page", defaultValue="1") int page){

		
		
		return cockMemberService.getMemberWroteList(page);
	}
	
	
}
