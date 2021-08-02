package ptithcm.internship.movieapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.internship.movieapp.dto.FilmDetailRequest;
import ptithcm.internship.movieapp.dto.FilmRequest;
import ptithcm.internship.movieapp.entrity.Category;
import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.FilmDetail;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.CategoryService;
import ptithcm.internship.movieapp.service.FilmDetailService;
import ptithcm.internship.movieapp.service.FilmService;
import ptithcm.internship.movieapp.service.SendMailService;
import ptithcm.internship.movieapp.service.WebService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@Controller
public class MemberController {
	@Autowired
	private FilmService filmService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FilmDetailService filmDetailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SendMailService mailService;

	@Autowired
	private WebService webService;

	@ModelAttribute("web")
	public Web getWeb() {
		return webService.findByHasUse();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

	@Value("${web.domain}")
	private String webDomain;

	@RequestMapping("/request-manager/{page}")
	public String requestManager(Model model, @PathVariable("page") int page) {
		LOGGER.info("requestManager");

		if (page < 1) {
			return "redirect:/request-manager/" + 1;
		}

		List<Film> listFilmNoDeployOrHasRequest;

		listFilmNoDeployOrHasRequest = filmService
				.findFilmsNoDeployOrHasRequestWithFirstAndLast(PageRequest.of((page - 1), ConstantVariable.MAX_ITEMS));

		if (listFilmNoDeployOrHasRequest == null) {
			listFilmNoDeployOrHasRequest = new ArrayList<Film>();
		}

		List<Film> listAllFilmNoDeployOrHasRequest = filmService.findFilmsNoDeployOrHasRequest();

		int pageCount = (int) Math.ceil(listAllFilmNoDeployOrHasRequest.size() / (ConstantVariable.MAX_ITEMS * 1d));
		if (page > pageCount && page != 1) {
			return "redirect:/request-manager/" + pageCount;
		}
		int currentPage = page;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}

		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("listFilmNoDeployOrHasRequest", listFilmNoDeployOrHasRequest);
		return "request_manager";
	}

	@RequestMapping("/review-has-role/{id}")
	public String getFilmInfoWithRole(Model model, @PathVariable("id") int id) {
		LOGGER.info("getFilmInfoWithRole: " + "id=" + id);

		Film film = filmService.findFilmById(id);
		if (film.getFdeploy() == 0 || film.getFrequest() == 1) {
			List<Category> listCategory = categoryService.findAllCategoryByFid(id);
			String categories = "";
			for (Category c : listCategory) {
				categories = categories + "/ " + c.getCname();
			}
			categories = categories.substring(1);
			model.addAttribute("categories", categories);
			model.addAttribute("film", film);
			return "review_video_role";
		}
		return "403";
	}

	@RequestMapping("/watch-has-role/{id}")
	public String getFilmToWatchWithRole(Model model, @PathVariable("id") int id) {
		LOGGER.info("getFilmToWatchWithRole: " + "id=" + id);

		Film film = filmService.findFilmById(id);
		List<FilmDetail> listFilmDetail = filmDetailService.findAllByFid(id);
		if (film.getFdeploy() == 0 || film.getFrequest() == 1) {
			model.addAttribute("webDomain", webDomain);
			model.addAttribute("film", film);
			model.addAttribute("filmDetail", listFilmDetail.get(0));
			model.addAttribute("listFilmDetail", listFilmDetail);
			return "watch_video";
		}
		return "403";
	}

