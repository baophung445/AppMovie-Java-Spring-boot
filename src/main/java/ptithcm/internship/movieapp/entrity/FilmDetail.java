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
@Table(name = "VIDEO_DETAILS")
public class FilmDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VIDEO_DETAIL_ID")
	private int fdid;
	@Column(name = "EPISODE", nullable = false)
	private int episode;
	@Column(name = "VIDEO_URL", nullable = false)
	private String furl;
	@Column(name = "ACCEPT_ACCOUNT")
	private String acceptAccount;
	@Column(name = "REQUEST_EMAIL")
	private String requestEmail;
	@Column(name = "VIDEO_DETAIL_DEPLOY")
	private int fddeploy;
	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_TIME")
	private Date fdRequestTime;
	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPT_TIME")
	private Date fdAcceptTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id")
	private Film film;

	public FilmDetail() {
		super();
	}

	public FilmDetail(int episode, String furl, String requestEmail, int fddeploy, Date fdRequestTime, Film film) {
		super();
		this.episode = episode;
		this.furl = furl;
		this.requestEmail = requestEmail;
		this.fddeploy = fddeploy;
		this.fdRequestTime = fdRequestTime;
		this.film = film;
	}

	public FilmDetail(int episode, String furl, String acceptAccount, String requestEmail, int fddeploy,
			Date fdRequestTime, Date fdAcceptTime, Film film) {
		super();
		this.episode = episode;
		this.furl = furl;
		this.acceptAccount = acceptAccount;
		this.requestEmail = requestEmail;
		this.fddeploy = fddeploy;
		this.fdRequestTime = fdRequestTime;
		this.fdAcceptTime = fdAcceptTime;
		this.film = film;
	}

	public int getFdid() {
		return fdid;
	}

	public void setFdid(int fdid) {
		this.fdid = fdid;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public int getFddeploy() {
		return fddeploy;
	}

	public void setFddeploy(int fddeploy) {
		this.fddeploy = fddeploy;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getAcceptAccount() {
		return acceptAccount;
	}

	public void setAcceptAccount(String acceptAccount) {
		this.acceptAccount = acceptAccount;
	}

	public String getRequestEmail() {
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}

	public Date getFdRequestTime() {
		return fdRequestTime;
	}

	public void setFdRequestTime(Date fdRequestTime) {
		this.fdRequestTime = fdRequestTime;
	}

	public Date getFdAcceptTime() {
		return fdAcceptTime;
	}

	public void setFdAcceptTime(Date fdAcceptTime) {
		this.fdAcceptTime = fdAcceptTime;
	}

	@Override
	public String toString() {
		return "FilmDetail [fdid=" + fdid + ", episode=" + episode + ", furl=" + furl + ", acceptAccount="
				+ acceptAccount + ", requestEmail=" + requestEmail + ", fddeploy=" + fddeploy + ", fdRequestTime="
				+ fdRequestTime + ", fdAcceptTime=" + fdAcceptTime + ", film=" + film + "]";
	}

}
