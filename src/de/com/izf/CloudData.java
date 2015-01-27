package de.com.izf;


import org.json.JSONArray;

import de.com.izf.network.JsonDownloader;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;


public class CloudData {

	private static CloudData instance;
	  private JSONArray JEvents;
	  private JSONArray JFAQ;
	  private JSONArray JNews;
	  private String[] UrlsDe = { "http://www.izfrankfurt.de/webservice/news.php?lang=de&count=20",
			  "http://www.izfrankfurt.de/webservice/faq.php?lang=fa&count=20",
			  "http://www.izfrankfurt.de/webservice/activity.php?lang=de&count=20" };
	  private String[] UrlsFa = { "http://www.izfrankfurt.de/webservice/news.php?lang=fa&count=20",
			  "http://www.izfrankfurt.de/webservice/faq.php?lang=fa&count=20",
			  "http://www.izfrankfurt.de/webservice/activity.php?lang=fa&count=20" };
	  private static Context ctx;
	  private String language;
	  private Handler mHandler = new Handler();


	public static CloudData getInstance(Context ctx , boolean isSplash)
	{
		if (instance == null)
		{
			instance = new CloudData(ctx);
		}
		else if (isSplash)
		{
			Intent intent = new Intent(ctx, MainActivity.class);
			ctx.startActivity(intent);
		}
		return instance;
	}
	
	public boolean isNull()
	{
		if(instance == null)
			return true;
		return false;
	}

	public CloudData(Context ctx)
	{
		this.ctx = ctx;
		this.language = getLanguage();
		JsonDownloader localJsonDownloader = new JsonDownloader(ctx , this, false, language);
		try 
		{
			if (language.equals("de"))
		      {
		        localJsonDownloader.execute(this.UrlsDe);
		        return;
		      }
		      localJsonDownloader.execute(this.UrlsFa);
		      return;
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
	
	public void setmHandler(Handler paramHandler)
	{
	    this.mHandler = paramHandler;
	}
	
	/*public void Refresh(boolean check) {
		
		if(isNetworkAvailable())
		{
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
		else
		{
			Toast.makeText(ctx, "Keine Verbindung möglich.\nBitte die Netzwerk-einstellungen überprüfen.", Toast.LENGTH_LONG).show();
		}
		
	}*/
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public void changeLanguage(String paramString)
	  {
	    this.language = paramString;
	    instance = new CloudData(this.ctx);
	  }
	
	private static String getLanguage()
	  {
	    SharedPreferences localSharedPreferences = ctx.getSharedPreferences("Language", 0);
	    if ((localSharedPreferences.getString("language", null) != null) && (localSharedPreferences.getString("language", "No name defined").equals("fa"))) {
	      return "fa";
	    }
	    return "de";
	  }
}
