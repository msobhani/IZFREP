package izf.network;


import org.json.JSONArray;
import org.json.JSONObject;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class JsonDownloader extends AsyncTask<String, String, JSONArray> 
{
	private ProgressDialog pDialog;
	private Context ctx;
	
	public JsonDownloader(Context ctx)
	{
		this.ctx = ctx;
	}
	
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
	}
	
	@Override
	protected JSONArray doInBackground(String... args)
	{
		JSONParser jParser = new JSONParser();
		JSONArray json = jParser.getJSONFromUrl(args[0]);
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONArray json)
	{
	}
}
