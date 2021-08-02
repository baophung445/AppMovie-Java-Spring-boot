package ptithcm.internship.movieapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.MemberReportChart;
import ptithcm.internship.movieapp.entrity.Report;
import ptithcm.internship.movieapp.repository.ReportRepository;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
	
	public Report save(Report report) {
		LOGGER.info("save: " + "report=" + report);
		
		return reportRepository.save(report);
	}
	
	public void delete(Report report) {
		LOGGER.info("delete: " + "report=" + report);
		
		reportRepository.delete(report);
	}
	
	public Report findByRpId(int rpid) {
		LOGGER.info("findByRpId: " + "rpid=" + rpid);
		
		return reportRepository.findByRpId(rpid);
	}
	
	public List<Report> findAll(){
		LOGGER.info("findAll:");
		
		return reportRepository.findAll();
	}
	
	public List<Report> findAllReports(){
		LOGGER.info("findAllReports: ");
		
		return reportRepository.findAllReports();
	}
	
	public List<Report> findAllByUserId(int uid){
		LOGGER.info("findAllByUserId: " + "uid=" + uid);
		List<Report> listReport = reportRepository.findAllByUserId(uid);
		
		if (listReport == null) {
			listReport = new ArrayList<Report>();
		}
		
		return listReport;
	}
	
	public List<Report> findAllByUserIdNotResolve(int uid){
		LOGGER.info("findAllByUserIdNotResolve: " + "uid=" + uid);
		List<Report> listReport = reportRepository.findAllByUserIdNotResolve(uid);
		
		if (listReport == null) {
			listReport = new ArrayList<Report>();
		}
		
		return listReport;
	}
	
	public List<Report> findAllByUserIdNotResolveFirstAndLast(int uid,Pageable pageable){
		LOGGER.info("findAllByUserIdNotResolveFirstAndLast: " + "uid=" + uid);
		
		List<Report> listReport = reportRepository.findAllByUserIdNotResolveFirstAndLast(uid, pageable);
		
		if (listReport == null) {
			listReport = new ArrayList<Report>();
		}
		
		return listReport;
	}
	
	public List<Report> findAllByRpType(int rpType){
		LOGGER.info("findAllByRpType: " + "rpType: " + rpType);
		
		return reportRepository.findAllByRpType(rpType);
	}
	
	public List<Report> findAllReportsWithFirstAndLast(Pageable pageable){
		LOGGER.info("findAllReportsWithFirstAndLast: " + "pageable" + pageable);
		
		return reportRepository.findAllReportsWithFirstAndLast(pageable);
	}
	
	public int countByUserIdAndReportTypeAndDate(int userId, int rType, String year) {
		LOGGER.info("countByUserIdAndDate: " + "userId=" + userId + " rType=" + rType +" year=" + year);
		
		return reportRepository.countByUserIdAndReportTypeAndDate(userId, rType, year);
	}
	
	public MemberReportChart getMemberReportChart(int userId, String year) {
		LOGGER.info("getMemberReportChart: " + "userId=" + userId + " year=" + year);
		
		MemberReportChart memberReportChart = new MemberReportChart();
		memberReportChart.setrTypeError(countByUserIdAndReportTypeAndDate(userId, ConstantVariable.REPORT_TYPE_ERROR, year));
		memberReportChart.setrTypeWarning(countByUserIdAndReportTypeAndDate(userId, ConstantVariable.REPORT_TYPE_WARNING, year));
		memberReportChart.setrTypeOk(countByUserIdAndReportTypeAndDate(userId, ConstantVariable.REPORT_TYPE_OK, year));
		
		return memberReportChart;
	}
}
