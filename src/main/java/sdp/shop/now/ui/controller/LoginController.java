package sdp.shop.now.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdp.shop.now.dao.entity.Cart;
import sdp.shop.now.dao.entity.Customer;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CartService;
import sdp.shop.now.service.CustomerService;
import sdp.shop.now.service.ProductService;
import sdp.shop.now.ui.initializer.DatabaseInitializer;
import sdp.shop.now.util.Encrypter;

@Controller
@RequestMapping(value = "login")
public class LoginController {

	private static String EMPTY = "";

	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private static boolean isInitialized = false;

	@Autowired
	private DatabaseInitializer DatabaseInitializer;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;

	@RequestMapping(method = RequestMethod.GET)
	public String onLoad(HttpServletRequest request, Model model) {
		if (!isInitialized) {
			try {
				DatabaseInitializer.init();
			} catch (ECommerceException e) {
				LOGGER.info("INIT: " + e.getMessage());
			}
			isInitialized = true;
		}

		if (null != request.getSession().getAttribute("username")) {
			addProductsAndCartDetails(request, model);
			return "product";
		}
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, String username, String password) {
		if (!EMPTY.equals(username) && !EMPTY.equals(password)) {
			try {
				Customer customer = customerService.getCustomerByUserId(username);
				if (customer.getUserId().equals(username)
						&& customer.getPassword().equals(Encrypter.encrypt(password))) {
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("customerId", customer.getId());
					addProductsAndCartDetails(request, model);
					return "product";
				}
			} catch (ECommerceException e) {
				LOGGER.info(e.getMessage());
			}
		}
		model.addAttribute("message", "Invalid username or password");
		return "login";
	}
	
	private void addProductsAndCartDetails(HttpServletRequest request, Model model) {
		model.addAttribute("products", productService.getProducts());
		List<Cart> cart = cartService.getCart((long) request.getSession().getAttribute("customerId"));
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("cartSize", cart.size());
	}
}
