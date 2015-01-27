package de.com.izf;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class Activity_Splash extends Activity{
	
	private Context ctx ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		this.ctx = this;
		//SharedPreferences.Editor localEditor = getSharedPreferences("Language", 0).edit();
        //localEditor.putString("language", "de");
	    
		CloudData getdata = null;
		if(isNetworkAvailable())
		{
			//if(getdata.isNull())
				getdata.getInstance(ctx,true);
			//else
			//{
			//	Intent intent = new Intent(this, MainActivity.class);
			//	startActivity(intent);
			//}
		}
		else
		{
			Toast.makeText(ctx, "Keine Verbindung möglich.\nBitte die Netzwerk-einstellungen überprüfen.", Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
}






















