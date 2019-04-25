package sdp.shop.now.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdp.shop.now.dao.entity.Cart;
import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CartService;
import sdp.shop.now.service.ProductService;

@Controller
public class CartDetailsController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/addToCart/{idQnty}", method = RequestMethod.GET)
	public String addToCart(HttpServletRequest request, @PathVariable("idQnty") String idQnty) {
		String productId = idQnty.split("qnty")[0];
		String quantity = idQnty.split("qnty")[1];

		if (null != request.getSession().getAttribute("username")) {
			request.getSession().removeAttribute("cart");
			request.getSession().removeAttribute("cartSize");
			Cart cart = new Cart();
			cart.setCustomerId((long) request.getSession().getAttribute("customerId"));
			cart.setProductId(Long.parseLong(productId));
			cart.setQuantity(Integer.parseInt(quantity));
			try {
				cartService.addToCart(cart);
			} catch (ECommerceException e) {
				// TODO Auto-generated catch block
			}
		}
		return "redirect:../login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cartDetails", method = RequestMethod.GET)
	public String retrieveCart(HttpServletRequest request, Model model) {
		if (null != request.getSession().getAttribute("username")) {
			List<Cart> cart = (List<Cart>) request.getSession().getAttribute("cart");
			List<Product> cartProducts = new ArrayList<>();
			Product product = null;
			for (Cart c : cart) {
				try {
					product = productService.getProductById(c.getProductId());
					product.setQuantity(c.getQuantity());
					cartProducts.add(product);
				} catch (ECommerceException e) {
					// TODO Auto-generated catch block
				}
			}
			model.addAttribute("cartProducts", cartProducts);
			return "cartDetails";
		}
		return "login";
	}
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET)
	public String removeFromCart(HttpServletRequest request, Model model, @PathVariable("productId") long productId) {
		if (null != request.getSession().getAttribute("username")) {
			Cart cart = new Cart();
			cart.setCustomerId((long) request.getSession().getAttribute("customerId"));
			cart.setProductId(productId);
			cartService.removeFromCart(cart, false);
			List<Cart> cartItems = cartService.getCart((long) request.getSession().getAttribute("customerId"));
			request.getSession().setAttribute("cart", cartItems);
			request.getSession().setAttribute("cartSize", cartItems.size());
		}
		return "redirect:../cartDetails";
	}
}
