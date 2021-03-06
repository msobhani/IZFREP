package de.com.izf.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Toast;
import de.com.izf.CloudData;
import de.com.izf.R;

public class Activity_ContactUs extends Activity
{
	
	private QAFuncs allQuestions;
	ExpandableListView list;
	private ArrayList<String> allSubjects;
	private  HashMap<String, List<QandA>> allGroupedQuestions;
	
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
		this.setContentView(R.layout.activity_questions);
		
		list = (ExpandableListView) findViewById(R.id.lvExpQuestions);
		list.setCacheColorHint(Color.TRANSPARENT);
		
		try 
		{
			CloudData getFAQData;
			
			if(isNetworkAvailable())
	        {
				getFAQData = CloudData.getInstance(this,false);
				allQuestions = QAFuncs.getInstance();
				allQuestions.setQuestions(getFAQData.getJFAQ());
				allSubjects = allQuestions.getSubjects(allQuestions.getQuestions());
				allGroupedQuestions = getListFeed(allQuestions.getSubjects(allQuestions.getQuestions()));
				ContactsExpandableListviewAdapter questionAdapter = 
						new ContactsExpandableListviewAdapter(this.getApplicationContext()
								, allSubjects,
								allGroupedQuestions);
				
				list.setAdapter(questionAdapter);
				list.setOnChildClickListener(new OnChildClickListener() {
					
					@Override
					public boolean onChildClick(ExpandableListView parent, View v,
							int groupPosition, int childPosition, long id) {
						// TODO Auto-generated method stub
						showPopUp(allGroupedQuestions.get(allSubjects.get(groupPosition)).get(childPosition));
						return false;
					}
				});
	        }
	        
	        else
	        {
	        	Toast.makeText(this, "Connect to the Internet", Toast.LENGTH_LONG).show();
	        }
			
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private HashMap<String, List<QandA>> getListFeed(ArrayList<String> Subjects)
	{
		HashMap<String, List<QandA>> ans = new HashMap<String, List<QandA>>();
		for(int i = 0 ; i < Subjects.size() ; i++)
		{
			List<QandA> tmp = new ArrayList<QandA>();
			for(int j = 0 ; j < allQuestions.getQuestions().size() ; j++)
			{
				if(Subjects.get(i).equals(allQuestions.getQuestions().get(j).getQuestion().getSubject()))
				{
					tmp.add(allQuestions.getQuestions().get(j));
				}
			}
			ans.put(Subjects.get(i), tmp);
		}
		return ans;
	}
	
	private void showPopUp(QandA question)
	{
 
		final Dialog dialog = new Dialog(this);
		
		
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.question_popup);

		ArrayList<String> asnwers = new ArrayList<String>();
		for(int i = 0 ; i < question.getAnswers().size(); i++)
		{
			asnwers.add(question.getAnswers().get(i).getBody());
		}
		ListView lvAnswers = (ListView) dialog.findViewById(R.id.lvAnswers);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.question_details, R.id.txtQuestion, asnwers);
		lvAnswers.setAdapter(adapter);
		dialog.show();
	}
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}















