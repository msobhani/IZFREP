package de.com.izf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import de.com.izf.prayer.PrayTime;

public class Activity_Prayer extends Activity implements CordovaInterface,
		LocationListener {
	CordovaWebView cwv;

	protected LocationManager locationManager;
	protected LocationListener locationListener;
	
	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prayer);
		cwv = (CordovaWebView) findViewById(R.id.compassView);

		cwv.setInitialScale(getScale());
		cwv.setBackgroundColor(0x00000000);
		cwv.setVerticalScrollBarEnabled(false);
		cwv.setHorizontalScrollBarEnabled(false);
		cwv.setEnabled(false);
		// cwv.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
		cwv.loadUrl("file:///android_asset/www/index.html");

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		//provider = LocationManager.GPS_PROVIDER; // We want to use the GPS

		// Initialize the location fields
		Location location = locationManager.getLastKnownLocation(provider);
		
		if(location != null)
			updatePrayerInfo(location);
		else
			Toast.makeText(this, "Bitte warten Sie einen Moment. \nLokalisierung..." ,Toast.LENGTH_SHORT).show();

		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				1, this);

		
	}
	

	public void updatePrayerInfo(Location location)
	{
		// TODO Auto-generated method stub

				double latitude = location.getLatitude();
				double longitude = location.getLongitude();

				double timezone = 1;

		        int fajrOffset = 0;
		        // Test Prayer times here
		        
		        Calendar cal = Calendar.getInstance();

		       
		        if(cal.get(Calendar.MONTH) >= Calendar.APRIL && cal.get(Calendar.MONTH) <= Calendar.SEPTEMBER)
		        	timezone = 2;
		        else if(cal.get(Calendar.MONTH) == Calendar.MARCH && cal.get(Calendar.DAY_OF_MONTH) > 26 )
		        	timezone = 2;        
		        else if(cal.get(Calendar.MONTH) == Calendar.OCTOBER && cal.get(Calendar.DAY_OF_MONTH) < 30 )
		        	timezone = 2;
		        
		        if(cal.get(Calendar.MONTH) == Calendar.JUNE)
		        {
		        	if(cal.get(Calendar.DAY_OF_MONTH) == 1) fajrOffset = 9;
		        	else
		        		if(cal.get(Calendar.DAY_OF_MONTH) == 2) fajrOffset = 23;
		        		else
		            		if(cal.get(Calendar.DAY_OF_MONTH) > 2) fajrOffset = (int)(29 + (cal.get(Calendar.DAY_OF_MONTH) - 2) * 0.07);
		        }
		        else
		        	if(cal.get(Calendar.MONTH) == Calendar.JULY)
		            {

		              if(cal.get(Calendar.DAY_OF_MONTH) <11) fajrOffset = (int)(30 + (cal.get(Calendar.DAY_OF_MONTH)) * 0.07);
		              else
		            	  if(cal.get(Calendar.DAY_OF_MONTH) == 11) fajrOffset = 16;
		                  else
		                	  if(cal.get(Calendar.DAY_OF_MONTH) == 12) fajrOffset = 5;

		            }

		        
				// Test Prayer times here
				PrayTime prayers = new PrayTime();

				prayers.setTimeFormat(prayers.Time24);
				prayers.setCalcMethod(prayers.Tehran);
				prayers.setAsrJuristic(prayers.Shafii);
				prayers.setAdjustHighLats(prayers.MidNight);
				int[] offsets = { fajrOffset, 0, 0, 0, 0, 0, 0, 0 }; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha,
															// Nimeshab}
				prayers.tune(offsets);

				ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal, latitude,
						longitude, timezone);

				TextView tvSobh = (TextView) findViewById(R.id.tvFajr);
				tvSobh.setText(prayerTimes.get(0));

				TextView tvZohr = (TextView) findViewById(R.id.tvSobh);
				tvZohr.setText(prayerTimes.get(1));

				TextView tvAsr = (TextView) findViewById(R.id.tvZohr);
				tvAsr.setText(prayerTimes.get(2));

				TextView tvMaghrib = (TextView) findViewById(R.id.tvMaghrib);
				tvMaghrib.setText(prayerTimes.get(5));

	}
	
	@Override
	public void onLocationChanged(Location location) {
		updatePrayerInfo(location);
	}

	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ExecutorService getThreadPool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object onMessage(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActivityResultCallback(CordovaPlugin arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startActivityForResult(CordovaPlugin arg0, Intent arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	private int getScale() {
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		int width = display.getWidth();
		Double val = new Double(width) / new Double(720) *0.85;
		val = val * 80d;
		return val.intValue();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
locationManager.removeUpdates(this);
	}
}