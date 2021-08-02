package ptithcm.internship.movieapp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ptithcm.internship.movieapp.dto.CommentRequest;
import ptithcm.internship.movieapp.entrity.Comment;
import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.CommentService;
import ptithcm.internship.movieapp.service.FilmService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentRestController.class);

	@PostMapping("/addComment")
	public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest, Authentication auth) {
		LOGGER.info("addComment: " + "commentRequest=" + commentRequest);

		Comment comment = new Comment();
		boolean hasRole = false;
		int cShow = commentRequest.getCommentShow();
		if (auth != null) {
			User user = userRepository.findByEmail(auth.getName());
			commentRequest.setUname(user.getUname());
			hasRole = true;
		}
		comment.setUname(commentRequest.getUname());
		comment.setContent(commentRequest.getContent());
		int fid = commentRequest.getFid();

		Film film = filmService.findFilmById(fid);

		comment.setFilm(film);
		comment.setCtime(new Date());
		commentService.save(comment);

		List<Comment> list = commentService.findAllByFidWithFirstAndLast(fid, PageRequest.of(0,cShow));
		List<CommentRequest> listCommentRequests = new ArrayList<CommentRequest>();
		DateFormat dateFormat = new SimpleDateFormat(ConstantVariable.SIMPLE_DATE_FORMAT);  
		String strDate = null;
		for (Comment c : list) {
			CommentRequest cmRequest = new CommentRequest();
			cmRequest.setCmid(c.getCmid());
			cmRequest.setFid(c.getFilm().getFid());
			cmRequest.setUname(c.getUname());
			cmRequest.setContent(c.getContent());
			cmRequest.setHaveRole(hasRole);
			strDate = dateFormat.format(c.getCtime());
			cmRequest.setCtime(strDate);
			listCommentRequests.add(cmRequest);
		}

		return new ResponseEntity<List<CommentRequest>>(listCommentRequests, HttpStatus.OK);
	}

	@GetMapping("/getAllComment")
	public ResponseEntity<?> getAllComment() {
		LOGGER.info("getAllComment: ");

		List<Comment> list = commentService.findAll();

		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/deleteComment/{id}/{commentShow}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") String id,@PathVariable("commentShow") String commentShow , Authentication auth) {
		LOGGER.info("deleteComment: " + "id=" + id);
		boolean hasRole = false;
		if (auth != null) {
			hasRole = true;
		}
		
		int cShow = Integer.parseInt(commentShow);
		int cmid = Integer.parseInt(id);
		Comment comment = commentService.findByCmid(cmid);
		int fid = comment.getFilm().getFid();
		
		commentService.delete(comment);
		
		List<Comment> list = commentService.findAllByFidWithFirstAndLast(fid, PageRequest.of(0,cShow));
		List<CommentRequest> listCommentRequests = new ArrayList<CommentRequest>();
		DateFormat dateFormat = new SimpleDateFormat(ConstantVariable.SIMPLE_DATE_FORMAT);  
		String strDate = null;
		for (Comment c : list) {
			CommentRequest cmRequest = new CommentRequest();
			cmRequest.setCmid(c.getCmid());
			cmRequest.setFid(c.getFilm().getFid());
			cmRequest.setUname(c.getUname());
			cmRequest.setContent(c.getContent());
			cmRequest.setHaveRole(hasRole);
			strDate = dateFormat.format(c.getCtime());
			cmRequest.setCtime(strDate);
			listCommentRequests.add(cmRequest);
		}

		return new ResponseEntity<List<CommentRequest>>(listCommentRequests, HttpStatus.OK);
	}
	
	@GetMapping("/moreComment/{id}/{commentShow}")
	public ResponseEntity<?> moreComment(@PathVariable("id") String id,@PathVariable("commentShow") String commentShow , Authentication auth) {
		LOGGER.info("moreComment: " + "id=" + id);
		boolean hasRole = false;
		if (auth != null) {
			hasRole = true;
		}
		
		int cShow = Integer.parseInt(commentShow);
		int fid = Integer.parseInt(id);
		
		List<Comment> list = commentService.findAllByFidWithFirstAndLast(fid, PageRequest.of(0,cShow));
		List<CommentRequest> listCommentRequests = new ArrayList<CommentRequest>();
		DateFormat dateFormat = new SimpleDateFormat(ConstantVariable.SIMPLE_DATE_FORMAT);  
		String strDate = null;
		for (Comment c : list) {
			CommentRequest cmRequest = new CommentRequest();
			cmRequest.setCmid(c.getCmid());
			cmRequest.setFid(c.getFilm().getFid());
			cmRequest.setUname(c.getUname());
			cmRequest.setContent(c.getContent());
			cmRequest.setHaveRole(hasRole);
			strDate = dateFormat.format(c.getCtime());
			cmRequest.setCtime(strDate);
			listCommentRequests.add(cmRequest);
		}

		return new ResponseEntity<List<CommentRequest>>(listCommentRequests, HttpStatus.OK);
	}
}
