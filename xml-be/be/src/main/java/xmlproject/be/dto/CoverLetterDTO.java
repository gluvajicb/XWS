package xmlproject.be.dto;

public class CoverLetterDTO {
	String coverLetterId;
	String title;

	String scientificWorkId;
	String text;
	public String getCoverLetterId() {
		return coverLetterId;
	}
	public void setCoverLetterId(String coverLetterId) {
		this.coverLetterId = coverLetterId;
	}
	public String getScientificWorkId() {
		return scientificWorkId;
	}
	public void setScientificWorkId(String scientificWorkId) {
		this.scientificWorkId = scientificWorkId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public CoverLetterDTO(String coverLetterId, String title, String scientificWorkId, String text) {
		super();
		this.coverLetterId = coverLetterId;
		this.title = title;
		this.scientificWorkId = scientificWorkId;
		this.text = text;
	}
	
	
	
}
