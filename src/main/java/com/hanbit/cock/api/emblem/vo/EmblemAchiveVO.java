package com.hanbit.cock.api.emblem.vo;

import java.util.List;

public class EmblemAchiveVO {
	private String uid;
	private List<EmblemVO> emblems;
	private int registRest;
	private int registArticle;
	private int eatNoodle;
	private int eatMeat;
	private int eatChicken;
	
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
	public int getEatNoodle() {
		return eatNoodle;
	}
	public void setEatNoodle(int eatNoodle) {
		this.eatNoodle = eatNoodle;
	}
	public int getEatMeat() {
		return eatMeat;
	}
	public void setEatMeat(int eatMeat) {
		this.eatMeat = eatMeat;
	}
	public int getEatChicken() {
		return eatChicken;
	}
	public void setEatChicken(int eatChicken) {
		this.eatChicken = eatChicken;
	}
	public int getRegistRest() {
		return registRest;
	}
	public void setRegistRest(int registRest) {
		this.registRest = registRest;
	}
	public int getRegistArticle() {
		return registArticle;
	}
	public void setRegistArticle(int registArticle) {
		this.registArticle = registArticle;
	}
}
