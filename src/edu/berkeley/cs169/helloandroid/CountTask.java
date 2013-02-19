package edu.berkeley.cs169.helloandroid;

import android.os.Bundle;
import android.os.AsyncTask;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;

import org.json.JSONObject;

import java.util.HashMap;
import java.io.InputStream;

public class CountTask extends AsyncTask<String, Void, int[]> {
	
	@Override
	protected int[] doInBackground(String...params) {
		
		try {
			String username = params[0];
			String password = params[1];
			String uri = params[2];
			String errCode = "0";
			String count = "0";
			
			// Setup HTTP Client and Post requests
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://damp-wave-7108.herokuapp.com/users/" + uri);
			
			// Create JSON dictionary for username and password, credentials checked in server
			HashMap<String, String> credMap = new HashMap<String, String>();
			credMap.put("user", username);
			credMap.put("password", password);
			JSONObject credentials = new JSONObject(credMap);
			StringEntity credJson = new StringEntity(credentials.toString());
			
			httpPost.setEntity(credJson);
	
			// Parse the response for an errCode and count
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				JSONObject jsonResponse = new JSONObject(instream); //need to turn the object from entity to json
				errCode = jsonResponse.getString("errCode");
				count = jsonResponse.getString("count");
			}
			int[] errCount = new int[]{Integer.parseInt(errCode), Integer.parseInt(count)};
			return errCount;
			
		} catch (Exception e) {
			return null;
			
		}
	}
	
}