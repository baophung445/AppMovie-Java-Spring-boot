package ptithcm.internship.movieapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import ptithcm.internship.movieapp.dto.CategoryRequest;
import ptithcm.internship.movieapp.dto.CountryRequest;
import ptithcm.internship.movieapp.dto.FilmDetailRequest;
import ptithcm.internship.movieapp.dto.FilmRequest;
import ptithcm.internship.movieapp.dto.UserRequest;
import ptithcm.internship.movieapp.dto.WebRequest;
import ptithcm.internship.movieapp.entrity.Category;
import ptithcm.internship.movieapp.entrity.Country;
import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.FilmDetail;
import ptithcm.internship.movieapp.entrity.Role;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.helper.YearStringComparator;
import ptithcm.internship.movieapp.multipartfile.UploadForm;
import ptithcm.internship.movieapp.repository.RoleRepository;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.CategoryService;
import ptithcm.internship.movieapp.service.CountryService;
import ptithcm.internship.movieapp.service.FilmDetailService;
import ptithcm.internship.movieapp.service.FilmService;
import ptithcm.internship.movieapp.service.SendMailService;
import ptithcm.internship.movieapp.service.WebService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private FilmDetailService filmDetailService;

	@Autowired
	private WebService webService;

	@ModelAttribute("web")
	public Web getWeb() {
		return webService.findByHasUse();
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private SendMailService mailService;

	@Value("${web.domain}")
	private String webDomain;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping("")
	public String getAdminPage(Model model) {
		return "admin";
	}

	@RequestMapping("/accountManager")
	public String accountManager(Model model) {
		LOGGER.info("accountManager:");

		List<User> listUser = userRepository.findAll();

		model.addAttribute("listUser", listUser);
		model.addAttribute("webDomain", webDomain);

		return "account_manager";
	}

	@RequestMapping("/deleteAccount/{uid}")
	public String deleteAccount(Model model, @PathVariable("uid") String uid) {
		try {
			LOGGER.info("deleteAccount: " + "uid=" + uid);
			int userId = Integer.parseInt(uid);
			User user = userRepository.findByUid(userId);
			String email = user.getEmail();
			userRepository.deleteById(userId);
			mailService.sendEmail(email, ConstantVariable.DELETE_ACCOUNT_SUBJECT, ConstantVariable.DELETE_ACCOUNT_BODY);

			return "redirect:/admin/accountManager";
		} catch (Exception e) {
			return "redirect:/admin/accountManager";
		}

	}

	@RequestMapping("/editAccount/{uid}/{newUserName}/{newPassword}")
	public String editAccount(Model model, @PathVariable("uid") String uid, @PathVariable("newUserName") String nName,
			@PathVariable("newPassword") String nPassword) {
		LOGGER.info("editAccount: " + "uid=" + uid + " nName=" + nName + " nPassword=" + nPassword);

		int userId = Integer.parseInt(uid);

		User user = userRepository.findByUid(userId);
		user.setPassword(passwordEncoder.encode(nPassword));
		user.setUname(nName);

		userRepository.save(user);

		return "redirect:/admin/accountManager";
	}

	@RequestMapping("/createAccount/{email}/{password}/{uname}")
	public String createAccount(Model model, @PathVariable("email") String email,
			@PathVariable("password") String password, @PathVariable("uname") String uname) {
		LOGGER.info("createAccount: " + "email=" + email + " password=" + password + " uname=" + uname);

		User user = new User();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setUname(uname);
		HashSet<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("ROLE_MEMBER"));
		user.setRoles(roles);

		userRepository.save(user);

		return "redirect:/admin/accountManager";
	}

	@PostMapping("/api/createAccount")
	@ResponseBody
	public ResponseEntity<?> createAccountRest(@RequestBody UserRequest userRequest) {
		LOGGER.info("createAccountRest: " + " userRequest=" + userRequest);

		User user = new User();
		user.setEmail(userRequest.getEmail());

		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setUname(userRequest.getUname());

		HashSet<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("ROLE_MEMBER"));
		user.setRoles(roles);

		userRepository.save(user);

		String email = user.getEmail();

		mailService.sendEmail(email, ConstantVariable.CREATE_ACCOUNT_SUBJECT,
				ConstantVariable.CREATE_ACCOUNT_BODY + userRequest.getPassword());

		return new ResponseEntity<UserRequest>(new UserRequest(), HttpStatus.OK);
	}

	@RequestMapping("/countryManager")
	public String countryManager(Model model) {
		LOGGER.info("countryManager:");

		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("listCountry", listCountry);
		model.addAttribute("webDomain", webDomain);

		return "country_manager";
	}

	@PostMapping("/api/createCountry")
	@ResponseBody
	public ResponseEntity<?> createCountryRest(@RequestBody CountryRequest countryRequest) {
		LOGGER.info("createCountryRest: " + " countryRequest=" + countryRequest);

		Country country = new Country();
		country.setCoid(countryRequest.getCoid());
		country.setConame(countryRequest.getConame());
		countryService.save(country);

		return new ResponseEntity<CountryRequest>(new CountryRequest(), HttpStatus.OK);
	}

	@RequestMapping("/deleteCountry/{countryId}")
	public String deleteCountry(Model model, @PathVariable("countryId") String countryId) {

		try {
			LOGGER.info("deleteCountry: " + "countryId=" + countryId);
			Country country = countryService.findById(countryId);
			countryService.delete(country);
			return "redirect:/admin/countryManager";
		} catch (Exception e) {
			return "redirect:/admin/countryManager";
		}

	}

	@RequestMapping("/editCountry/{countryId}/{newCountryName}")
	public String editCountry(@PathVariable("countryId") String countryId,
			@PathVariable("newCountryName") String newCountryName) {
		LOGGER.info("editCountry: " + "countryId=" + countryId + " newCountryName=" + newCountryName);

		Country country = countryService.findById(countryId);
		country.setConame(newCountryName);
		countryService.save(country);

		return "redirect:/admin/countryManager";
	}

	@RequestMapping("/categoryManager")
	public String categoryManager(Model model) {
		LOGGER.info("categoryManager:");

		List<Category> listCategory = categoryService.findAllCategory();

		model.addAttribute("listCategory", listCategory);
		model.addAttribute("webDomain", webDomain);

		return "category_manager";
	}

	@PostMapping("/api/createCategory")
	@ResponseBody
	public ResponseEntity<?> createCategoryRest(@RequestBody CategoryRequest categoryRequest) {
		LOGGER.info("createCategoryRest: " + " categoryRequest=" + categoryRequest);

		Category category = new Category();
		category.setCid(categoryRequest.getCid());
		category.setCname(categoryRequest.getCname());
		categoryService.save(category);

		return new ResponseEntity<CategoryRequest>(new CategoryRequest(), HttpStatus.OK);
	}

	@RequestMapping("/deleteCategory/{categoryId}")
	public String deleteCategory(Model model, @PathVariable("categoryId") String categoryId) {
		try {
			LOGGER.info("deleteCategory: " + "categoryId=" + categoryId);
			Category category = categoryService.findByCategoryId(categoryId);
			categoryService.delete(category);
			return "redirect:/admin/categoryManager";
		
			
		} catch (Exception e) {
				
			return "redirect:/admin/categoryManager";
		}

	}

	@RequestMapping("/editCategory/{categoryId}/{newCategoryName}")
	public String editCategory(@PathVariable("categoryId") String categoryId,
			@PathVariable("newCategoryName") String newCategoryName) {
		LOGGER.info("editCategory: " + "categoryId=" + categoryId + " newCategoryName=" + newCategoryName);

		Category category = categoryService.findByCategoryId(categoryId);
		category.setCname(newCategoryName);
		categoryService.save(category);

		return "redirect:/admin/categoryManager";
	}

	@RequestMapping("/webManager")
	public String webManager(Model model) {
		List<Web> listWeb = webService.findAllWeb();

		model.addAttribute("numberOfWebTemplate", listWeb.size());
		model.addAttribute("listWeb", listWeb);
		model.addAttribute("WebRequest", new WebRequest());
		return "web_manager";
	}

	@PostMapping("/add-new-web")
	public String addNewWeb(Model model, @ModelAttribute("WebRequest") WebRequest webRequest) {
		LOGGER.info("addNewWeb: " + " webRequest=" + webRequest);

		UploadForm upload = webRequest.getUpload();

		// Thư mục gốc upload file.
		String uploadRootPath = ConstantVariable.UPLOAD_IMAGE_REAL_PATH;

		File uploadRootDir = new File(uploadRootPath);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = upload.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();
		Date date = new Date();
		int count = 0;
		String name = null;
		for (MultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			switch (count) {
			case 0:
				name = date.getTime() + "_logo_" + fileData.getOriginalFilename();
				break;
			case 1:
				name = date.getTime() + "_guest_" + fileData.getOriginalFilename();
				break;
			case 2:
				name = date.getTime() + "_member_" + fileData.getOriginalFilename();
				break;
			case 3:
				name = date.getTime() + "_admin_" + fileData.getOriginalFilename();
				break;
			}

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

		MultipartFile fileDataLogo = webRequest.getUpload().getFileDatas()[0];
		MultipartFile fileDataGuest = webRequest.getUpload().getFileDatas()[1];
		MultipartFile fileDataMember = webRequest.getUpload().getFileDatas()[2];
		MultipartFile fileDataAdmin = webRequest.getUpload().getFileDatas()[3];

		Web web = new Web();
		web.setWname(webRequest.getWname());
		web.setWtitle(webRequest.getWtitle());
		web.setWlogoUrl(date.getTime() + "_logo_" + fileDataLogo.getOriginalFilename());
		web.setWguestAvatartUrl(date.getTime() + "_guest_" + fileDataGuest.getOriginalFilename());
		web.setWmemberAvatartUrl(date.getTime() + "_member_" + fileDataMember.getOriginalFilename());
		web.setWadminAvatarUrl(date.getTime() + "_admin_" + fileDataAdmin.getOriginalFilename());
		web.setIsUse(0);
		webService.save(web);

		return "redirect:/admin/webManager";
	}

	@RequestMapping("/deleteWeb/{webId}")
	public String deleteWeb(@PathVariable("webId") String webId) {
		try {
			LOGGER.info("deleteWeb: " + "webId=" + webId);
			int wid = Integer.parseInt(webId);
			Web web = webService.findByWid(wid);
			webService.delete(web);
			return "redirect:/admin/webManager";
		} catch (Exception e) {
			return "redirect:/admin/webManager";
		}

	}

	@RequestMapping("/editWeb/{webId}/{newWebName}/{newWebTitle}")
	public String editWeb(@PathVariable("webId") String webId, @PathVariable("newWebName") String newWebName,
			@PathVariable("newWebTitle") String newWebTitle) {
		LOGGER.info("editWeb: " + "webId=" + webId + " newWebName=" + newWebName + " newWebTitle=" + newWebTitle);

		int wid = Integer.parseInt(webId);
		Web web = webService.findByWid(wid);
		web.setWname(newWebName);
		web.setWtitle(newWebTitle);
		webService.save(web);

		return "redirect:/admin/webManager";
	}

	@RequestMapping("/useWeb/{webId}")
	public String useWeb(@PathVariable("webId") String webId) {
		LOGGER.info("useWeb: " + "webId=" + webId);

		int wid = Integer.parseInt(webId);
		Web web = webService.findByWid(wid);
		List<Web> listWeb = webService.findAllWeb();
		for (Web w : listWeb) {
			if (w.getIsUse() == 1) {
				w.setIsUse(0);
				webService.save(w);
				break;
			}
		}
		web.setIsUse(1);
		webService.save(web);

		return "redirect:/admin/webManager";
	}

	/*
	 * @RequestMapping("/filmManagerAdvance") public String filmManagerAdvance(Model
	 * model) { LOGGER.info("filmManagerAdvance: ");
	 * 
	 * List<Film> listFilm = filmService.findAll(); model.addAttribute("listFilm",
	 * listFilm); model.addAttribute("webDomain", webDomain);
	 * 
	 * return "film_manager_advance"; }
	 */

	@RequestMapping("/videoManagerAdvance")
	public String filmManagerAdvance(Model model,
			@RequestParam(name = "searchCondition", required = false) String searchCondition) {
		LOGGER.info("filmManagerAdvance: ");

		List<Film> listFilm;
		if (searchCondition == null || searchCondition.isEmpty()) {
			listFilm = filmService.findAll();
		} else {
			listFilm = filmService.findFilmsByTextSearch(searchCondition);
		}

		model.addAttribute("listFilm", listFilm);
		model.addAttribute("webDomain", webDomain);

		return "video_manager_advance";
	}

	@PostMapping("editVideo/{fid}")
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

			if (fileData.getOriginalFilename() != null && fileData.getOriginalFilename().length() > 0) {
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

	@RequestMapping("deleteFilm/{fid}")
	public String deleteFilm(@PathVariable("fid") String fid) {
		try {
			LOGGER.info("deleteFilm: " + " fid=" + fid);
			int id = Integer.parseInt(fid);
			Film film = filmService.findFilmById(id);

			List<FilmDetail> listFilmDetail = filmDetailService.findAllByFid(id);
			for (FilmDetail fd : listFilmDetail) {
				filmDetailService.delete(fd);
			}
			filmService.delete(film);
			return "redirect:/admin/videoManagerAdvance";
		} catch (Exception e) {
			return "redirect:/admin/videoManagerAdvance";
		}
	}

	@RequestMapping("videoDetail/{fid}")
	public String filmDetail(Model model, @PathVariable("fid") String fid,
			@RequestParam("searchCondition") String searchCondition) {
		LOGGER.info("filmDetail: " + " fid=" + fid + " searchCondition=" + searchCondition);

		int id = Integer.parseInt(fid);
		Film film = filmService.findFilmById(id);
		List<FilmDetail> listFilmDetail;

		if (searchCondition == "" || searchCondition.isEmpty()) {
			listFilmDetail = filmDetailService.findAllByFid(id);
		} else {
			listFilmDetail = filmDetailService.findAllByFidSearchCondition(id, Integer.parseInt(searchCondition));
		}

		model.addAttribute("film", film);
		model.addAttribute("listFilmDetail", listFilmDetail);
		model.addAttribute("webDomain", webDomain);

		return "video_detail_manager";
	}

	@PostMapping("editVideoDetail/{fdid}")
	@ResponseBody
	public ResponseEntity<?> editFilmDetailRest(@RequestBody FilmDetailRequest filmDetailRequest,
			@PathVariable("fdid") String fdid) {
		LOGGER.info("editFilmDetailRest: " + " filmDetailRequest=" + filmDetailRequest + " fdid=" + fdid);

		int id = Integer.parseInt(fdid);
		FilmDetail filmDetail = filmDetailService.findByFdid(id);
		filmDetail.setEpisode(filmDetailRequest.getEpisode());
		filmDetail.setFurl(filmDetailRequest.getFurl());
		filmDetail.setFddeploy(filmDetailRequest.getFddeploy());
		filmDetail.setRequestEmail(filmDetailRequest.getRequestEmail());
		filmDetail.setAcceptAccount(filmDetailRequest.getAcceptAccount());

		filmDetailService.save(filmDetail);

		return new ResponseEntity<FilmDetailRequest>(new FilmDetailRequest(), HttpStatus.OK);
	}

	@RequestMapping("deleteVideoDetail/{fdid}")
	public String deleteFilmDetail(@PathVariable("fdid") String fdid) {

		try {
			LOGGER.info("deleteFilmDetail: " + " fdid=" + fdid);
			int id = Integer.parseInt(fdid);
			FilmDetail filmDetail = filmDetailService.findByFdid(id);
			filmDetailService.delete(filmDetail);
			return "redirect:/admin/videoDetail/" + id + "?searchCondition=";
		} catch (Exception e) {
			int id = Integer.parseInt(fdid);
			return "redirect:/admin/videoDetail/" + id + "?searchCondition=";
		}
	}

	@RequestMapping("/statisticsChart")
	public String getStatisticsChart(Model model) {
		LOGGER.info("getStatisticsChart: ");

		List<Film> listFilm = filmService.findAllFilm();

		Set<String> setYear = new HashSet<String>();
		DateFormat dateFormat = new SimpleDateFormat("YYYY");
		String strYear;

		for (Film film : listFilm) {
			strYear = dateFormat.format(film.getfAcceptTime());
			setYear.add(strYear);
		}

		List<String> listYear = new ArrayList<String>();
		listYear.addAll(setYear);
		Comparator<String> cmp = new YearStringComparator();
		Collections.sort(listYear, cmp);

		List<User> listUser = userRepository.findAll();

		model.addAttribute("listUser", listUser);
		model.addAttribute("listYear", listYear);
		model.addAttribute("webDomain", webDomain);

		return "statistics-chart";
	}

}
