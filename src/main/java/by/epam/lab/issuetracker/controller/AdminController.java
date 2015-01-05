package by.epam.lab.issuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.service.RoleManager;
import by.epam.lab.issuetracker.service.UserManager;

@Controller
public class AdminController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RoleManager rolerManager;
	
	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET) 
	public String getUsers() {
		return "users";
	}
	
	@RequestMapping(value = "/adduser/", method = RequestMethod.GET)
	public ModelAndView showFormAddUser() {
		ModelAndView modelAndView = new ModelAndView("adduser");
		modelAndView.addObject("roles", rolerManager.getAllRole());
		return modelAndView;
	}
	
	@RequestMapping(value = "/adduser/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user,
			BindingResult result, WebRequest request) throws Exception {
		System.out.println("@RequestMapping(value = /add, method = RequestMethod.POST)");
		System.out.println("=================");
		System.out.println("+++User= " + user);

		userManager.addUser(user);
		return "redirect:/" ;
	}
	
	@ModelAttribute("user")	
	public User inputFormUser(){
		return new User();
	}
	
	@ModelAttribute("users")	
	public List<User> getAllUser() throws Exception{
		return userManager.getAllUser();
	}
}
