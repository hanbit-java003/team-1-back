package com.hanbit.cock.api.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.admin.vo.AdminAlertArticleVO;
import com.hanbit.cock.api.admin.vo.AdminArticleVO;
import com.hanbit.cock.api.admin.vo.AdminMemberVO;
import com.hanbit.cock.api.admin.vo.AdminPageVO;
import com.hanbit.cock.api.admin.vo.AdminRestVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@Repository
public class AdminCockDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 맛집 관리 리스트
	public List<AdminRestVO> selectAdminRest() {
		return sqlSession.selectList("cockAdmin.selectAdminRest");
	}
	
	// 맛집 수정 페이지
	public List<AdminRestVO> selectAdminRestEdit(int rid) {
		return sqlSession.selectList("cockAdmin.selectAdminRestEdit", rid);
	}
	
	// 맛집 추가 입력사항 저장
	public int insertAdminRestDetail(RestDetailVO restDetailVO) {
		return sqlSession.insert("cockAdmin.insertAdminRestDetail", restDetailVO);
	}
	
	// 맛집 추가 입력사항 수정
	public int updateAdminRestDetail(RestDetailVO restDetailVO) {
		return sqlSession.update("cockAdmin.updateAdminRestDetail", restDetailVO);
	}
	
	// 맛집 기본 입력사항 삭제 (cc_rest)
	public int deleteAdminRest(int rid) {
		return sqlSession.delete("cockAdmin.deleteAdminRest", rid);
	}
	
	// 맛집 추가 입력사항 삭제 (cc_rest_detail)
	public int deleteAdminRestDetail(int rid) {
		return sqlSession.delete("cockAdmin.deleteAdminRestDetail", rid);
	}
	
	// 게시글 관리 리스트
	public List<AdminArticleVO> selectAdminArticle(int page) {
		int rowsPerPage = 20;
		int firstIndex = (page - 1) * rowsPerPage;
		
		AdminPageVO adminPageVO = new AdminPageVO();
		adminPageVO.setFirstIndex(firstIndex);
		adminPageVO.setRowsPerPage(rowsPerPage);
		
		return sqlSession.selectList("cockAdmin.selectAdminArticle", adminPageVO);
	}
	
	// 메뉴 삭제
	public int deleteAdminMenus(AdminArticleVO adminArticleVO) {
		return sqlSession.delete("cockAdmin.deleteAdminMenus", adminArticleVO);
	}
	
	// 이미지 삭제
	public int deleteAdminImgs(AdminArticleVO adminArticleVO) {
		return sqlSession.delete("cockAdmin.deleteAdminImgs", adminArticleVO);
	}
	
	// 태그 삭제
	public int deleteAdminTags(AdminArticleVO adminArticleVO) {
		return sqlSession.delete("cockAdmin.deleteAdminTags", adminArticleVO);
	}
	
	// 게시글 삭제
	public int deleteAdminArticle(AdminArticleVO adminArticleVO) {
		return sqlSession.delete("cockAdmin.deleteAdminArticle", adminArticleVO);
	}
	
	// 회원 관리 리스트
	public List<AdminMemberVO> selectAdminMember() {
		return sqlSession.selectList("cockAdmin.selectAdminMember");
	}

	// 신고 기사 게시물 접수 리스트
	public List<AdminAlertArticleVO> selectAdminAlertArticle(int page) {
		int rowsPerPage = 20;
		int firstIndex = (page - 1) * rowsPerPage;
		
		AdminPageVO adminPageVO = new AdminPageVO();
		adminPageVO.setFirstIndex(firstIndex);
		adminPageVO.setRowsPerPage(rowsPerPage);
		
		return sqlSession.selectList("cockAdmin.selectAdminAlertArticle", adminPageVO);
	}

	public int selectMaxAAID(AdminAlertArticleVO aaa) {
		return sqlSession.selectOne("cockAdmin.getMaxAAID", aaa);
	}
	
	public int insertAdminAlertArticle(AdminAlertArticleVO aaa) {
		return sqlSession.insert("cockAdmin.insertAdminAlertArticle", aaa);
	}

}
