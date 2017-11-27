package com.hanbit.cock.api.admin.vo;

public class AdminAlertArticleVO {

	// aaid, rid, article_id, uid, report, status
	private int no;
	private int aaid;
	private int rid;
	private int articleId;
	private String uid;
	private String report;
	private String status;
	
	public int getAaid() {
		return aaid;
	}
	public void setAaid(int aaid) {
		this.aaid = aaid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}
