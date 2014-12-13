package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.exception.RecordNotFoundException;
import in.co.sunrays.proj1.form.RoleForm;
import in.co.sunrays.proj1.form.UserForm;
import in.co.sunrays.proj1.service.UserServiceInt;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * ChangePassword functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class ChangePasswordCtl extends BaseCtl {

	@Autowired
	private UserServiceInt service;

	/**
	 * Display User Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/ChangePassword/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		System.out.println("In ChangePasswordCtl.doDisplay()" + id);
		UserForm form = new UserForm();
		UserDTO dto = new UserDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findByPK(id);
				form.setId(dto.getId());
				form.setEmailId(dto.getEmailId());
			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("ChangePassword", "form", form);

	}

	/**
	 * Perform ChangePassword Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	@RequestMapping(value = "/ChangePassword/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("form")  @Valid UserForm form,BindingResult bindingResult,
			@RequestParam(required = false) Long id, String oldPassword,
			String newPassword,HttpSession session) {
		System.out.println("Session value :"
				+ session.getAttribute("userId"));
		id=(Long) session.getAttribute("userId");
		System.out.println("In ChangePasswordCtl.doSubmit()");
		System.out.println("id" + form.getId());
		System.out.println("oldPassword" + oldPassword);
		System.out.println("newPassword" + newPassword);

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in ChangePasswordCtl add operation");
				try {

					service.changePassword(id, oldPassword, newPassword);

					form.setMessage("Password Change Successfully");
				} catch (RecordNotFoundException e) {
					form.setMessage("Old Password not match.");
				}catch (ApplicationException e) {
					System.out.println("Critical Issue " + e);
				}
			}

		} catch (Exception e) {
			System.out.println("Critical Issue " + e);
			return new ModelAndView("ChangePassword", "form", form);
		}

		System.out.println("out ChangePasswordCtl add operation");
		return new ModelAndView("ChangePassword", "form", form);
	}

}
