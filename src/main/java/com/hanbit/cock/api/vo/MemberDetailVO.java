package com.hanbit.cock.api.vo;

public class MemberDetailVO {

	private String phone;
	private String info;
	private String avatar;
	private String food;
	private MemberDetailFeat feat;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	
	public MemberDetailFeat getFeat() {
		return feat;
	}
	public void setFeat(MemberDetailFeat feat) {
		this.feat = feat;
	}
	
}
