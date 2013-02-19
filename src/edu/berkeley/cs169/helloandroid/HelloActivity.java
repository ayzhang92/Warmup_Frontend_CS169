package edu.berkeley.cs169.helloandroid;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.content.Intent;
import android.widget.EditText;
import android.os.AsyncTask;

public class HelloActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "edu.berkeley.cs169.helloandroid.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_hello, menu);
        return true;
    }
    
    /** Called when user clicks the Login button **/
    public void login(View view) {
    	displayMessage("login");
    }
    
    /** Called when user clicks the Add User button **/
    public void addUser(View view) {
    	displayMessage("add");
    }
    
    public void displayMessage(String uri) {
    	int errCode = 0;
    	int count = 0;
    	String message;
    	
    	// Creates an Intent to display the results of clicking the login button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	// Retrieves the username and password
    	EditText editTextUser = (EditText) findViewById(R.id.user_message);
    	EditText editTextPassword = (EditText) findViewById(R.id.password_message);
    	String username = editTextUser.getText().toString();
    	String password = editTextPassword.getText().toString();
    	
    	// Uses an AsyncTask to access the server and check the login info, retrieves errCode and count values
    	AsyncTask<String, Void, int[]> countTask = new CountTask().execute(username, password, uri);
    	try {
    		int[] codeCount = countTask.get();
    		if (codeCount != null) {
        		errCode = codeCount[0];
        		count = codeCount[1];
        	}
    	} catch (Exception e) {
    		errCode = -5;
    	}
    	
    	// Chooses the message to be displayed based on the errCode and count retrieved from the AsyncTask
    	if (errCode == 1 && uri == "login") {
    		message = "Welcome back " + username + "!\n You have logged in " + Integer.toString(count) + " times.";
    	} else if (errCode == 1 && uri == "add") {
    		message = "Welcome " + username + "!\n You have logged in 1 time."; 
    	} else if (errCode == -1){
    		message = "Invalid username and password combination. Please try again.";
    	} else if (errCode == -2) {
    		message = "The user name should not be empty. Please try again.";
    	} else if (errCode == -3) {
    		message = "The user name should not be empty or more than 128 characters. Please try again.";
    	} else if (errCode == -4) {
    		message = "The password should not be longer than 128 characters. Please try again.";
    	} else if (errCode == -5) {
    		message = "Something went wrong with the AsyncTask";
    	} else {
    		message = "Unknown error: " + Integer.toString(errCode);
    	}
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}
