package com.hanbit.cock.api.bookmark.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.cock.api.bookmark.vo.BookmarkReviewVO;
import com.hanbit.cock.api.bookmark.vo.BookmarkTagsVO;
import com.hanbit.cock.api.bookmark.vo.BookmarkVO;

@Repository
public class BookmarkDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<BookmarkVO> selectBookmark(String uid) {
		return sqlSession.selectList("cockBookmark.selectBookmark", uid);
	}

	public List<BookmarkTagsVO> selectBookmarkTags() {
		return sqlSession.selectList("cockBookmark.selectBookmarkTags");
	}

	public List<BookmarkReviewVO> selectBookmarkReview() {
		return sqlSession.selectList("cockBookmark.selectBookmarkReview");
	}

	public List<BookmarkVO> selectBookmarkList(String uid) {
		return sqlSession.selectList("cockBookmark.selectBookmarkList", uid);
	}

	public int insertBookmarkList(BookmarkVO bookmarkVO) {
		return sqlSession.insert("cockBookmark.insertBookmarkList", bookmarkVO);
	}

	public int deleteBookmarkList(BookmarkVO bookmarkVO) {
		return sqlSession.delete("cockBookmark.deleteBookmarkList", bookmarkVO);
	}

}
