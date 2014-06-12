package izf.events;

import izf.network.JsonDownloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.izf.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class Activity_Events extends Activity{
	
	ExpandableListView list;
    private String EventsUrl = "http://izfrankfurt.de/webservices/activity/"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_events);
		
		list = (ExpandableListView) findViewById(R.id.lvExpEvents);
		list.setCacheColorHint(Color.TRANSPARENT);
		
		try
		{
			JsonDownloader getEvents = new JsonDownloader(this);
			JSONArray obj = new JSONArray();
			obj = getEvents.execute(EventsUrl).get();
			ArrayList<Events> allEvents = JsonToNews(obj);
			EventsExpandableListviewAdapter newsAdapter = 
					new EventsExpandableListviewAdapter(this.getApplicationContext()
							, allEvents,
							getListFeed(allEvents));
			list.setAdapter(newsAdapter);
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
