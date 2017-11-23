package com.hanbit.cock.api.bookmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.cock.api.bookmark.dao.BookmarkDAO;
import com.hanbit.cock.api.bookmark.vo.BookmarkReviewVO;
import com.hanbit.cock.api.bookmark.vo.BookmarkTagsVO;
import com.hanbit.cock.api.bookmark.vo.BookmarkVO;

@Service
public class BookmarkService {

	@Autowired
	private BookmarkDAO bookmarkDAO;

	public List<BookmarkVO> pageBookmark(String uid) {
		return bookmarkDAO.selectBookmark(uid);
	}

	public List<BookmarkTagsVO> pageBookmarkTags() {
		return bookmarkDAO.selectBookmarkTags();
	}

	public List<BookmarkReviewVO> pageBookmarkReview() {
		return bookmarkDAO.selectBookmarkReview();
	}

	public List<BookmarkVO> bookmarkList(String uid) {
		return bookmarkDAO.selectBookmarkList(uid);
	}

	public int saveBookmarkList(BookmarkVO bookmarkVO) {
		return bookmarkDAO.insertBookmarkList(bookmarkVO);
	}

	public int deleteBookmarkList(BookmarkVO bookmarkVO) {
		return bookmarkDAO.deleteBookmarkList(bookmarkVO);
	}
}
