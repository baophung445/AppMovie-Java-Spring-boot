package ptithcm.internship.movieapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptithcm.internship.movieapp.entrity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	@Query("SELECT rp FROM Report rp ORDER BY rp.rpTime DESC")
	List<Report> findAllReports();
	@Query("SELECT rp FROM Report rp ORDER BY rp.rpTime DESC")
	List<Report> findAllReportsWithFirstAndLast(Pageable pageable);
	@Query("SELECT rp FROM Report rp WHERE rp.rpid = ?1 ORDER BY rp.rpTime DESC")
	Report findByRpId(int rpid);
	@Query("SELECT rp FROM Report rp WHERE rp.user.user_id = ?1 ORDER BY rp.rpTime DESC")
	List<Report> findAllByUserId(int uid);
	@Query("SELECT rp FROM Report rp WHERE rp.user.user_id = ?1 AND rp.resolve = false ORDER BY rp.rpTime DESC")
	List<Report> findAllByUserIdNotResolve(int uid);
	@Query("SELECT rp FROM Report rp WHERE rp.user.user_id = ?1 AND rp.resolve = false ORDER BY rp.rpTime DESC")
	List<Report> findAllByUserIdNotResolveFirstAndLast(int uid,Pageable pageable);
	@Query("SELECT rp FROM Report rp WHERE rp.rpType = ?1 ORDER BY rp.rpTime DESC")
	List<Report> findAllByRpType(int rpType);
	
	@Query("SELECT COUNT(report) FROM Report report WHERE TO_CHAR(report.rpTime, 'YYYY') = ?3 AND report.user.user_id = ?1 AND report.rpType = ?2")
	int countByUserIdAndReportTypeAndDate(int userId, int rType, String year);
}
