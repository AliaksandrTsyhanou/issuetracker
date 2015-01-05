package by.epam.lab.issuetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signin() {
		return "login";
	}
	
	@RequestMapping(value = "/login-failure", method = RequestMethod.GET)
	public String signinFailure(Model model) {
		model.addAttribute("message", "Wrong login or password");
		return "login";
	}
	
}
