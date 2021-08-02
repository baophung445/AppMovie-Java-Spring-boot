package ptithcm.internship.movieapp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ptithcm.internship.movieapp.dto.AcceptVideoChartRequest;
import ptithcm.internship.movieapp.dto.CommentChartRequest;
import ptithcm.internship.movieapp.dto.MemberReportChartRequest;
import ptithcm.internship.movieapp.entrity.CommentChart;
import ptithcm.internship.movieapp.entrity.GroupVideoChart;
import ptithcm.internship.movieapp.entrity.MemberReportChart;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.entrity.VideoChart;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.CommentService;
import ptithcm.internship.movieapp.service.FilmService;
import ptithcm.internship.movieapp.service.ReportService;

@RestController
@RequestMapping("/admin/chart")
public class ChartController {
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChartController.class);
	
	@GetMapping(value = "/getAcceptVideoChart")
	public ResponseEntity<?> getAcceptVideoChart(){
		LOGGER.info("getAcceptVideoChart: ");
		
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("YYYY");
		String currentYear = dateFormat.format(currentDate);
		VideoChart videoChart = filmService.getVideoChart(currentYear);
		GroupVideoChart groupVideoChart = filmService.getGroupVideoChart(currentYear);
		
		return new ResponseEntity<AcceptVideoChartRequest>(new AcceptVideoChartRequest(videoChart, groupVideoChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAcceptVideoChart/{year}")
	public ResponseEntity<?> getAcceptVideoChartWithYear(@PathVariable("year") String year){
		LOGGER.info("getAcceptVideoChart: ");

		VideoChart videoChart = filmService.getVideoChart(year);
		GroupVideoChart groupVideoChart = filmService.getGroupVideoChart(year);
		
		return new ResponseEntity<AcceptVideoChartRequest>(new AcceptVideoChartRequest(videoChart, groupVideoChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCommentChart")
	public ResponseEntity<?> getCommentChart(){
		LOGGER.info("getCommentChart: ");
		
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("YYYY");
		String currentYear = dateFormat.format(currentDate);
		CommentChart commentChart = commentService.getCommentChart(currentYear);
		
		return new ResponseEntity<CommentChartRequest>(new CommentChartRequest(commentChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCommentChart/{year}")
	public ResponseEntity<?> getCommentChartWithYear(@PathVariable("year") String year){
		LOGGER.info("getCommentChartWithYear: " + "year=" + year);
		
		CommentChart commentChart = commentService.getCommentChart(year);
		
		return new ResponseEntity<CommentChartRequest>(new CommentChartRequest(commentChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getReportMemberChart")
	public ResponseEntity<?> getReportMemberChart(){
		LOGGER.info("getReportMemberChart: ");
		
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("YYYY");
		String currentYear = dateFormat.format(currentDate);
		
		User currentUser = userRepository.findByUserName("Admin");
		int currentUserId = currentUser.getUser_id();
		MemberReportChart memberReportChart = reportService.getMemberReportChart(currentUserId, currentYear);
		
		return new ResponseEntity<MemberReportChartRequest>(new MemberReportChartRequest(memberReportChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getReportMemberChart/{year}")
	public ResponseEntity<?> getReportMemberChartWithYear(@PathVariable("year") String year){
		LOGGER.info("getReportMemberChartWithYear: " + "year=" + year);
		
		User currentUser = userRepository.findByUserName("Admin");
		int currentUserId = currentUser.getUser_id();
		MemberReportChart memberReportChart = reportService.getMemberReportChart(currentUserId, year);
		
		return new ResponseEntity<MemberReportChartRequest>(new MemberReportChartRequest(memberReportChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getReportMemberChartWithUser/{userId}")
	public ResponseEntity<?> getReportMemberChartWithUserId(@PathVariable("userId") String userId){
		LOGGER.info("getReportMemberChartWithUserId: " + "userId=" + userId);

		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("YYYY");
		String currentYear = dateFormat.format(currentDate);
		MemberReportChart memberReportChart = reportService.getMemberReportChart(Integer.parseInt(userId), currentYear);
		
		return new ResponseEntity<MemberReportChartRequest>(new MemberReportChartRequest(memberReportChart), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getReportMemberChart/{year}/{userId}")
	public ResponseEntity<?> getReportMemberChartWithYearAndUserId(@PathVariable("year") String year, @PathVariable("userId") String userId){
		LOGGER.info("getReportMemberChartWithYearAndUserId: " + "year=" + year + " userId=" + userId);

		MemberReportChart memberReportChart = reportService.getMemberReportChart(Integer.parseInt(userId), year);
		
		return new ResponseEntity<MemberReportChartRequest>(new MemberReportChartRequest(memberReportChart), HttpStatus.OK);
	}
}
