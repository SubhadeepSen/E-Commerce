package sdp.shop.now.ui.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sdp.shop.now.dao.entity.Customer;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CustomerService;
import sdp.shop.now.ui.model.Register;

@Controller
@RequestMapping(value = "register")
public class RegisterController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public String onLoad() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(HttpServletRequest request, Model model, Register register) {
		if (!register.getDob().matches("\\d{4}-\\d{2}-\\d{2}")) {
			model.addAttribute("message", "Please enter DoB in yyyy-mm-dd format.");
			return "register";
		}
		
		Customer customer = buildCustomerEntity(register);
		try {
			customerService.addCustomer(customer);
			request.getSession().setAttribute("username", customer.getUserId());
		} catch (ECommerceException e) {
			model.addAttribute("message", e.getMessage());
			return "register";
		}

		return "forward:login";
	}

	private Customer buildCustomerEntity(Register register) {
		Customer customer = new Customer();
		customer.setAddress(register.getAddress());
		customer.setDob(LocalDate.parse(register.getDob()));
		customer.setEmailId(register.getEmailAddress());
		customer.setFirstname(register.getFirstname());
		customer.setLastname(register.getLastname());
		customer.setPassword(register.getPassword());
		customer.setPhoneNumber(register.getPhoneNumber());
		customer.setUserId(register.getUsername());
		return customer;
	}
}
