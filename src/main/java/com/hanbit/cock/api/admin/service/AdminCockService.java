package com.hanbit.cock.api.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.admin.dao.AdminCockDAO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;

@Service
public class AdminCockService {
	
	@Autowired
	private AdminCockDAO adminRestDAO;
	
	public List<AdminRestVO> listAdminRest() {
		return adminRestDAO.selectAdminRest();
	}

}
