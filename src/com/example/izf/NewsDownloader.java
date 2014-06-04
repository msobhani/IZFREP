package com.example.izf;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

class NewsDownloader extends AsyncTask<String, String, JSONArray> 
{
	private ProgressDialog pDialog;
	private Context ctx;
	
	public NewsDownloader(Context ctx)
	{
		this.ctx = ctx;
	}
	
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		/*pDialog = new ProgressDialog(ctx);
		pDialog.setMessage("Getting Data ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();*/
	}
	
	@Override
	protected JSONArray doInBackground(String... args)
	{
		JSONParser jParser = new JSONParser();
		// Getting JSON from URL
		JSONArray json = jParser.getJSONFromUrl(args[0]);
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONArray json)
	{
		//pDialog.dismiss();
	}
}
