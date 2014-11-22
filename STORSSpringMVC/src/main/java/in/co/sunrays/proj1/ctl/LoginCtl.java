package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.form.CollegeForm;
import in.co.sunrays.proj1.form.LoginForm;
import in.co.sunrays.proj1.service.CollegeServiceInt;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Login functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class LoginCtl extends BaseCtl {

	/**
	 * Display Login Page
	 * 
	 * @return
	 */

	@RequestMapping(value = "/Login/display", method = RequestMethod.GET)
	public ModelAndView doDisplay() {
		System.out.println("In LoginCtl.doDisplay()");
		LoginForm form = new LoginForm();
		form.setMessage("Welcome");
		return new ModelAndView("Login", "form", form);
	}

	/**
	 * Performs Login operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/Login/submit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView doSubmit(@ModelAttribute("form") @Valid LoginForm form,
			BindingResult bindingResult, HttpSession session) {
		System.out.println("In LogfinCtl.doSubmit()");

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("Login", "form", form);
		}
		if (OP_SIGNIN.equalsIgnoreCase(form.getOperation())) {

			System.out.println("in CollegeCtl SignIn operation");

			if (form.getEmailId().equals("raj@gmail.com")
					&& form.getPassword().equals("kumar")) {

				session.setAttribute("user", form.getEmailId());
				System.out.println("Session value :"
						+ session.getAttribute("user"));
				form.setMessage("Welcome :" + form.getEmailId());
				return new ModelAndView("Welcome", "form", form);
			} else {

				form.setMessage("invalid emailId or password");
				return new ModelAndView("Login", "form", form);
			}
		}

		System.out.println("out LoginCtl save operation");
		return new ModelAndView("Login", "form", form);

	}

	/**
	 * Perform Logout operation
	 * 
	 * @param session
	 * @param operation
	 * @return
	 */

	@RequestMapping(value = "/Login/logout", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView doLogout(HttpSession session,
			@RequestParam String operation) {
		System.out.println("In LoginCtl.doLogout()");

		LoginForm form = new LoginForm();
		if (OP_LOGOUT.equalsIgnoreCase(operation)) {
			session.invalidate();
			form.setMessage("Logout successfully");
			return new ModelAndView("Login", "form", form);

		}
		return new ModelAndView("Login", "form", form);

	}

}