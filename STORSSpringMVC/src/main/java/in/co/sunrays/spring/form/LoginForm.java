package in.co.sunrays.spring.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm extends BaseForm {

	@Email(message = "Enter Valid EmailId")
	private String emailId;

	@NotEmpty(message = "Password Can not be null")
	private String password;

	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
