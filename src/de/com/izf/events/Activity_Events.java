package de.com.izf.events;

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

public class Activity_Events extends Activity{
	
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
		this.setContentView(R.layout.activity_events);
		
		list = (ExpandableListView) findViewById(R.id.lvExpEvents);
		list.setCacheColorHint(Color.TRANSPARENT);
		
		try
		{
			CloudData getEvents;
			
			if(isNetworkAvailable())
	        {
				getEvents = CloudData.getInstance(this,false);
				JSONArray obj = new JSONArray();
				obj = getEvents.getJEvents();
				ArrayList<Events> allEvents = JsonToNews(obj);
				EventsExpandableListviewAdapter newsAdapter = 
						new EventsExpandableListviewAdapter(this.getApplicationContext()
								, allEvents,
								getListFeed(allEvents));
				list.setAdapter(newsAdapter);
	        }
	        
	        else
	        {
	        	Toast.makeText(this, "" +
	        			"Keine Verbindung möglich.\nBitte die Netzwerk-einstellungen überprüfen.", Toast.LENGTH_LONG).show();
	        }
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private HashMap<Events, List<String>> getListFeed(ArrayList<Events> events)
	{
		HashMap<Events, List<String>> ans = new HashMap<Events, List<String>>();
		for(int i = 0 ; i < events.size() ; i++)
		{
			List<String> tmp = new ArrayList<String>();
			tmp.add(events.get(i).getBody());
			ans.put(events.get(i), tmp);
		}
		return ans;
	}
	
	@SuppressLint("NewApi") 
	private ArrayList<Events> JsonToNews(JSONArray json)
	{
		ArrayList<Events> ans = new ArrayList<Events>();
		try 
		{
			for(int i = 0 ;i < json.length() ; i++)
			{
				JSONObject TJson = json.getJSONObject(i);
				Events temp = new Events();
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
