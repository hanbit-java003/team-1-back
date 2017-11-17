package com.hanbit.cock.api.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.cock.api.admin.service.AdminCockService;
import com.hanbit.cock.api.admin.vo.AdminAlertArticleVO;
import com.hanbit.cock.api.admin.vo.AdminArticleVO;
import com.hanbit.cock.api.admin.vo.AdminMemberVO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.annotation.SignInRequired;
import com.hanbit.cock.api.service.FileService;
import com.hanbit.cock.api.vo.RestDetailVO;

@RestController
@RequestMapping("/api/cock/admin")
public class AdminCockController {
	
	@Autowired
	private AdminCockService adminCockService;
	
	@Autowired
	private FileService fileService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	// 맛집 관리 리스트
	@RequestMapping("/rest")
	public List<AdminRestVO> listAdminRest() {
		return adminCockService.listAdminRest();
	}
	
	// 맛집 수정 페이지
	@RequestMapping("/rest/{rid}")
	public List<AdminRestVO> listAdminRestEdit(@PathVariable("rid") int rid) {
		return adminCockService.listAdminRestEdit(rid);
	}
	
	// 맛집 추가 입력사항 저장
	@PostMapping("/rest/save")
	public Map saveAdminRestDetail(@RequestParam("json") String json, @RequestParam("photo") MultipartFile photo) throws Exception {		
		RestDetailVO restDetailVO = mapper.readValue(json, RestDetailVO.class);
		
		adminCockService.saveAdminRestDetail(restDetailVO, photo);		
		
		Map result = new HashMap();
		result.put("status", "ok");
		
		return result;	
				
	}
	
	// 맛집 추가 입력사항 수정
	@PostMapping("/rest/edit/{rid}")
	public Map modifyAdminRestDetail(@PathVariable("rid") int rid, MultipartHttpServletRequest request) throws Exception {		
		String json = request.getParameter("json");
		
		RestDetailVO restDetailVO = mapper.readValue(json, RestDetailVO.class);
		
		MultipartFile photo = request.getFile("photo");
		
		adminCockService.modifyAdminRestDetail(restDetailVO, photo);
		
		Map result = new HashMap();
		result.put("status", "ok");
		
		return result;	
				
	}
	
	// 맛집 기본 & 추가 입력사항 삭제
	@DeleteMapping("/{rid}")
	public Map removeAdminRest(@PathVariable("rid") int rid) {
		adminCockService.removeAdminRest(rid);
		
		Map result = new HashMap();
		result.put("ok", true);
		
		return result;
	}
	
	// 게시글 관리 리스트
	@RequestMapping("/article")	
	public List<AdminArticleVO> listAdminArticle(@RequestParam(value="page", defaultValue="1") int page) {
		return adminCockService.listAdminArticle(page);
	}
	
	// 게시글 삭제
	@DeleteMapping("/{rid}/{articleId}")
	public Map removeAdminArticle(@PathVariable("rid") int rid, @PathVariable("articleId") int articleId) {
		
		AdminArticleVO adminArticleVO = new AdminArticleVO();
		adminArticleVO.setRid(rid);
		adminArticleVO.setArticleId(articleId);		
		
		adminCockService.removeAdminArticle(adminArticleVO);	
		
		Map result = new HashMap();
		result.put("ok", true);
		
		return result;
	}
	
	// 회원 관리 리스트
	@RequestMapping("/member")
	public List<AdminMemberVO> listAdminMember() {
		return adminCockService.listAdminMember();
	}
	
	// 게시글 신고 페이지
	@RequestMapping("/alert/articles")
	public List<AdminAlertArticleVO> getListAlertArticles(@RequestParam(value="page", defaultValue="1") int page) {
		return adminCockService.listAdminAlertArticle(page);
	}
	
	// 게시글 신고 접수 페이지
	@SignInRequired
	@PostMapping("/alert/article/insert")
	public int saveAlertArticle(@RequestBody AdminAlertArticleVO data) throws Exception {
		return adminCockService.saveAlertArticle(data);
	}
	
}
