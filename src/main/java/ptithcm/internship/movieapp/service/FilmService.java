package ptithcm.internship.movieapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.Film;
import ptithcm.internship.movieapp.entrity.GroupVideoChart;
import ptithcm.internship.movieapp.entrity.VideoChart;
import ptithcm.internship.movieapp.repository.FilmRepository;
import ptithcm.internship.movieapp.utils.MovieAppConstant;

@Service
public class FilmService {
	@Autowired
	FilmRepository filmRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FilmService.class);

	public List<Film> findAllFilm() {
		return filmRepository.findAllFilm();
	}

	public List<Film> findAll() {
		LOGGER.info("findAll: ");

		return filmRepository.findAll();
	}

	public Film findFilmById(int id) {
		LOGGER.info("findFilmById: " + "id=" + id);

		return filmRepository.findFilmById(id);
	}

	public Film findFilmByIdAndAcceptAccount(int fid, String uName) {
		LOGGER.info("findFilmByIdAndAcceptAccount: " + "fid=" + fid + " uName=" + uName);

		return filmRepository.findFilmByIdAndAcceptAccount(fid, uName);
	}

	public List<Film> findFilmsByCid(String cid) {
		LOGGER.info("findFilmsByCid: " + "cid=" + cid);

		return filmRepository.findFilmsByCategoryId(cid);
	}

	public List<Film> findFilmsByCoid(String coid) {
		LOGGER.info("findFilmsByCoid: " + "coid=" + coid);

		return filmRepository.findFilmsByCountryId(coid);
	}

	public List<Film> findFilmsByYear(int fyear) {
		LOGGER.info("findFilmsByYear: " + "fyear=" + fyear);

		return filmRepository.findFilmsByYear(fyear);
	}

	public List<Film> findFilmsByAcceptAccount(String uName) {
		LOGGER.info("findFilmsByAcceptAccount: " + "uName=" + uName);

		return filmRepository.findFilmsByAcceptAccount(uName);
	}

	public List<Film> findFilmsByFstatusAndDeploy(int fstatus) {
		LOGGER.info("findFilmsByFstatusAndDeploy: " + "fstatus=" + fstatus);

		return filmRepository.findFilmsByFstatusAndDeploy(fstatus);
	}

	public List<Film> findFilmsByCidAndYear(String cid, int fyear) {
		LOGGER.info("findFilmsByCidAndYear: " + "cid=" + cid + " fyear=" + fyear);

		return filmRepository.findFilmsByCategoryIdAndYear(cid, fyear);
	}

	public List<Film> findHotFilmsByView() {
		LOGGER.info("findHotFilmsByView: ");

		return filmRepository.findHotFilmsByView();
	}

	public List<Film> findFilmsByTextSearch(String textSearch) {
		LOGGER.info("findFilmsByTextSearch: " + "textSearch=" + textSearch);

		return filmRepository.findFilmsByTextSearch(textSearch);
	}

	public List<Film> findFilmsByTextSearchAndAcceptAccount(String textSearch, String uName) {
		LOGGER.info("findFilmsByTextSearchAndAcceptAccount: " + "textSearch=" + textSearch + " uName=" + uName);

		return filmRepository.findFilmsByTextSearchAndAcceptAccount(textSearch, uName);
	}

	public List<Film> findHotFilmsByViewAndHasBanner(String url) {
		LOGGER.info("findHotFilmsByViewAndHasBanner: " + "url=" + url);

		return filmRepository.findHotFilmsByViewAndHasBanner(url);
	}

	public List<Film> findHotFilmsByCategoryId(String cid, String url) {
		LOGGER.info("findHotFilmsByCategoryId: " + "cid=" + cid + " url=" + url);

		return filmRepository.findHotFilmsByCategoryId(cid, url);
	}

	public Film save(Film film) {
		LOGGER.info("save: " + "film=" + film);

		return filmRepository.save(film);
	}

	public List<Film> findFilmsNoDeployOrHasRequest() {
		LOGGER.info("findFilmsNoDeployOrHasRequest: ");

		return filmRepository.findFilmsNoDeployOrHasRequest();
	}

	public List<Film> findFilmsNoDeployOrHasRequestWithFirstAndLast(Pageable pageable) {
		LOGGER.info("findFilmsNoDeployOrHasRequestWithFirstAndLast: ");

		return filmRepository.findFilmsNoDeployOrHasRequestWithFirstAndLast(pageable);
	}

	public int countFilmsByAcceptDateAndStatus(String year, String month, int status) {
		LOGGER.info("findFilmsByAcceptYearAndStatus: " + "year=" + year + " month=" + month + " status=" + status);

		return filmRepository.countFilmsByAcceptDateAndStatus(year, month, status);
	}

	public void delete(Film film) {
		LOGGER.info("delete: " + "film=" + film);

		filmRepository.delete(film);
	}

	public VideoChart getVideoChart(String year) {
		LOGGER.info("getVideoChart: " + "year=" + year);
		
		VideoChart videoChart = new VideoChart();

		videoChart.setvJanurary(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_JANURARY, MovieAppConstant.VIDEO_STATUS));
		videoChart.setvFebruary(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_FEBRUARY, MovieAppConstant.VIDEO_STATUS));
		videoChart.setvMarch(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_MARCH,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvApril(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_APRIL,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvMay(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_MAY,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvJune(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_JUNE,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvJuly(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_JULY,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvAugust(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_AUGUST,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvSeptember(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_SEPTEMBER, MovieAppConstant.VIDEO_STATUS));
		videoChart.setvOctober(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_OCTOBER,
				MovieAppConstant.VIDEO_STATUS));
		videoChart.setvNovember(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_NOVEMBER, MovieAppConstant.VIDEO_STATUS));
		videoChart.setvDecember(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_DECEMBER, MovieAppConstant.VIDEO_STATUS));
		return videoChart;
	}
	
	public GroupVideoChart getGroupVideoChart(String year) {
		LOGGER.info("getGroupVideoChart: " + "year=" + year);
		
		GroupVideoChart groupVideoChart = new GroupVideoChart();

		groupVideoChart.setVgJanurary(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_JANURARY, MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgFebruary(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_FEBRUARY, MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgMarch(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_MARCH,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgApril(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_APRIL,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgMay(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_MAY,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgJune(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_JUNE,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgJuly(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_JULY,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgAugust(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_AUGUST,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgSeptember(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_SEPTEMBER, MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgOctober(countFilmsByAcceptDateAndStatus(year, MovieAppConstant.MONTH_OCTOBER,
				MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgNovember(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_NOVEMBER, MovieAppConstant.GROUP_VIDEO_STATUS));
		groupVideoChart.setVgDecember(countFilmsByAcceptDateAndStatus(year,
				MovieAppConstant.MONTH_DECEMBER, MovieAppConstant.GROUP_VIDEO_STATUS));
		return groupVideoChart;
	}
	
}
