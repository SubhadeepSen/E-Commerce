package sdp.shop.now.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.ProductService;

@Controller
public class ProductDetailsController {

	@Autowired
	private ProductService poductService;

	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public String onLoad(HttpServletRequest request, Model model, @PathVariable("productId") long productId) {
		if (null != request.getSession().getAttribute("username")) {
			try {
				model.addAttribute("product", poductService.getProductById(productId));
			} catch (ECommerceException e) {
				// TODO Auto-generated catch block
			}
			return "productDetails";
		}
		return "login";
	}
}
