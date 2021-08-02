package ptithcm.internship.movieapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.Comment;
import ptithcm.internship.movieapp.entrity.CommentChart;
import ptithcm.internship.movieapp.repository.CommentRepository;
import ptithcm.internship.movieapp.utils.MovieAppConstant;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	
	public Comment findByCmid(int cmid) {
		LOGGER.info("findByCmid: " + "cmid=" + cmid);
		
		return commentRepository.findByCmid(cmid);
	}
	
	public List<Comment> findAll(){
		LOGGER.info("findByCmid");
		
		return commentRepository.findAll();
	}
	
	public List<Comment> findAllByFid(int fid){
		LOGGER.info("findAllByFid: " + "fid=" + fid);
		
		return commentRepository.findAllByFid(fid);
	} 
	
	public List<Comment> findAllByFidWithFirstAndLast(int fid, Pageable pageable) {
		return commentRepository.findAllByFidWithFirstAndLast(fid, pageable);
	}
	
	public Comment save(Comment comment) {
		LOGGER.info("save: " + "comment=" + comment);
		
		return commentRepository.save(comment);
	}
	
	public void delete(Comment comment) {
		LOGGER.info("delete: " + "comment=" + comment);
		
		commentRepository.delete(comment);
	}
	
	public int countCommentsByCommentTime(String year, String month) {
		LOGGER.info("countCommentsByCommentTime: " + "year=" + year + " month=" + month);
		
		return commentRepository.countCommentsByCommentTime(year, month);
	}
	
	public CommentChart getCommentChart(String year) {
		LOGGER.info("getCommentChart: " + "year=" + year);
		
		CommentChart commentChart = new CommentChart();
		
		commentChart.setcJanurary(countCommentsByCommentTime(year, MovieAppConstant.MONTH_JANURARY));
		commentChart.setcFebruary(countCommentsByCommentTime(year, MovieAppConstant.MONTH_FEBRUARY));
		commentChart.setcMarch(countCommentsByCommentTime(year, MovieAppConstant.MONTH_MARCH));
		commentChart.setcApril(countCommentsByCommentTime(year, MovieAppConstant.MONTH_APRIL));
		commentChart.setcMay(countCommentsByCommentTime(year, MovieAppConstant.MONTH_MAY));
		commentChart.setcJune(countCommentsByCommentTime(year, MovieAppConstant.MONTH_JUNE));
		commentChart.setcJuly(countCommentsByCommentTime(year, MovieAppConstant.MONTH_JULY));
		commentChart.setcAugust(countCommentsByCommentTime(year, MovieAppConstant.MONTH_AUGUST));
		commentChart.setcSeptember(countCommentsByCommentTime(year, MovieAppConstant.MONTH_SEPTEMBER));
		commentChart.setcOctober(countCommentsByCommentTime(year, MovieAppConstant.MONTH_OCTOBER));
		commentChart.setcNovember(countCommentsByCommentTime(year, MovieAppConstant.MONTH_NOVEMBER));
		commentChart.setcDecember(countCommentsByCommentTime(year, MovieAppConstant.MONTH_DECEMBER));
		
		return commentChart;
	}
}
