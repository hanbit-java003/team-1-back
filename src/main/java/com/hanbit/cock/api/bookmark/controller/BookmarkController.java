package com.hanbit.cock.api.bookmark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/tags")
	public List<BookmarkTagsVO> pageBookmarkTags() {

		return bookmarkService.pageBookmarkTags();
	}

	@RequestMapping("/review")
	public List<BookmarkReviewVO> pageBookmarkReview() {

		return bookmarkService.pageBookmarkReview();
	}

	@RequestMapping("/list")
	public List<BookmarkVO> bookmarkList(HttpSession session) {
		String uid = (String) session.getAttribute("uid");

		return bookmarkService.bookmarkList(uid);
	}

	@RequestMapping("/save")
	public Map saveBookmarkList(@RequestParam("rid") int rid, HttpSession session) {
		String uid = (String) session.getAttribute("uid");

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setRid(rid);
		bookmarkVO.setUid(uid);

		bookmarkService.saveBookmarkList(bookmarkVO);

		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}

	@RequestMapping("/delete")
	public Map deleteBookmarkList(@RequestParam("rid") int rid, HttpSession session) {
		String uid = (String) session.getAttribute("uid");

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setRid(rid);
		bookmarkVO.setUid(uid);

		bookmarkService.deleteBookmarkList(bookmarkVO);

		Map result = new HashMap();
		result.put("status", "ok");

		return result;
	}

}
