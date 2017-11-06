package com.hanbit.cock.api.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.admin.vo.AdminRestVO;

@Repository
public class AdminCockDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<AdminRestVO> selectAdminRest() {
		return sqlSession.selectList("cockAdmin.selectAdminRest");
	}

}
