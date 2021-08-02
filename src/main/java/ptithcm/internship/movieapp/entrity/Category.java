package ptithcm.internship.movieapp.entrity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class Category {
	@Id
	@Column(name = "CATEGORY_ID")
	private String cid;
	@Column(name = "CATEGORY_NAME", nullable=false)
	private String cname;
	
	@ManyToMany(mappedBy = "belongedCategories")
	Set<Film> belongs = new HashSet<>();
	
	public Category() {
		super();
	}

	public Category(String cid, String cname, Set<Film> belongs) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.belongs = belongs;
	}

	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<Film> getBelongs() {
		return belongs;
	}

	public void setBelongs(Set<Film> belongs) {
		this.belongs = belongs;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname +  "]";
	}
	
}
