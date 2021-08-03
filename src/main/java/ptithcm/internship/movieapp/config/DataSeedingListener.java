package ptithcm.internship.movieapp.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ptithcm.internship.movieapp.entrity.Category;
import ptithcm.internship.movieapp.entrity.Country;
import ptithcm.internship.movieapp.entrity.Role;
import ptithcm.internship.movieapp.entrity.User;
import ptithcm.internship.movieapp.entrity.Web;
import ptithcm.internship.movieapp.repository.RoleRepository;
import ptithcm.internship.movieapp.repository.UserRepository;
import ptithcm.internship.movieapp.service.CategoryService;
import ptithcm.internship.movieapp.service.CountryService;
import ptithcm.internship.movieapp.service.WebService;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private WebService webService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

		if (roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}

		// Admin account
		if (userRepository.findByEmail("baophung445@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("baophung445@gmail.com");
			admin.setPassword(passwordEncoder.encode("binh01139"));
			admin.setUname("Admin");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}

		// Member account
		if (userRepository.findByEmail("member@gmail.com") == null) {
			User user = new User();
			user.setEmail("member@gmail.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setUname("BlackCat");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);
		}

		// Country
		if (countryService.findAllCountry().size() <= 0) {
			countryService.save(new Country("VN", "Vietnam"));
			countryService.save(new Country("CN", "China"));
			countryService.save(new Country("JP", "Japan"));
			countryService.save(new Country("HK", "Hongkong"));
			countryService.save(new Country("US", "United State"));
			countryService.save(new Country("EN", "England"));
			countryService.save(new Country("IN", "Indian"));
			countryService.save(new Country("KR", "Korean"));
			countryService.save(new Country("FR", "France"));
		}

		// Category
		if (categoryService.findAllCategory().size() <= 0) {
			categoryService.save(new Category("AT", "Action"));
			categoryService.save(new Category("FA", "Fantasy"));
			categoryService.save(new Category("CT", "Cartoon"));
			categoryService.save(new Category("HR", "Horror"));
			categoryService.save(new Category("RM", "Romance"));
			categoryService.save(new Category("CM", "Comedy"));
			categoryService.save(new Category("TR", "Travel"));
			categoryService.save(new Category("FO", "Food"));
			categoryService.save(new Category("SP", "Sport"));
			categoryService.save(new Category("OT", "Other"));
		}
		
		//Web templates
		if (webService.findAllWeb().size() <= 0) {
			webService.save(new Web("Share Yo Video", "Watch free and share video online. Base on Youtube", "logo.png", "guest.png", "member.png", "admin.png", 1));
		}

	}

}
