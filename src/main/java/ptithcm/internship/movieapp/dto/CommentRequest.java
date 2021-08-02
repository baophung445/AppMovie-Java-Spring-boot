package ptithcm.internship.movieapp.dto;

public class CommentRequest {
	private int cmid;
	private String uname;
	private String content;
	private int fid;
	private String ctime;
	private int commentShow;
	private boolean haveRole;
	public CommentRequest() {
		super();
	}
	
	public CommentRequest(String uname, String content, int fid, String ctime, int commentShow, boolean haveRole) {
		super();
		this.uname = uname;
		this.content = content;
		this.fid = fid;
		this.ctime = ctime;
		this.commentShow = commentShow;
		this.haveRole = haveRole;
	}

	public CommentRequest(int cmid, String uname, String content, int fid, String ctime, int commentShow,
			boolean haveRole) {
		super();
		this.cmid = cmid;
		this.uname = uname;
		this.content = content;
		this.fid = fid;
		this.ctime = ctime;
		this.commentShow = commentShow;
		this.haveRole = haveRole;
	}

	public int getCmid() {
		return cmid;
	}
	public void setCmid(int cmid) {
		this.cmid = cmid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	public boolean isHaveRole() {
		return haveRole;
	}
	public void setHaveRole(boolean haveRole) {
		this.haveRole = haveRole;
	}
	

	public int getCommentShow() {
		return commentShow;
	}

	public void setCommentShow(int commentShow) {
		this.commentShow = commentShow;
	}

	@Override
	public String toString() {
		return "CommentRequest [cmid=" + cmid + ", uname=" + uname + ", content=" + content + ", fid=" + fid
				+ ", ctime=" + ctime + ", commentShow=" + commentShow + ", haveRole=" + haveRole + "]";
	}
	
}
