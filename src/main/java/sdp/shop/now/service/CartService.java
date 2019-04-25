package sdp.shop.now.service;

import java.util.List;

import sdp.shop.now.dao.entity.Cart;
import sdp.shop.now.exception.ECommerceException;

public interface CartService {

	public void addToCart(Cart cart) throws ECommerceException;

	public List<Cart> getCart(long customerId);
	
	public int removeFromCart(Cart cart, boolean isAllItem);
}
