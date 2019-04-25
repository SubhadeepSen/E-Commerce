package sdp.shop.now.service.impl;

import static sdp.shop.now.util.ECommerceUtil.isEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.dao.repository.CategoryRepository;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public long addCategory(Category category) throws ECommerceException {
		if (null != category && isValid(category)) {
			try {
				getCategoryByName(category.getName());
			} catch (ECommerceException e) {
				return categoryRepository.save(category).getId();
			}
			throw new ECommerceException(
					"cannot be added as the category [" + category.getName() + "] already exists");
		}
		return -1;
	}

	@Override
	public Category getCategoryByName(String categoryName) throws ECommerceException {
		if (isEmpty(categoryName))
			return null;
		TypedQuery<Category> namedQuery = entityManager.createNamedQuery("findByCategoryName", Category.class);
		Category category = null;
		try {
			category = namedQuery.setParameter("categoryName", categoryName).getSingleResult();
		} catch (NoResultException e) {
			throw new ECommerceException("[" + categoryName + "] " + e.getMessage());
		} finally {
			entityManager.close();
		}
		return buildCategory(category);
	}

	@Override
	public List<Category> getCategories() {
		return buildCategoryList(categoryRepository.findAll());
	}

	@Override
	public void deleteCategoryByName(String categoryName) throws ECommerceException {
		categoryRepository.delete(getCategoryByName(categoryName));
	}

	private boolean isValid(Category category) throws ECommerceException {
		if (isEmpty(category.getName()))
			throw new ECommerceException("Category name cannot be empty");
		return true;
	}

	private List<Category> buildCategoryList(Iterable<Category> categories) {
		List<Category> categoryList = null;
		if (null != categories) {
			categoryList = new ArrayList<>();
			for (Category c : categories) {
				categoryList.add(buildCategory(c));
			}
		}
		return categoryList;
	}

	private Category buildCategory(Category c) {
		Category category = null;
		if (null != c) {
			category = new Category();
			category.setId(c.getId());
			category.setName(c.getName());
		}
		return category;
	}
}
