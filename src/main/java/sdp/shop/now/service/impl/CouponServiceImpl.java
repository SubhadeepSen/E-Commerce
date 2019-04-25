package sdp.shop.now.service.impl;

import static sdp.shop.now.util.ECommerceUtil.isEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdp.shop.now.dao.entity.Category;
import sdp.shop.now.dao.entity.Coupon;
import sdp.shop.now.dao.repository.CategoryRepository;
import sdp.shop.now.dao.repository.CouponRepository;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CategoryService;
import sdp.shop.now.service.CouponService;
import sdp.shop.now.util.ECommerceUtil;

@Service
public class CouponServiceImpl implements CouponService {

	private static Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private EntityManager entityManager;

	@Override
	public long addCoupon(Coupon coupon) throws ECommerceException {
		if (null != coupon && isValid(coupon) && isNotExisting(coupon)) {
			Category category = null;
			try {
				category = categoryService.getCategoryByName(coupon.getCategory().getName());
			} catch (ECommerceException e) {
				LOGGER.info(e.getMessage() + " : " + "updating category");
			}
			if (null == category) {
				categoryService.addCategory(coupon.getCategory());
			} else {
				coupon.getCategory().setId(category.getId());
				coupon.getCategory().setName(category.getName());
				categoryRepository.save(coupon.getCategory());
			}
			return couponRepository.save(coupon).getId();
		}
		return -1;
	}

	@Override
	public Coupon getCouponByCode(String couponCode) throws ECommerceException {
		if (isEmpty(couponCode))
			return null;
		TypedQuery<Coupon> namedQuery = entityManager.createNamedQuery("findByCouponCode", Coupon.class);
		Coupon coupon = null;
		try {
			coupon = namedQuery.setParameter("couponCode", couponCode).getSingleResult();
		} catch (NoResultException e) {
			throw new ECommerceException("[" + couponCode + "]" + e.getMessage());
		} finally {
			entityManager.close();
		}
		return buildCoupon(coupon);
	}

	@Override
	public List<Coupon> getCoupons() {
		return buildCouponList(couponRepository.findAll());
	}

	@Override
	public void updateCoupon(Coupon coupon) throws ECommerceException {
		Coupon cpn = getCouponByCode(coupon.getCode());
		if (null != cpn) {
			coupon.setId(cpn.getId());
			couponRepository.save(coupon);
		}
	}

	@Override
	public void deleteCouponById(long couponId) {
		couponRepository.deleteById(couponId);
	}

	private boolean isValid(Coupon coupon) throws ECommerceException {
		if (ECommerceUtil.isEmpty(coupon.getCode()))
			throw new ECommerceException("coupon code cannot be empty");
		if (null == coupon.getCategory())
			throw new ECommerceException("category cannot be null");
		if (ECommerceUtil.isEmpty(coupon.getCategory().getName()))
			throw new ECommerceException("category name cannot be empty");
		/*if (coupon.getMaxLimit() == 0)
			throw new ECommerceException("maximum limit cannot be zero");*/
		return true;
	}

	private List<Coupon> buildCouponList(Iterable<Coupon> coupons) {
		List<Coupon> couponList = null;
		if (null != coupons) {
			couponList = new ArrayList<>();
			for (Coupon c : coupons) {
				couponList.add(buildCoupon(c));
			}
		}
		return couponList;
	}

	private Coupon buildCoupon(Coupon c) {
		Coupon coupon = null;
		if (null != c) {
			coupon = new Coupon();
			coupon.setCategory(c.getCategory());
			coupon.setCode(c.getCode());
			coupon.setId(c.getId());
			coupon.setMaxLimit(c.getMaxLimit());
			coupon.setPercentage(c.getPercentage());
		}
		return coupon;
	}

	private boolean isNotExisting(Coupon coupon) throws ECommerceException {
		Coupon c = null;
		try {
			c = getCouponByCode(coupon.getCode());
		} catch (ECommerceException e) {
			LOGGER.info(e.getMessage());
		}
		if (null != c && c.equals(coupon)) {
			throw new ECommerceException("cannot be added as the coupon already exists");
		}
		return true;
	}

}
