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
    
    /** Called when user clicks the Send button **/
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}
