package com.hanbit.cock.api.vo;

import com.hanbit.cock.api.vo.MemberDetailVO;

public class MemberVO {

	private String uid;
	private String email;
	private String nick;
	private String currentPw;  // 현재
	private String password;  // new
	private String createDt;
	private String createdBy;
	private String food;
	private MemberDetailVO detail; 
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getCurrentPw() {
		return currentPw;
	}
	public void setCurrentPw(String currentPw) {
		this.currentPw = currentPw;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MemberDetailVO getDetail() {
		return detail;
	}
	public void setDetail(MemberDetailVO detail) {
		this.detail = detail;
	}
	
}
