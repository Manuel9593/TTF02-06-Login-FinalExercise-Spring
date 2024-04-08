package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ttf02.ecommerce.spring.backend.services.ProductService;

@Slf4j
@Controller
public class CartController {

	@Autowired
	PortalSession session;
	
	@Autowired
	ProductService ps;
	
	

	@GetMapping("carrello")
	public String carrello(Model m) {
		m.addAttribute("user", session.getUser());
		log.info("{}", session.getUser());
		m.addAttribute("cart", session.getCart());
		return "carrello";
	}
	
	@GetMapping("aggiungi")
	public String aggiungi(Model m) {
		m.addAttribute("user", session.getUser());
		log.info("{}", session.getUser());
		m.addAttribute("products", ps.getAllProducts());
		return "aggiungi";
	}
	
	@Controller
	@RequestMapping("carrello")
	public class CartOperationsController {

		@Autowired
		PortalSession session;
		
		@Autowired
		ProductService ps;

		@PostMapping("add-product")
		public String addproduct(int prodotto, int quantita) {
			session.getCart().add(ps.getProduct(prodotto), quantita);
			return "redirect:../carrello";
		}
		
		
		@GetMapping("remove-product")
		public String removeProduct(int product) {
			session.getCart().remove(ps.getProduct(product));
			return "redirect:../carrello";
		}
	}

}
