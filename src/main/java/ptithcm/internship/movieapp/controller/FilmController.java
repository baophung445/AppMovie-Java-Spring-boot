package ptithcm.internship.movieapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.internship.movieapp.dto.FilmRequest;
import ptithcm.internship.movieapp.entrity.Category;
import ptithcm.internship.movieapp.entrity.Comment;
import ptithcm.internship.movieapp.entrity.Country;
import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.FilmDetail;
import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.helper.YearComparator;
import ptithcm.internship.movieapp.multipartfile.UploadForm;
import ptithcm.internship.movieapp.service.CategoryService;
import ptithcm.internship.movieapp.service.CommentService;
import ptithcm.internship.movieapp.service.CountryService;
import ptithcm.internship.movieapp.service.FilmDetailService;
import ptithcm.internship.movieapp.service.FilmService;
import ptithcm.internship.movieapp.service.WebService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@Controller
public class FilmController {
	@Autowired
	private FilmService filmService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private FilmDetailService filmDetailService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private WebService webService;

	@ModelAttribute("web")
	public Web getWeb() {
		return webService.findByHasUse();
	}

	@Value("${web.domain}")
	private String webDomain;

	private static final Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

	@RequestMapping("/search")
	public String searchFilm(Model model, @RequestParam("text") String textSearch) {
		List<Film> listFilm = filmService.findFilmsByTextSearch(textSearch);
		if (listFilm.size() > ConstantVariable.TOP_ITEMS) {
			listFilm = listFilm.subList(0, ConstantVariable.TOP_ITEMS);
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = 1;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}

		model.addAttribute("textSearch", textSearch);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());

		return "search";
	}

	@RequestMapping("/search/{page}")
	public String searchFilm(Model model, @PathVariable("page") int page, @RequestParam("text") String textSearch) {
		if (page < 1) {
			return "redirect:/search/" + 1 + "?text=" + textSearch;
		}
		List<Film> listFilm = filmService.findFilmsByTextSearch(textSearch);
		if (listFilm.size() > ConstantVariable.TOP_ITEMS) {
			listFilm = listFilm.subList(0, ConstantVariable.TOP_ITEMS);
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		if (page > pageCount) {
			return "redirect:/search/" + pageCount + "?text=" + textSearch;
		}
		int currentPage = page;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}

		model.addAttribute("textSearch", textSearch);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());

		return "search";
	}

	@RequestMapping("/hotvideo")
	public String getHotFilm(Model model) {
		List<Film> listFilm = filmService.findHotFilmsByView();
		if (listFilm.size() > ConstantVariable.TOP_ITEMS) {
			listFilm = listFilm.subList(0, ConstantVariable.TOP_ITEMS);
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = 1;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}
		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());

		return "hotvideo";
	}

	@RequestMapping("/hotvideo/{page}")
	public String getHotFilm(Model model, @PathVariable("page") int page) {
		if (page < 1) {
			return "redirect:/hotvideo/" + 1;
		}
		List<Film> listFilm = filmService.findHotFilmsByView();
		if (listFilm.size() > ConstantVariable.TOP_ITEMS) {
			listFilm = listFilm.subList(0, ConstantVariable.TOP_ITEMS);
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		if (page > pageCount) {
			return "redirect:/hotvideo/" + pageCount;
		}
		int currentPage = page;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}
		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());

		return "hotvideo";
	}

//	Phần filter : thể loại + năm + quốc gia
	  @RequestMapping("/categories/{cid}/{year}/{country}") public String
	 getCategoryPageByAll(Model model, @PathVariable("cid") String cid,
	  
	  @PathVariable("year") String year , @PathVariable("coid") String coid)
	  {
	  
	  List<Film> listFilm = filmService.findAllFilm();
	  
		/* if (cid.equals("ALL") && year.equals("ALL") && country.equals("ALL") ) */
	  
//		  listFilm = ;  
	  
	  
	  if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}
	  
	  List<Category> listCategory = categoryService.findAllCategory();
		List<Film> listAll = filmService.findAllFilm();

		Comparator<Integer> cmp = new YearComparator();
		Set<Integer> list = new HashSet<Integer>();
		for (Film film : listAll) {
			list.add(film.getFyear());
		}
		List<Integer> listYear = new ArrayList<Integer>();
		for (Integer y : list) {
			listYear.add(y);
		}
		Collections.sort(listYear, cmp);

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = 1;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}

		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("cid", cid);
		model.addAttribute("fyear", year);
		model.addAttribute("coid", coid);
		model.addAttribute("listYear", listYear);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listCountry", listCountry);
		
		return "categories";
	  
	  }
	  
	  
