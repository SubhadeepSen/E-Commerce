package sdp.shop.now.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CouponService;

@RestController
public class CouponValidateController {

	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "/coupon/{couponCode}", method = RequestMethod.GET)
	public String couponValidator(@PathVariable("couponCode") String couponCode) {
		String response = "";
		try {
			Coupon couponByCode = couponService.getCouponByCode(couponCode);
			response = "" + couponByCode.getPercentage() + ":" + couponByCode.getMaxLimit();
		} catch (ECommerceException e) {
			// TODO Auto-generated catch block
		}
		return response;
	}

}
