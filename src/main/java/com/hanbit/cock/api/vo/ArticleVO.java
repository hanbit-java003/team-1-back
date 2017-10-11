package com.hanbit.cock.api.vo;

import java.util.List;

// article_id, rid, comment, status, likes, hate, write_dt
public class ArticleVO {
	private int articleId;
	private int rid;
	private String comment;
	private String status;
	private int likes;
	private int hate;
	private String writeDt;
	private List<ImgVO> imgs;
	
	public List<ImgVO> getImgs() {
		return imgs;
	}
	public void setImgs(List<ImgVO> imgs) {
		this.imgs = imgs;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
	public String getWriteDt() {
		return writeDt;
	}
	public void setWriteDt(String writeDt) {
		this.writeDt = writeDt;
	}
}
