package edu.berkeley.cs169.helloandroid;

import android.os.Bundle;
import android.os.AsyncTask;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
			
			// Create JSON dictionary for username and password
			JSONObject credentials = new JSONObject();
			credentials.put("user", username);
			credentials.put("password", password);
			StringEntity credEntity = new StringEntity(credentials.toString());
			
			httpPost.setEntity(credEntity);
			httpPost.addHeader("Content-type", "application/json");
	
			// Parse the response from the server for an errCode and count
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			StringBuilder builder = new StringBuilder();
			if (entity != null) {
				InputStream instream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
		        String line;
		        while ((line = reader.readLine()) != null) {
		          builder.append(line);
		        }
		        instream.close();
				JSONObject jsonResponse = new JSONObject(builder.toString());
				errCode = jsonResponse.getString("errCode");
				if (errCode.equals("1")) {
					count = jsonResponse.getString("count");
				}
			}
			int[] errCount = new int[]{Integer.parseInt(errCode), Integer.parseInt(count)};
			return errCount;
			
		} catch (Exception e) {
			return null;
		}
	}
	
}