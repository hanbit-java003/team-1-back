package com.hanbit.cock.api.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@Repository
public class AdminCockDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<AdminRestVO> selectAdminRest() {
		return sqlSession.selectList("cockAdmin.selectAdminRest");
	}
	
	public List<AdminRestVO> selectAdminRestEdit(int rid) {
		return sqlSession.selectList("cockAdmin.selectAdminRestEdit", rid);
	}
	
	public int insertAdminRestDetail(RestDetailVO restDetailVO) {
		return sqlSession.insert("cockAdmin.insertAdminRestDetail", restDetailVO);
	}
	
	public int updateAdminRestDetail(RestDetailVO restDetailVO) {
		return sqlSession.update("cockAdmin.updateAdminRestDetail", restDetailVO);
	}

}
