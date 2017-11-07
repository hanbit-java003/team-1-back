package com.hanbit.cock.api.search.vo;

public class SearchRestVO {
	private int rid;
	private String name;
	private int articleCnt;
	private int favoCnt;
	private int likeCnt;
	private double lat;
	private double lng;
	private String status;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArticleCnt() {
		return articleCnt;
	}
	public void setArticleCnt(int articleCnt) {
		this.articleCnt = articleCnt;
	}
	public int getFavoCnt() {
		return favoCnt;
	}
	public void setFavoCnt(int favoCnt) {
		this.favoCnt = favoCnt;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}