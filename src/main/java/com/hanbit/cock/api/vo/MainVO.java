package com.hanbit.cock.api.vo;

public class MainVO {
	
	private Integer rid;
	private String name;
	private double lat;
	private double lng;
	private String tags;
	private String img;
	private Integer count;
	private String writeDt;
	
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
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getWriteDt() {
		return writeDt;
	}
	public void setWriteDt(String writeDt) {
		this.writeDt = writeDt;
	}	

}
