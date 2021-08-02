package ptithcm.internship.movieapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.FilmDetail;
import ptithcm.internship.movieapp.repository.FilmDetailRepository;

@Service
public class FilmDetailService {
	@Autowired
	private FilmDetailRepository filmDetailRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilmDetailService.class);
	
	public FilmDetail save(FilmDetail filmDetail) {
		LOGGER.info("save: " + "filmDetail=" + filmDetail);
		
		return filmDetailRepository.save(filmDetail);
	}
	
	public void delete(FilmDetail filmDetail) {
		LOGGER.info("delete: " + "filmDetail=" + filmDetail);
		
		filmDetailRepository.delete(filmDetail);
	}
	
	public List<FilmDetail> findAll(){
		LOGGER.info("findAll: ");
		
		return filmDetailRepository.findAll();
	}
	
	public List<FilmDetail> findAllByFid(int fid) {
		LOGGER.info("findAllByFid: " + "fid=" + fid);
		
		return filmDetailRepository.findAllByFid(fid);
	}
	
	public List<FilmDetail> findAllByFidAndAcceptAccount(int fdid, String uName) {
		LOGGER.info("findAllByFidAndAcceptAccount: " + "fdid=" + fdid + " uName=" + uName);
		
		return filmDetailRepository.findAllByFidAndAcceptAccount(fdid, uName);
	}
	
	public List<FilmDetail> findAllByFidSearchCondition(int fid, int searchCondition) {
		LOGGER.info("findAllByFidSearchCondition: " + "fid=" + fid + " searchCondition=" + searchCondition);
		
		return filmDetailRepository.findAllByFidSearchCondition(fid, searchCondition);
	}
	
	public List<FilmDetail> findAllByFidSearchConditionAndAcceptAccount(int fid, int searchCondition, String uName) {
		LOGGER.info("findAllByFidSearchConditionAndAcceptAccount: " + "fid=" + fid + " searchCondition=" + searchCondition + " uName=" + uName);
	
		return filmDetailRepository.findAllByFidSearchConditionAndAcceptAccount(fid, searchCondition, uName);
	}
	
	public List<FilmDetail> findAllByFidDeploy(int fid) {
		LOGGER.info("findAllByFidDeploy: " + "fid=" + fid);
		
		return filmDetailRepository.findAllByFidDeploy(fid);
	}
	
	public List<FilmDetail> findAllByFidNoDeploy(int fid){
		LOGGER.info("findAllByFidNoDeploy: " + "fid=" + fid);
		
		return filmDetailRepository.findAllByFidNoDeploy(fid);
	}
	
	public FilmDetail findByFdid(int fdid) {
		LOGGER.info("findByFdid: " + "fdid=" + fdid);
		
		return filmDetailRepository.findByFdid(fdid);
	}
	
	public FilmDetail findByFidAndEpisode(int fid, int episode) {
		LOGGER.info("findByFidAndEpisode: " + "fid=" + fid + " episode=" + episode);
		
		return filmDetailRepository.findByFidAndEpisode(fid, episode);
	}
}
