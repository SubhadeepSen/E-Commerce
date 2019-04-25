package sdp.shop.now.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CouponService;

@RestController
@RequestMapping("api/couponService")
@Api(value = "Coupon Service", description = "Coupon Service")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@ApiOperation(value = "Add New Coupon")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New Coupon Added") })
	@RequestMapping(value = "/addCoupon", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public long addCoupon(@RequestBody Coupon Coupon) throws ECommerceException {
		return couponService.addCoupon(Coupon);
	}

	@ApiOperation(value = "Retrieve Coupon")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Coupon Retrieved") })
	@RequestMapping(value = "/retrieveCoupon/{couponCode}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public Coupon retrieveCouponByCode(@PathVariable("couponCode") String couponCode) throws ECommerceException {
		return couponService.getCouponByCode(couponCode);
	}

	@ApiOperation(value = "Retrieve Coupons")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Coupons Retrieved") })
	@RequestMapping(value = "/retrieveCoupons", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public List<Coupon> retrieveCoupons() {
		return couponService.getCoupons();
	}

	@ApiOperation(value = "Update Coupon")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Coupon Updated") })
	@RequestMapping(value = "/updateCoupon", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public void updateCoupon(@RequestBody Coupon coupon) throws ECommerceException {
		couponService.updateCoupon(coupon);
	}

	@ApiOperation(value = "Delete Coupon")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Coupon Deleted") })
	@RequestMapping(value = "/deleteCoupon/{couponId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	public void deleteCouponById(@PathVariable("couponId") long couponId) {
		couponService.deleteCouponById(couponId);
	}
}
