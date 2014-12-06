package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.form.UserForm;
import in.co.sunrays.proj1.service.UserServiceInt;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * User functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class UserCtl extends BaseCtl {

	@Autowired
	private UserServiceInt service;

	/**
	 * Display User Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/User/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
		System.out.println("In UserCtl.doDisplay()" + id);
		UserForm form = new UserForm();
		UserDTO dto = new UserDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findByPK(id);
				form.setId(dto.getId());
				form.setFirstName(dto.getFirstName());
				form.setLastName(dto.getLastName());
				form.setDob(dto.getDob());
				form.setLogin(dto.getLogin());
				form.setGender(dto.getGender());
				form.setMobileNo(dto.getMobileNo());
			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("User", "form", form);

	}

	/**
	 * Perform User Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	@RequestMapping(value = "/User/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("form") @Valid UserForm form,
			BindingResult bindingResult) {
		System.out.println("In UserCtl.doSubmit()");
		UserDTO dto = new UserDTO();

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("User", "form", form);
		}

		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setDob(form.getDob());
		dto.setGender(form.getGender());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setMobileNo(form.getMobileNo());
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in UserCtl add operation");
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
			return new ModelAndView("User", "form", form);
		} catch (DuplicateRecordException e) {
			System.out.println("Collge Name already exist." + e);
			form.setMessage("Collge Name already exist");
			return new ModelAndView("User", "form", form);
		}

		System.out.println("out UserCtl add operation");
		return new ModelAndView("User", "form", form);
	}

	/**
	 * Performs List and Search operation on UserList
	 * 
	 * @param form
	 * @return
	 */

	@RequestMapping(value = "User/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") UserForm form) {
		System.out.println("in collegectl searchList method");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		list = null;
		UserDTO dto = new UserDTO();

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
		System.out.println("out UserCtl.searchList()");
		return new ModelAndView("UserList", "form", form);
	}

	@RequestMapping(method = RequestMethod.GET, value = "User/pdf")
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

	@RequestMapping(method = RequestMethod.GET, value = "User/xls")
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

	@RequestMapping(method = RequestMethod.GET, value = "User/csv")
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

	@RequestMapping(method = RequestMethod.GET, value = "User/html")
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
