package com.cgit.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class WelcomeActivity extends Activity {
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        new BackgroundAsyncTask().execute();
    }
    public class BackgroundAsyncTask extends android.os.AsyncTask<Void,Boolean,Void>{

    	boolean myTextOn;
    	
		@Override
		protected Void doInBackground(Void... params) {
				myTextOn = !myTextOn;
				SystemClock.sleep(1000);
				publishProgress(myTextOn);  
				return null; 
		}

		/**
		 * 
		 */
		@Override
		protected void onPostExecute(Void result) {
			
		}

		/**
		 * 
		 */
		@Override
		protected void onPreExecute() {
			myTextOn = false;
		}

		/**
		 * 
		 */
		@Override
		protected void onProgressUpdate(Boolean... values) {
			if (values[0]){
				startNewAct();	
			}			
		}
    }


	public void startNewAct() {
		Intent i=  new Intent(this,LoginActivity.class);
		startActivity(i);
		finish();
	}
}