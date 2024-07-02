package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ttf02.ecommerce.spring.backend.services.UserService;

@Controller
public class RechargeController {
	@Autowired
	PortalSession session;
	
	@Autowired
	UserService userService;
	
	@GetMapping("carica")
	public String user(Model m) {
		m.addAttribute("user", session.getUser());
		return "carica";
	}
}
