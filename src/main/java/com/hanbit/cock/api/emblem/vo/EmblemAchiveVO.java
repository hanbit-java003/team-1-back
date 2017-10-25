package com.hanbit.cock.api.emblem.vo;

import java.util.List;

public class EmblemAchiveVO {
	private String uid;
	private List<EmblemVO> emblems;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<EmblemVO> getEmblems() {
		return emblems;
	}
	public void setEmblems(List<EmblemVO> emblems) {
		this.emblems = emblems;
	}
}
