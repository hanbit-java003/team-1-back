package com.hanbit.cock.api.bookmark.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.bookmark.service.BookmarkService;
import com.hanbit.cock.api.bookmark.vo.BookmarkReviewVO;
import com.hanbit.cock.api.bookmark.vo.BookmarkTagsVO;
import com.hanbit.cock.api.bookmark.vo.BookmarkVO;

@RestController
@RequestMapping("/api/cock/bookmark")
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;

	@RequestMapping("/bookmarks")
	public List<BookmarkVO> pageBookmark(HttpSession session) {
		String uid = (String) session.getAttribute("uid");

		return bookmarkService.pageBookmark(uid);
	}

	@RequestMapping("/bookmarkTags")
	public List<BookmarkTagsVO> pageBookmarkTags() {

		return bookmarkService.pageBookmarkTags();
	}

	@RequestMapping("/bookmarkReview")
	public List<BookmarkReviewVO> pageBookmarkReview() {

		return bookmarkService.pageBookmarkReview();
	}
}
