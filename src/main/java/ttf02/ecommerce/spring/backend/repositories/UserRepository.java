package ttf02.ecommerce.spring.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ttf02.ecommerce.spring.backend.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, String> {
	@Query("select u from User u where login=:log and password=:pass")
	public User login(String log, String pass);
}
