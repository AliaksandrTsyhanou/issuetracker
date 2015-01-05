package by.epam.lab.issuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import by.epam.lab.issuetracker.entity.Role;
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
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.POST) 
	public String saveEdit(@ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		System.out.println("@RequestMapping(value=/users/{id}, method = RequestMethod.POST)");
		userManager.updateUser(user);
		return "users";
	}
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET) 
	public String getUser(@PathVariable int id, Model model) throws Exception {
		System.out.println("@RequestMapping(value=/users/{id}, method = RequestMethod.GET)");
		System.out.println("id=" + id);
		User user = userManager.getUserById(id);
		model.addAttribute("user", user);		
		return "edituser";
	}
	
	@RequestMapping(value = "/adduser/", method = RequestMethod.GET)
	public String showFormAddUser() {
		return "adduser";
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
	
	@ModelAttribute("roles")	
	public List<Role> getAllRole() throws Exception{
		return rolerManager.getAllRole();
	}
}
