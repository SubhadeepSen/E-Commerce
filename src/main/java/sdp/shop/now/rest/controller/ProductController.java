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
import sdp.shop.now.dao.entity.Product;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.ProductService;

@RestController
@RequestMapping("api/productService")
@Api(value = "Product Service", description = "Product Service")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Add New Product")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New Product Added") })
	@RequestMapping(value = "/addProduct", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public long addProduct(@RequestBody Product product) throws ECommerceException {
		return productService.addProduct(product);
	}

	@ApiOperation(value = "Retrieve Product")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Retrieved") })
	@RequestMapping(value = "/retrieveProduct/{productId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public Product retrieveProductById(@PathVariable("productId") long productId) throws ECommerceException {
		return productService.getProductById(productId);
	}

	@ApiOperation(value = "Retrieve Products")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Products Retrieved") })
	@RequestMapping(value = "/retrieveProducts", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public List<Product> retrieveProducts() {
		return productService.getProducts();
	}

	@ApiOperation(value = "Update Product")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Updated") })
	@RequestMapping(value = "/updateProduct", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public void updateProduct(@RequestBody Product product) throws ECommerceException {
		productService.updateProduct(product);
	}

	@ApiOperation(value = "Delete Product")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Deleted") })
	@RequestMapping(value = "/deleteProduct/{ProductId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	public void deleteProductById(@PathVariable("ProductId") long ProductId) {
		productService.deleteProductById(ProductId);
	}
}
