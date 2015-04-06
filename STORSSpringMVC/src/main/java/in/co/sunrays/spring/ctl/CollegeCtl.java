package in.co.sunrays.spring.ctl;

import java.util.List;

import in.co.sunrays.spring.dto.CollegeDTO;
import in.co.sunrays.spring.exception.ApplicationException;
import in.co.sunrays.spring.exception.DuplicateRecordException;
import in.co.sunrays.spring.form.CollegeForm;
import in.co.sunrays.spring.form.LoginForm;
import in.co.sunrays.spring.service.CollegeServiceInt;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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

	private static Logger log = Logger.getLogger(CollegeCtl.class);

	@RequestMapping(value = "/College", method = RequestMethod.GET)
	public String doDisplay(@RequestParam(required = false) Long id,
			@ModelAttribute("form") CollegeForm form, Model model) {

		log.debug("In CollegeCtl display start " + id);

		CollegeDTO dto = new CollegeDTO();

		if (id != null && id > 0) {
			dto = service.findById(id);
			form.setId(dto.getId());
			form.setName(dto.getName());
			form.setAddress(dto.getAddress());
			form.setCity(dto.getCity());
			form.setState(dto.getState());
			form.setPhoneNo(dto.getPhoneNo());
		}
		return "College";

	}

	@RequestMapping(value = "/College", method = RequestMethod.POST)
	public String doSubmit(@RequestParam String operation,
			@ModelAttribute("form") @Valid CollegeForm form,
			BindingResult bindingResult, Model model) {

		log.debug("In CollegeCtl.doSubmit()");

		if (bindingResult.hasErrors()) {
			log.debug("Input Error");
			return "College";
		}

		CollegeDTO dto = new CollegeDTO();
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
					model.addAttribute("success",
							"Data is Updated Successfully");
				} else {
					Long id = service.add(dto);
					form.setId(id);
					model.addAttribute("success", "Data is Added Successfully");
				}
			} else if (OP_DELETE.equalsIgnoreCase(operation)) {
				service.delete(dto);
				model.addAttribute("success", "Data is Deleted Successfully");
			}

		} catch (Exception e) {
			model.addAttribute("error", "Critical Issue " + e.getMessage());
		}

		return "College";
	}

	@RequestMapping(value = "College/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String searchList(@ModelAttribute("form") CollegeForm form,
			@RequestParam(required = false) String operation, Model model) {

		log.debug("in collegectl searchList method");

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
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

		List list = service.search(dto, pageNo, pageSize);

		form.setDtoList(list);
		form.setPageNo(pageNo);

		return "CollegeList";
	}

}
