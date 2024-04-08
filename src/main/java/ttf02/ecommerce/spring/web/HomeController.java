package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import ttf02.ecommerce.spring.backend.services.ProductService;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	PortalSession session;
	
	@Autowired
	ProductService ps;
	
	@GetMapping("")
	public String landing(String err, Model m) {
		if(err!=null) {
			log.debug("Err: utente non trovato");
			m.addAttribute("err","utente non trovato");
		}
		return "index";
	}
	@GetMapping("home")
	public String updated(String err, Model m) {
		m.addAttribute("user", session.getUser());
		return "home";
	}
}