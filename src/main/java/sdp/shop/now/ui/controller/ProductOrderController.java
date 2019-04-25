package sdp.shop.now.ui.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdp.shop.now.dao.entity.Cart;
import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.dao.entity.Order;
import sdp.shop.now.dao.entity.OrderedProduct;
import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CartService;
import sdp.shop.now.service.CouponService;
import sdp.shop.now.service.OrderService;
import sdp.shop.now.service.ProductService;

@Controller
public class ProductOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "orderHistory", method = RequestMethod.GET)
	public String onLoad(HttpServletRequest request, Model model) {
		if (null != request.getSession().getAttribute("username")) {
			try {
				List<Order> orderHistory = orderService
						.orderHistory((long) request.getSession().getAttribute("customerId"));
				model.addAttribute("oldOrders", orderHistory);
			} catch (ECommerceException e) {
				// TODO: do something
			}
			return "orderHistory";
		}
		return "login";
	}

	@RequestMapping(value = "placeOrder", method = RequestMethod.POST)
	public String placeOrder(HttpServletRequest request, Model model) throws ECommerceException {
		if (null != request.getSession().getAttribute("username")) {
			String[] idQnty = request.getParameterValues("prodId");
			String couponCode = request.getParameter("couponCode");
			List<OrderedProduct> orderedProducts = new ArrayList<>();
			long customerId = (long) request.getSession().getAttribute("customerId");
			int quantity = 0;
			long productId = 0;
			double totalPrice = 0.0;
			Product product = null;
			Order order = new Order();
			OrderedProduct orderedProduct = null;
			Cart cart = new Cart();
			for (String str : idQnty) {
				productId = Long.parseLong(str.split(":")[0]);
				quantity = Integer.parseInt(str.split(":")[1]);
				cart.setCustomerId(customerId);
				cart.setProductId(productId);
				cartService.removeFromCart(cart, false);
				product = productService.getProductById(productId);
				orderedProduct = new OrderedProduct();
				orderedProduct.setCategory(product.getCategory());
				orderedProduct.setName(product.getName());
				orderedProduct.setPrice(quantity * product.getPrice());
				orderedProduct.setQuantity(quantity);
				orderedProducts.add(orderedProduct);
				totalPrice += quantity * product.getPrice();
			}
			order.setOrderedProducts(orderedProducts);
			order.setCustomerId(customerId);
			order.setDate(LocalDate.now());
			totalPrice = applyCoupon(order, couponCode, totalPrice);
			order.setTotalPrice(totalPrice);
			long orderId = orderService.placeOrder(order);
			request.getSession().setAttribute("orderId", orderId);
			request.getSession().setAttribute("totalAmount", totalPrice);
			return "redirect:confirmation";
		}
		return "login";
	}

	@RequestMapping(value = "confirmation", method = RequestMethod.GET)
	public String confirmation(HttpServletRequest request, Model model) {
		model.addAttribute("orderId", request.getSession().getAttribute("orderId"));
		model.addAttribute("totalAmount", request.getSession().getAttribute("totalAmount"));
		request.getSession().removeAttribute("orderId");
		request.getSession().removeAttribute("totalAmount");
		List<Cart> cart = cartService.getCart((long) request.getSession().getAttribute("customerId"));
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("cartSize", cart.size());
		return "confirmation";
	}

	private double applyCoupon(Order order, String couponCode, double totalPrice) {
		Coupon coupon = null;
		try {
			coupon = couponService.getCouponByCode(couponCode);
		} catch (ECommerceException e) {

		}
		if (null != coupon) {
			order.setCoupon(coupon);
			double discount = (totalPrice * coupon.getPercentage()) / 100;
			if (discount > coupon.getMaxLimit()) {
				totalPrice = totalPrice - coupon.getMaxLimit();
				order.setDiscount(coupon.getMaxLimit());
			} else {
				totalPrice = totalPrice - discount;
				order.setDiscount(discount);
			}
		} else {
			order.setCoupon(coupon);
			order.setDiscount(0.0);
		}
		return totalPrice;
	}
}
