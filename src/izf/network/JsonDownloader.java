package izf.network;


import org.json.JSONArray;

import com.example.izf.CloudData;
import com.example.izf.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class JsonDownloader extends AsyncTask<String, String, JSONArray []> 
{
	Context ctx;
	CloudData mCloudData;
	ProgressDialog pd;
	boolean refresh;
	
	public JsonDownloader(Context ctx , CloudData mCloudData , boolean refresh)
	{
		this.ctx = ctx;
		this.mCloudData = mCloudData;
		this.refresh = refresh;
	}
	
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		if(!refresh)
		{
			pd = new ProgressDialog(ctx);
			pd.setTitle("Get Data");
			pd.setMessage("Downloading");
			pd.show();
		}
	}
	
	@Override
	protected JSONArray[] doInBackground(String... args)
	{
		JSONArray [] res = new JSONArray[3];
		for(int i = 0 ; i < args.length ; i++)
		{
			JSONParser jParser = new JSONParser();
			res[i] = jParser.getJSONFromUrl(args[i]);
		}
		return res;
	}
	
	@Override
	protected void onPostExecute(JSONArray[] json)
	{
		mCloudData.setJNews(json[0]);
		mCloudData.setJFAQ(json[1]);
		mCloudData.setJEvents(json[2]);
		if(!refresh)
		{
			pd.dismiss();
		}
		else
		{
			Intent intent = new Intent(ctx, MainActivity.class);
			ctx.startActivity(intent);
		}
	}
}
