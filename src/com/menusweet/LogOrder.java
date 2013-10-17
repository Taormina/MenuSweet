package com.menusweet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogOrder {
	private JSONObject orderlog;
	public LogOrder(){
	}
	//returns true if successfully made log of orders
	public boolean logOrder(UserCart cart){
		List<CartItem> listorders = cart.getList();
		//probably sort it somehow by price, category, or alphabetically
			//java.util.Collections.
			//CartItem[] arrorders = listorders.toArray();
			//sort(arrorders)
		JSONObject order = new JSONObject();
		JSONArray orderarr = new JSONArray();
		try {
			for(CartItem c : listorders){
				orderarr.put(createJSON(c));
			}
			order.put("Orders", orderarr);
			order.put("Time", getDateTime());
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		
		orderlog = order;
		
		return true;
	}
	
	private JSONObject createJSON(CartItem c) throws JSONException{
		JSONObject json = new JSONObject();
		json.put("Name", c.getItem().getName());
       	json.put("Price", c.getItem().getPrice());
        json.put("Quantity", c.getQuantity());
		return json;
	}

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}