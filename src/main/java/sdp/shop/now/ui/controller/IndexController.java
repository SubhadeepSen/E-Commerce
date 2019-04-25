package sdp.shop.now.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String onLoad(HttpServletRequest request, Model model) {
		if (null != request.getSession().getAttribute("username")
				&& request.getSession().getAttribute("username").equals("sunny12345")) {
			model.addAttribute("adminUser", true);
		}
		model.addAttribute("baseUrl", request.getRequestURL());
		return "index";
	}
}
