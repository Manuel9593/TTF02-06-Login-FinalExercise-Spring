package ttf02.ecommerce.spring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ttf02.ecommerce.spring.backend.entities.Product;

public class Cart {
	
	private Map<Product, Integer> cartList = new HashMap<Product, Integer>();
	
	public List<Product> getProducts() {
		List<Product> res = new ArrayList<>();
		cartList.keySet().forEach(p -> res.add(p));
		return res;
	}
	
	public int getQuantity(Product p) {
		Integer res = cartList.get(p);
		return res == null ? 0 : res.intValue();
	}
	
	public void add(Product p, int quantity) {
		cartList.put(p, getQuantity(p)+quantity);
	}
	
	public void remove(Product p) {
		cartList.remove(p);
	}
	
	public void clearCart() {
		cartList.clear();
	}
	
	public int getTotal() {
		int total = 0;
		for (Product p: getProducts())
			total += getQuantity(p) * p.getPrice();
		return total;
	}
}
