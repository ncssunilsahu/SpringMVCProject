package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.MarksheetDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.form.MarksheetForm;
import in.co.sunrays.proj1.service.MarksheetServiceInt;

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
 * Marksheet functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class MarksheetCtl extends BaseCtl {

	@Autowired
	private MarksheetServiceInt service;

	/**
	 * Display Marksheet Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/Marksheet/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
		System.out.println("In MarksheetCtl.doDisplay()" + id);
		MarksheetForm form = new MarksheetForm();
		MarksheetDTO dto = new MarksheetDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findByPK(id);
				form.setName(dto.getName());
				form.setRollNo(dto.getRollNo());
				form.setPhysics(dto.getPhysics());
				form.setChemistry(dto.getChemistry());
				form.setMaths(dto.getMaths());

			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("Marksheet", "form", form);

	}

	/**
	 * Perform Marksheet Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	/*
	 * @RequestMapping(value = "/Marksheet/submit", method = RequestMethod.POST)
	 * public ModelAndView doSubmit(
	 * 
	 * @ModelAttribute("form") @Valid MarksheetForm form, BindingResult
	 * bindingResult) {
	 */

	@RequestMapping(value = "/Marksheet/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(
			@ModelAttribute("form") @Valid MarksheetForm form,
			BindingResult bindingResult) {
		System.out.println("In MarksheetCtl.doSubmit()");
		MarksheetDTO dto = new MarksheetDTO();

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("Marksheet", "form", form);
		}

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setRollNo(form.getRollNo());
		dto.setPhysics(form.getPhysics());
		dto.setChemistry(form.getChemistry());
		dto.setMaths(form.getMaths());

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in MarksheetCtl add operation");
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
			return new ModelAndView("Marksheet", "form", form);
		} catch (DuplicateRecordException e) {
			System.out.println("Roll No already exist." + e);
			form.setMessage("Roll No already exist");
			return new ModelAndView("Marksheet", "form", form);
		}

		System.out.println("out MarksheetCtl add operation");
		return new ModelAndView("Marksheet", "form", form);
	}

	/**
	 * Performs List and Search operation on MarksheetList
	 * 
	 * @param form
	 * @return
	 */

	@RequestMapping(value = "Marksheet/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") MarksheetForm form) {
		System.out.println("in Marksheetctl searchList method");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		list = null;
		MarksheetDTO dto = new MarksheetDTO();

		if (OP_NEXT.equalsIgnoreCase(form.getOperation())) {
			pageNo++;

		} else if (OP_PREVIOUS.equalsIgnoreCase(form.getOperation())) {
			pageNo--;

		} else if (OP_GO.equalsIgnoreCase(form.getOperation())) {
			if (form.getName() != null && form.getName().length() > 0) {
				dto.setName(form.getName());
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
		System.out.println("out MarksheetCtl.searchList()");
		return new ModelAndView("MarksheetList", "form", form);
	}

}
