package ptithcm.internship.movieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptithcm.internship.movieapp.entrity.FilmDetail;

@Repository
public interface FilmDetailRepository extends JpaRepository<FilmDetail, Integer>{
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 ORDER BY fd.episode")
	List<FilmDetail> findAllByFid(int fdid);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 AND fd.acceptAccount = ?2 ORDER BY fd.episode")
	List<FilmDetail> findAllByFidAndAcceptAccount(int fdid, String uName);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 AND fd.fddeploy = 1 ORDER BY fd.episode")
	List<FilmDetail> findAllByFidDeploy(int fid);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 AND fd.fddeploy = 0 ORDER BY fd.episode")
	List<FilmDetail> findAllByFidNoDeploy(int fid);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.fdid = ?1")
	FilmDetail findByFdid(int fdid);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 AND fd.episode = ?2")
	FilmDetail findByFidAndEpisode(int fid, int episode);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 AND fd.episode = ?2 ORDER BY fd.episode")
	List<FilmDetail> findAllByFidSearchCondition(int fid, int searchCondition);
	
	@Query("SELECT fd FROM FilmDetail fd WHERE fd.film.fid = ?1 AND fd.episode = ?2 AND fd.acceptAccount = ?3 ORDER BY fd.episode")
	List<FilmDetail> findAllByFidSearchConditionAndAcceptAccount(int fid, int searchCondition, String uName);
}
