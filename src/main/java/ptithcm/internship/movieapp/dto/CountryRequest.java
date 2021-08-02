package ptithcm.internship.movieapp.dto;

public class CountryRequest {
	private String coid;
	private String coname;
	public CountryRequest() {
		super();
	}
	public CountryRequest(String coid, String coname) {
		super();
		this.coid = coid;
		this.coname = coname;
	}
	public String getCoid() {
		return coid;
	}
	public void setCoid(String coid) {
		this.coid = coid;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	@Override
	public String toString() {
		return "CountryRequest [coid=" + coid + ", coname=" + coname + "]";
	}
	
}
