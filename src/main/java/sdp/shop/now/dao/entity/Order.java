package sdp.shop.now.dao.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAILS")
@NamedQuery(name = "findOrderByCustomerId", query = "SELECT order FROM Order order WHERE order.customerId = :customerId")
public class Order {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	@SequenceGenerator(name = "order_id", sequenceName = "order_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")
	private long id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<OrderedProduct> orderedProducts;

	@Column(name = "DATE", nullable = false)
	private LocalDate date;

	@Column(name = "TOTAL_PRICE", nullable = false)
	private double totalPrice;

	@Column(name = "DISCOUNT", nullable = false)
	private double discount;

	@OneToOne
	@JoinColumn(name = "COUPON")
	private Coupon coupon;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private long customerId;

	public Order() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the orderedProducts
	 */
	public List<OrderedProduct> getOrderedProducts() {
		return orderedProducts;
	}

	/**
	 * @param orderedProducts
	 *            the orderedProducts to set
	 */
	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the coupon
	 */
	public Coupon getCoupon() {
		return coupon;
	}

	/**
	 * @param coupon
	 *            the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public boolean equals(Object object) {
		if (!object.getClass().equals(this.getClass()))
			return false;
		Order order = (Order) object;
		return order.coupon.equals(coupon) && order.customerId == customerId && order.date.equals(date)
				&& order.discount == discount && order.orderedProducts.equals(orderedProducts)
				&& order.totalPrice == totalPrice;

	}

	@Override
	public int hashCode() {
		return (int) (id + customerId + discount + totalPrice + coupon.hashCode() + orderedProducts.hashCode());
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", CustomerId: " + customerId + ", TotalPrice: " + totalPrice + ", Discount: " + discount
				+ ", Date: " + date + ", OrderedProduct: " + orderedProducts.toString() + "]";
	}
}
