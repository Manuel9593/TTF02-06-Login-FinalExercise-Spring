package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ttf02.ecommerce.spring.backend.Cart;
import ttf02.ecommerce.spring.backend.services.ProductService;
import ttf02.ecommerce.spring.backend.services.UserService;

@Controller
public class LoginController {
	@Autowired
	PortalSession session;
	
	@Autowired
	UserService us;
	
	@Autowired
	ProductService ps;
	
	@PostMapping("login")
	public String login(String login, String password, Model m) {
		session.setUser(us.login(login, password));
		session.setCart(new Cart());
		m.addAttribute("user", session.getUser());
		return session.getUser() == null ? "redirect:/?err" : "redirect:/home";
	}
	
	@GetMapping("logout")
	public String logout() {
		session.setUser(null);
		session.setCart(new Cart());
		return "redirect:";
	}
}