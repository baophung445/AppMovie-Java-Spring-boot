package ptithcm.internship.movieapp.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
        .and()
        .authorizeRequests()
        	.antMatchers("/admin", "/admin/**", "/admin/editVideo/**").hasRole("ADMIN")
            .antMatchers("/request-manager", "/request-manager/**",
            		"/report-manager", "/report-manager/**", 
            		"/review-has-role", "/review-has-role/**", 
            		"/video-manager", "/video-manager/**",
            		"/accept-video-request", "/accept-video-request/**",
            		"/delete-video-request", "/delete-video-request/**", 
            		"/watch-has-role", "/watch-has-role/**", 
            		"/resolve-report", "/resolve-report/**",
            		"/member", "/member/**").hasAnyRole("MEMBER", "ADMIN")
            .antMatchers("/", "/login", "/logout",
            		"/hotvideo" ,"/hotvideo/**",
        			"/categories" ,"/categories/**",
        			"/countries" ,"/countries/**",
        			"/add-new-video", "/add-new-video/**",
        			"/add-new-group-video", "/add-new-group-video/**",
        			"/review", "/review/**", 
        			"/watch", "/watch/**",
        			"/report/api/createReport").permitAll()
            .and()
        .formLogin()
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/request-manager/1")
            .failureUrl("/login?error")
            .and()
        .exceptionHandling()
            .accessDeniedPage("/403");
        	
    }
 
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
