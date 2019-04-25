package sdp.shop.now.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "logout")
public class LogoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("customerId");
		request.getSession().invalidate();
		model.addAttribute("message", "You have been succesfully logged out");
		return "login";
	}
}
