package ptithcm.internship.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptithcm.internship.movieapp.entrity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{
	@Query("select country from Country country WHERE country.coid = ?1")
	Country findCountryById(String id);
}
