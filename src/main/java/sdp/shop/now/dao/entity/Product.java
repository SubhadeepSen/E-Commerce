package sdp.shop.now.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_DETAILS")
public class Product {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	@SequenceGenerator(name = "product_id", sequenceName = "product_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "PRICE", nullable = false)
	private double price;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@OneToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	public Product() {

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object object) {
		if (!object.getClass().equals(this.getClass()))
			return false;
		Product product = (Product) object;
		return product.name.equalsIgnoreCase(name) && product.category.equals(category) && product.price == price
				&& product.quantity == quantity;

	}

	@Override
	public int hashCode() {
		return (int) (id + name.hashCode() + category.hashCode() + price + quantity);
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Category: "
				+ category.toString() + "]";
	}
}
