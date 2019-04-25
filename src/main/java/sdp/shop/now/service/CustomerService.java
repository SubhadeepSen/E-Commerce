package sdp.shop.now.service;

import java.util.List;

import sdp.shop.now.dao.entity.Customer;
import sdp.shop.now.exception.ECommerceException;

public interface CustomerService {

	public long addCustomer(Customer customer) throws ECommerceException;

	public Customer getCustomerById(long customerId);

	public Customer getCustomerByUserId(String userId) throws ECommerceException;

	public List<Customer> getCustomers();

	public void updateCustomer(Customer customer) throws ECommerceException;

	public void deleteCustomerById(long customerId);
}
