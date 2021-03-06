package in.co.sunrays.spring.ctl;

import in.co.sunrays.spring.dto.UserDTO;
import in.co.sunrays.spring.form.LoginForm;
import in.co.sunrays.spring.service.UserServiceInt;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Login functionality Controller. Performs operation
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
@Controller
public class LoginCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Autowired
	private UserServiceInt service = null;

	public void setService(UserServiceInt service) {
		this.service = service;
	}

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") LoginForm form, Model model) {
		log.debug("Login Submit Started");
		model.addAttribute("message", "Welcome");
		return "Login";
	}

	/**
	 * Performs Login operation
	 * 
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/Login", method = { RequestMethod.POST })
	public String submit(@ModelAttribute("form") @Valid LoginForm form,
			BindingResult bindingResult, HttpSession session, Model model) {

		log.debug("Login Submit Started");

		System.out.println("result Fail :" + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("Has Error");
			return "Login";
		}

		if (OP_SIGNIN.equalsIgnoreCase(form.getOperation())) {

			UserDTO dto = service.authenticate(form.getEmailId(),
					form.getPassword());
			if (dto == null) {
				model.addAttribute("error", "Invalid emailId or password");
			} else {
				session.setAttribute("user", dto);
				model.addAttribute("message", "Welcome :" + form.getEmailId());
				return "redirect:College/search";
			}
		}

		log.debug("Login Submit End");
		return "Login";

	}
}