package sdp.shop.now.service.impl;

import static sdp.shop.now.util.ECommerceUtil.isEmpty;
import static sdp.shop.now.util.Encrypter.encrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdp.shop.now.dao.entity.Customer;
import sdp.shop.now.dao.repository.CustomerRepository;
import sdp.shop.now.exception.ECommerceException;
import sdp.shop.now.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public long addCustomer(Customer customer) throws ECommerceException {
		if (null != customer && isValidDetail(customer)) {
			try {
				getCustomerByUserId(customer.getUserId());
			} catch (ECommerceException e) {
				customer.setPassword(encrypt(customer.getPassword()));
				return customerRepository.save(customer).getId();
			}
			throw new ECommerceException(
					"customer cannot be added as the user id [" + customer.getUserId() + "] already exists");
		}
		return -1L;
	}

	@Override
	public Customer getCustomerById(long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent())
			return buildCustomer(customer.get());
		return null;
	}

	@Override
	public Customer getCustomerByUserId(String userId) throws ECommerceException {
		if (isEmpty(userId))
			return null;
		TypedQuery<Customer> namedQuery = entityManager.createNamedQuery("findByUserId", Customer.class);
		Customer customer = null;
		try {
			customer = namedQuery.setParameter("userId", userId).getSingleResult();
		} catch (NoResultException e) {
			throw new ECommerceException("[" + userId + "] " + e.getMessage());
		} finally {
			entityManager.close();
		}
		return buildCustomer(customer);
	}

	@Override
	public List<Customer> getCustomers() {
		return buildCustomerList(customerRepository.findAll());
	}

	@Override
	public void updateCustomer(Customer customer) throws ECommerceException {
		Customer cust = getCustomerByUserId(customer.getUserId());
		if (null != cust && isValidDetail(customer)) {
			customer.setId(cust.getId());
			customer.setPassword(encrypt(customer.getPassword()));
			customerRepository.save(customer);
		}
	}

	@Override
	public void deleteCustomerById(long customerId) {
		customerRepository.deleteById(customerId);
	}

	private boolean isValidDetail(Customer customer) throws ECommerceException {
		if (isEmpty(customer.getAddress()))
			throw new ECommerceException("value for [Address] cannot be empty");
		if (isEmpty(customer.getEmailId()))
			throw new ECommerceException("value for [Email Address] cannot be empty");
		if (isEmpty(customer.getFirstname()))
			throw new ECommerceException("value for [First Name] cannot be empty");
		if (isEmpty(customer.getLastname()))
			throw new ECommerceException("value for [Last Name] cannot be empty");
		if (isEmpty(customer.getPassword()))
			throw new ECommerceException("value for [Password] cannot be empty");
		if (isEmpty(customer.getUserId()))
			throw new ECommerceException("value for [User Id] cannot be empty");
		if (isEmpty(customer.getPhoneNumber()))
			throw new ECommerceException("value for [Phone Number] cannot be empty");
		if (null == customer.getDob())
			throw new ECommerceException("value for [Date of Birth] cannot be null");
		return true;
	}

	private List<Customer> buildCustomerList(Iterable<Customer> customers) {
		List<Customer> customerList = null;
		if (null != customers) {
			customerList = new ArrayList<>();
			for (Customer customer : customers) {
				customerList.add(buildCustomer(customer));
			}
		}
		return customerList;
	}

	private Customer buildCustomer(Customer c) {
		Customer customer = null;
		if (null != c) {
			customer = new Customer();
			customer.setAddress(c.getAddress());
			customer.setDob(c.getDob());
			customer.setEmailId(c.getEmailId());
			customer.setFirstname(c.getFirstname());
			customer.setId(c.getId());
			customer.setLastname(c.getLastname());
			customer.setPassword(c.getPassword());
			customer.setPhoneNumber(c.getPhoneNumber());
			customer.setUserId(c.getUserId());
		}
		return customer;
	}
}
