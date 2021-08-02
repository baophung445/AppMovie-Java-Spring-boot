package ptithcm.internship.movieapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class ReportRequest {
	private int rpType;
	private String rpDescription;
	private String fid;
	private String episode;
	private MultipartFile data;
	public ReportRequest() {
		super();
	}
	public ReportRequest(int rpType, String rpDescription, String fid, String episode, MultipartFile data) {
		super();
		this.rpType = rpType;
		this.rpDescription = rpDescription;
		this.fid = fid;
		this.episode = episode;
		this.data = data;
	}
	public int getRpType() {
		return rpType;
	}
	public void setRpType(int rpType) {
		this.rpType = rpType;
	}
	public String getRpDescription() {
		return rpDescription;
	}
	public void setRpDescription(String rpDescription) {
		this.rpDescription = rpDescription;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getEpisode() {
		return episode;
	}
	public void setEpisode(String episode) {
		this.episode = episode;
	}
	public MultipartFile getData() {
		return data;
	}
	public void setData(MultipartFile data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ReportRequest [rpType=" + rpType + ", rpDescription=" + rpDescription + ", fid=" + fid + ", episode="
				+ episode + ", data=" + data + "]";
	}
	
}
