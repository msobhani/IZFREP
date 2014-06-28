package com.example.izf;


import izf.network.JsonDownloader;
import org.json.JSONArray;
import android.content.Context;
import android.content.Intent;


public class CloudData {

	private static CloudData instance;
	private Context ctx;
	private JSONArray JNews;
	private JSONArray JEvents;
	private JSONArray JFAQ;
	private String[] Urls = {"http://izfrankfurt.de/webservices/news/"
		,"http://izfrankfurt.de/webservices/faq"
		,"http://izfrankfurt.de/webservices/activity/"
	};


	public static CloudData getInstance(Context ctx , boolean check)
	{
		if (instance == null)
		{
			instance = new CloudData(ctx, check);
		}
		
		else if (check)
		{
			Intent intent = new Intent(ctx, MainActivity.class);
			ctx.startActivity(intent);
		}
		
		return instance;
	}

	public CloudData(Context ctx , boolean check)
	{
		this.ctx = ctx;
		JsonDownloader getJsons = new JsonDownloader(ctx , this, check);
		try 
		{
			getJsons.execute(Urls);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JSONArray getJNews() {
		return JNews;
	}

	public void setJNews(JSONArray jNews) {
		 this.JNews = jNews;
	}

	public JSONArray getJEvents() {
		return JEvents;
	}

	public void setJEvents(JSONArray jEvents) {
		this.JEvents = jEvents;
	}

	public JSONArray getJFAQ() {
		return JFAQ;
	}

	public void setJFAQ(JSONArray jFAQ) {
		this.JFAQ = jFAQ;
	}
	public void Refresh(boolean check) {
		JsonDownloader getJsons = new JsonDownloader(ctx , this, check);
		try 
		{
			getJsons.execute(Urls);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
