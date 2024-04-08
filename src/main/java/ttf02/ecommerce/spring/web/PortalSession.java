package ttf02.ecommerce.spring.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;
import ttf02.ecommerce.spring.backend.Cart;
import ttf02.ecommerce.spring.backend.entities.User;

@Data
@Component
@SessionScope
public class PortalSession {
	private User user;
	private Cart cart = new Cart();
}
