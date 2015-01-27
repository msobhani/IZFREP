package de.com.izf;


import de.com.izf.contact.Activity_ContactUs;
import de.com.izf.events.Activity_Events;
import de.com.izf.news.Activity_News;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.Theme;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.ToggleButton;

public class MainActivity extends TabActivity implements OnClickListener{

	private ImageView btnOptions;
	private Dialog dialog;
	ToggleButton btnLang;
	private CloudData data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		data = CloudData.getInstance(this,false);
		
		btnOptions = (ImageView) findViewById(R.id.btnOption);
		
		btnOptions.setOnClickListener(this);
		
		
		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
		addTab(new Intent(this, Activity_News.class), R.drawable.tab_news);
		addTab(new Intent(this, Activity_Events.class), R.drawable.tab_events);
		addTab(new Intent(this, Activity_Prayer.class), R.drawable.tab_prayer);
		addTab(new Intent(this, Activity_ContactUs.class), R.drawable.tab_contact);
	}

	private void addTab(Intent intent, int drawableId) {

        
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        TabHost.TabSpec spec = tabHost.newTabSpec("");       
       
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);

        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);
       
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
       
    }

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.option_popup);
		dialog.show();
		btnLang = (ToggleButton) dialog.findViewById(R.id.btnLanguage);
		if(getLanguage().equals("de"))
			btnLang.setChecked(false);
		else
			btnLang.setChecked(true);
		btnLang.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor localEditor = MainActivity.this.getSharedPreferences("Language", 0).edit();
				if (btnLang.isChecked()) 
				{
		            localEditor.putString("language", "fa");
		        }
				else
				{
					localEditor.putString("language", "de");
				}
				localEditor.commit();
	            data.changeLanguage(MainActivity.this.getLanguage());
	            dialog.dismiss();
	            Intent localIntent = new Intent(MainActivity.this.getApplicationContext(), Activity_Splash.class);
	            MainActivity.this.startActivity(localIntent);
			}
		});
		
		Button btnAboutUs = (Button) dialog.findViewById(R.id.btnAboutUs);
		btnAboutUs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				showAboutUs();
			}
		});
		
		Button btnDonate = (Button) dialog.findViewById(R.id.btnDonate);
		btnDonate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Donate();
			}
		});
	}
	
	private void showAboutUs()
	{
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.about_us);
		dialog.show();
	}
	
	private void Donate()
	{
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=9A7GJGVEX7VF6"));
		startActivity(browserIntent);
	}
	
	private void Refresh()
	{
		//CloudData data = CloudData.getInstance(this , false);
		//	data.Refresh(false);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//System.exit(0);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//finish();
	}
	private String getLanguage()
	  {
	    SharedPreferences localSharedPreferences = getSharedPreferences("Language", 0);
	    if ((localSharedPreferences.getString("language", null) != null) && (localSharedPreferences.getString("language", "No name defined").equals("fa"))) {
	      return "fa";
	    }
	    return "de";
	  }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//System.exit(0);
	}

}









