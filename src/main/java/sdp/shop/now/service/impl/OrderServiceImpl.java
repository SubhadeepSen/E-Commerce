package sdp.shop.now.service.impl;

import static sdp.shop.now.util.ECommerceUtil.isEmpty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.dao.entity.Order;
import sdp.shop.now.dao.entity.OrderedProduct;
import sdp.shop.now.dao.repository.CategoryRepository;
import sdp.shop.now.dao.repository.CouponRepository;
import sdp.shop.now.dao.repository.OrderRepository;
import sdp.shop.now.dao.repository.OrderedProductRepository;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CategoryService;
import sdp.shop.now.service.CouponService;
import sdp.shop.now.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderedProductRepository orderedProductRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public long placeOrder(Order order) throws ECommerceException {
		if (null != order && isValid(order)) {
			Category category = null;
			for (OrderedProduct product : order.getOrderedProducts()) {
				saveCategory(category, product);
			}
			saveCoupon(order);
			return orderRepository.save(order).getId();
		}
		return -1;
	}

	@Override
	public List<Order> orderHistory(long customerId) throws ECommerceException {
		if (customerId <= 0)
			throw new ECommerceException("invalid customer id [" + customerId + "]");
		TypedQuery<Order> namedQuery = entityManager.createNamedQuery("findOrderByCustomerId", Order.class);
		List<Order> orders = namedQuery.setParameter("customerId", customerId).getResultList();
		entityManager.close();
		return orders;
	}

	private boolean isValid(Order order) throws ECommerceException {
		if (order.getCustomerId() <= 0)
			throw new ECommerceException("customer id is not valid");
		if (null == order.getOrderedProducts() || order.getOrderedProducts().size() <= 0)
			throw new ECommerceException("ordered product should have valid order(s)");
		for (OrderedProduct product : order.getOrderedProducts()) {
			isValidProduct(product);
		}
		if (null == order.getDate())
			throw new ECommerceException("order should have a valid date");
		if (order.getTotalPrice() <= 0)
			throw new ECommerceException("price cannot be zero or less");
		return true;
	}

	private boolean isValidProduct(OrderedProduct product) throws ECommerceException {
		if (isEmpty(product.getName()))
			throw new ECommerceException("Product name cannot be empty");
		if (product.getPrice() == 0)
			throw new ECommerceException("Product price cannot be zero");
		if (null == product.getCategory())
			throw new ECommerceException("Product category cannot be null");
		if (isEmpty(product.getCategory().getName()))
			throw new ECommerceException("Product category cannot be empty");
		return true;
	}

	private void saveCoupon(Order order) {
		Coupon coupon = null;
		if (null != order.getCoupon()) {
			try {
				coupon = couponService.getCouponByCode(order.getCoupon().getCode());
			} catch (ECommerceException e) {
				LOGGER.info(e.getMessage());
			}
		}
		if (null != coupon) {
			order.setCoupon(coupon);
			couponRepository.save(order.getCoupon());
		} else {
			order.setCoupon(null);
		}
	}

	private void saveCategory(Category category, OrderedProduct product) throws ECommerceException {
		try {
			category = categoryService.getCategoryByName(product.getCategory().getName());
		} catch (ECommerceException e) {
			LOGGER.info(e.getMessage() + " : " + "updating category");
		}
		if (null == category) {
			categoryService.addCategory(product.getCategory());
		} else {
			product.getCategory().setId(category.getId());
			product.getCategory().setName(category.getName());
			categoryRepository.save(product.getCategory());
		}
		orderedProductRepository.save(product);
	}
}
