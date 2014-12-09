package in.co.sunrays.proj1.form;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User Form class of project. (1)It encapsulate User attribute and populate
 * data between Form and Controller
 * 
 * (2)Apply Validation at User Form
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class UserForm extends BaseForm {

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";
	/**
	 * First Name of User
	 */
	@NotEmpty
	private String firstName;
	/**
	 * Last Name of User
	 */
	@NotEmpty
	private String lastName;
	/**
	 * Login of User
	 */
	@Email(message = "Enter Valid EmailId")
	private String emailId;
	/**
	 * Password of User
	 */
	@NotEmpty(message = "Password Can not be null")
	private String password;
	/**
	 * Date of Birth of User
	 */
	private Date dob;
	/**
	 * MobielNo of User
	 */
	@NotEmpty
	private String mobileNo;
	/**
	 * Role of User
	 */
	private long roleId;
	/**
	 * Number of unsuccessful login attempt
	 */
	private int unSuccessfulLogin;
	/**
	 * Gender of User
	 */
	private String gender;
	/**
	 * Last login timestamp
	 */
	private Timestamp lastLogin;
	/**
	 * User Lock
	 */
	private String lock = INACTIVE;
	/**
	 * IP Address of User from where User was registred.
	 */
	private String registeredIP;
	/**
	 * IP Address of User of his last login
	 */
	private String lastLoginIP;

	/**
	 * accessor
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}

	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getRegisteredIP() {
		return registeredIP;
	}

	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

}
