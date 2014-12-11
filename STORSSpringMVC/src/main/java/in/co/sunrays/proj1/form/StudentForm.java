package in.co.sunrays.proj1.form;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class StudentForm extends BaseForm {

	/**
	 * First Name of Student
	 */
	@NotEmpty
	private String firstName;
	/**
	 * Last Name of Student
	 */
	@NotEmpty
	private String lastName;
	/**
	 * Date of Birth of Student
	 */
	private Date dob;
	/**
	 * Mobileno of Student
	 */
	@NotEmpty
	private String mobileNo;
	/**
	 * Email of Student
	 */
	@NotEmpty
	private String email;
	/**
	 * CollegeId of Student
	 */

	private long collegeId;
	/**
	 * College name of Student
	 */
	@NotEmpty
	private String collegeName;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

}
