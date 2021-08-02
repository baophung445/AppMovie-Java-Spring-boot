package ptithcm.internship.movieapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.service.FilmService;
import ptithcm.internship.movieapp.service.WebService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@Controller
public class HomeController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private WebService webService;
	
	@ModelAttribute("web")
	public Web getWeb(){
	    return webService.findByHasUse();
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String getIndex(Model model) {
		LOGGER.info("getIndex: ");
		List<Film> listFilm = filmService.findHotFilmsByViewAndHasBanner(ConstantVariable.BANNER_DEFAULT_URL);
		if (listFilm.size() < ConstantVariable.TOP_FILM) {
			listFilm = listFilm.subList(0, listFilm.size());
		} else {
			listFilm = listFilm.subList(0, ConstantVariable.TOP_FILM);
		}
		
		List<Film> Filmhot = filmService.findHotFilmsByView();

		
		

		List<Film> listAction = filmService.findHotFilmsByCategoryId("AT", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listFantasy = filmService.findHotFilmsByCategoryId("FA", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listCartoon = filmService.findHotFilmsByCategoryId("CT", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listHorror = filmService.findHotFilmsByCategoryId("HR", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listRomance = filmService.findHotFilmsByCategoryId("RM", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listComedy = filmService.findHotFilmsByCategoryId("CM", ConstantVariable.BANNER_DEFAULT_URL);

		if (listAction.size() > 0) {
			model.addAttribute("isAction", true);
		} else {
			model.addAttribute("isAction", false);
		}

		if (listFantasy.size() > 0) {
			model.addAttribute("isFantasy", true);
		} else {
			model.addAttribute("isFantasy", false);
		}

		if (listCartoon.size() > 0) {
			model.addAttribute("isCartoon", true);
		} else {
			model.addAttribute("isCartoon", false);
		}

		if (listHorror.size() > 0) {
			model.addAttribute("isHorror", true);
		} else {
			model.addAttribute("isHorror", false);
		}

		if (listRomance.size() > 0) {
			model.addAttribute("isRomance", true);
		} else {
			model.addAttribute("isRomance", false);
		}

		if (listComedy.size() > 0) {
			model.addAttribute("isComedy", true);
		} else {
			model.addAttribute("isComedy", false);
		}
		
		model.addAttribute("listAction", listAction);
		model.addAttribute("listFantasy", listFantasy);
		model.addAttribute("listCartoon", listCartoon);
		model.addAttribute("listHorror", listHorror);
		model.addAttribute("listRomance", listRomance);
		model.addAttribute("listComedy", listComedy);
		
		model.addAttribute("Filmhot", Filmhot);
		

		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());

		return "index";
	}

	@RequestMapping("/index")
	public String getHomePage(Model model) {
		LOGGER.info("getHomePage: ");
		List<Film> listFilm = filmService.findHotFilmsByViewAndHasBanner(ConstantVariable.BANNER_DEFAULT_URL);
		if (listFilm.size() < ConstantVariable.TOP_FILM) {
			listFilm = listFilm.subList(0, listFilm.size());
		} else {
			listFilm = listFilm.subList(0, ConstantVariable.TOP_FILM);
		}
		
		List<Film> Filmhot = filmService.findHotFilmsByView();
		
		List<Film> listAction = filmService.findHotFilmsByCategoryId("AT", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listFantasy = filmService.findHotFilmsByCategoryId("FA", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listCartoon = filmService.findHotFilmsByCategoryId("CT", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listHorror = filmService.findHotFilmsByCategoryId("HR", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listRomance = filmService.findHotFilmsByCategoryId("RM", ConstantVariable.BANNER_DEFAULT_URL);
		List<Film> listComedy = filmService.findHotFilmsByCategoryId("CM", ConstantVariable.BANNER_DEFAULT_URL);

		if (listAction.size() > 0) {
			model.addAttribute("isAction", true);
		} else {
			model.addAttribute("isAction", false);
		}

		if (listFantasy.size() > 0) {
			model.addAttribute("isFantasy", true);
		} else {
			model.addAttribute("isFantasy", false);
		}

		if (listCartoon.size() > 0) {
			model.addAttribute("isCartoon", true);
		} else {
			model.addAttribute("isCartoon", false);
		}

		if (listHorror.size() > 0) {
			model.addAttribute("isHorror", true);
		} else {
			model.addAttribute("isHorror", false);
		}

		if (listRomance.size() > 0) {
			model.addAttribute("isRomance", true);
		} else {
			model.addAttribute("isRomance", false);
		}

		if (listComedy.size() > 0) {
			model.addAttribute("isComedy", true);
		} else {
			model.addAttribute("isComedy", false);
		}

		model.addAttribute("listAction", listAction);
		model.addAttribute("listFantasy", listFantasy);
		model.addAttribute("listCartoon", listCartoon);
		model.addAttribute("listHorror", listHorror);
		model.addAttribute("listRomance", listRomance);
		model.addAttribute("listComedy", listComedy);
		
		model.addAttribute("Filmhot", Filmhot);

		model.addAttribute("listFilm", listFilm);
		model.addAttribute("size", listFilm.size());

		return "index";
	}

	@RequestMapping("/403")
	public String getAccessDenied(Model model) {
		LOGGER.info("getAccessDenied: ");
		return "403";
	}
	
	@GetMapping("/login") 
    public String getLogin() {
		LOGGER.info("getLogin: ");
        return "login";
    }
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("logout: ");
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";
	}
}
