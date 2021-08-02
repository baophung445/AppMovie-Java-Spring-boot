package ptithcm.internship.movieapp.entrity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WEB_TEMPLATES")
public class Web {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WEB_ID")
	private int wid;
	@Column(name = "WEB_NAME", nullable = false)
	private String wname;
	@Column(name = "WEB_TITLE", nullable = false)
	private String wtitle;
	@Column(name = "WEB_LOGO_URL", nullable = false)
	private String wlogoUrl;
	@Column(name = "WEB_GUEST_AVATAR_URL", nullable = false)
	private String wguestAvatartUrl;
	@Column(name = "WEB_MEMBER_AVATAR_URL", nullable = false)
	private String wmemberAvatartUrl;
	@Column(name = "WEB_ADMIN_AVATAR_URL", nullable = false)
	private String wadminAvatarUrl;
	@Column(name = "IS_IN_USE")
	private int isUse;
	public Web() {
		super();
	}
	
	public Web(String wname, String wtitle, String wlogoUrl, String wguestAvatartUrl, String wmemberAvatartUrl,
			String wadminAvatarUrl, int isUse) {
		super();
		this.wname = wname;
		this.wtitle = wtitle;
		this.wlogoUrl = wlogoUrl;
		this.wguestAvatartUrl = wguestAvatartUrl;
		this.wmemberAvatartUrl = wmemberAvatartUrl;
		this.wadminAvatarUrl = wadminAvatarUrl;
		this.isUse = isUse;
	}

	public Web(int wid, String wname, String wtitle, String wlogoUrl, String wguestAvatartUrl, String wmemberAvatartUrl,
			String wadminAvatarUrl, int isUse) {
		super();
		this.wid = wid;
		this.wname = wname;
		this.wtitle = wtitle;
		this.wlogoUrl = wlogoUrl;
		this.wguestAvatartUrl = wguestAvatartUrl;
		this.wmemberAvatartUrl = wmemberAvatartUrl;
		this.wadminAvatarUrl = wadminAvatarUrl;
		this.isUse = isUse;
	}

	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
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
	public String getWlogoUrl() {
		return wlogoUrl;
	}
	public void setWlogoUrl(String wlogoUrl) {
		this.wlogoUrl = wlogoUrl;
	}
	public String getWguestAvatartUrl() {
		return wguestAvatartUrl;
	}
	public void setWguestAvatartUrl(String wguestAvatartUrl) {
		this.wguestAvatartUrl = wguestAvatartUrl;
	}
	public String getWmemberAvatartUrl() {
		return wmemberAvatartUrl;
	}
	public void setWmemberAvatartUrl(String wmemberAvatartUrl) {
		this.wmemberAvatartUrl = wmemberAvatartUrl;
	}
	public String getWadminAvatarUrl() {
		return wadminAvatarUrl;
	}
	public void setWadminAvatarUrl(String wadminAvatarUrl) {
		this.wadminAvatarUrl = wadminAvatarUrl;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	@Override
	public String toString() {
		return "Web [wid=" + wid + ", wname=" + wname + ", wtitle=" + wtitle + ", wlogoUrl=" + wlogoUrl
				+ ", wguestAvatartUrl=" + wguestAvatartUrl + ", wmemberAvatartUrl=" + wmemberAvatartUrl
				+ ", wadminAvatarUrl=" + wadminAvatarUrl + ", isUse=" + isUse + "]";
	}
	
	
}
