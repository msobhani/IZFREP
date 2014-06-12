package izf.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


public class JSONParser 
{
	
	static InputStream is = null;
	static JSONArray jObj = null;
	static String json = "";
	
	// constructor
	
	public JSONParser() 
	{
	}
	
	public JSONArray getJSONFromUrl(String url)
	{
		
		//Making HTTP request
		try
		{
			//defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(
			is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			is.close();
			json = returnJsonArray(sb);
		} 
		catch (Exception e)
		{
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		// try parse the string to a JSON object
		try 
		{
			jObj = new JSONArray(json);
		} 
		catch (JSONException e) 
		{
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		// return JSON String
		return jObj;
	}
	
	private String returnJsonArray(StringBuilder str)
	{
		int index2 = str.indexOf(":{\"Id\":");
		int index1;
		while(index2 >= 0) 
		{
			int counter = 0;
			index1 = index2;
			while(counter < 2)
			{
				if(str.charAt(index1) == '"')
				{
					counter ++;
				}
				str.setCharAt(index1, ' ');
				index1--;
			}
			
			
			
			index2 = str.indexOf(":{\"Id\":", index2+1);
		}
		str.setCharAt(0, '[');
		str.setCharAt(str.length() - 1, ']');
		String ans = str.toString();// + "]";
		return ans;
	}
	
}