package ptithcm.internship.movieapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.repository.WebRepository;

@Service
public class WebService {
	@Autowired
	private WebRepository webRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebService.class);
	
	public List<Web> findAllWeb(){
		LOGGER.info("findAllWeb:");
		
		return webRepository.findAll();
	}
	
	public Web findByWid(int wid) {
		LOGGER.info("findByWid: " + "wid=" + wid);
		
		return webRepository.findByWid(wid);
	}
	
	public Web findByHasUse() {
		LOGGER.info("findByHasUse: ");
		
		return webRepository.findByHasUse();
	}
	
	public Web save(Web web) {
		LOGGER.info("save: " + "web=" + web);
		
		return webRepository.save(web);
	}
	
	public void delete(Web web) {
		LOGGER.info("delete: " + "web=" + web);
		
		webRepository.delete(web);
	}
}
