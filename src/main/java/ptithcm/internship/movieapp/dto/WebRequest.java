package ptithcm.internship.movieapp.dto;

import ptithcm.internship.movieapp.multipartfile.UploadForm;

public class WebRequest {
	private String wname;
	private String wtitle;
	private UploadForm upload;
	public WebRequest() {
		super();
	}
	public WebRequest(String wname, String wtitle, UploadForm upload) {
		super();
		this.wname = wname;
		this.wtitle = wtitle;
		this.upload = upload;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWtitle() {
		return wtitle;
	}
	public void setWtitle(String wtitle) {
		this.wtitle = wtitle;
	}
	public UploadForm getUpload() {
		return upload;
	}
	public void setUpload(UploadForm upload) {
		this.upload = upload;
	}
	@Override
	public String toString() {
		return "WebRequest [wname=" + wname + ", wtitle=" + wtitle + ", upload=" + upload + "]";
	}
	
	
}
