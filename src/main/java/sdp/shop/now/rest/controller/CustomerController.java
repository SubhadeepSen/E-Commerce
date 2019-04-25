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
import sdp.shop.now.dao.entity.Customer;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CustomerService;

@RestController
@RequestMapping("api/customerService")
@Api(value = "Customer Service", description = "Customer Service")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Add New Customer")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New Customer Added") })
	@RequestMapping(value = "/addNewCustomer", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public long addCustomer(@RequestBody Customer customer) throws ECommerceException {
		return customerService.addCustomer(customer);
	}

	@ApiOperation(value = "Retrieve Customer")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer Retrieved") })
	@RequestMapping(value = "/retrieveCustomerById/{customerId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public Customer retrieveCustomerById(@PathVariable("customerId") long customerId) {
		return customerService.getCustomerById(customerId);
	}

	@ApiOperation(value = "Retrieve Customer")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer Retrieved") })
	@RequestMapping(value = "/retrieveCustomerByUserId/{userId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public Customer retrieveCustomerByUserId(@PathVariable("userId") String userId) throws ECommerceException {
		return customerService.getCustomerByUserId(userId);
	}

	@ApiOperation(value = "Retrieve Customers")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customers Retrieved") })
	@RequestMapping(value = "/retrieveCustomers", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public List<Customer> retrieveCustomers() {
		return customerService.getCustomers();
	}

	@ApiOperation(value = "Update Customer")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer Updated") })
	@RequestMapping(value = "/updateCustomer", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public void updateCustomer(@RequestBody Customer customer) throws ECommerceException {
		customerService.updateCustomer(customer);
	}

	@ApiOperation(value = "Delete Customer")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer Deleted") })
	@RequestMapping(value = "/deleteCustomer/{customerId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable("customerId") long customerId) {
		customerService.deleteCustomerById(customerId);
	}

}
