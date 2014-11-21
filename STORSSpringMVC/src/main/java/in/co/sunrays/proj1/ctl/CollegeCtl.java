package in.co.sunrays.proj1.ctl;

import in.co.sunrays.form.CollegeForm;
import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.service.CollegeServiceInt;
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
 * College functionality Controller. Performs operation
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@Controller
public class CollegeCtl extends BaseCtl {

	@Autowired
	private CollegeServiceInt service;

	@RequestMapping(value = "/College/hello", method = RequestMethod.GET)
	public void hello() {
		System.out.println("In CollegeCtl.Hello()");

	}

	/*
	 * @RequestMapping(value = "/College/display", method = RequestMethod.GET)
	 * public ModelAndView doDisplay(
	 * 
	 * @RequestParam(value="0", required = false) Long id) {
	 */

	@RequestMapping(value = "/College/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		System.out.println("In CollegeCtl.doDisplay()" + id);
		CollegeForm form = new CollegeForm();
		CollegeDTO dto = new CollegeDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findById(id);
				form.setId(dto.getId());
				form.setName(dto.getName());
				form.setAddress(dto.getAddress());
				form.setCity(dto.getCity());
				form.setState(dto.getState());
				form.setPhoneNo(dto.getPhoneNo());
			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("College", "form", form);

	}

	/*
	 * 
	 * @RequestMapping(value = "/College/submit", method = RequestMethod.POST)
	 * public ModelAndView doSubmit(
	 * 
	 * @ModelAttribute("form")CollegeForm form, @Valid CollegeForm form,
	 * BindingResult bindingResult, @RequestParam String operation)
	 */

	@RequestMapping(value = "/College/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(
			@ModelAttribute("form") @Valid CollegeForm form,
			BindingResult bindingResult, @RequestParam String operation) {
		System.out.println("In CollegeCtl.doSubmit()");
		CollegeDTO dto = new CollegeDTO();

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("College", "form", form);
		}

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setAddress(form.getAddress());
		dto.setCity(form.getCity());
		dto.setState(form.getState());
		dto.setPhoneNo(form.getPhoneNo());
		try {
			if (OP_SAVE.equalsIgnoreCase(operation)) {

				System.out.println("in CollegeCtl add operation");
				if (dto.getId() > 0) {
					service.update(dto);
					form.setMessage("Data is Updated Successfully");
				} else {
					Long id = service.add(dto);
					System.out.println(id + " data inserted");
					form.setId(id);
					form.setMessage("Data is Added Successfully");
				}
			} else if (OP_DELETE.equalsIgnoreCase(operation)) {
				service.delete(dto);
				form.setMessage("Data is Deleted Successfully");
				System.out.println("Data Deleted Successfully.");
			}

		} catch (ApplicationException e) {
			System.out.println("Critical Issue " + e);
			return new ModelAndView("College", "form", form);
		} catch (DuplicateRecordException e) {
			System.out.println("Collge Name already exist." + e);
			form.setMessage("Collge Name already exist");
			return new ModelAndView("College", "form", form);
		}

		System.out.println("out CollegeCtl add operation");
		return new ModelAndView("College", "form", form);
	}

	@RequestMapping(value = "College/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") CollegeForm form,
			@RequestParam(required = false) String operation) {
		System.out.println("in collegectl searchList method");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		list = null;
		CollegeDTO dto = new CollegeDTO();

		if (OP_NEXT.equalsIgnoreCase(operation)) {
			pageNo++;

		} else if (OP_PREVIOUS.equalsIgnoreCase(operation)) {
			pageNo--;

		} else if (OP_GO.equalsIgnoreCase(operation)) {
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
		System.out.println("out CollegeCtl.searchList()");
		return new ModelAndView("CollegeList", "form", form);
	}

}
