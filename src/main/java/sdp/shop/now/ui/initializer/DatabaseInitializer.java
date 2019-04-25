package sdp.shop.now.ui.initializer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.dao.entity.Customer;
import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CouponService;
import sdp.shop.now.service.CustomerService;
import sdp.shop.now.service.ProductService;

@Component
public class DatabaseInitializer {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;
	@Autowired
	private CouponService couponService;

	public void init() throws ECommerceException {
		addCutomer();
		addProduct();
	}

	private void addProduct() throws ECommerceException {
		Product product1 = new Product();
		Category category1 = new Category();
		category1.setName("Music");
		product1.setCategory(category1);
		product1.setName("Sony IPOD");
		product1.setPrice(345.00);
		product1.setQuantity(10);
		productService.addProduct(product1);
		Product product2 = new Product();
		product2.setCategory(category1);
		product2.setName("DVD Player");
		product2.setPrice(1220.00);
		product2.setQuantity(10);
		productService.addProduct(product2);
		Product product3 = new Product();
		Category category2 = new Category();
		category2.setName("Electronics");
		product3.setCategory(category2);
		product3.setName("Play Station");
		product3.setPrice(7543.00);
		product3.setQuantity(10);
		productService.addProduct(product3);
		Product product4 = new Product();
		product4.setCategory(category2);
		product4.setName("Laptop");
		product4.setPrice(35124.00);
		product4.setQuantity(10);
		productService.addProduct(product4);
		Product product5 = new Product();
		Category category3 = new Category();
		category3.setName("Movies");
		product5.setCategory(category3);
		product5.setName("Avengers");
		product5.setPrice(5500.00);
		product5.setQuantity(10);
		productService.addProduct(product5);
		Product product6 = new Product();
		product6.setCategory(category3);
		product6.setName("Spider Man");
		product6.setPrice(3245.00);
		product6.setQuantity(10);
		productService.addProduct(product6);
		Product product7 = new Product();
		product7.setCategory(category3);
		product7.setName("Justice League");
		product7.setPrice(4675.00);
		product7.setQuantity(10);
		productService.addProduct(product7);
		
		Coupon coupon = new Coupon();
		coupon.setCategory(category1);
		coupon.setCode("AllFree");
		coupon.setMaxLimit(250.00);
		coupon.setPercentage(8.0);
		couponService.addCoupon(coupon);
	}

	private void addCutomer() throws ECommerceException {
		Customer customer = new Customer();
		customer.setAddress("Hyderabad");
		customer.setDob(LocalDate.parse("1995-07-28"));
		customer.setEmailId("xyz@gmail.com");
		customer.setFirstname("John");
		customer.setLastname("Jones");
		customer.setPassword("12345");
		customer.setPhoneNumber("7549315467");
		customer.setUserId("john");
		customerService.addCustomer(customer);
		
		customer = new Customer();
		customer.setAddress("Hyderabad");
		customer.setDob(LocalDate.parse("1995-07-28"));
		customer.setEmailId("xyz@gmail.com");
		customer.setFirstname("Sunny");
		customer.setLastname("Sen");
		customer.setPassword("12345");
		customer.setPhoneNumber("7549315467");
		customer.setUserId("sunny12345");
		customerService.addCustomer(customer);
	}

}
