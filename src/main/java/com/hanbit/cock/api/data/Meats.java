package com.hanbit.cock.api.data;

public class Meats extends DataCollection{
	String[] meat = {"고기", "meat", "바베큐", "육", "살", "베이컨",
					 "스테이크", "steak", "소세지", "곱창", "함박",
					 "돼지", "우"};
	String title = "meat";
	String attribute = "eat_meat";
	
	public Meats() {
		super.collection = this.meat;
		super.title = this.title;
		super.attribute = this.attribute;
	}
}
