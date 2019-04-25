package sdp.shop.now.dao.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_DETAILS")
@NamedQuery(name = "findByUserId", query = "SELECT customer FROM Customer customer WHERE customer.userId = :userId")
public class Customer {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	@SequenceGenerator(name = "customer_id", sequenceName = "customer_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
	private long id;

	@Column(name = "FIRST_NAME", nullable = false, updatable = false)
	private String firstname;

	@Column(name = "LAST_NAME", nullable = false, updatable = false)
	private String lastname;

	@Column(name = "EMAIL_ID", nullable = false)
	private String emailId;

	@Column(name = "DOB", nullable = false, updatable = false)
	private LocalDate dob;

	@Column(name = "USER_ID", nullable = false, updatable = false)
	private String userId;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	public Customer() {
		super();
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object object) {
		if (!object.getClass().equals(this.getClass()))
			return false;
		Customer customer = (Customer) object;
		return customer.userId.equals(userId) && customer.address.equals(address) && customer.dob.equals(dob)
				&& customer.emailId.equals(emailId) && customer.firstname.equals(firstname)
				&& customer.lastname.equals(lastname) && customer.phoneNumber.equals(phoneNumber);

	}

	@Override
	public int hashCode() {
		return (int) id + firstname.hashCode() + lastname.hashCode() + address.hashCode() + emailId.hashCode()
				+ dob.hashCode() + phoneNumber.hashCode() + userId.hashCode();
	}

	@Override
	public String toString() {
		return "[ID: " + id + ", FirstName: " + firstname + ", LastName: " + lastname + ", UserID: " + userId
				+ ", Date of Birth: " + dob + ", EmailID: " + emailId + ", PhoneNumber: " + phoneNumber + ", Address: "
				+ address + "]";
	}

}
