package sdp.shop.now.dao.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
@SqlResultSetMapping(name = "deleteResult", columns = { @ColumnResult(name = "count") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "deleteItemFromCart", query = "DELETE FROM CART WHERE PRODUCT_ID = :productId AND CUSTOMER_ID = :customerId", resultSetMapping = "deleteResult"),
		@NamedNativeQuery(name = "deleteAllItemFromCart", query = "DELETE FROM CART WHERE CUSTOMER_ID = :customerId", resultSetMapping = "deleteResult") })
@NamedQuery(name = "retrieveCartItems", query = "SELECT cart FROM Cart cart WHERE cart.customerId = :customerId")
public class Cart {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	@SequenceGenerator(name = "cart_id", sequenceName = "cart_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id")
	private long id;

	@Column(name = "PRODUCT_ID", nullable = false)
	private long productId;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private long customerId;

	public Cart() {
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
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		Cart cart = (Cart) object;
		return cart.customerId == customerId && cart.productId == productId && cart.quantity == quantity;

	}

	@Override
	public int hashCode() {
		return (int) (id + customerId + productId + quantity);
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", CustomerID: " + customerId + ", ProductID: " + productId + ", Quantity: " + quantity
				+ "]";
	}
}
