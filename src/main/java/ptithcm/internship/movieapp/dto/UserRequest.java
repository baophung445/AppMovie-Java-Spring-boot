package ptithcm.internship.movieapp.dto;

public class UserRequest {

    private String email;

    private String password;
    
    private String uname;

	public UserRequest(String email, String password, String uname) {
		super();
		this.email = email;
		this.password = password;
		this.uname = uname;
	}

	public UserRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "UserRequest [email=" + email + ", password=" + password + ", uname=" + uname + "]";
	}
    
    
}
