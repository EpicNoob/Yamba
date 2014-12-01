package com.example.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


/** implement OnClickListener to be able to Catch clicks with the tweetButton**/
public class StatusActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//check if this activity was created before
		if(savedInstanceState == null){
			//create fragment
			StatusFragment fragment = new StatusFragment();
			getFragmentManager()
				.beginTransaction()
				.add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
				.commit();
			
		}
	}
}
