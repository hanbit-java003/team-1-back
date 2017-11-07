package com.hanbit.cock.api.data;

public class Chickens extends DataCollection{
	String[] chicken = {"치킨", "chicken", "닭", "윙", "카라아게", "탄두리",
						"후라이드", "백숙"};
	String title = "chicken";
	String attribute = "eat_chicken";
	
	public Chickens() {
		super.collection = this.chicken;
		super.title = this.title;
		super.attribute = this.attribute;
	}
	
}
