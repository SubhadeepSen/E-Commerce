package sdp.shop.now.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_DETAILS")
@NamedQuery(name = "findByCategoryName", query = "SELECT category FROM Category category WHERE category.name = :categoryName")
public class Category {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	@SequenceGenerator(name = "category_id", sequenceName = "category_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id")
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	public Category() {
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

	@Override
	public boolean equals(Object object) {
		if (!object.getClass().equals(this.getClass()))
			return false;
		Category category = (Category) object;
		return category.name.equalsIgnoreCase(name);

	}

	@Override
	public int hashCode() {
		return (int) id + name.hashCode();
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", Name: " + name + "]";
	}
}