//	  
//	  
	 

	@RequestMapping("/categories/{cid}/{year}")
	public String getCategoryPageByCid(Model model, @PathVariable("cid") String cid,
			@PathVariable("year") String year) {
		List<Film> listFilm;

		if (cid.equals("ALL")) {
			if (year.equals("ALL")) {
				listFilm = filmService.findAllFilm();
			} else {
				listFilm = filmService.findFilmsByYear(Integer.parseInt(year));
			}
		} else if (year.equals("ALL")) {
			listFilm = filmService.findFilmsByCid(cid);
		} else {
			listFilm = filmService.findFilmsByCidAndYear(cid, Integer.parseInt(year));
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		List<Category> listCategory = categoryService.findAllCategory();
		List<Film> listAll = filmService.findAllFilm();

		Comparator<Integer> cmp = new YearComparator();
		Set<Integer> list = new HashSet<Integer>();
		for (Film film : listAll) {
			list.add(film.getFyear());
		}
		List<Integer> listYear = new ArrayList<Integer>();
		for (Integer y : list) {
			listYear.add(y);
		}
		Collections.sort(listYear, cmp);

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = 1;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}

		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("cid", cid);
		model.addAttribute("fyear", year);
		model.addAttribute("listYear", listYear);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listCountry", listCountry);
		return "categories";
	}

	@RequestMapping("/categories/{cid}/{year}/{page}")
	public String getCategoryPage(Model model, @PathVariable("page") int page, @PathVariable("cid") String cid,
			@PathVariable("year") String year) {
		if (page < 1) {
			return "redirect:/categories/" + cid + "/" + year + "/" + 1;
		}

		List<Film> listFilm;
		if (cid.equals("ALL")) {
			if (year.equals("ALL")) {
				listFilm = filmService.findAllFilm();
			} else {
				listFilm = filmService.findFilmsByYear(Integer.parseInt(year));
			}
		} else if (year.equals("ALL")) {
			listFilm = filmService.findFilmsByCid(cid);
		} else {
			listFilm = filmService.findFilmsByCidAndYear(cid, Integer.parseInt(year));
		}

//		
		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		List<Category> listCategory = categoryService.findAllCategory();
		List<Film> listAll = filmService.findAllFilm();
		Set<Integer> listYear = new HashSet<Integer>();
		for (Film film : listAll) {
			listYear.add(film.getFyear());
		}

//		pagination

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = page;
		if (page > pageCount) {
			return "redirect:/categories/" + cid + "/" + year + "/" + pageCount;
		}
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}

		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("cid", cid);
		model.addAttribute("fyear", year);
		model.addAttribute("listYear", listYear);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listCountry", listCountry);

		return "categories";
	}

	@RequestMapping("/review/{id}")
	public String getFilmInfo(Model model, @PathVariable("id") int id) {
		Film film = filmService.findFilmById(id);

		List<FilmDetail> listFilmDetail = filmDetailService.findAllByFidDeploy(id);
		int numberOfFilmDetail = listFilmDetail.size();

		double rate = film.getFevaluatepoint() / film.getFevaluatecount();
		rate = Math.round(rate * 2) / 2.0;

		if (film.getFdeploy() == 1) {
			List<Category> listCategory = categoryService.findAllCategoryByFid(id);
			String categories = "";
			for (Category c : listCategory) {
				categories = categories + "/ " + c.getCname();
			}
			categories = categories.substring(1);
			model.addAttribute("webDomain", webDomain);
			model.addAttribute("categories", categories);
			model.addAttribute("numberOfFilmDetail", numberOfFilmDetail);
			model.addAttribute("film", film);
			model.addAttribute("rate", rate);
			return "review_video";
		}
		return "403";
	}

	@RequestMapping("/watch/{id}")
	public String getFilmToWatch(Model model, @PathVariable("id") int id) {
		Film film = filmService.findFilmById(id);
		List<FilmDetail> listFilmDetail = filmDetailService.findAllByFidDeploy(id);
		List<Comment> list = commentService.findAllByFid(id);
		List<Comment> listComment = commentService.findAllByFidWithFirstAndLast(id,
				PageRequest.of(0, ConstantVariable.DEFAULT_COMMENT));
		if (film.getFdeploy() == 1) {
			model.addAttribute("webDomain", webDomain);
			model.addAttribute("film", film);
			model.addAttribute("filmDetail", listFilmDetail.get(0));
			model.addAttribute("listFilmDetail", listFilmDetail);
			model.addAttribute("listComment", listComment);
			model.addAttribute("commentShow", ConstantVariable.DEFAULT_COMMENT);
			model.addAttribute("countComment", list.size());
			film.setFview(film.getFview() + 1);
			filmService.save(film);
			return "watch_video";
		}
		return "403";
	}

	@RequestMapping("/countries/{coid}")
	public String getFilmByCountry(Model model, @PathVariable("coid") String coid) {
		List<Film> listFilm;
		if (coid.equals("ALL")) {
			listFilm = filmService.findAllFilm();
		} else {
			listFilm = filmService.findFilmsByCoid(coid);
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		List<Country> listCountry = countryService.findAllCountry();

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = 1;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}
		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("coid", coid);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());
		model.addAttribute("listCountry", listCountry);
		return "countries";
	}

	@RequestMapping("/countries/{coid}/{page}")
	public String getFilmByCountry(Model model, @PathVariable("coid") String coid, @PathVariable("page") int page) {
		if (page < 1) {
			return "redirect:/countries/" + coid + "/" + 1;
		}
		List<Film> listFilm;
		if (coid.equals("ALL")) {
			listFilm = filmService.findAllFilm();
		} else {
			listFilm = filmService.findFilmsByCoid(coid);
		}

		if (listFilm.size() == 0 || listFilm.isEmpty()) {
			listFilm.add(new Film());
		} else {
			String newName = "";
			for (Film f : listFilm) {
				if (f.getFname().length() > ConstantVariable.MAX_FILM_NAME_LENGTH) {
					newName = f.getFname().substring(0, ConstantVariable.MAX_FILM_NAME_LENGTH) + "...";
					f.setFname(newName);
				}
			}
		}

		List<Country> listCountry = countryService.findAllCountry();

		int pageCount = (int) Math.ceil(listFilm.size() / (ConstantVariable.MAX_ITEMS * 1d));
		int currentPage = page;
		if (page > pageCount) {
			return "redirect:/countries/" + coid + "/" + pageCount;
		}
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}
		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("maxItem", ConstantVariable.MAX_ITEMS);
		model.addAttribute("coid", coid);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());
		model.addAttribute("listCountry", listCountry);
		return "countries";
	}

	@RequestMapping(value = "/add-new-video", method = RequestMethod.GET)
	public String newFilm(Model model) {
		List<Category> listCategory = categoryService.findAllCategory();
		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("newFilmRequest", new FilmRequest());
		model.addAttribute("listCatagory", listCategory);
		model.addAttribute("listCountry", listCountry);
		return "add_new_video";
	}

	@RequestMapping(value = "/add-new-video", method = RequestMethod.POST)
	public String newFilm(Model model, @ModelAttribute("newFilmRequest") FilmRequest filmRequest,
			HttpServletRequest request) {
		UploadForm upload = filmRequest.getUpload();

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
		Film film = new Film();
		film.setFname(filmRequest.getFname());
		film.setFepisodecount(1);
		film.setFyear(filmRequest.getFyear());
		film.setFstatus(0);
		film.setIntroduction(filmRequest.getIntroduction());
		film.setLength(filmRequest.getLength());
		film.setRequestEmail(filmRequest.getRequestEmail());
		film.setRequestType("Create new film");
		film.setfRequestTime(date);
		MultipartFile fileDataBanner = filmRequest.getUpload().getFileDatas()[0];
		MultipartFile fileDataImage = filmRequest.getUpload().getFileDatas()[1];
		if (fileDataBanner != null && !fileDataImage.getOriginalFilename().isEmpty()) {
			film.setFimageurl(date.getTime() + "_image_" + fileDataImage.getOriginalFilename());
		} else {
			film.setFimageurl("default.jpg");
		}

		if (fileDataBanner != null && !fileDataBanner.getOriginalFilename().isEmpty()) {
			film.setFbanner(date.getTime() + "_banner_" + fileDataBanner.getOriginalFilename());
		} else {
			film.setFbanner("default_banner.png");
		}
		// film.setFurl(filmRequest.getFurl());
		film.setFdeploy(0);
		film.setFrequest(0);
		film.setFevaluatecount(0.0);
		;
		film.setFevaluatepoint(0.0);
		Country country = countryService.findById(filmRequest.getCountry_id());
		film.setCountry(country);
		Set<Category> listCategory = new HashSet<Category>();
		for (String cid : filmRequest.getList_category_id()) {
			listCategory.add(categoryService.findByCategoryId(cid));
		}
		film.setBelongedCategories(listCategory);
		Film currentFilm = filmService.save(film);
		FilmDetail filmDetail = new FilmDetail(1, filmRequest.getFurl(), currentFilm.getRequestEmail(), 0, date,
				currentFilm);
		filmDetailService.save(filmDetail);

		List<Category> listCategory1 = categoryService.findAllCategory();
		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("newFilmRequest", new FilmRequest());
		model.addAttribute("listCatagory", listCategory1);
		model.addAttribute("listCountry", listCountry);
		model.addAttribute("message", "Send request success. Please wait admin confirm!");
		return "add_new_video";
	}

	@RequestMapping(value = "/add-new-group-video", method = RequestMethod.GET)
	public String newFilmTV(Model model) {
		List<Category> listCategory = categoryService.findAllCategory();
		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("newFilmRequest", new FilmRequest());
		model.addAttribute("listCatagory", listCategory);
		model.addAttribute("listCountry", listCountry);
		return "add_new_group_video";
	}

	@RequestMapping(value = "/add-new-group-video", method = RequestMethod.POST)
	public String newFilmTV(Model model, @ModelAttribute("newFilmRequest") FilmRequest filmRequest,
			HttpServletRequest request) {
		LOGGER.info("newFilmTV: " + "filmRequest=" + filmRequest);

		UploadForm upload = filmRequest.getUpload();

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
		Film film = new Film();
		film.setFname(filmRequest.getFname());
		film.setFepisodecount(filmRequest.getFepisodecount());
		film.setFyear(filmRequest.getFyear());
		film.setFstatus(1);
		film.setIntroduction(filmRequest.getIntroduction());
		film.setLength(filmRequest.getLength());
		film.setRequestEmail(filmRequest.getRequestEmail());
		film.setRequestType("Create new film");
		film.setfRequestTime(date);
		MultipartFile fileDataBanner = filmRequest.getUpload().getFileDatas()[0];
		MultipartFile fileDataImage = filmRequest.getUpload().getFileDatas()[1];
		film.setFimageurl(date.getTime() + "_image_" + fileDataImage.getOriginalFilename());
		film.setFbanner(date.getTime() + "_banner_" + fileDataBanner.getOriginalFilename());
		// film.setFurl(filmRequest.getFurl());
		film.setFdeploy(0);
		film.setFrequest(0);
		film.setFevaluatecount(0.0);
		;
		film.setFevaluatepoint(0.0);
		Country country = countryService.findById(filmRequest.getCountry_id());
		film.setCountry(country);
		Set<Category> listCategory = new HashSet<Category>();
		for (String cid : filmRequest.getList_category_id()) {
			listCategory.add(categoryService.findByCategoryId(cid));
		}
		film.setBelongedCategories(listCategory);
		Film currentFilm = filmService.save(film);
		int episodeCount = 1;
		for (String furl : filmRequest.getListFilmUrl()) {
			FilmDetail filmDetail = new FilmDetail(episodeCount++, furl, currentFilm.getRequestEmail(), 0, date,
					currentFilm);
			filmDetail.setFdRequestTime(date);
			filmDetailService.save(filmDetail);
		}

		List<Category> listCategory1 = categoryService.findAllCategory();
		List<Country> listCountry = countryService.findAllCountry();

		model.addAttribute("newFilmRequest", new FilmRequest());
		model.addAttribute("listCatagory", listCategory1);
		model.addAttribute("listCountry", listCountry);
		model.addAttribute("message", "Send request success. Please wait admin confirm!");
		return "add_new_group_video";
	}
}
