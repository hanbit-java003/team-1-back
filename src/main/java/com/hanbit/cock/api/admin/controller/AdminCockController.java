package com.hanbit.cock.api.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.admin.service.AdminCockService;
import com.hanbit.cock.api.admin.vo.AdminArticleVO;
import com.hanbit.cock.api.admin.vo.AdminMemberVO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@RestController
@RequestMapping("/api/cock/admin")
public class AdminCockController {
	
	@Autowired
	private AdminCockService adminCockService;	
	
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
	@RequestMapping("/rest/save")
	public Map saveAdminRestDetail(@RequestParam("rid") int rid, @RequestParam("address") String address, @RequestParam("phone") String phone,
			@RequestParam("operating") String operating, @RequestParam("signature") String signature) {
		
		RestDetailVO restDetailVO = new RestDetailVO();
		restDetailVO.setRid(rid);
		restDetailVO.setAddress(address);
		restDetailVO.setPhone(phone);
		restDetailVO.setOperating(operating);
		restDetailVO.setSignature(signature);
		
		adminCockService.saveAdminRestDetail(restDetailVO);
		
		Map result = new HashMap();
		result.put("status", "ok");
		return result;	
				
	}
	
	// 맛집 추가 입력사항 수정
	@RequestMapping("/rest/edit")
	public Map modifyAdminRestDetail(@RequestParam("rid") int rid, @RequestParam("address") String address, @RequestParam("phone") String phone,
			@RequestParam("operating") String operating, @RequestParam("signature") String signature) {
		
		RestDetailVO restDetailVO = new RestDetailVO();		
		restDetailVO.setAddress(address);
		restDetailVO.setPhone(phone);
		restDetailVO.setOperating(operating);
		restDetailVO.setSignature(signature);
		
		adminCockService.modifyAdminRestDetail(restDetailVO);
		
		Map result = new HashMap();
		result.put("status", "ok");
		return result;	
				
	}
	
	// 맛집 기본 & 추가 입력사항 삭제
	@DeleteMapping("/rest/{rid}")
	public Map deleteAdminRest(@PathVariable("rid") int rid) {
		adminCockService.deleteAdminRest(rid);
		
		Map result = new HashMap();
		result.put("status", "ok");
		
		return result;
	}
	
	// 게시글 관리 리스트
	@RequestMapping("/article")	
	public List<AdminArticleVO> listAdminArticle(@RequestParam(value="page", defaultValue="1") int page) {
		return adminCockService.listAdminArticle(page);
	}
	
	// 회원 관리 리스트
	@RequestMapping("/member")
	public List<AdminMemberVO> listAdminMember() {
		return adminCockService.listAdminMember();
	}
	
}
