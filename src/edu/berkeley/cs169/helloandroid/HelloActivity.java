package edu.berkeley.cs169.helloandroid;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.content.Intent;
import android.widget.EditText;

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
    	int count = 0; //need to change later
    	String message;
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editTextUser = (EditText) findViewById(R.id.user_message);
    	EditText editTextPassword = (EditText) findViewById(R.id.password_message);
    	String username = editTextUser.getText().toString();
    	String password = editTextPassword.getText().toString();
    	//Check username and password in database, get the count and return
    	if (count == 1) {
    		message = "Welcome back " + username + "!\n You have logged in " + Integer.toString(count) + " times.";
    	} else if (count == -1){
    		message = "Invalid username and password combination. Please try again.";
    	} else {
    		message = "Unknown error: " + Integer.toString(count);
    	}
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    /** Called when user clicks the Add User button **/
    public void addUser(View view) {
    	int count = 0; //need to change that for later
    	String message;
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editTextUser = (EditText) findViewById(R.id.user_message);
    	EditText editTextPassword = (EditText) findViewById(R.id.password_message);
    	String username = editTextUser.getText().toString();
    	String password = editTextPassword.getText().toString();
    	// send username and password to database
    	if (count == 1) {
    		message = "Welcome " + username + "!\n You have logged in 1 time."; 
    	} else if (count == -2) {
    		message = "The user name should not be empty. Please try again.";
    	} else if (count == -3) {
    		message = "The user name should not be empty or more than 128 characters. Please try again.";
    	} else if (count == -4) {
    		message = "The password should not be longer than 128 characters. Please try again.";
    	} else {
    		message = "Unknown error: " + Integer.toString(count);
    	}
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}
