package ttf02.ecommerce.spring.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import ttf02.ecommerce.spring.backend.entities.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
