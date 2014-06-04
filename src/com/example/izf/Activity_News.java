package com.example.izf;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;

public class Activity_News extends ListActivity{


    private String NewsUrl = "http://izfrankfurt.de/webservices/?name=ksjdf"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.getListView().setCacheColorHint(Color.TRANSPARENT);
		
		try
		{
			NewsDownloader getNews = new NewsDownloader(this);
			JSONArray obj = new JSONArray();
			obj = getNews.execute(NewsUrl).get();
			NewsAdapter newsAdapter = new NewsAdapter(this, JsonToNews(obj));
			setListAdapter(newsAdapter);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressLint("NewApi") 
	private ArrayList<News> JsonToNews(JSONArray json)
	{
		ArrayList<News> ans = new ArrayList<News>();
		try 
		{
			for(int i = 0 ;i < json.length() ; i++)
			{
				JSONObject TJson = json.getJSONObject(i);
				News temp = new News();
				temp.setTitle(TJson.getString("Title"));
				temp.setThumbImage(TJson.getString("ThumbImage"));
				temp.setIntroText(TJson.getString("IntroText"));
				temp.setBody(TJson.getString("Body"));
				
				ans.add(temp);
			}
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans;	
	}
}
