package com.hanbit.cock.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.cock.api.CockConstants;
import com.hanbit.cock.api.annotation.SignInRequired;
import com.hanbit.cock.api.service.MemberService;
import com.hanbit.cock.api.vo.MemberVO;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	// 보안상 Post를 사용한다.
	@PostMapping("/signup")
	public Map signUp(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("nick") String nick){

		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(email);
		memberVO.setPassword(password);
		memberVO.setNick(nick);

		memberService.signUp(memberVO);

		Map result = new HashMap();
		result.put("status", "ok");

		return result;

	}

	@PostMapping("/signin")
	public Map signIn(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("remember") boolean remember,
			HttpSession session,
			HttpServletResponse response){

		//System.out.println(remember);

		MemberVO memberVO = memberService.signIn(email, password);

		logger.debug(email + " has signed in.");

		if (remember) {
			Cookie cookie = new Cookie("rid", "1234");
			cookie.setMaxAge(600); // 초 단위 숫자값
			response.addCookie(cookie);
		}

		// session 범위동안.. 사용할 수 있다? 너무 많이 있으면 안된다.
		session.setAttribute(CockConstants.SIGNIN_KEY, true);
		session.setAttribute("uid", memberVO.getUid());
		session.setAttribute("email", memberVO.getEmail());
		
		if(memberVO.getDetail() != null) {// detail이 null이 아닐 경우에만 실행.
		session.setAttribute("avatar", memberVO.getDetail().getAvatar());
		}


		Map result = new HashMap();
		result.put("email", memberVO.getEmail());

		return result;

	}

	@RequestMapping("/get")
	public Map getMember(HttpSession session) { // session 에서 읽어옴.

		Map member = new HashMap();

		if(session.getAttribute(CockConstants.SIGNIN_KEY) == null) {
			member.put(CockConstants.SIGNIN_KEY, false);
		}
		else {
			member.put(CockConstants.SIGNIN_KEY, true);
			member.put("email", session.getAttribute("email"));
			member.put("avatar", session.getAttribute("avatar"));
		}

		return member;

	}

	@RequestMapping("/signout")
	public Map signOut(HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response){

		session.invalidate(); // 로그아웃. 현재 session 을 폐기.

		Cookie cookie =WebUtils.getCookie(request, "rid");

		if (cookie != null) {
			cookie.setValue(null);
			response.addCookie(cookie);
		}

		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}

	@SignInRequired  // 로그인 했을때만 되게끔.. 
	@RequestMapping("/detail")
	public MemberVO getMemberDetail(HttpSession session) {//uid를 session에서 꺼내오기로 함.
		String uid =(String) session.getAttribute("uid");

		return memberService.getMemberDetail(uid);
	}

	@SignInRequired
	@PostMapping("/save")
	public Map saveMemberDetail(@RequestParam("member") String json,
			@RequestParam(value= "avatar", required=false) MultipartFile image,
			HttpSession session) throws Exception {
		
		MemberVO memberVO = objectMapper.readValue(json, MemberVO.class);
		memberVO.setUid((String) session.getAttribute("uid"));
		
		memberService.saveMemberDetail(memberVO, image);


		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}



}

