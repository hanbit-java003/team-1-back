package com.hanbit.cock.api.detail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.cock.api.detail.service.DetailService;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.DetailVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.LikeVO;
import com.hanbit.cock.api.vo.RestDetailVO;

@RestController
@RequestMapping("/api/cock/detail")
public class DetailController {

	@Autowired
	private DetailService detailService;

	@RequestMapping("/{rid}/{likes}")
	public DetailVO getRest(@PathVariable(value = "rid") int rid,
							@PathVariable(value = "likes") boolean likes) {
		return detailService.getRest(rid, likes);
	}
	
	@RequestMapping("/info/{rid}")
	public RestDetailVO getRestDetail(@PathVariable(value = "rid") int rid) {
		return detailService.getRestDetail(rid);
	}

	@DeleteMapping("/{rid}/{articleId}")
	public Map<String, Boolean> removeArticle(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId) {
		
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		
		detailService.removeArticle(article);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("ok", true);
		
		return result;
	}
	
	@RequestMapping("/likes/{rid}")
	public List<LikeVO> getLikes(@PathVariable("rid") int rid) {
		return detailService.getLikes(rid);
	}
	
	@RequestMapping("/inc/{rid}/{articleId}")
	public Map<String, Integer> increaseLikes(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId,
											  @RequestParam("uid") String uid) {
		
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article.setUid(uid);
		
		int likeCount = detailService.getLikeCount(article);
		detailService.increaseLikes(article);
		
		Map<String, Integer> result = new HashMap<>();
		result.put("like", likeCount);
		
		return result;
	}
	
	@RequestMapping("/dec/{rid}/{articleId}")
	public Map<String, Integer> decreaseLikes(@PathVariable("rid") int rid,
											  @PathVariable("articleId") int articleId,
											  @RequestParam("uid") String uid) {
		
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article.setUid(uid);

		int likeCount = detailService.getLikeCount(article);
		detailService.decreaseLikes(article);
		
		Map<String, Integer> result = new HashMap<>();
		result.put("like", likeCount);
		
		return result;
	}
	
	@RequestMapping("/img/{rid}/{articleId}")
	public List<ImgVO> getImg(@PathVariable("rid") int rid,
			  			@PathVariable("articleId") int articleId) {
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);

		return detailService.getImgs(article);
	}
}
