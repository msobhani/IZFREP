package izf.contact;

import java.util.ArrayList;
import izf.network.JsonDownloader;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

public class Activity_ContactUs extends ListActivity
{
	
	private String FaqUrl = "http://izfrankfurt.de/webservices/faq";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		JsonDownloader getContacts = new JsonDownloader(this);
		
		try 
		{
			QAFuncs getQuestions = new QAFuncs(getContacts.execute(FaqUrl).get());
			ArrayList<QandA> allQuestions = getQuestions.bindQuestionsToAnswers();
			ArrayList<String> temp = getQuestions.getSubjects();
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
	                 this, 
	                 android.R.layout.simple_list_item_1,
	                 temp);
			this.getListView().setAdapter(arrayAdapter);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
