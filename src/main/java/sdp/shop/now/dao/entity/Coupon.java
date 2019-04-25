package sdp.shop.now.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COUPON_DETAILS")
@NamedQuery(name = "findByCouponCode", query = "SELECT coupon FROM Coupon coupon WHERE coupon.code = :couponCode")
public class Coupon {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	@SequenceGenerator(name = "coupon_id", sequenceName = "coupon_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_id")
	private long id;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "PERCENTAGE", nullable = false)
	private double percentage;

	@Column(name = "MAX_LIMIT", nullable = false)
	private double maxLimit;

	@OneToOne
	@JoinColumn
	private Category category;

	public Coupon() {
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the percentage
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the maxLimit
	 */
	public double getMaxLimit() {
		return maxLimit;
	}

	/**
	 * @param maxLimit
	 *            the maxLimit to set
	 */
	public void setMaxLimit(double maxLimit) {
		this.maxLimit = maxLimit;
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
		Coupon coupon = (Coupon) object;
		return coupon.code.equals(code) && coupon.category.equals(category) && coupon.maxLimit == maxLimit
				&& coupon.percentage == percentage;

	}

	@Override
	public int hashCode() {
		return (int) (id + code.hashCode() + category.hashCode() + maxLimit + percentage);
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", Code: " + code + ", Percentage: " + percentage + ", MaxLimit: " + maxLimit
				+ ", Category: " + category.toString() + "]";
	}

}
