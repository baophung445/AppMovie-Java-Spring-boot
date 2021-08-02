package ptithcm.internship.movieapp.dto;

public class FilmDetailRequest {
	private int episode;
	private String furl;
	private String requestEmail;
	private String acceptAccount;
	private int fddeploy;
	public FilmDetailRequest() {
		super();
	}
	public FilmDetailRequest(int episode, String furl) {
		super();
		this.episode = episode;
		this.furl = furl;
	}
	public FilmDetailRequest(int episode, String furl, String requestEmail, String acceptAccount, int fddeploy) {
		super();
		this.episode = episode;
		this.furl = furl;
		this.requestEmail = requestEmail;
		this.acceptAccount = acceptAccount;
		this.fddeploy = fddeploy;
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
	public int getFddeploy() {
		return fddeploy;
	}
	public void setFddeploy(int fddeploy) {
		this.fddeploy = fddeploy;
	}
	@Override
	public String toString() {
		return "FilmDetailRequest [episode=" + episode + ", furl=" + furl + ", requestEmail=" + requestEmail
				+ ", acceptAccount=" + acceptAccount + ", fddeploy=" + fddeploy + "]";
	}

}
