package com.example.yamba;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


/** implement OnClickListener to be able to Catch clicks with the tweetButton**/
public class StatusActivity extends Activity implements OnClickListener{

	/** android XML Elements **/
	private EditText editStatus;
	private Button buttonTweet;
	private TextView textCount;
	private int defaultTextColor;
	
	/** Logfile parameters**/
	private static final String TAG = "StatusAtivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		/** link android xml elements with java elements here **/
		editStatus  = (EditText) findViewById(R.id.editStatus);
		buttonTweet = (Button) findViewById(R.id.buttonTweet);
		textCount   = (TextView) findViewById(R.id.textCount);
		
		/**At this view to the listener list of the tweetButton**/
		buttonTweet.setOnClickListener(this);
		
		/** set the default color = green for the textCount element **/
		defaultTextColor = textCount.getTextColors().getDefaultColor();
		
		/** implement the textChangeListener to editStatus **/
		editStatus.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				int count = 140 - editStatus.length();
				textCount.setText(Integer.toString(count));
				textCount.setTextColor(Color.GREEN);
				if(count < 10)
					textCount.setTextColor(Color.RED);
				else
					textCount.setTextColor(defaultTextColor);
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
		});
		
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
		
		/** push message to yamba **/
		new PostTask().execute(status);
	}
	
	/** implement threadlike class for yamba server communication features **/
	private final class PostTask extends
		AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			YambaClient yambaCloud = new YambaClient("student", "password");
			try{
				yambaCloud.postStatus(params[0]);
				return "Successfully posted";
			}catch (YambaClientException e){
				Log.e(e.toString(), e.toString());
				return "Failed to post to yamba servive";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
		}
		
		
		
	}
}
