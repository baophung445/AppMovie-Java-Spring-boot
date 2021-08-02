package ptithcm.internship.movieapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.internship.movieapp.entrity.Report;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.ReportService;
import ptithcm.internship.movieapp.service.WebService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@Controller
public class ReportController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private WebService webService;
	
	@ModelAttribute("web")
	public Web getWeb() {
		return webService.findByHasUse();
	}
	
	@Value("${web.domain}")
	private String webDomain;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	@RequestMapping("report-manager/{page}")
	public String reportManager(Model model, @PathVariable("page") int page, Authentication auth) {
		LOGGER.info("reportManager: " + "page=" + page);
		
		if (page < 1) {
			return "redirect:/report-manager/" + 1;
		}
		User currentUser = userRepository.findByEmail(auth.getName());
		
		List<Report> listReport;
		List<Report> listAllReport;

		if (currentUser.getUname().equals("Admin")) {
			listReport = reportService.findAllReportsWithFirstAndLast(PageRequest.of((page - 1), ConstantVariable.MAX_ITEMS));
			listAllReport = reportService.findAllReports();
		}else {
			listReport = reportService.findAllByUserIdNotResolveFirstAndLast(currentUser.getUser_id(), PageRequest.of((page - 1), ConstantVariable.MAX_ITEMS));
			listAllReport = reportService.findAllByUserIdNotResolve(currentUser.getUser_id());
		}
			
		int pageCount = (int) Math.ceil(listAllReport.size() / (ConstantVariable.MAX_ITEMS * 1d));
		if (page > pageCount && page != 1) {
			return "redirect:/report-manager/" + pageCount;
		}
		int currentPage = page;
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			pages.add(i);
		}
		
		model.addAttribute("listReport", listReport);
		model.addAttribute("pages", pages);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("webDomain", webDomain);
		
		return "report_manager";
	}
	
	@RequestMapping("resolve-report/{rpid}/{page}")
	public String resolveReport(Model model, @PathVariable("rpid") String rpid, @PathVariable("page") int page) {
		LOGGER.info("resolveReport: " + "rpid=" + rpid);
		 
		Report report = reportService.findByRpId(Integer.parseInt(rpid));
		report.setResolve(true);
		reportService.save(report);
		return "redirect:/report-manager/" + page;
	}
}
