package com.menusweet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LogOrder {
	private Context context;
	private JSONArray orderlog;
	private String filename = "order_json.json";
	private String url = "http://menusweet.com/orderUpdate.php"; //sample url. doesnt actually work lol
	private final int TIMEOUT_MILLISEC = 5000;

	public LogOrder(Context context) {
		this.context = context;
	}

	public boolean logOrder(UserCart cart){
		JSONArray orders_json = readJSON(filename);
		JSONObject order_json = orderToJSON(cart.getList());
		orders_json.put(order_json);
		return saveJSON(orders_json);
	}
	
	// returns JSONObject of the order based on UserCart
	public JSONObject orderToJSON(List<CartItem> listorders) {
		// probably sort it somehow by price, category, or alphabetically
		// java.util.Collections.
		// CartItem[] arrorders = listorders.toArray();
		// sort(arrorders)
		JSONObject order = new JSONObject();
		JSONArray orderarr = new JSONArray();
		try {
			for (CartItem c : listorders) {
				orderarr.put(itemToJSON(c));
			}
			order.put("Orders", orderarr);
			order.put("Time", getDateTime());
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}

	private JSONObject itemToJSON(CartItem c) throws JSONException {
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

	private boolean saveJSON(JSONArray jsonarr){
		try{
			FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(jsonarr.toString().getBytes());
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private JSONArray readJSON(String filename) {
		JSONArray jsonarr;
		try {
			InputStream inputStream = context.openFileInput(filename);

			int sizeofjsonfile = inputStream.available();

			byte[] bytes = new byte[sizeofjsonfile];

			// reading data into the array from the file
			inputStream.read(bytes);

			// close the input stream
			inputStream.close();

			String jsonstring = new String(bytes, "UTF-8");
			jsonarr = new JSONArray(jsonstring);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return jsonarr;
	}
	
	public boolean syncWithServer(JSONArray orders){
		new sendOperation().execute(orders);
		return true;
	}	
	
	private class sendOperation extends AsyncTask<JSONArray, Void, String>{
		@Override
		protected String doInBackground(JSONArray... params) {
			sendOrders(params[0]);
			return null;
		}
	}
	private void sendOrders(JSONArray order){
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy);
				
		String result = "";
	    try {
	        HttpParams httpParams = new BasicHttpParams();
	        HttpConnectionParams.setConnectionTimeout(httpParams,TIMEOUT_MILLISEC);
	        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	        HttpClient client = new DefaultHttpClient(httpParams);

	        HttpPost request = new HttpPost(url);
	        request.setEntity(new ByteArrayEntity(order.toString().getBytes("UTF8")));
	        
	        //request.setHeader("json", json.toString());
	        HttpResponse response = client.execute(request);
	        
	        Log.d("Logs","Executed http request");
	        
	        HttpEntity entity = response.getEntity();
	        
	        // Prints out the response from the url in LogCat
	        if (entity != null) {
	            InputStream is = entity.getContent();
	            result = input(is);
	            Log.d("Log", result);
	        }
	        
	    } catch (Throwable t) {
	        Toast.makeText(context, "Request failed: " + t.toString(), Toast.LENGTH_LONG).show();
	    }
	    
	}
	
	private String input(InputStream is){
		//convert response to string
		String result = "";
		try{
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		            sb.append(line + "\n");
		    }
		    is.close();

		    result=sb.toString();
		}catch(Exception e){
		    Log.e("log_tag", "Error converting result "+e.toString());
		}

		return result;
	}

	
}