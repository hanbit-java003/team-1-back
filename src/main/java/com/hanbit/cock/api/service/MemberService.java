package com.hanbit.cock.api.service;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.cock.api.dao.MemberDAO;
import com.hanbit.cock.api.emblem.dao.CockEmblemDAO;
import com.hanbit.cock.api.vo.MemberVO;
import com.hanbit.cock.api.exception.CockException;
import com.hanbit.cock.api.service.FileService;
import com.hanbit.cock.api.vo.FileVO;

@Service
public class MemberService {

	// 중복확인을 위한 일련번호라고 생각하면 된다. uid 유니크아이디.
	private static final char[] CHARS
	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private FileService fileService;

	@Autowired
	private CockEmblemDAO cockEmblemDAO;

	// E-mail 중복확인
	public void emailCheck(MemberVO memberVO) {
		// countMember가 0보다 크면 있는거다. 중복확인..
		if(memberDAO.countMember(memberVO.getEmail()) > 0) {
			// exception을 발생 시켜준다.
			throw new CockException("이미 가입된 이메일 입니다.");
		}
	}

	// 별명 중복확인 버튼.
	public void nickCheck(MemberVO memberVO) { 
		// countNick 이 0보다 크면 있는거.
		if(memberDAO.countNick(memberVO.getNick())> 0) {
			// exception을 발생 시켜준다.
			throw new CockException("이미 중복된 별명 입니다.");
		}
	}

	@Transactional
	public void signUp(MemberVO memberVO){

		// countMember가 0보다 크면 있는거다. 중복확인..
		if(memberDAO.countMember(memberVO.getEmail()) > 0) {
			// exception을 발생 시켜준다.
			throw new CockException("이미 가입된 이메일 입니다.");
		}
		// countNick 이 0보다 크면 있는거.
		if(memberDAO.countNick(memberVO.getNick())> 0) {
			// exception을 발생 시켜준다.
			throw new CockException("이미 중복된 별명 입니다.");
		}

		// 중복확인을 위해서 .
		memberVO.setUid(generateUid());

		//패스워드 암호화
		String encodedPassword = passwordEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(encodedPassword);

		memberDAO.insertMember(memberVO);

		// 엠블럼 릴레이션 생성
		cockEmblemDAO.signUpEmblemId(memberVO.getUid());

	}


	private String generateUid() {
		//62^12 거의 안겹친다고 보면된다. 
		int length = 12;
		// uid 배열에다가 length를 넣을 것이다.
		char[] uid = new char[length];
		// Random 이란 함수는.. 숫자형을 최대치까지 랜덤으로? 돌리는 녀석?
		Random random = new Random();

		for (int i=0; i<length; i++) {
			// 1.배열을 만들고
			// 최대값 int를 해서 0부터 시작하는 랜덤 숫자가 나온다.random.nextInt(CHARS.length)
			uid[i] = CHARS[random.nextInt(CHARS.length)];
		}

		// 2.배열만든것을 리턴하면되는데..
		return new String(uid);
	}

	public MemberVO signIn(String email, String password) {
		MemberVO memberVO = memberDAO.selectMember(email);

		if (memberVO == null) {
			throw new CockException("가입되지 않은 이메일입니다.");
		}

		if(!passwordEncoder.matches(password, memberVO.getPassword())){
			throw new CockException("잘못된 비밀번호 입니다.");
		}

		return memberVO;
	}

	public MemberVO getMemberDetail(String uid) {

		return memberDAO.selectMemberDetail(uid); 
	}
	@Transactional // 여러개의 DAO를 실행해야되기 때문에
	public void saveMemberDetail(MemberVO memberVO,MultipartFile image)
			throws IOException{
		if(image != null) { // 파일이 있으면
			FileVO fileVO = new FileVO();
			// 파일 ID 셋팅?
			String fileId = "avatar-" + memberVO.getUid();
			fileVO.setFileId(fileId);
			// 파일확장자 구함.
			String fileExt = FilenameUtils.getExtension(image.getOriginalFilename());
			String fileName = memberVO.getUid() + "." + fileExt;
			fileVO.setFileName(fileName);
			fileVO.setFilePath("/hanbit/webpack/cock-front/src/img/avatars/" + fileName);

			fileVO.setContentType(image.getContentType());
			fileVO.setContentLength(image.getSize());
			// 사진저장
			fileService.modifyFile(fileVO, image.getInputStream(), 200); // 폭을 200으로 줄여주겠다.
			memberVO.getDetail().setAvatar("/api/file/" + fileId);
		}

		memberDAO.insertMemberDetail(memberVO);
		memberDAO.updatetNick(memberVO);


		// 패스워드.
		if(StringUtils.isNotBlank(memberVO.getCurrentPw())) {
			changePassword(memberVO.getUid(), 
					memberVO.getCurrentPw(), memberVO.getPassword());
		}

	}

	private void changePassword(String uid, String currentPw, String newPw){
		String encodedPw = memberDAO.selectPassword(uid);// 암호화된 패스워드

		if (!passwordEncoder.matches(currentPw, encodedPw)) {
			throw new CockException("현재 패스워드가 일치하지 않습니다.");
		}

		// 패스워드를 암호화 하기 위해서 만들어줌.		
		String password = passwordEncoder.encode(newPw);

		MemberVO memberVO = new MemberVO();
		memberVO.setUid(uid);
		memberVO.setPassword(password); // 이녀석은 암호화 해줘야한다.

		memberDAO.updatePassword(memberVO);

	}
	
	private void bannPassword(String uid, String currentPw){
		String encodedPw = memberDAO.selectPassword(uid);// 암호화된 패스워드

		if (!passwordEncoder.matches(currentPw, encodedPw)) {
			throw new CockException("비밀번호가 일치하지 않습니다. 확인해주세요!");
		}
	}
	
	public void bannPasswordVall(MemberVO memberVO){
		
		// 패스워드.
		if(StringUtils.isNotBlank(memberVO.getCurrentPw())) {
			bannPassword(memberVO.getUid(), 
					memberVO.getCurrentPw());
			
		}
	}
	
	//회원탈퇴단.
	public int bannMember(MemberVO memberVO) {

		return memberDAO.deleteMember(memberVO);
	}

}
