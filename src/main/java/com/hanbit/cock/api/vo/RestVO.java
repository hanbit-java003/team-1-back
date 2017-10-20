package com.hanbit.cock.api.vo;

import java.util.List;

public class RestVO {

	private Integer rid;
	private String name;
	private double lat;
	private double lng;
	private String status;
	private List<ArticleVO> articles;
	private List<MenuVO> menus;
	private List<TagVO> tags;
	private List<ImgVO> imgs;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<ArticleVO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleVO> articles) {
		this.articles = articles;
	}

	public List<MenuVO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuVO> menus) {
		this.menus = menus;
	}

	public List<TagVO> getTags() {
		return tags;
	}

	public void setTags(List<TagVO> tags) {
		this.tags = tags;
	}

	public List<ImgVO> getImgs() {
		return imgs;
	}

	public void setImgs(List<ImgVO> imgs) {
		this.imgs = imgs;
	}

}
