package izf.events;

import java.io.File;

import android.graphics.Bitmap;
import java.util.*;

public class Events {

	private int Id;
	private String Lang;
	private String WebsiteId;
	private String TypeId;
	private String StateId;
	private Bitmap Image;
	private String ThumbImage;
	private Bitmap MoreImage;
	private File Voice;
	private File Video;
	private String HeadLine;
	private String TitleAlias;
	private String Title;
	private String IntroText;
	private String Body;
	private String Description;
	private String Keywords;
	private String Source;
	private String SourceLink;
	private String Url;
	private String ModifiedBy;
	private java.util.Date InsertDate;
	private Date StartPublishing;
	private Date FinishPublishing;
	private String TopNews;
	private String PrintPage;
	private String EmailPage;
	private String ImportantNews;
	private String UeserIdeaPage;
	private int CounterHits;
	private Date LastHitDate;
	private String RedactorStatus;
	private Date RedactorDate;
	private String MetaDescription;
	private String MetaKeyword;
	
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
	
	public String getTypeId() {
		return TypeId;
	}
	
	public void setTypeId(String typeId) {
		TypeId = typeId;
	}
	
	public String getStateId() {
		return StateId;
	}
	
	public void setStateId(String stateId) {
		StateId = stateId;
	}
	
	public Bitmap getImage() {
		return Image;
	}
	
	public void setImage(Bitmap image) {
		Image = image;
	}
	
	public String getThumbImage() {
		return ThumbImage;
	}
	
	public void setThumbImage(String thumbImageUrl) {
		
		this.ThumbImage = "http://izfrankfurt.de/Images/News/" + thumbImageUrl;
	}
	
	public Bitmap getMoreImage() {
		return MoreImage;
	}
	
	public void setMoreImage(Bitmap moreImage) {
		MoreImage = moreImage;
	}
	
	public File getVoice() {
		return Voice;
	}
	
	public void setVoice(File voice) {
		Voice = voice;
	}
	
	public File getVideo() {
		return Video;
	}
	
	public void setVideo(File video) {
		Video = video;
	}
	
	public String getHeadLine() {
		return HeadLine;
	}
	
	public void setHeadLine(String headLine) {
		HeadLine = headLine;
	}
	
	public String getTitleAlias() {
		return TitleAlias;
	}
	
	public void setTitleAlias(String titleAlias) {
		TitleAlias = titleAlias;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		Title = title;
	}
	
	public String getIntroText() {
		return IntroText;
	}
	
	public void setIntroText(String introText) {
		IntroText = introText;
	}
	
	public String getBody() {
		return Body;
	}
	
	public void setBody(String body) {
		Body = body;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getKeywords() {
		return Keywords;
	}
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getSourceLink() {
		return SourceLink;
	}
	public void setSourceLink(String sourceLink) {
		SourceLink = sourceLink;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public java.util.Date getInsertDate() {
		return InsertDate;
	}
	public void setInsertDate(java.util.Date insertDate) {
		InsertDate = insertDate;
	}
	public Date getStartPublishing() {
		return StartPublishing;
	}
	public void setStartPublishing(Date startPublishing) {
		StartPublishing = startPublishing;
	}
	public Date getFinishPublishing() {
		return FinishPublishing;
	}
	public void setFinishPublishing(Date finishPublishing) {
		FinishPublishing = finishPublishing;
	}
	public String getTopNews() {
		return TopNews;
	}
	public void setTopNews(String topNews) {
		TopNews = topNews;
	}
	public String getPrintPage() {
		return PrintPage;
	}
	public void setPrintPage(String printPage) {
		PrintPage = printPage;
	}
	public String getEmailPage() {
		return EmailPage;
	}
	public void setEmailPage(String emailPage) {
		EmailPage = emailPage;
	}
	public String getImportantNews() {
		return ImportantNews;
	}
	public void setImportantNews(String importantNews) {
		ImportantNews = importantNews;
	}
	public String getUeserIdeaPage() {
		return UeserIdeaPage;
	}
	public void setUeserIdeaPage(String ueserIdeaPage) {
		UeserIdeaPage = ueserIdeaPage;
	}
	public int getCounterHits() {
		return CounterHits;
	}
	public void setCounterHits(int counterHits) {
		CounterHits = counterHits;
	}
	public Date getLastHitDate() {
		return LastHitDate;
	}
	public void setLastHitDate(Date lastHitDate) {
		LastHitDate = lastHitDate;
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
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
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
	public int getRss() {
		return Rss;
	}
	public void setRss(int rss) {
		Rss = rss;
	}
	private int Priority;
	private String Active;
	private Date Date;
	private int Rss;
}
