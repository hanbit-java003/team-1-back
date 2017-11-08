package com.hanbit.cock.api.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.admin.dao.AdminCockDAO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@Service
public class AdminCockService {
	
	@Autowired
	private AdminCockDAO adminCockDAO;
	
	public List<AdminRestVO> listAdminRest() {
		return adminCockDAO.selectAdminRest();
	}
	
	public List<AdminRestVO> listAdminRestEdit(int rid) {
		return adminCockDAO.selectAdminRestEdit(rid);
	}
	
	public void saveAdminRestDetail(RestDetailVO restDetailVO) {
		adminCockDAO.insertAdminRestDetail(restDetailVO);
	}
	
	public void modifyAdminRestDetail(RestDetailVO restDetailVO) {
		adminCockDAO.updateAdminRestDetail(restDetailVO);
	}
	
	public void deleteAdminRest(int rid) {		
		adminCockDAO.deleteAdminRest(rid);
	}

}
