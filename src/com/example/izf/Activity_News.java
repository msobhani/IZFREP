package com.example.izf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class Activity_News extends Activity{


	ExpandableListView list;
    private String NewsUrl = "http://izfrankfurt.de/webservices/?name=ksjdf"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_news);
		
		list = (ExpandableListView) findViewById(R.id.lvExp);
		list.setCacheColorHint(Color.TRANSPARENT);
		
		try
		{
			NewsDownloader getNews = new NewsDownloader(this);
			JSONArray obj = new JSONArray();
			obj = getNews.execute(NewsUrl).get();
			ArrayList<News> allNews = JsonToNews(obj);
			NewsExpandableListviewAdapter newsAdapter = 
					new NewsExpandableListviewAdapter(this.getApplicationContext()
							, allNews,
							getListFeed(allNews));
			list.setAdapter(newsAdapter);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private HashMap<News, List<String>> getListFeed(ArrayList<News> news)
	{
		HashMap<News, List<String>> ans = new HashMap<News, List<String>>();
		for(int i = 0 ; i < news.size() ; i++)
		{
			List<String> tmp = new ArrayList<String>();
			tmp.add(news.get(i).getBody());
			ans.put(news.get(i), tmp);
		}
		return ans;
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
