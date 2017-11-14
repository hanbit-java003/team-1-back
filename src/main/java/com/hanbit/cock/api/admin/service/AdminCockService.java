package com.hanbit.cock.api.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.cock.api.admin.dao.AdminCockDAO;
import com.hanbit.cock.api.admin.vo.AdminArticleVO;
import com.hanbit.cock.api.admin.vo.AdminMemberVO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.vo.RestDetailVO;
import com.hanbit.cock.api.vo.RestVO;

@Service
public class AdminCockService {
	
	@Autowired
	private AdminCockDAO adminCockDAO;
	
	// 맛집 관리 리스트
	public List<AdminRestVO> listAdminRest() {
		return adminCockDAO.selectAdminRest();
	}
	
	// 맛집 수정 페이지
	public List<AdminRestVO> listAdminRestEdit(int rid) {
		return adminCockDAO.selectAdminRestEdit(rid);
	}
	
	// 맛집 추가 입력사항 저장
	public void saveAdminRestDetail(RestDetailVO restDetailVO) {
		adminCockDAO.insertAdminRestDetail(restDetailVO);
	}	
	
	// 맛집 추가 입력사항 수정
	public void modifyAdminRestDetail(RestDetailVO restDetailVO) {
		adminCockDAO.updateAdminRestDetail(restDetailVO);
	}
	
	// 맛집 기본 & 추가 입력사항 삭제
	@Transactional
	public void deleteAdminRest(int rid) {
		adminCockDAO.deleteAdminRestDetail(rid);
		adminCockDAO.deleteAdminRest(rid);
		
		System.out.println("삭제 완료");
	}
	
	// 게시글 관리 리스트
	public List<AdminArticleVO> listAdminArticle(int page) {
		return adminCockDAO.selectAdminArticle(page);
	}
	
	// 회원 관리 리스트
	public List<AdminMemberVO> listAdminMember() {
		return adminCockDAO.selectAdminMember();
	}

}
