package by.epam.lab.issuetracker.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signin() {
		return "login";
	}
	
	@RequestMapping(value = "/login-failure", method = RequestMethod.GET)
	public String signinFailure(Model model) {
//		model.addAttribute("login.errmessage.name", "login.errmessage.value");
		String errmessage = messageSource.getMessage  ("login.errmessage.value", null, Locale.US);
		String errmessageName = messageSource.getMessage  ("login.errmessage.name", null, Locale.US);
		
		model.addAttribute(errmessageName, errmessage);
		return "login";
	}
	
}