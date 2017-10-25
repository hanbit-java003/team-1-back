package com.hanbit.cock.api.emblem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.emblem.dao.CockEmblemDAO;
import com.hanbit.cock.api.emblem.vo.EmblemAchiveVO;

@Service
public class CockEmblemService {

	@Autowired
	private CockEmblemDAO cockEmblemDAO;
	
	public EmblemAchiveVO getEmblem(String uid) {
		EmblemAchiveVO emblemAchive = new EmblemAchiveVO();
		emblemAchive.setUid(uid);
		emblemAchive.setEmblems(cockEmblemDAO.selectUidEmblem(uid));
		
		return emblemAchive;
	}

}
