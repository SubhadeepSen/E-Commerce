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
import sdp.shop.now.dao.entity.Cart;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CartService;

@RestController
@RequestMapping("api/cartService")
@Api(value = "Cart Service", description = "Cart Service")
public class CartController {

	@Autowired
	private CartService cartService;

	@ApiOperation(value = "Add To Cart")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Added To Cart") })
	@RequestMapping(value = "/addToCart", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public void addToCart(@RequestBody Cart cart) throws ECommerceException {
		cartService.addToCart(cart);
	}

	@ApiOperation(value = "Retrieve Cart")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cart Retrieved") })
	@RequestMapping(value = "/retrieveCart/{customerId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public List<Cart> retrieveCart(@PathVariable("customerId") long customerId) {
		return cartService.getCart(customerId);
	}

	@ApiOperation(value = "Remove From Cart")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Removed From Cart") })
	@RequestMapping(value = "/removeFromCart/{allItems}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	public int removeFromCart(@RequestBody Cart cart, @PathVariable("allItems") boolean isAllItem) {
		return cartService.removeFromCart(cart, isAllItem);
	}
}
