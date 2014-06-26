package com.example.izf;

import java.util.concurrent.ExecutorService;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;



	public class Activity_Prayer extends Activity implements CordovaInterface {
	    CordovaWebView cwv;
	    /* Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_prayer);
	        cwv = (CordovaWebView) findViewById(R.id.tutorialView);
	        
	        cwv.setInitialScale(getScale());
	        cwv.setBackgroundColor(0x00000000);
	        cwv.setVerticalScrollBarEnabled(false);
	        cwv.setHorizontalScrollBarEnabled(false);
	        cwv.setEnabled(false);
	        //cwv.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
	        cwv.loadUrl("file:///android_asset/www/index.html");

	    }
		@Override
		public Activity getActivity() {
			// TODO Auto-generated method stub
			return this;
		}
		@Override
		public ExecutorService getThreadPool() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Object onMessage(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void setActivityResultCallback(CordovaPlugin arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void startActivityForResult(CordovaPlugin arg0, Intent arg1,
				int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		private int getScale(){
		    Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
		    int width = display.getWidth(); 
		    Double val = new Double(width)/new Double(720);
		    val = val * 80d;
		    return val.intValue();
		}
	}