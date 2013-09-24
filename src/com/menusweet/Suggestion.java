package com.menusweet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Suggestion {
	public static void main(String[] args) {
		Suggestion g = new Suggestion();
	}
	private Set<Item> menuitems;
	public Suggestion(){
		this.menuitems = new HashSet<Item>();
	}
	public Item getTopSuggestion(Set<Item> orders){
		return (Item) orders.toArray()[0];
	}
	
	
	
	private class OrderSet{
		private Set<Item> orderset;
		private List<OrderSet> nextorders;
		public OrderSet(Set<Item> orderset, List<OrderSet> nextorders){
			this.orderset = orderset;
			this.nextorders = nextorders;
		}
		public List<OrderSet> getSuccessors(){
			return nextorders;
		}
	}
	private class Item{
		private String itemname;
		private int price; 
		public Item(String itemname, int price){
			this.itemname = itemname;
			this.price = price;
		}
		public String getName(){
			return itemname;
		}
		public int getPrice(){
			return price;
		}
	}
}
