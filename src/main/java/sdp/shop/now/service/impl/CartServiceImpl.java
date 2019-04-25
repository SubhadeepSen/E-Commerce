package sdp.shop.now.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdp.shop.now.dao.entity.Cart;
import sdp.shop.now.dao.repository.CartRepository;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public void addToCart(Cart cart) throws ECommerceException {
		if (cart.getCustomerId() <= 0)
			throw new ECommerceException("customer id is not valid");
		if (cart.getProductId() <= 0)
			throw new ECommerceException("product id is not valid");
		if (cart.getQuantity() <= 0)
			throw new ECommerceException("quantity cannot be zero");
		cartRepository.save(cart);
	}

	@Override
	public int removeFromCart(Cart cart, boolean isAllItem) {
		int result = 0;
		if (isAllItem) {
			result = entityManager.createNamedQuery("deleteAllItemFromCart", Cart.class)
					.setParameter("customerId", cart.getCustomerId()).executeUpdate();
		} else {
			result = entityManager.createNamedQuery("deleteItemFromCart", Cart.class)
					.setParameter("productId", cart.getProductId()).setParameter("customerId", cart.getCustomerId())
					.executeUpdate();
		}
		entityManager.flush();
		entityManager.close();
		return result;
	}

	@Override
	public List<Cart> getCart(long customerId) {
		List<Cart> cartItems = entityManager.createNamedQuery("retrieveCartItems", Cart.class)
				.setParameter("customerId", customerId).getResultList();
		entityManager.close();
		return cartItems;
	}
}
