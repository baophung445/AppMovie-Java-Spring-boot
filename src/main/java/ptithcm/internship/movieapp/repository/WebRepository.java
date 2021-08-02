package ptithcm.internship.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ptithcm.internship.movieapp.entrity.Web;

public interface WebRepository extends JpaRepository<Web, Integer>{
	@Query("SELECT web from Web web WHERE web.wid = ?1")
	Web findByWid(int wid);
	
	@Query("SELECT web from Web web WHERE web.isUse = 1")
	Web findByHasUse();
}
