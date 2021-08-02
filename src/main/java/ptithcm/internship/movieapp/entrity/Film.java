package ptithcm.internship.movieapp.entrity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VIDEOS")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VIDEO_ID")
	private int fid;
	@Column(name = "VIDEO_NAME", nullable = false)
	private String fname;
	@Column(name = "VIDEO_EPISODE_COUNT", nullable = false)
	private int fepisodecount;
	@Column(name = "VIDEO_YEAR", nullable = false)
	private int fyear;
	@Column(name = "VIDEO_STATUS")
	private int fstatus;
	@Column(name = "VIDEO_REQUEST")
	private int frequest;
	@Column(name = "VIDEO_INTRODUCTION", length = 4000)
	private String introduction;
	@Column(name = "VIDEO_LENGTH")
	private String length;
	@Column(name = "VIDEO_IMAGE_URL", nullable = false)
	private String fimageurl;
	@Column(name = "VIDEO_BANNER", nullable = false)
	private String fbanner;
	@Column(name = "REQUEST_EMAIL")
	private String requestEmail;
	@Column(name = "ACCEPTE_ACCOUNT")
	private String acceptAccount;
	@Column(name = "REQUEST_TYPE")
	private String requestType;
	@Column(name = "VIDEO_DEPLOY")
	private int fdeploy;
	@Column(name = "VIDEO_VIEW")
	private int fview;
	@Column(name = "VIDEO_EVALUATE_COUNT")
	private double fevaluatecount;
	@Column(name = "VIDEO_EVALUATE_POINT")
	private double fevaluatepoint;
	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_TIME")
	private Date fRequestTime;
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPT_TIME")
	private Date fAcceptTime;
	@ManyToMany
	@JoinTable(name = "category_video", joinColumns = @JoinColumn(name = "video_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> belongedCategories = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	public Film() {
		super();
	}

	public Film(String fname, int fepisodecount, int fyear, int fstatus, int frequest, String introduction,
			String length, String fimageurl, String fbanner, String requestEmail, String acceptAccount,
			String requestType, int fdeploy, int fview, double fevaluatecount, double fevaluatepoint,
			Set<Category> belongedCategories, Country country) {
		super();
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.frequest = frequest;
		this.introduction = introduction;
		this.length = length;
		this.fimageurl = fimageurl;
		this.fbanner = fbanner;
		this.requestEmail = requestEmail;
		this.acceptAccount = acceptAccount;
		this.requestType = requestType;
		this.fdeploy = fdeploy;
		this.fview = fview;
		this.fevaluatecount = fevaluatecount;
		this.fevaluatepoint = fevaluatepoint;
		this.belongedCategories = belongedCategories;
		this.country = country;
	}

	public Film(int fid, String fname, int fepisodecount, int fyear, int fstatus, int frequest, String introduction,
			String length, String fimageurl, String fbanner, String requestEmail, String acceptAccount,
			String requestType, int fdeploy, int fview, double fevaluatecount, double fevaluatepoint,
			Set<Category> belongedCategories, Country country) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.frequest = frequest;
		this.introduction = introduction;
		this.length = length;
		this.fimageurl = fimageurl;
		this.fbanner = fbanner;
		this.requestEmail = requestEmail;
		this.acceptAccount = acceptAccount;
		this.requestType = requestType;
		this.fdeploy = fdeploy;
		this.fview = fview;
		this.fevaluatecount = fevaluatecount;
		this.fevaluatepoint = fevaluatepoint;
		this.belongedCategories = belongedCategories;
		this.country = country;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getFepisodecount() {
		return fepisodecount;
	}

	public void setFepisodecount(int fepisodecount) {
		this.fepisodecount = fepisodecount;
	}

	public int getFyear() {
		return fyear;
	}

	public void setFyear(int fyear) {
		this.fyear = fyear;
	}

	public int getFstatus() {
		return fstatus;
	}

	public void setFstatus(int fstatus) {
		this.fstatus = fstatus;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getFimageurl() {
		return fimageurl;
	}

	public void setFimageurl(String fimageurl) {
		this.fimageurl = fimageurl;
	}

	public String getFbanner() {
		return fbanner;
	}

	public void setFbanner(String fbanner) {
		this.fbanner = fbanner;
	}

	public int getFdeploy() {
		return fdeploy;
	}

	public void setFdeploy(int fdeploy) {
		this.fdeploy = fdeploy;
	}

	public int getFview() {
		return fview;
	}

	public void setFview(int fview) {
		this.fview = fview;
	}

	public double getFevaluatecount() {
		return fevaluatecount;
	}

	public void setFevaluatecount(double fevaluatecount) {
		this.fevaluatecount = fevaluatecount;
	}

	public double getFevaluatepoint() {
		return fevaluatepoint;
	}

	public void setFevaluatepoint(double fevaluatepoint) {
		this.fevaluatepoint = fevaluatepoint;
	}

	public Set<Category> getBelongedCategories() {
		return belongedCategories;
	}

	public void setBelongedCategories(Set<Category> belongedCategories) {
		this.belongedCategories = belongedCategories;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getRequestEmail() {
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}

	public String getAcceptAccount() {
		return acceptAccount;
	}

	public void setAcceptAccount(String acceptAccount) {
		this.acceptAccount = acceptAccount;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public int getFrequest() {
		return frequest;
	}

	public void setFrequest(int frequest) {
		this.frequest = frequest;
	}

	public Date getfRequestTime() {
		return fRequestTime;
	}

	public void setfRequestTime(Date fRequestTime) {
		this.fRequestTime = fRequestTime;
	}

	public Date getfAcceptTime() {
		return fAcceptTime;
	}

	public void setfAcceptTime(Date fAcceptTime) {
		this.fAcceptTime = fAcceptTime;
	}

	@Override
	public String toString() {
		return "Film [fid=" + fid + ", fname=" + fname + ", fepisodecount=" + fepisodecount + ", fyear=" + fyear
				+ ", fstatus=" + fstatus + ", frequest=" + frequest + ", introduction=" + introduction + ", length="
				+ length + ", fimageurl=" + fimageurl + ", fbanner=" + fbanner + ", requestEmail=" + requestEmail
				+ ", acceptAccount=" + acceptAccount + ", requestType=" + requestType + ", fdeploy=" + fdeploy
				+ ", fview=" + fview + ", fevaluatecount=" + fevaluatecount + ", fevaluatepoint=" + fevaluatepoint
				+ ", fRequestTime=" + fRequestTime + ", fAcceptTime=" + fAcceptTime + ", belongedCategories="
				+ belongedCategories + ", country=" + country + "]";
	}

}
