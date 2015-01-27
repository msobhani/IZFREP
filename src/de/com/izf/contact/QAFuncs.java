package de.com.izf.contact;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class QAFuncs {
	
	private static QAFuncs instance = null;

	public static QAFuncs getInstance()
	{
		if (instance == null)
		{
			instance = new QAFuncs();
		}
		
		return instance;
	}

	private ArrayList<QandA> Questions;

	public ArrayList<QandA> getQuestions() {
		return Questions;
	}

	public void setQuestions(JSONArray JContacts) {
		ArrayList<Contact> contacts = extractContacsFromJsonArray(JContacts);
		Questions = bindQuestionsToAnswers(contacts);
	}
	
	public QAFuncs() 
	{
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Contact> extractContacsFromJsonArray(JSONArray JContacts)
	{
		ArrayList<Contact> ans = new ArrayList<Contact>();
		for(int i = 0 ; i < JContacts.length() ; i++)
		{
			try 
			{
				Contact temp = new Contact();
				//temp.setReplyId(JContacts.getJSONObject(i).getInt("ReplyId"));
				temp.setId(JContacts.getJSONObject(i).getInt("id"));
				temp.setSubject(JContacts.getJSONObject(i).getString("question"));
				temp.setBody(clearText(JContacts.getJSONObject(i).getString("answer")));
				ans.add(temp);
				
			}
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ans;
	}
	
	private ArrayList<QandA> bindQuestionsToAnswers(ArrayList<Contact> contacts)
	{
		ArrayList<QandA> ans = new ArrayList<QandA>();
		
		for(int i  = 0 ; i < contacts.size() ; i++)
		{
			if(contacts.get(i).getReplyId() == 0)
			{
				QandA temp = new QandA();
				temp.setQuestion(contacts.get(i));
				ans.add(temp);
			}	
		}
		
		for(int i = 0 ; i < ans.size() ; i++)
		{
			ArrayList<Contact> answers = new ArrayList<Contact>();
			for(int j = 0 ; j < contacts.size() ; j++)
			{
				if(ans.get(i).getQuestion().getId() == contacts.get(j).getReplyId())
				{
					answers.add(contacts.get(j));
				}
			}
			ans.get(i).setAnswers(answers);
		}
		
		return ans;
	}
	
	public ArrayList<String> getSubjects(ArrayList<QandA> mQuestions)
	{
		ArrayList<String> ans = new ArrayList<String>();
		for(int i = 0 ; i < mQuestions.size() ; i++)
		{
			boolean check = false;
			for(int j = 0 ; j < ans.size() ; j++)
			{
				if(mQuestions.get(i).getQuestion().getSubject().equals(ans.get(j)))
				{
					check = true;
					break;
				}
			}
			if(!check)
			{
				ans.add(mQuestions.get(i).getQuestion().getSubject());
			}
		}
		return ans;
	}
	
	public ArrayList<QandA> getQuestionsOfSubjects(String subject)
	{
		ArrayList<QandA> ans = new ArrayList<QandA>();
		for(int i = 0 ; i < Questions.size() ; i++)
		{
			if(Questions.get(i).getQuestion().getSubject().equals(subject))
			{
				ans.add(Questions.get(i));
			}
		}
		return ans;
	}

	private String clearText(String input)
    {
    	String ans = "";
    	boolean check = false;
    	for(int i = 0 ;i < input.length() ; i++)
    	{
    		if(input.charAt(i) == '<')
    			check = true;
    		if(!check)
    		{
    			ans += input.charAt(i);
    		}
    		if(input.charAt(i) == '>')
    			check = false;
    	}
    	return ans;
    }
}

















