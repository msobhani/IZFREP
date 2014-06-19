package com.example.izf;

import izf.contact.Activity_ContactUs;
import izf.events.Activity_Events;
import izf.news.Activity_News;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends TabActivity implements OnTabChangeListener  {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
		addTab(new Intent(this, Activity_ContactUs.class), R.drawable.tab_contact);
		addTab(new Intent(this, Activity_Prayer.class), R.drawable.tab_prayer);
		addTab(new Intent(this, Activity_Events.class), R.drawable.tab_events);
		addTab(new Intent(this, Activity_News.class), R.drawable.tab_news);

     
        tabHost.setOnTabChangedListener(this);
        tabHost.setCurrentTab(3);

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
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
	}

	
}