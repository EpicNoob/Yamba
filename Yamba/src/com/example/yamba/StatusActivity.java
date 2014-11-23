package com.example.yamba;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;


/** implement OnClickListener to be able to Catch clicks with the tweetButton**/
public class StatusActivity extends Activity implements OnClickListener{

	/** android XML Elements **/
	private EditText editStatus;
	private Button buttonTweet;
	
	/** Logfile parameters**/
	private static final String TAG = "StatusAtivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		/** link android xml elements with java elements here **/
		editStatus = (EditText) findViewById(R.id.editStatus);
		buttonTweet = (Button) findViewById(R.id.buttonTweet);
		
		/**At this view to the listener list of the tweetButton**/
		buttonTweet.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		/**what should happen when someone pushes the tweetButton is coded here**/
		String status = editStatus.getText().toString();
		Log.d(TAG, "onClicked with Status: " + status);
	}
}
