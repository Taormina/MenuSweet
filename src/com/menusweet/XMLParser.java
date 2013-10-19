package com.menusweet;

import java.util.ArrayList;
import java.util.List;

public class XMLParser {
	public static void main(String[] args) {
		XMLParser g = new XMLParser();
	}
	private List<Item> menuitems;
	public XMLParser(){
		//List of all menu items
		this.menuitems = new ArrayList<Item>();
	}
	public Item getTopSuggestion(UserCart uc){
		List orderlist = uc.getList();
		return (Item) orderlist.toArray()[0];
	}
}
