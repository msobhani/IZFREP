package izf.contact;

import java.util.Date;

public class Contact {
	private int Id;	
	private String Lang;
	private String WebsiteId;
	private int ReplyId;
	private String Subject;
	private String Body;
	private String UserName;
	private String UserEmail;
	private String RedactorStatus;
	private Date RedactorDate;
	private String MetaDescription;
	private String MetaKeyword;
	private String Active;
	private Date Date;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public String getLang() {
		return Lang;
	}
	public void setLang(String lang) {
		Lang = lang;
	}
	
	public String getWebsiteId() {
		return WebsiteId;
	}
	public void setWebsiteId(String websiteId) {
		WebsiteId = websiteId;
	}
	
	public int getReplyId() {
		return ReplyId;
	}
	public void setReplyId(int replyId) {
		ReplyId = replyId;
	}
	
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	
	public String getRedactorStatus() {
		return RedactorStatus;
	}
	public void setRedactorStatus(String redactorStatus) {
		RedactorStatus = redactorStatus;
	}
	
	public Date getRedactorDate() {
		return RedactorDate;
	}
	public void setRedactorDate(Date redactorDate) {
		RedactorDate = redactorDate;
	}
	
	public String getMetaDescription() {
		return MetaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		MetaDescription = metaDescription;
	}
	
	public String getMetaKeyword() {
		return MetaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		MetaKeyword = metaKeyword;
	}
	
	public String getActive() {
		return Active;
	}
	public void setActive(String active) {
		Active = active;
	}
	
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
}
