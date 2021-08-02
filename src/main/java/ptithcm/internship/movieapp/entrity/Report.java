package ptithcm.internship.movieapp.entrity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "REPORTS")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REPORT_ID")
	private int rpid;
	@Column(name = "REPORT_TYPE")
	private int rpType;
	@Column(name = "REPORT_DESCRIPTION", length = 4000, nullable = false)
	private String rpDescription;
	@Column(name = "REPORT_ATTACHMENT_URL")
	private String attachmentUrl;
	@Column(name = "IS_RESOLVE")
	private boolean resolve;
	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME")
	private Date rpTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	public Report() {
		super();
	}
	public Report(int rpid, int rpType, String rpDescription, Date rpTime, User user) {
		super();
		this.rpid = rpid;
		this.rpType = rpType;
		this.rpDescription = rpDescription;
		this.rpTime = rpTime;
		this.user = user;
	}
	public Report(int rpType, String rpDescription, Date rpTime, User user) {
		super();
		this.rpType = rpType;
		this.rpDescription = rpDescription;
		this.rpTime = rpTime;
		this.user = user;
	}
	
	public Report(int rpType, String rpDescription, String attachmentUrl, Date rpTime, User user) {
		super();
		this.rpType = rpType;
		this.rpDescription = rpDescription;
		this.attachmentUrl = attachmentUrl;
		this.rpTime = rpTime;
		this.user = user;
	}
	
	public Report(int rpType, String rpDescription, String attachmentUrl, boolean resolve, Date rpTime, User user) {
		super();
		this.rpType = rpType;
		this.rpDescription = rpDescription;
		this.attachmentUrl = attachmentUrl;
		this.resolve = resolve;
		this.rpTime = rpTime;
		this.user = user;
	}
	public int getRpid() {
		return rpid;
	}
	public void setRpid(int rpid) {
		this.rpid = rpid;
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
	public Date getRpTime() {
		return rpTime;
	}
	public void setRpTime(Date rpTime) {
		this.rpTime = rpTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	public boolean isResolve() {
		return resolve;
	}
	public void setResolve(boolean resolve) {
		this.resolve = resolve;
	}
	@Override
	public String toString() {
		return "Report [rpid=" + rpid + ", rpType=" + rpType + ", rpDescription=" + rpDescription + ", attachmentUrl="
				+ attachmentUrl + ", resolve=" + resolve + ", rpTime=" + rpTime + ", user=" + user + "]";
	}

}
 