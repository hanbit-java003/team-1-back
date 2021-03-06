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
import com.hanbit.cock.api.vo.MemberDetailVO;
import com.hanbit.cock.api.vo.MemberVO;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	

	@Autowired
	private MemberService memberService;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	 
/*	@RequestMapping("/emailCheck")
	public Map emailCheck(@RequestParam("email") String email) {
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(email);
		
		memberService.emailCheck(memberVO);
		
		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}*/
	
	@RequestMapping("/nickCheck")
	public Map nickCheck(@RequestParam("nick") String nick) {
		MemberVO memberVO = new MemberVO();
		memberVO.setNick(nick);
		
		memberService.nickCheck(memberVO);
		
		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}

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
			cookie.setMaxAge(6000); // 초 단위 숫자값
			response.addCookie(cookie);
		}

		// session 범위동안.. 사용할 수 있다? 너무 많이 있으면 안된다.
		session.setAttribute(CockConstants.SIGNIN_KEY, true);
		session.setAttribute("uid", memberVO.getUid());
		session.setAttribute("email", memberVO.getEmail());
		session.setAttribute("nick", memberVO.getNick());
		session.setAttribute("master", memberVO.getMaster());
		session.setAttribute("bann", memberVO.getBann());
		
		if(memberVO.getDetail() != null) {// detail이 null이 아닐 경우에만 실행.
		session.setAttribute("avatar", memberVO.getDetail().getAvatar());
		}


		Map result = new HashMap();
		result.put("email", memberVO.getEmail());
		result.put("nick", memberVO.getNick());
		result.put("bann", memberVO.getBann());
		result.put("master", memberVO.getMaster());
		
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
			member.put("nick", session.getAttribute("nick"));
			member.put("email", session.getAttribute("email"));
			member.put("avatar", session.getAttribute("avatar"));
			member.put("uid", session.getAttribute("uid"));
			member.put("master", session.getAttribute("master"));
			member.put("bann", session.getAttribute("bann"));
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
		
		String uid = (String) session.getAttribute("uid");
		
		MemberVO memberVO = objectMapper.readValue(json, MemberVO.class);
		memberVO.setUid(uid);
		
		memberService.saveMemberDetail(memberVO, image);
		
		memberVO = memberService.getMemberDetail(uid);

		session.setAttribute("nick", memberVO.getNick());
		
		if(memberVO.getDetail() != null) {// detail이 null이 아닐 경우에만 실행.
			session.setAttribute("avatar", memberVO.getDetail().getAvatar());
		}

		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}
	
	// 회원탈퇴시 비밀번호 확인.
	@SignInRequired
	@PostMapping("/bann/pw")
	public Map bannPasswordVall(@RequestParam("currentPw") String currentPw,
			HttpSession session){
		
		String uid = (String) session.getAttribute("uid");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUid(uid);
		memberVO.setCurrentPw(currentPw);
		
		memberService.bannPasswordVall(memberVO);
		
		
		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}
	
	// 회원탈퇴단
	@SignInRequired
	@RequestMapping("/bann")
	public Map bannMember(@RequestParam("bann") String bann,
			HttpSession session){
		String uid = (String) session.getAttribute("uid");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUid(uid);
		memberVO.setBann(bann);
		
		memberService.bannMember(memberVO);
		
		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}
	
	// sns로그인
	@PostMapping("/snsSignin")
	public Map snsSignin(@RequestParam("nick") String nick,
			@RequestParam("avatar") String avatar,
			@RequestParam("uid") String uid,
			HttpSession session) {
		MemberVO memberVO = new MemberVO();
		memberVO.setNick(nick);
		memberVO.setDetail(new MemberDetailVO());
		memberVO.getDetail().setAvatar(avatar);
		memberVO.setUid(uid);
		
		session.setAttribute(CockConstants.SIGNIN_KEY, true);
		session.setAttribute("nick", memberVO.getNick());
		session.setAttribute("avatar", memberVO.getDetail().getAvatar());
		session.setAttribute("uid", memberVO.getUid());
		
		System.out.println(nick + avatar);
		
		Map result = new HashMap<>();
		result.put("nick", memberVO.getNick());
		result.put("avatar", memberVO.getDetail().getAvatar());
		result.put("uid", memberVO.getUid());
		
		
		return result;
	}
	



}

