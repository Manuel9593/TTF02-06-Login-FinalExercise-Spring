package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Data;
import lombok.NoArgsConstructor;
import ttf02.ecommerce.spring.backend.services.UserService;

@Controller
public class UserController {
	@Autowired
	PortalSession session;
	
	@Autowired
	UserService userService;
	
	@GetMapping("dettagli")
	public String user(Model m) {
		m.addAttribute("user", session.getUser());
		return "dettagli";
	}
	
	@Data
	@Component
	@NoArgsConstructor
	private class UserError {
		private boolean name = false;
		private boolean surname = false;
	}
	
	@PostMapping("update-user")
	public String updateUser(String name, String surname, Model model) {
		UserError error = new UserError();
		if (name.isBlank())
			error.setName(true);
		if (surname.isBlank())
			error.setSurname(true);
		model.addAttribute("user", session.getUser());
		model.addAttribute("userError", error);
		return "redirect:/dettagli";
	}
	
	@Data
	@Component
	@NoArgsConstructor
	private class PasswordError {
		private boolean name = false;
		private boolean surname = false;
	}
	@PostMapping("update-password")
	public String updateUser(String oldPassword, String newPassword, String confirmPassword, Model model) {
		PasswordError error = new PasswordError();
		if (oldPassword.isBlank())
			return "redirect:/dettaglio?passError=old";
		if (newPassword.isBlank())
			return "redirect:/dettaglio?passError=new";
		if (confirmPassword.isBlank())
			return "redirect:/dettaglio?passError=confirm";
		model.addAttribute("user", session.getUser());
		model.addAttribute("passwordError", error);
		return "redirect:/dettagli";
	}
}
