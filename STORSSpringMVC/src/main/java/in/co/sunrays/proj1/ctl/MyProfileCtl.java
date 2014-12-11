package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.form.UserForm;
import in.co.sunrays.proj1.service.UserServiceInt;

import java.sql.Timestamp;
import java.util.Date;

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
 * MyProfile functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class MyProfileCtl extends BaseCtl {

	@Autowired
	private UserServiceInt service;

	/**
	 * Display MyProfile Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/MyProfile/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
		System.out.println("In MyProfileCtl.doDisplay()" + id);
		UserForm form = new UserForm();
		UserDTO dto = new UserDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findByPK(id);
				form.setId(dto.getId());
				form.setFirstName(dto.getFirstName());
				form.setLastName(dto.getLastName());
				form.setDob(dto.getDob());
				form.setEmailId(dto.getEmailId());
				form.setGender(dto.getGender());
				form.setMobileNo(dto.getMobileNo());
			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("MyProfile", "form", form);

	}

	/**
	 * Perform User Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	@RequestMapping(value = "/MyProfile/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("form") @Valid UserForm form,
			BindingResult bindingResult) {
		System.out.println("In MyProfileCtl.doSubmit()");
		UserDTO dto = new UserDTO();
		System.out.println("result Fail :" + bindingResult.hasErrors());

		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setDob(form.getDob());
		dto.setGender(form.getGender());
		dto.setEmailId(form.getEmailId());
		dto.setMobileNo(form.getMobileNo());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in MyProfileCtl add operation");
				service.findByLogin(dto.getEmailId());
				form.setMessage("Data is Updated Successfully");
			}

		} catch (ApplicationException e) {
			System.out.println("Critical Issue " + e);
			return new ModelAndView("MyProfile", "form", form);
		}

		System.out.println("out UserCtl add operation");
		return new ModelAndView("MyProfile", "form", form);
	}
}
