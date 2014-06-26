package com.example.izf;

import izf.contact.Activity_ContactUs;
import izf.events.Activity_Events;
import izf.news.Activity_News;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements OnClickListener{

	private Button btnOptions;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnOptions = (Button) findViewById(R.id.btnOptions);
		
		btnOptions.setOnClickListener(this);
		
		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
		addTab(new Intent(this, Activity_News.class), R.drawable.tab_news);
		addTab(new Intent(this, Activity_Events.class), R.drawable.tab_events);
		addTab(new Intent(this, Activity_Prayer.class), R.drawable.tab_prayer);
		addTab(new Intent(this, Activity_ContactUs.class), R.drawable.tab_contact);
	}

	private void addTab(Intent intent, int drawableId) {

        
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        TabHost.TabSpec spec = tabHost.newTabSpec("");       
       
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);

        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);
       
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
       
    }

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.option_popup);
		dialog.show();
		Button btnRefresh = (Button) dialog.findViewById(R.id.btnRefresh);
		btnRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Refresh();
			}
		});
	}
	
	private void Donate()
	{
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("https://www.paypal.com/" +
						"us/cgi-bin/webscr?" +
						"cmd=_flow&SESSION=YyHJ59TwsCIKAh_6XjUvMxBXySOVwnAJozM8ICelgsTFq5jx9-L2u4gDzjm&dispatch=5885d80a13c0db1f8e263663d3faee8d5402c249c5a2cfd4a145d37ec05e9a5e"));
		startActivity(browserIntent);
	}
	
	private void Refresh()
	{
		CloudData data = CloudData.getInstance(this , false);
		data.Refresh();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//finish();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//finish();
	}

}









