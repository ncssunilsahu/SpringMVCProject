package in.co.sunrays.proj1.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Role Form class of project. (1)It encapsulate Role attribute and
 * populate data between Form and Controller
 * 
 * (2)Apply Validation at Role Form
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class RoleForm extends BaseForm {

	/**
	 * Predefined Role constants
	 */
	public static final long ROLE_ADMIN = 1;
	public static final long ROLE_STUDENT = 2;
	public static final long ROLE_COLLEGE = 3;
	public static final long ROLE_KIOSK = 4;

	/**
	 * Role Name
	 */
	@NotEmpty
	private String name;
	/**
	 * Role Description
	 */
	@NotEmpty
	private String description;

	/**
	 * accessor
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
