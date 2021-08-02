package ptithcm.internship.movieapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptithcm.internship.movieapp.entrity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	@Query("SELECT cm FROM Comment cm WHERE cm.cmid = ?1")
	Comment findByCmid(int cmid);
	
	@Query("SELECT cm FROM Comment cm WHERE cm.film.fid = ?1 ORDER BY cm.ctime DESC")
	List<Comment> findAllByFid(int fid);
	
	@Query("SELECT cm FROM Comment cm WHERE cm.film.fid = ?1 ORDER BY cm.ctime DESC")
	List<Comment> findAllByFidWithFirstAndLast(int fid, Pageable pageable);
	
	@Query("SELECT COUNT(comment) FROM Comment comment WHERE TO_CHAR(comment.ctime, 'YYYY') = ?1 AND TO_CHAR(comment.ctime, 'MM') = ?2")
	int countCommentsByCommentTime(String year, String month);
}
