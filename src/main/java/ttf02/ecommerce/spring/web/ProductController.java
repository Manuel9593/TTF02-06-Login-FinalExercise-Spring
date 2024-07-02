package ttf02.ecommerce.spring.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ttf02.ecommerce.spring.backend.services.ProductService;
import ttf02.ecommerce.spring.web.exceptions.ForbiddenException;

@Controller
public class ProductController {
	
	@Autowired
	PortalSession session;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("prodotti")
	public String prodotti(Model model) {
		if (session.getUser() == null || !session.getUser().isAdmin()) {
			throw new ForbiddenException();
		}
		model.addAttribute("products", productService.getAllProducts());
		return "prodotti";
	}
	
	@RequestMapping("prodotti")
	public class ProductAdminController {
		
		
		@PostMapping("add-product")
		public String aggiungi(String name, int price, Model model) {
			if (session.getUser() != null && session.getUser().isAdmin()) {
				throw new ForbiddenException();
			}
			
			return "prodotti";
		}
		
		@GetMapping("remove-product")
		public String rimuovi(Model model) {
			if (session.getUser() != null && session.getUser().isAdmin()) {
				throw new ForbiddenException();
			}
			return "prodotti";
		}
	}
	
}
