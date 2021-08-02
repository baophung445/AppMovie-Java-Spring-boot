package ptithcm.internship.movieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ptithcm.internship.movieapp.entrity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	
	@Query("SELECT user from User user WHERE user.user_id = ?1")
	User findByUid(int uid);
	
	@Query("SELECT user from User user WHERE user.uname = ?1")
	User findByUserName(String userName);
}
