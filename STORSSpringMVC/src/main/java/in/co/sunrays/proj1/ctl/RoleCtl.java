package in.co.sunrays.proj1.ctl;

import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.form.RoleForm;
import in.co.sunrays.proj1.service.RoleServiceInt;
import java.sql.Timestamp;
import java.util.Date;
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
 * Role functionality Controller. Performs operation
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Controller
public class RoleCtl extends BaseCtl {

	@Autowired
	private RoleServiceInt service;

	/**
	 * Display Role Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/Role/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
		System.out.println("In RolerCtl.doDisplay()" + id);
		RoleForm form = new RoleForm();
		RoleDTO dto = new RoleDTO();
		if (id != null && id > 0) {
			try {
				dto = service.findByPK(id);
				form.setId(dto.getId());
				form.setName(dto.getName());
				form.setDescription(dto.getDescription());
			} catch (ApplicationException e) {
				e.printStackTrace();
				form.setMessage("Critical issue : " + e.getMessage());
			}
		}
		return new ModelAndView("Role", "form", form);

	}

	/**
	 * Perform Role Operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	@RequestMapping(value = "/Role/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("form") @Valid RoleForm form,
			BindingResult bindingResult) {
		System.out.println("In RoleCtl.doSubmit()");
		RoleDTO dto = new RoleDTO();

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return new ModelAndView("Role", "form", form);
		}

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setDescription(form.getDescription());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in RoleCtl add operation");
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
			return new ModelAndView("Role", "form", form);
		} catch (DuplicateRecordException e) {
			System.out.println("Role Name already exist." + e);
			form.setMessage("Role Name already exist");
			return new ModelAndView("Role", "form", form);
		}

		System.out.println("out RoleCtl add operation");
		return new ModelAndView("Role", "form", form);
	}

	/**
	 * Performs List and Search operation on RoleList
	 * 
	 * @param form
	 * @return
	 */

	@RequestMapping(value = "Role/search", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") RoleForm form,
			@RequestParam(required = false) Long id) {
		System.out.println("in rolectl searchList method");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		list = null;
		RoleDTO dto = new RoleDTO();

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
				long ids = 0;
				return searchList(form, ids);

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
		System.out.println("out RoleCtl.searchList()");
		return new ModelAndView("RoleList", "form", form);
	}

	@RequestMapping(method = RequestMethod.GET, value = "Role/pdf")
	public ModelAndView generatePdfReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List rolesList;
		try {
			rolesList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					rolesList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("pdfReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "Role/xls")
	public ModelAndView generateXLSReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List rolesList;
		try {
			rolesList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					rolesList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("xlsReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "Role/csv")
	public ModelAndView generateCSVReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List rolesList;
		try {
			rolesList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					rolesList);
			parameterMap.put("datasource", JRdataSource);
			// pdfReport bean has ben declared in the jasper-views.xml file
			modelAndView = new ModelAndView("csvReport", parameterMap);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "Role/html")
	public ModelAndView generateHTMLReport(ModelAndView modelAndView) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List rolesList;
		try {
			rolesList = service.list();
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(
					rolesList);
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
