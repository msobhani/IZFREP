package izf.contact;

import java.util.ArrayList;

public class QandA 
{
	private Contact question;
	private ArrayList<Contact> answers;
	
	public Contact getQuestion() {
		return question;
	}
	public void setQuestion(Contact question) {
		this.question = question;
	}
	
	public ArrayList<Contact> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Contact> answers) {
		this.answers = answers;
	}
}
