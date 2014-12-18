package in.co.sunrays.proj1.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.RecordNotFoundException;
import in.co.sunrays.proj1.form.UserForm;
import in.co.sunrays.proj1.service.UserServiceInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgetPasswordCtl extends BaseCtl {

	@Autowired
	private UserServiceInt service;

	/**
	 * Display Forget Password Page
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/ForgetPassword/display", method = RequestMethod.GET)
	public ModelAndView doDisplay(@RequestParam(required = false) Long id) {
		// @RequestParam(value="0", required = false) Long id) {
		System.out.println("In ForgetPasswordCtl.doDisplay()" + id);
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
		return new ModelAndView("ForgetPassword", "form", form);

	}

	/**
	 * Performs List and Search operation on UserList
	 * 
	 * @param form
	 * @return
	 */

	@RequestMapping(value = "/ForgetPassword/submit", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("form") @Valid UserForm form,
			BindingResult bindingResult,
			@RequestParam(required = false) Long id, String emailId) {

		System.out.println("in ForgetPasswordctl searchList method");

		try {
			if (OP_GO.equalsIgnoreCase(form.getOperation())) {

				System.out.println("in ForgetPasswordCtl add operation");
				try {

					service.forgetPassword(emailId);

					form.setMessage("Your password has been sent on ur Email.");
				} catch (RecordNotFoundException e) {
					form.setMessage("Login not exist");
				} catch (ApplicationException e) {
					System.out.println("Critical Issue " + e);
				}
			}

		} catch (Exception e) {
			System.out.println("Critical Issue " + e);
			return new ModelAndView("ForgetPassword", "form", form);
		}

		System.out.println("out ForgetPasswordCtl add operation");
		return new ModelAndView("ForgetPassword", "form", form);
	}

}
