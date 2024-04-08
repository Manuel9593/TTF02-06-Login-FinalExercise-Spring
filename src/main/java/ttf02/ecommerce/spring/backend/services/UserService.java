package ttf02.ecommerce.spring.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ttf02.ecommerce.spring.backend.repositories.UserRepository;
import ttf02.ecommerce.spring.backend.entities.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public User getByLogin(String login) {
		User u = repo.findById(login).get();
		return u;
	}
	
	public User login(String login, String password) {
		User u = repo.login(login, password);
		return u;
	}
	
	public void update(String login, String name, String surname) {
		User user = repo.findById(login).get();
		user.setName(name);
		user.setSurname(surname);
		repo.save(user);
	}
}
