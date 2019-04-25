package sdp.shop.now.service;

import java.util.List;

import sdp.shop.now.dao.entity.Order;
import sdp.shop.now.exception.ECommerceException;

public interface OrderService {

	public long placeOrder(Order order) throws ECommerceException;

	public List<Order> orderHistory(long customerId) throws ECommerceException;
}
