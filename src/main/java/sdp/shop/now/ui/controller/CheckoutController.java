package sdp.shop.now.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.ProductService;

@Controller
public class CheckoutController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkoutCart(HttpServletRequest request, Model model) throws ECommerceException {
		String[] idQnty = request.getParameterValues("prodId");
		List<Product> products = new ArrayList<>();
		int quantity = 0;
		long productId = 0;
		double totalPrice = 0.0;
		Product product = null;
		for (String str : idQnty) {
			productId = Long.parseLong(str.split(":")[0]);
			quantity = Integer.parseInt(str.split(":")[1]);
			product = productService.getProductById(productId);
			product.setQuantity(quantity);
			products.add(product);
			totalPrice += quantity * product.getPrice();
		}
		model.addAttribute("products", products);
		model.addAttribute("totalPrice", totalPrice);
		return "reviewProduct";
	}

}