	@RequestMapping("accept-video-request/{fid}")
	public String acceptFilmRequest(Model model, @PathVariable("fid") int fid,
			@ModelAttribute("message") String message, Authentication auth) {
		LOGGER.info("acceptFilmRequest: " + "fid=" + fid + " message=" + message + "mailService=" + mailService);

		String email = auth.getName();
		User user = userRepository.findByEmail(email);

		Film film = filmService.findFilmById(fid);

		Date currentDate = new Date();

		if (film.getFdeploy() == 0) {
			film.setFdeploy(1);
			film.setAcceptAccount(user.getUname());
			film.setfAcceptTime(currentDate);

			List<FilmDetail> listFilmDetails = filmDetailService.findAllByFid(fid);
			for (FilmDetail fd : listFilmDetails) {
				fd.setFddeploy(1);
				fd.setAcceptAccount(user.getUname());
				fd.setFdAcceptTime(currentDate);
				filmDetailService.save(fd);
			}
			mailService.sendEmail(film.getRequestEmail(),
					"[" + film.getFname() + "]" + ConstantVariable.ACCEPT_MAIL_SUBJECT,
					ConstantVariable.ACCEPT_MAIL_BODY);
		} else {
			if (film.getFrequest() == 1) {
				List<FilmDetail> listFilmDetails = filmDetailService.findAllByFidNoDeploy(fid);
				String emailSend = "";
				for (FilmDetail fd : listFilmDetails) {
					if (fd.getFddeploy() == 0) {
						fd.setFddeploy(1);
						fd.setAcceptAccount(user.getUname());
						fd.setFdAcceptTime(currentDate);
						filmDetailService.save(fd);
						emailSend = fd.getRequestEmail();
					}
				}
				film.setFrequest(0);
				filmService.save(film);
				mailService.sendEmail(emailSend,
						"[" + film.getFname() + "]" + ConstantVariable.ACCEPT_EPISODE_MAIL_SUBJECT,
						ConstantVariable.ACCEPT_EPISODE_MAIL_BODY);
			}

		}

		filmService.save(film);
		model.addAttribute("message", "Accept request OK!");
		model.addAttribute("film", film);
		return "review_video_role";
	}

	@RequestMapping("delete-video-request/{fid}")
	public String deleteFilmRequest(Model model, @PathVariable("fid") int fid,
			@ModelAttribute("message") String message) {
		LOGGER.info("deleteFilmRequest: " + "fid=" + fid + " message=" + message + "mailService=" + mailService);
		
		List<FilmDetail> listFilmDetails;

		Film film = filmService.findFilmById(fid);
		if (film.getFdeploy() == 0) {
			listFilmDetails = filmDetailService.findAllByFidNoDeploy(fid);
			for (FilmDetail fd : listFilmDetails) {
				filmDetailService.delete(fd);
			}
			filmService.delete(film);
			mailService.sendEmail(film.getRequestEmail(),
					"[" + film.getFname() + "]" + ConstantVariable.DELETE_MAIL_SUBJECT,
					ConstantVariable.DELETE_MAIL_BODY);
		}

		if (film.getFdeploy() == 1 && film.getFrequest() == 1) {
			listFilmDetails = filmDetailService.findAllByFidNoDeploy(fid);
			String emailSend = "";
			for (FilmDetail fd : listFilmDetails) {
				emailSend = fd.getRequestEmail();
				filmDetailService.delete(fd);
			}
			film.setFrequest(0);
			filmService.save(film);
			mailService.sendEmail(emailSend, "[" + film.getFname() + "]" + ConstantVariable.DELETE_EPISODE_MAIL_SUBJECT,
					ConstantVariable.DELETE_EPISODE_MAIL_BODY);
		}
		return "redirect:/request-manager/1";
	}

	@RequestMapping("/video-manager")
	public String filmManager(Model model, @RequestParam("searchCondition") String searchCondition,
			Authentication auth) {
		LOGGER.info("filmManager: " + "searchCondition=" + searchCondition);

		User currentUser = userRepository.findByEmail(auth.getName());

		List<Film> listFilm;
		if (searchCondition == null || searchCondition.isEmpty()) {
			listFilm = filmService.findFilmsByAcceptAccount(currentUser.getUname());
		} else {
			listFilm = filmService.findFilmsByTextSearchAndAcceptAccount(searchCondition, currentUser.getUname());
		}

		if (listFilm == null) {
			listFilm = new ArrayList<Film>();
		}

		model.addAttribute("listFilm", listFilm);
		model.addAttribute("webDomain", webDomain);

		return "video_manager";
	}

