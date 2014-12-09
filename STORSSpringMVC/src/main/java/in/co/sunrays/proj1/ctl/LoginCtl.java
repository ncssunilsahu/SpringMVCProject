package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.form.LoginForm;
import in.co.sunrays.proj1.form.UserForm;
import in.co.sunrays.proj1.service.RoleServiceInt;
import in.co.sunrays.proj1.service.UserServiceInt;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private UserServiceInt service;

	@Autowired
	private RoleServiceInt roleservice;

	/**
	 * Display Login Page
	 * 
	 * @return
	 */

	@RequestMapping(value = "/Login/display", method = RequestMethod.GET)
	public String doDisplay(Model model) {
		System.out.println("In LoginCtl.doDisplay()");
		LoginForm form = new LoginForm();
		// form.setMessage("Welcome");
		model.addAttribute("form", form);
		model.addAttribute("message", "Welcome");
		return "Login";
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
		System.out.println("login" + form.getEmailId());
		System.out.println("password" + form.getPassword());
		UserDTO dto = new UserDTO();
		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("Login", "form", form);
		}

		dto.setEmailId(form.getEmailId());
		dto.setPassword(form.getPassword());
		try {
			if (OP_SIGNIN.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in CollegeCtl SignIn operation");
				dto = service.authenticate(dto);
				System.out.println("id" + dto.getRoleId());
				session.setAttribute("user", dto.getRoleId());
				RoleDTO roleDTO = roleservice.findByPK(dto.getRoleId());
				System.out.println("roleid" + roleDTO.getId());
				session.setAttribute("role", roleDTO.getName());
				System.out.println("Session value :"
						+ session.getAttribute("user"));
				form.setMessage("Welcome :" + dto.getFirstName());
				return new ModelAndView("Welcome", "form", form);

			}

		} catch (ApplicationException e) {
			System.out.println("Critical Issue " + e);
			form.setMessage("Invalid EmailID And Password");
			return new ModelAndView("Login", "form", form);
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
			return new ModelAndView("Login", "form", form);

		}
		return new ModelAndView("Login", "form", form);

	}

}