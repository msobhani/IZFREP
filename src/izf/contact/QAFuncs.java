package izf.contact;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class QAFuncs {

	private ArrayList<Contact> contacts;
	private ArrayList<QandA> Questions;
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public ArrayList<QandA> getQuestions() {
		return Questions;
	}

	public void setQuestions(ArrayList<QandA> questions) {
		Questions = questions;
	}

	public QAFuncs(JSONArray JContacts) 
	{
		// TODO Auto-generated constructor stub
		this.contacts = extractContacsFromJsonArray(JContacts);
	}
	
	public ArrayList<Contact> extractContacsFromJsonArray(JSONArray JContacts)
	{
		ArrayList<Contact> ans = new ArrayList<Contact>();
		for(int i = 0 ; i < JContacts.length() ; i++)
		{
			try 
			{
				Contact temp = new Contact();
				temp.setReplyId(JContacts.getJSONObject(i).getInt("ReplyId"));
				temp.setId(JContacts.getJSONObject(i).getInt("Id"));
				temp.setSubject(JContacts.getJSONObject(i).getString("Subject"));
				temp.setBody(JContacts.getJSONObject(i).getString("Body"));
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
	
	public ArrayList<String> getSubjects()
	{
		ArrayList<String> ans = new ArrayList<String>();
		for(int i = 0 ; i < contacts.size() ; i++)
		{
			boolean check = false;
			for(int j = 0 ; j < ans.size() ; j++)
			{
				if(contacts.get(i).getSubject().equals(ans.get(j)))
				{
					check = true;
					break;
				}
			}
			if(!check)
			{
				ans.add(contacts.get(i).getSubject());
			}
		}
		return ans;
	}
	
	public ArrayList<QandA> bindQuestionsToAnswers()
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
}

















