package sdp.shop.now.service;

import java.util.List;

import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.exception.ECommerceException;

public interface ProductService {

	public long addProduct(Product product) throws ECommerceException;

	public Product getProductById(long productId) throws ECommerceException;

	public List<Product> getProducts();

	public void updateProduct(Product product) throws ECommerceException;

	public void deleteProductById(long ProductId);
}
