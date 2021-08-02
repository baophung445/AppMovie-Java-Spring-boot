package ptithcm.internship.movieapp.dto;

public class CategoryRequest {
	private String cid;
	private String cname;
	public CategoryRequest() {
		super();
	}
	public CategoryRequest(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "CategoryRequest [cid=" + cid + ", cname=" + cname + "]";
	}
	
}
