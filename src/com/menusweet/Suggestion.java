package com.menusweet;

import java.util.ArrayList;
import java.util.List;

public class Suggestion {
	public static void main(String[] args) {
		Suggestion g = new Suggestion();
	}
	private List<Item> menuitems;
	public Suggestion(){
		//List of all menu items
		this.menuitems = new ArrayList<Item>();
	}
	public Item getTopSuggestion(UserCart uc){
		List orderlist = uc.getList();
		return (Item) orderlist.toArray()[0];
	}
}
