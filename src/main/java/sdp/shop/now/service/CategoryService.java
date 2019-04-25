package sdp.shop.now.service;

import java.util.List;

import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.exception.ECommerceException;

public interface CategoryService {

	public long addCategory(Category category) throws ECommerceException;

	public Category getCategoryByName(String categoryName) throws ECommerceException;

	public List<Category> getCategories();

	public void deleteCategoryByName(String categoryName) throws ECommerceException;
}
