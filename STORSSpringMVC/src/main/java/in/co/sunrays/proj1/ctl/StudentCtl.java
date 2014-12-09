package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.StudentDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.form.StudentForm;
import in.co.sunrays.proj1.service.StudentServiceInt;
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
 * Student functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class StudentCtl extends BaseCtl {

	@Autowired
	private StudentServiceInt service;

	/**
	 * Display Student Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/Student/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
		System.out.println("In StudentCtl.doDisplay()" + id);
		StudentForm form = new StudentForm();
		StudentDTO dto = new StudentDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findByPK(id);
				form.setFirstName(dto.getFirstName());
				form.setLastName(dto.getLastName());
				form.setCollegeName(dto.getCollegeName());
				form.setDob(dto.getDob());
				form.setMobileNo(dto.getMobileNo());
				form.setEmail(dto.getEmail());

			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("Student", "form", form);

	}

	/**
	 * Perform Student Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	/*
	 * @RequestMapping(value = "/Student/submit", method = RequestMethod.POST)
	 * public ModelAndView doSubmit(
	 * 
	 * @ModelAttribute("form") @Valid StudentForm form, BindingResult
	 * bindingResult) {
	 */

	@RequestMapping(value = "/Student/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(
			@ModelAttribute("form") @Valid StudentForm form,
			BindingResult bindingResult) {
		System.out.println("In StudentCtl.doSubmit()");
		StudentDTO dto = new StudentDTO();

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("Student", "form", form);
		}

		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setCollegeName(form.getCollegeName());
		dto.setDob(form.getDob());
		dto.setMobileNo(form.getMobileNo());
		dto.setEmail(form.getEmail());

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in StudentCtl add operation");
				if (dto.getId() > 0) {
					service.update(dto);
					form.setMessage("Data is Updated Successfully");
				} else {
					Long id = service.add(dto);
					System.out.println(id + " data inserted");
					form.setMessage("Data is Added Successfully");
				}
			} else if (OP_DELETE.equalsIgnoreCase(form.getOperation())) {
				service.delete(dto);
				form.setMessage("Data is Deleted Successfully");
				System.out.println("Data Deleted Successfully.");
			}

		} catch (ApplicationException e) {
			System.out.println("Critical Issue " + e);
			return new ModelAndView("Student", "form", form);
		} catch (DuplicateRecordException e) {
			System.out.println("Collge Name already exist." + e);
			form.setMessage("Collge Name already exist");
			return new ModelAndView("Student", "form", form);
		}

		System.out.println("out StudentCtl add operation");
		return new ModelAndView("Student", "form", form);
	}

	/**
	 * Performs List and Search operation on StudentList
	 * 
	 * @param form
	 * @return
	 */

	@RequestMapping(value = "Student/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") StudentForm form) {
		System.out.println("in Studentctl searchList method");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		list = null;
		StudentDTO dto = new StudentDTO();

		if (OP_NEXT.equalsIgnoreCase(form.getOperation())) {
			pageNo++;

		} else if (OP_PREVIOUS.equalsIgnoreCase(form.getOperation())) {
			pageNo--;

		} else if (OP_GO.equalsIgnoreCase(form.getOperation())) {
			if (form.getFirstName() != null && form.getFirstName().length() > 0) {
				dto.setFirstName(form.getFirstName());
			}
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;

		try {

			list = service.search(dto, pageNo, pageSize);

			if (list.size() == 0) {
				pageNo--;
				list = service.search(dto, pageNo, pageSize);

			}

			form.setPageNo(pageNo);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		form.setDtoList(list);

		System.out.println("form list size :" + form.getDtoList().size());
		System.out.println("out StudentCtl.searchList()");
		return new ModelAndView("StudentList", "form", form);
	}

}