	@PostMapping("member/editVideo/{fid}")
	@ResponseBody
	public ResponseEntity<?> editFilmRest(FilmRequest filmRequest, @PathVariable("fid") String fid) {
		LOGGER.info("editFilmRest: " + " filmRequest=" + filmRequest + " fid=" + fid);

		int id = Integer.parseInt(fid);
		Film film = filmService.findFilmById(id);
		film.setFname(filmRequest.getFname());
		film.setFyear(filmRequest.getFyear());
		film.setLength(filmRequest.getLength());
		film.setIntroduction(filmRequest.getIntroduction());
		;
		film.setFdeploy(filmRequest.getFdeploy());
		film.setFrequest(filmRequest.getFrequest());
		film.setFepisodecount(filmRequest.getFepisodecount());
		film.setRequestEmail(filmRequest.getRequestEmail());
		film.setAcceptAccount(filmRequest.getAcceptAccount());

		String uploadRootPath = ConstantVariable.UPLOAD_IMAGE_REAL_PATH;

		File uploadRootDir = new File(uploadRootPath);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = filmRequest.getFileDatas();
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();
		Date date = new Date();
		int count = 0;
		String name = null;
		for (MultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			if (count == 0)
				name = date.getTime() + "_banner_" + fileData.getOriginalFilename();
			else
				name = date.getTime() + "_image_" + fileData.getOriginalFilename();

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
				} catch (Exception e) {
					failedFiles.add(name);
				}
			}
			count++;
		}

		MultipartFile fileDataBanner = filmRequest.getFileDatas()[0];
		MultipartFile fileDataImage = filmRequest.getFileDatas()[1];
		if (fileDataBanner != null && !fileDataImage.getOriginalFilename().isEmpty()) {
			film.setFimageurl(date.getTime() + "_image_" + fileDataImage.getOriginalFilename());
		}

		if (fileDataBanner != null && !fileDataBanner.getOriginalFilename().isEmpty()) {
			film.setFbanner(date.getTime() + "_banner_" + fileDataBanner.getOriginalFilename());
		}

		filmService.save(film);

		return new ResponseEntity<FilmRequest>(new FilmRequest(), HttpStatus.OK);
	}

	@RequestMapping("member/videoDetail/{fid}")
	public String filmDetail(Model model, @PathVariable("fid") String fid,
			@RequestParam("searchCondition") String searchCondition, Authentication auth) {
		LOGGER.info("filmDetail: " + " fid=" + fid + " searchCondition=" + searchCondition + " auth=" + auth);

		User currentUser = userRepository.findByEmail(auth.getName());

		int id = Integer.parseInt(fid);
		Film film = filmService.findFilmByIdAndAcceptAccount(id, currentUser.getUname());
		List<FilmDetail> listFilmDetail;

		if (searchCondition == "" || searchCondition.isEmpty()) {
			listFilmDetail = filmDetailService.findAllByFidAndAcceptAccount(id, currentUser.getUname());
		} else {
			listFilmDetail = filmDetailService.findAllByFidSearchConditionAndAcceptAccount(id,
					Integer.parseInt(searchCondition), currentUser.getUname());
		}

		model.addAttribute("film", film);
		model.addAttribute("listFilmDetail", listFilmDetail);
		model.addAttribute("webDomain", webDomain);

		return "video_detail_manager";
	}

	@PostMapping("member/editVideoDetail/{fdid}")
	@ResponseBody
	public ResponseEntity<?> editFilmDetailRest(@RequestBody FilmDetailRequest filmDetailRequest,
			@PathVariable("fdid") String fdid) {
		LOGGER.info("editFilmDetailRest: " + " filmDetailRequest=" + filmDetailRequest + " fdid=" + fdid);

		int id = Integer.parseInt(fdid);
		FilmDetail filmDetail = filmDetailService.findByFdid(id);
		filmDetail.setEpisode(filmDetailRequest.getEpisode());
		filmDetail.setFurl(filmDetailRequest.getFurl());
		filmDetail.setFddeploy(filmDetailRequest.getFddeploy());

		filmDetailService.save(filmDetail);

		return new ResponseEntity<FilmDetailRequest>(new FilmDetailRequest(), HttpStatus.OK);
	}
}
