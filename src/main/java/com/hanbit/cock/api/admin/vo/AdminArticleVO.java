package com.hanbit.cock.api.admin.vo;

public class AdminArticleVO {
	
	private int no;
	private int articleId;
	private int rid;
	private String comment;
	private int likes;
	private int hate;
	private String writeDt;
	private String uid;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}	

}
