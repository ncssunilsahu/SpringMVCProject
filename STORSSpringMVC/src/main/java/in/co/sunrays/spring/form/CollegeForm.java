package in.co.sunrays.spring.form;


import org.hibernate.validator.constraints.NotEmpty;

public class CollegeForm extends BaseForm {

	@NotEmpty(message = "Name Can not be null")
	// @Size(min = 2, max = 15, message =
	// "Your Name must between 2 and 15 characters")
	private String name;
	@NotEmpty
	private String address;
	@NotEmpty
	private String state;
	@NotEmpty
	private String city;
	@NotEmpty
	// @Size(min = 10, max = 12)
	// @NotNull @Min(18) @Max(100)
	private String phoneNo;
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
