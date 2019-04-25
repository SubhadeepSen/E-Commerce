package sdp.shop.now.service.impl;

import static sdp.shop.now.util.ECommerceUtil.isEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.dao.repository.CategoryRepository;
import sdp.shop.now.dao.repository.ProductRepository;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CategoryService;
import sdp.shop.now.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	public long addProduct(Product product) throws ECommerceException {
		if (null != product && isValid(product)) {
			Category category = null;
			try {
				category = categoryService.getCategoryByName(product.getCategory().getName());
			} catch (ECommerceException e) {
				LOGGER.info(e.getMessage() + " : " + "updating category");
			}
			if (null == category) {
				categoryService.addCategory(product.getCategory());
			} else {
				product.getCategory().setId(category.getId());
				product.getCategory().setName(category.getName());
				categoryRepository.save(product.getCategory());
			}
			return productRepository.save(product).getId();
		}
		return -1;
	}

	@Override
	public Product getProductById(long productId) throws ECommerceException {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent())
			return buildProduct(product.get());
		throw new ECommerceException("Product [" + productId + "] " + "not found");
	}

	@Override
	public List<Product> getProducts() {
		return buildProductList(productRepository.findAll());
	}

	@Override
	public void updateProduct(Product product) throws ECommerceException {
		if (isValid(product)) {
			Product p = getProductById(product.getId());
			product.setId(p.getId());
			productRepository.save(product);
		}
	}

	@Override
	public void deleteProductById(long ProductId) {
		productRepository.deleteById(ProductId);
	}

	private boolean isValid(Product product) throws ECommerceException {
		if (isEmpty(product.getName()))
			throw new ECommerceException("Product name cannot be empty");
		if (product.getPrice() == 0)
			throw new ECommerceException("Product price cannot be zero");
		if (null == product.getCategory())
			throw new ECommerceException("Product category cannot be null");
		if (isEmpty(product.getCategory().getName()))
			throw new ECommerceException("Product category cannot be empty");
		return true;
	}

	private List<Product> buildProductList(Iterable<Product> products) {
		List<Product> productList = null;
		if (null != products) {
			productList = new ArrayList<>();
			for (Product p : products) {
				productList.add(buildProduct(p));
			}
		}
		return productList;
	}

	private Product buildProduct(Product p) {
		Product product = new Product();
		product.setId(p.getId());
		product.setCategory(p.getCategory());
		product.setName(p.getName());
		product.setPrice(p.getPrice());
		product.setQuantity(p.getQuantity());
		return product;
	}

}
