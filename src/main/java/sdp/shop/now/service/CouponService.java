package sdp.shop.now.service;

import java.util.List;

import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.exception.ECommerceException;

public interface CouponService {

	public long addCoupon(Coupon coupon) throws ECommerceException;

	public Coupon getCouponByCode(String couponCode) throws ECommerceException;

	public List<Coupon> getCoupons();

	public void updateCoupon(Coupon coupon) throws ECommerceException;

	public void deleteCouponById(long couponId);
}
