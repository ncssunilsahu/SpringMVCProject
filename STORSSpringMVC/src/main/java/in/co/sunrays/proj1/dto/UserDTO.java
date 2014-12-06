package in.co.sunrays.proj1.dto;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * User JavaBean encapsulates User attributes
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Entity
@Table(name = "st_user")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class UserDTO extends BaseDTO {

	/**
	 * Non Business primary key
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 20)
	protected long id = 0;

	/**
	 * Contains USER ID who created this database record
	 */
	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy = null;

	/**
	 * Contains USER ID who modified this database record
	 */
	@Column(name = "MODIFIED_BY", length = 50)
	protected String modifiedBy = null;

	/**
	 * Contains Created Timestamp of database record
	 */
	@Column(name = "CREATED_DATETIME")
	protected Timestamp createdDatetime;

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";

	/**
	 * Contains Modified Timestamp of database record
	 */

	@Column(name = "MODIFIED_DATETIME")
	protected Timestamp modifiedDatetime;

	/**
	 * First Name of User
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;
	/**
	 * Last Name of User
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * Login Id of User
	 */
	@Column(name = "LOGIN_ID")
	private String login;
	/**
	 * Password of User
	 */
	@Column(name = "PASSWORD")
	private String password;
	/**
	 * Date of Birth of User
	 */
	@Column(name = "DATE_OF_BIRTH")
	private Date dob;
	/**
	 * Mobileno of User
	 */
	@Column(name = "MOBILE_NO")
	private String mobileNo;

	/**
	 * Role _Id of User
	 */
	@Column(name = "ROLE_ID")
	private long roleId;

	/**
	 * Number of unsuccessful login attempt
	 */
	@Column(name = "UN_SUCCESSFUL_LOGIN")
	private int unSuccessfulLogin;

	/**
	 * Gender of User
	 */
	@Column(name = "GENDER")
	private String gender;

	/**
	 * Last login timestamp
	 */
	@Column(name = "LAST_LOGIN")
	protected Timestamp lastLogin;

	/**
	 * User Lock
	 */
	@Column(name = "LOCK")
	private String lock;

	/**
	 * IP Address of User from where User was registred.
	 */
	@Column(name = "REGISTERED_IP")
	private String registeredIP;

	/**
	 * IP Address of User of his last login
	 */
	@Column(name = "LAST_LOGIN_IP")
	private String lastLoginIP;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
