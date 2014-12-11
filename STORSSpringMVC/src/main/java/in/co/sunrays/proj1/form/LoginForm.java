package in.co.sunrays.proj1.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Login Form class of project. (1)It encapsulate Login attribute and populate
 * data between Form and Controller
 * 
 * (2) Apply Validation at Login Form
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class LoginForm extends BaseForm {

	/**
	 * EmailId of User
	 */
	@NotEmpty(message = "EmailId Can not be null")
	@Email(message = "Enter Valid EmailId")
	private String emailId;

	/**
	 * Password of User
	 */
	@NotEmpty(message = "Password Can not be null")
	private String password;

	/**
	 * accessor
	 */
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

}
