package de.com.izf.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;
import de.com.izf.CloudData;
import de.com.izf.R;

public class Activity_News extends Activity{


	ExpandableListView list; 
	
	private String getLanguage()
	  {
	    SharedPreferences localSharedPreferences = getSharedPreferences("Language", 0);
	    if ((localSharedPreferences.getString("language", null) != null) && (localSharedPreferences.getString("language", "No name defined").equals("fa"))) {
	      return "fa";
	    }
	    return "de";
	  }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_news);
		
		list = (ExpandableListView) findViewById(R.id.lvExpNews);
		list.setCacheColorHint(Color.TRANSPARENT);
		
		try
		{
			CloudData getData = null;
			if(isNetworkAvailable())
	        {
	        	getData = CloudData.getInstance(this,false);
				JSONArray obj = new JSONArray();
				obj = getData.getJNews();
				ArrayList<News> allNews = JsonToNews(obj);
				NewsExpandableListviewAdapter newsAdapter = 
						new NewsExpandableListviewAdapter(this.getApplicationContext()
								, allNews,
								getListFeed(allNews));
				list.setAdapter(newsAdapter);
	        }
	        
	        else
	        {
	        	Toast.makeText(this, "Keine Verbindung möglich.\nBitte die Netzwerk-einstellungen überprüfen.", Toast.LENGTH_LONG).show();
	        }
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
				temp.setTitle(TJson.getString("title"));
				temp.setThumbImage(TJson.getString("image"));
				temp.setIntroText(TJson.getString("detail"));
				temp.setBody(TJson.getString("body"));
				
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
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
