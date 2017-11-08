package com.hanbit.cock.api.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.cock.api.admin.service.AdminCockService;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@RestController
@RequestMapping("/api/cock/admin")
public class AdminCockController {
	
	@Autowired
	private AdminCockService adminCockService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping("/rest")
	public List<AdminRestVO> listAdminRest() {
		return adminCockService.listAdminRest();
	}
	
	@RequestMapping("/rest/{rid}")
	public List<AdminRestVO> listAdminRestEdit(@PathVariable("rid") int rid) {
		return adminCockService.listAdminRestEdit(rid);
	}
	
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
	
}
