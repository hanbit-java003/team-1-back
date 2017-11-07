package com.hanbit.cock.api.data;

public class Noodles extends DataCollection{
	String[] noodle = {"면", "noodle", "국수", "우동", "누들",
					   "파스타", "라멘", "수제비", "소바", "스파게티"};
	String title = "noodle";
	String attribute = "eat_noodle";
	
	public Noodles() {
		super.collection = this.noodle;
		super.title = this.title;
		super.attribute = this.attribute;
	}
}
