package ptithcm.internship.movieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptithcm.internship.movieapp.entrity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
	@Query("select category from Category category inner join category.belongs belongs where belongs.fid = ?1")
	List<Category> findCategoriesByFid(int fid);
	
	@Query("select category from Category category WHERE category.cid = ?1")
	Category findByCategoryId(String cid);
}
