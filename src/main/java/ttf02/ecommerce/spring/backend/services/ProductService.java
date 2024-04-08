package ttf02.ecommerce.spring.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ttf02.ecommerce.spring.backend.repositories.ProductRepository;
import ttf02.ecommerce.spring.backend.entities.Product;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	public Product getProduct(int id) {
		return repo.findById(id).get();
	}
}
