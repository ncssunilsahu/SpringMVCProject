package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.form.CollegeForm;
import in.co.sunrays.proj1.service.CollegeServiceInt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class CollegeCtl extends BaseCtl {

	@Autowired
	private CollegeServiceInt service;

	/**
	 * Display College Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/College/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
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

	/**
	 * Perform College Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	@RequestMapping(value = "/College/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(
			@ModelAttribute("form") @Valid CollegeForm form,
			BindingResult bindingResult) {
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
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in CollegeCtl add operation");

				if (dto.getId() > 0) {
					service.update(dto);
					form.setMessage("Data is Updated Successfully");
				} else {
					Long id = service.add(dto);
					System.out.println(id + " data inserted");
					form.setMessage("Data is Added Successfully");
					form.setId(id);
				}
			} else if (OP_DELETE.equalsIgnoreCase(form.getOperation())) {
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

	/**
	 * Performs List and Search operation on CollegeList
	 * 
	 * @param form
	 * @return
	 */

	@RequestMapping(value = "College/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") CollegeForm form,
			@RequestParam(required = false) Long id) {
		System.out.println("in collegectl searchList method");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		list = null;
		CollegeDTO dto = new CollegeDTO();

		if (OP_NEXT.equalsIgnoreCase(form.getOperation())) {
			pageNo++;

		} else if (OP_PREVIOUS.equalsIgnoreCase(form.getOperation())) {
			pageNo--;

		} else if (OP_GO.equalsIgnoreCase(form.getOperation())) {
			if (form.getName() != null && form.getName().length() > 0) {
				dto.setName(form.getName());
			}
		}

		if (OP_DELETE.equalsIgnoreCase(form.getOperation())) {
			System.out.println("in del op");
			System.out.println("ctl delete id is :" + id);
			dto.setId(id);
			try {
				service.delete(dto);

			} catch (ApplicationException e) {
				e.printStackTrace();
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

	@RequestMapping(method = RequestMethod.GET, value = "College/pdf")
	public ModelAndView generatePdfReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List usersList;
		try {
			usersList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					usersList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("pdfReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "College/xls")
	public ModelAndView generateXLSReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List usersList;
		try {
			usersList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					usersList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("xlsReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "College/csv")
	public ModelAndView generateCSVReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List usersList;
		try {
			usersList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					usersList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("csvReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "College/html")
	public ModelAndView generateHTMLReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List usersList;
		try {
			usersList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					usersList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("htmlReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

}
