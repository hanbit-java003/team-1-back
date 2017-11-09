package com.hanbit.cock.api.member.controller;

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

@RestController
@RequestMapping("/api/cock/member/")
public class CockMemberController {
	
	@Autowired
	private CockMemberService cockMemberService;
	
	@Autowired
	private MemberController memberController;
	
	@RequestMapping("/count/article")
	public int memberCountArticle(@RequestParam("uid") String uid) {
		return cockMemberService.memberCountArticle(uid);
	}
	
	@SignInRequired //로그인 했을때만 됨.
	@RequestMapping("/wrote")
	public List<CockMemberWroteVO> getMemberWrote(@RequestParam("uid") String uid) {
		
		return cockMemberService.getMemberWrote(uid);
	}
	
	
}
