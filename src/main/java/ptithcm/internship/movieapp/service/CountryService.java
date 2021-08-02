package ptithcm.internship.movieapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.Country;
import ptithcm.internship.movieapp.repository.CountryRepository;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
	
	public List<Country> findAllCountry(){
		LOGGER.info("findAllCountry");
		
		return countryRepository.findAll();
	}
	
	public Country findById(String id) {
		LOGGER.info("findById: " + "id=" + id);
		
		return countryRepository.findCountryById(id);
	}
	
	public Country save(Country country) {
		LOGGER.info("save: " + "country=" + country);
		
		return countryRepository.save(country);
	}
	
	public void delete(Country country) {
		LOGGER.info("delete: " + "country=" + country);
		
		countryRepository.delete(country);
	}
}
