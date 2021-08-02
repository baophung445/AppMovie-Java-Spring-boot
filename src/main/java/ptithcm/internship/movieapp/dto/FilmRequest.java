package ptithcm.internship.movieapp.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ptithcm.internship.movieapp.multipartfile.UploadForm;

public class FilmRequest {
	private String fname;
	private int fepisodecount;
	private int fyear;
	private int fstatus;
	private int fdeploy;
	private int frequest;
	private String introduction;
	private String length;
	private String furl;
	private String requestEmail;
	private String acceptAccount;
	private String requestType;
	private List<String> listFilmUrl;
	private String country_id;
	private List<String> list_category_id;
	private UploadForm upload;
	private MultipartFile[] fileDatas;

	public FilmRequest() {
		super();
	}

	public FilmRequest(String fname, int fepisodecount, int fyear, int fstatus, String introduction, String length,
			String furl, String country_id, List<String> list_category_id, UploadForm upload) {
		super();
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.introduction = introduction;
		this.length = length;
		this.furl = furl;
		this.country_id = country_id;
		this.list_category_id = list_category_id;
		this.upload = upload;
	}

	public FilmRequest(String fname, int fepisodecount, int fyear, int fstatus, String introduction, String length,
			String furl, List<String> listFilmUrl, String country_id, List<String> list_category_id,
			UploadForm upload) {
		super();
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.introduction = introduction;
		this.length = length;
		this.furl = furl;
		this.listFilmUrl = listFilmUrl;
		this.country_id = country_id;
		this.list_category_id = list_category_id;
		this.upload = upload;
	}

	public FilmRequest(String fname, int fepisodecount, int fyear, int fstatus, String introduction, String length,
			String furl, String requestEmail, String acceptAccount, List<String> listFilmUrl, String country_id,
			List<String> list_category_id, UploadForm upload) {
		super();
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.introduction = introduction;
		this.length = length;
		this.furl = furl;
		this.requestEmail = requestEmail;
		this.acceptAccount = acceptAccount;
		this.listFilmUrl = listFilmUrl;
		this.country_id = country_id;
		this.list_category_id = list_category_id;
		this.upload = upload;
	}

	public FilmRequest(String fname, int fepisodecount, int fyear, int fstatus, String introduction, String length,
			String furl, String requestEmail, String acceptAccount, String requestType, List<String> listFilmUrl,
			String country_id, List<String> list_category_id, UploadForm upload) {
		super();
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.introduction = introduction;
		this.length = length;
		this.furl = furl;
		this.requestEmail = requestEmail;
		this.acceptAccount = acceptAccount;
		this.requestType = requestType;
		this.listFilmUrl = listFilmUrl;
		this.country_id = country_id;
		this.list_category_id = list_category_id;
		this.upload = upload;
	}

	public FilmRequest(String fname, int fepisodecount, int fyear, int fstatus, int fdeploy, int frequest,
			String introduction, String length, String furl, String requestEmail, String acceptAccount,
			String requestType, List<String> listFilmUrl, String country_id, List<String> list_category_id,
			MultipartFile[] fileDatas) {
		super();
		this.fname = fname;
		this.fepisodecount = fepisodecount;
		this.fyear = fyear;
		this.fstatus = fstatus;
		this.fdeploy = fdeploy;
		this.frequest = frequest;
		this.introduction = introduction;
		this.length = length;
		this.furl = furl;
		this.requestEmail = requestEmail;
		this.acceptAccount = acceptAccount;
		this.requestType = requestType;
		this.listFilmUrl = listFilmUrl;
		this.country_id = country_id;
		this.list_category_id = list_category_id;
		this.fileDatas = fileDatas;
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

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public List<String> getList_category_id() {
		return list_category_id;
	}

	public void setList_category_id(List<String> list_category_id) {
		this.list_category_id = list_category_id;
	}

	public UploadForm getUpload() {
		return upload;
	}

	public void setUpload(UploadForm upload) {
		this.upload = upload;
	}

	public List<String> getListFilmUrl() {
		return listFilmUrl;
	}

	public void setListFilmUrl(List<String> listFilmUrl) {
		this.listFilmUrl = listFilmUrl;
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
	
	public int getFdeploy() {
		return fdeploy;
	}

	public void setFdeploy(int fdeploy) {
		this.fdeploy = fdeploy;
	}

	public int getFrequest() {
		return frequest;
	}

	public void setFrequest(int frequest) {
		this.frequest = frequest;
	}

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

	@Override
	public String toString() {
		return "FilmRequest [fname=" + fname + ", fepisodecount=" + fepisodecount + ", fyear=" + fyear + ", fstatus="
				+ fstatus + ", fdeploy=" + fdeploy + ", frequest=" + frequest + ", introduction=" + introduction
				+ ", length=" + length + ", furl=" + furl + ", requestEmail=" + requestEmail + ", acceptAccount="
				+ acceptAccount + ", requestType=" + requestType + ", listFilmUrl=" + listFilmUrl + ", country_id="
				+ country_id + ", list_category_id=" + list_category_id + ", upload=" + upload + ", fileDatas="
				+ Arrays.toString(fileDatas) + "]";
	}
	
	
}
