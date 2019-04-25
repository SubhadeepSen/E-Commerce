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
import sdp.shop.now.dao.entity.Order;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.OrderService;

@RestController
@RequestMapping("api/orderService")
@Api(value = "Order Service", description = "Order Service")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Place New Order")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New Order Placed") })
	@RequestMapping(value = "/placeOrder", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public long placeOrder(@RequestBody Order order) throws ECommerceException {
		return orderService.placeOrder(order);
	}

	@ApiOperation(value = "Retrieve Order History")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order History Retrieved") })
	@RequestMapping(value = "/orderHistory/{customerId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public List<Order> orderHistory(@PathVariable("customerId") long customerId) throws ECommerceException {
		return orderService.orderHistory(customerId);
	}
}
