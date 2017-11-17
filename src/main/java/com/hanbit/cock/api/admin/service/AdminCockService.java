package com.hanbit.cock.api.admin.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.cock.api.admin.dao.AdminCockDAO;
import com.hanbit.cock.api.admin.vo.AdminAlertArticleVO;
import com.hanbit.cock.api.admin.vo.AdminArticleVO;
import com.hanbit.cock.api.admin.vo.AdminMemberVO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.service.FileService;
import com.hanbit.cock.api.vo.FileVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@Service
public class AdminCockService {
	
	@Autowired
	private AdminCockDAO adminCockDAO;
	
	@Autowired
	private FileService fileService;
	
	// 맛집 관리 리스트
	public List<AdminRestVO> listAdminRest() {
		return adminCockDAO.selectAdminRest();
	}
	
	// 맛집 수정 페이지
	public List<AdminRestVO> listAdminRestEdit(int rid) {
		return adminCockDAO.selectAdminRestEdit(rid);
	}
	
	// 맛집 추가 입력사항 저장
	@Transactional
	public void saveAdminRestDetail(RestDetailVO restDetailVO, MultipartFile photo) {
		String photoFileId = "photo-rest-" + restDetailVO.getRid();		
		
		restDetailVO.setPhoto("/api/file/" + photoFileId);		
		
		adminCockDAO.insertAdminRestDetail(restDetailVO);
		
		FileVO fileVO = new FileVO();
		
		String fileExt = FilenameUtils.getExtension(photo.getOriginalFilename());
		String fileName = photoFileId + "." + fileExt;
		
		fileVO.setFileId(photoFileId);
		fileVO.setFilePath("/hanbit/webpack/cock-front/src/img/rest-photo/" + fileName);
		fileVO.setContentType(photo.getContentType());
		fileVO.setContentLength(photo.getSize());
		fileVO.setFileName(fileName);
		
		try {
			fileService.addFile(fileVO, photo.getInputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}			
	}	
	
	// 맛집 추가 입력사항 수정
	@Transactional
	public void modifyAdminRestDetail(RestDetailVO restDetailVO, MultipartFile photo) {
		String photoFileId = "photo-rest-" + restDetailVO.getRid();
		
		if (photo != null) {
			restDetailVO.setPhoto("/api/file/" + photoFileId);
		}
		
		adminCockDAO.updateAdminRestDetail(restDetailVO);
		
		if (photo != null) {
			FileVO fileVO = new FileVO();		
			
			String fileExt = FilenameUtils.getExtension(photo.getOriginalFilename());
			String fileName = photoFileId + "." + fileExt;
			
			fileVO.setFileId(photoFileId);
			fileVO.setFilePath("/hanbit/webpack/cock-front/src/img/rest-photo/" + fileName);			
			fileVO.setContentType(photo.getContentType());
			fileVO.setContentLength(photo.getSize());
			fileVO.setFileName(fileName);
			
			try {
				fileService.modifyFile(fileVO, photo.getInputStream());
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}			
		}
	}
	
	// 맛집 기본 & 추가 입력사항 삭제
	@Transactional
	public void removeAdminRest(int rid) {
		adminCockDAO.deleteAdminRestDetail(rid);
		adminCockDAO.deleteAdminRest(rid);
		
		System.out.println("삭제 완료");
	}
	
	// 게시글 관리 리스트
	public List<AdminArticleVO> listAdminArticle(int page) {
		return adminCockDAO.selectAdminArticle(page);
	}
	
	// 게시글 삭제
	@Transactional
	public void removeAdminArticle(AdminArticleVO adminArticleVO) {
		adminCockDAO.deleteAdminMenus(adminArticleVO);
		adminCockDAO.deleteAdminImgs(adminArticleVO);
		adminCockDAO.deleteAdminTags(adminArticleVO);
		adminCockDAO.deleteAdminArticle(adminArticleVO);
		
		System.out.println("삭제 완료");
	}
	
	// 회원 관리 리스트
	public List<AdminMemberVO> listAdminMember() {
		return adminCockDAO.selectAdminMember();
	}

	// 신고 계시글 관련 리스트
	public List<AdminAlertArticleVO> listAdminAlertArticle(int page) {
		return adminCockDAO.selectAdminAlertArticle(page);
	}

	public int saveAlertArticle(AdminAlertArticleVO aaa) {
		aaa.setAaid(adminCockDAO.selectMaxAAID(aaa));
		return adminCockDAO.insertAdminAlertArticle(aaa);
	}

}
