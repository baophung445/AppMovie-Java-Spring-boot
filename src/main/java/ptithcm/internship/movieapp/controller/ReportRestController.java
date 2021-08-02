package ptithcm.internship.movieapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.internship.movieapp.dto.ReportRequest;
import ptithcm.internship.movieapp.entrity.FilmDetail;
import ptithcm.internship.movieapp.entrity.Report;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.FilmDetailService;
import ptithcm.internship.movieapp.service.ReportService;
import ptithcm.internship.movieapp.utils.ConstantVariable;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private FilmDetailService filmDetailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReportService reportService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportRestController.class);
	
	@PostMapping("api/createReport")
	public ResponseEntity<?> createReportApi(ReportRequest reportRequest){
		LOGGER.info("createReportApi: " + "reportRequest=" + reportRequest);
		
		FilmDetail filmDetail = filmDetailService.findByFidAndEpisode(Integer.parseInt(reportRequest.getFid()), Integer.parseInt(reportRequest.getEpisode()));
		User acceptUser = userRepository.findByUserName(filmDetail.getAcceptAccount());
		Report report = new Report();
		Date date = new Date();
		report.setRpTime(date);
		report.setRpDescription(reportRequest.getRpDescription());
		report.setRpType(reportRequest.getRpType());
		report.setUser(acceptUser);
		if (reportRequest.getRpType() == 2) {
			report.setResolve(true);
		}else {
			report.setResolve(false);
		}
		
		String uploadRootPath = ConstantVariable.UPLOAD_IMAGE_REAL_PATH;

		File uploadRootDir = new File(uploadRootPath);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		
		MultipartFile fileData = reportRequest.getData();
		if (fileData != null) {
			String name = date.getTime() + "_report_" + fileData.getOriginalFilename();
			if (fileData.getOriginalFilename() != null && fileData.getOriginalFilename().length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					report.setAttachmentUrl(name);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
		reportService.save(report);
		return new ResponseEntity<ReportRequest>(new ReportRequest(), HttpStatus.OK);
	}
}
