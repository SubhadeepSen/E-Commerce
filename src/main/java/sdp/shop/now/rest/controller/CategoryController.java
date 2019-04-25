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
import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CategoryService;

@RestController
@RequestMapping("api/categoryService")
@Api(value = "Category Service", description = "Category Service")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "Add New Category")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "New Category Added") })
	@RequestMapping(value = "/addCategory", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public long addCategory(@RequestBody Category category) throws ECommerceException {
		return categoryService.addCategory(category);
	}

	@ApiOperation(value = "Retrieve Category")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category Retrieved") })
	@RequestMapping(value = "/retrieveCategory/{categoryName}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public Category retrieveCategoryByName(@PathVariable("categoryName") String categoryName) throws ECommerceException {
		return categoryService.getCategoryByName(categoryName);
	}

	@ApiOperation(value = "Retrieve Categories")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categories Retrieved") })
	@RequestMapping(value = "/retrieveCategories", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public List<Category> retrieveCategories() {
		return categoryService.getCategories();
	}

	@ApiOperation(value = "Delete Category")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, dataType = "string", paramType = "header"), 
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Category Deleted") })
	@RequestMapping(value = "/deleteCategory/{categoryName}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	public void deleteCategoryByName(@PathVariable("categoryName") String categoryName) throws ECommerceException {
		categoryService.deleteCategoryByName(categoryName);
	}
}
