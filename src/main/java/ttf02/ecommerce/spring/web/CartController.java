package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ttf02.ecommerce.spring.backend.entities.Product;
import ttf02.ecommerce.spring.backend.services.ProductService;

@Controller
public class CartController {

	@Autowired
	PortalSession session;
	
	@Autowired
	ProductService ps;
	
	

	@GetMapping("carrello")
	public String carrello(String err, Model m) {
		if (err != null) {
			m.addAttribute("err", "credito");
		}
		m.addAttribute("user", session.getUser());
		m.addAttribute("cart", session.getCart());
		m.addAttribute("products", ps.getAllProducts());
		return "carrello";
	}
	
	@Controller
	@RequestMapping("carrello")
	public class CartOperationsController {

		@Autowired
		PortalSession session;
		
		@Autowired
		ProductService ps;

		@PostMapping("add-product")
		public String addproduct(Integer product, Integer quantity) {
			Product productObject = ps.getProduct(product);
			int prezzo = productObject.getPrice()*quantity;
			if (session.getCart().getTotal() + prezzo <= session.getUser().getCredit()) {
				session.getCart().add(productObject, quantity);
				return "redirect:../carrello";
			}
			return "redirect:../carrello?err";
		}
		
		
		@GetMapping("remove-product")
		public String removeProduct(int product) {
			session.getCart().remove(ps.getProduct(product));
			return "redirect:../carrello";
		}
		
		@GetMapping("purchase")
		public String removeProduct() {
			int userCredit = session.getUser().getCredit();
			int total = session.getCart().getTotal();
			int finalCredit = userCredit - total;
			if (finalCredit >= 0) {
				session.getCart().clearCart();
				session.getUser().setCredit(finalCredit);
				return "redirect:../carrello";
			}
			return "redirect:../carrello?err";
		}
	}

}
