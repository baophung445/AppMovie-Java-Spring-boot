package ptithcm.internship.movieapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ptithcm.internship.movieapp.dto.FilmDetailRequest;
import ptithcm.internship.movieapp.dto.FilmRequest;
import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.FilmDetail;
import ptithcm.internship.movieapp.service.FilmDetailService;
import ptithcm.internship.movieapp.service.FilmService;

@RestController
@RequestMapping("/api/video")
public class FilmRestController {
	@Autowired
	private FilmDetailService filmDetailService;
	
	@Autowired
	private FilmService filmService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilmRestController.class);
	
	@GetMapping("/getVideoDetailById/{fid}/{episode}")
	public ResponseEntity<?> getFilmDetailById(@PathVariable("fid") String fid, @PathVariable("episode") String episode){
		LOGGER.info("getFilmDetailById: " + "fid=" + fid + " episode=" + episode);
		
		FilmDetail filmDetail = filmDetailService.findByFidAndEpisode(Integer.parseInt(fid), Integer.parseInt(episode));
		FilmDetailRequest filmDetailRequest = new FilmDetailRequest();
		filmDetailRequest.setFurl(filmDetail.getFurl());
		filmDetailRequest.setEpisode(filmDetail.getEpisode());
		
		return new ResponseEntity<FilmDetailRequest>(filmDetailRequest, HttpStatus.OK);
	}
	
	@GetMapping("/ratingVideo/{fid}/{point}")
	public ResponseEntity<?> ratingFilmByFilmId(@PathVariable("fid") String fid, @PathVariable("point") String point){
		LOGGER.info("ratingFilmByFilmId: " + "fid=" + fid + " point=" + point);
		
		int id = Integer.parseInt(fid);
		double rPoint = Double.parseDouble(point);
		
		Film film = filmService.findFilmById(id);
		film.setFevaluatecount(film.getFevaluatecount() + 1);
		film.setFevaluatepoint(film.getFevaluatepoint() + rPoint);
		filmService.save(film);
		
		return new ResponseEntity<FilmRequest>(new FilmRequest(), HttpStatus.OK);
	}
	
	@PostMapping("/addVideoRequest/{fid}")
	public ResponseEntity<?> addFilmRequest(@PathVariable("fid") String fid, @RequestBody FilmDetailRequest filmDetailRequest){
		LOGGER.info("addFilmRequest: " + "fid=" + fid + " filmDetailRequest=" + filmDetailRequest);
		
		int id = Integer.parseInt(fid);
		Film film = filmService.findFilmById(id);
		Date currentDate = new Date();
		
		FilmDetail filmDetail = new FilmDetail();
		filmDetail.setRequestEmail(filmDetailRequest.getRequestEmail());
		filmDetail.setEpisode(filmDetailRequest.getEpisode());
		filmDetail.setFurl(filmDetailRequest.getFurl());
		filmDetail.setFilm(film);
		filmDetail.setFddeploy(0);
		filmDetail.setFdRequestTime(currentDate);
		filmDetailService.save(filmDetail);
		
		film.setFrequest(1);
		film.setRequestType("Add episode " + filmDetailRequest.getEpisode());
		filmService.save(film);
		
		return new ResponseEntity<FilmDetailRequest>(new FilmDetailRequest(), HttpStatus.OK);
	}
}
