package ttf02.ecommerce.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ttf02.ecommerce.spring.backend.services.UserService;

@Controller
@Slf4j
public class UserController {
	@Autowired
	PortalSession session;
	
	@Autowired
	UserService userService;
	
	@GetMapping("dettagli")
	public String user(String nameError, String passError, Model m) {
		m.addAttribute("user", session.getUser());
		m.addAttribute("nameError", nameError);
		m.addAttribute("passError", passError);
		return "dettagli";
	}
	
	
	@PostMapping("update-user")
	public String updateUser(String name, String surname, Model model) {
		if (name.isBlank())
			return "redirect:/dettagli?nameError=name";
		if (surname.isBlank())
			return "redirect:/dettagli?nameError=surname";
		userService.update(session.getUser().getLogin(), name, surname);
		model.addAttribute("user", session.getUser());
		return "redirect:/dettagli";
	}
	
	@PostMapping("update-password")
	public String updateUser(String oldPassword, String newPassword, String confirmPassword, Model model) {
		if (oldPassword.isBlank())
			return "redirect:/dettagli?passError=old";
		if (newPassword.isBlank())
			return "redirect:/dettagli?passError=new";
		if (confirmPassword.isBlank())
			return "redirect:/dettagli?passError=confirm";
		if (oldPassword.compareTo(newPassword) == 0)
			return "redirect:/dettagli?passError=already-used";
		if (newPassword.compareTo(confirmPassword) != 0)
			return "redirect:/dettagli?passError=not-equal";
		userService.updatePassword(session.getUser().getLogin(), confirmPassword);
		model.addAttribute("user", session.getUser());
		return "redirect:/dettagli";
	}
	
	@PostMapping("ricaricaCredito")
	public String updateUser(Integer credit) {
		if (credit >= 1 && credit != null) {
			userService.accreditatePurchase(session.getUser().getLogin(), credit);
			return "redirect:/carica";
		}
		return "redirect:/carica?err";
	}
}
