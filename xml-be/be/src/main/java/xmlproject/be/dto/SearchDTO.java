package xmlproject.be.dto;

public class SearchDTO {
	String abst;
	String title;
	String keyword;
	String author;
	String section;
	String status;
	public SearchDTO(String abst, String title, String keyword, String author, String section, String status) {
		super();
		this.abst = abst;
		this.title = title;
		this.keyword = keyword;
		this.author = author;
		this.section = section;
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAbst() {
		return abst;
	}
	public void setAbst(String abst) {
		this.abst = abst;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
}
