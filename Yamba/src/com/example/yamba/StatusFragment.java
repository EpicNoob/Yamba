package com.example.yamba;


import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusFragment extends Fragment implements OnClickListener {
	
	/** android XML Elements **/
	private EditText editStatus;
	private Button buttonTweet;
	private TextView textCount;
	private int defaultTextColor;
	
	public static final String TAG = "StatusFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater //this has changed compared to StatusActivity
				.inflate(R.layout.fragment_status, container, false); //fragment_status is an exact copy of activity_status
		
		/** link android xml elements with java elements here **/
		editStatus  = (EditText) view.findViewById(R.id.editStatus); //and we have to refer to the view
		buttonTweet = (Button) view.findViewById(R.id.buttonTweet);
		textCount   = (TextView) view.findViewById(R.id.textCount);
		
		/**At this view to the listener list of the tweetButton**/
		buttonTweet.setOnClickListener(this);
		
		/** set the default color = green for the textCount element **/
		defaultTextColor = textCount.getTextColors().getDefaultColor();
		
		/** implement the textChangeListener to editStatus **/
		editStatus.addTextChangedListener(new TextWatcher() { //innerclass example
			
			@Override
			public void afterTextChanged(Editable s) {
				int count = 140 - editStatus.length();
				textCount.setText(Integer.toString(count));
				textCount.setTextColor(Color.GREEN);
				if(count < 10)
					textCount.setTextColor(Color.RED);
				else
					textCount.setTextColor(defaultTextColor); //default Color because it depends in the system and setting which is used
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
		});
		//we need also to return the view
		return view;
		
	}

	@Override
	public void onClick(View v) {
		/**what should happen when someone pushes the tweetButton is coded here**/
		String status = editStatus.getText().toString();
		Log.d(TAG, "onClicked with Status: " + status);
		
		/** push message to yamba **/
		new PostTask().execute(status);
	}
	
	/** implement threadlike class for yamba server communication features **/
	protected final class PostTask extends //class in class example
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
			Toast.makeText(StatusFragment.this.getActivity(), result, Toast.LENGTH_LONG).show(); //this line has changed aswell
		}
		
		
		
	}

}
