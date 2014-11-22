package in.co.sunrays.proj1.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * College Form class of project. (1)It encapsulate College attribute and
 * populate data between Form and Controller
 * 
 * (2)Apply Validation at College Form
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public class CollegeForm extends BaseForm {

	/**
	 * Name of College
	 */
	@NotEmpty(message = "Name Can not be null")
	// @Size(min = 2, max = 15, message =
	// "Your Name must between 2 and 15 characters")
	private String name;

	/**
	 * Address of College
	 */
	@NotEmpty
	private String address;

	/**
	 * State of College
	 */
	@NotEmpty
	private String state;

	/**
	 * City of College
	 */
	@NotEmpty
	private String city;

	/**
	 * Phone number of College
	 */
	@NotEmpty
	// @Size(min = 10, max = 12)
	// @NotNull @Min(18) @Max(100)
	private String phoneNo;

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
