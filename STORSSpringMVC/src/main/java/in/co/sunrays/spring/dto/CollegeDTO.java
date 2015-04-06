package in.co.sunrays.spring.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "st_college")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class CollegeDTO {

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
	 * Contains Modified Timestamp of database record
	 */

/*	@Column(name = "MODIFIED_DATETIME")
	@Type(type="timestamp")
	protected Date modifiedDatetime;
*/
	/**
	 * Name of College
	 */
	@Column(name = "NAME", length = 100)
	private String name = null;

	/**
	 * Address of College
	 */
	@Column(name = "ADDRESS", length = 255)
	private String address = null;

	/**
	 * State of College
	 */
	@Column(name = "STATE", length = 20)
	private String state = null;

	/**
	 * City of College
	 */
	@Column(name = "CITY", length = 50)
	private String city = null;

	/**
	 * Phoneno of College
	 */
	@Column(name = "PHONE_NO", length = 15)
	private String phoneNo = null;

	
	/**
	 * accessor
	 */

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

/*	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}


	public void setModifiedDatetime(Date modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}
*/
	/**
	 * accessor
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	

	

}
