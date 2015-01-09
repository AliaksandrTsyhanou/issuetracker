package by.epam.lab.issuetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import by.epam.lab.issuetracker.entity.Role;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.service.RoleManager;
import by.epam.lab.issuetracker.service.UserManager;
import by.epam.lab.issuetracker.service.dto.UserAddDto;
import by.epam.lab.issuetracker.validators.UserAddDtoValidator;
import by.epam.lab.issuetracker.validators.UserValidator;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RoleManager rolerManager;
	
	@Autowired
	private UserValidator userValidator;
	
	@InitBinder("user")
	private void initUserBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	@Autowired
	private UserAddDtoValidator UserAddDtoValidator;	
	
	@InitBinder("userAddDto")
	private void initUserAddDtoBinder(WebDataBinder binder) {
		binder.addValidators(userValidator, UserAddDtoValidator);	
	}
	
	@RequestMapping(method=RequestMethod.GET) 
	public String getUsers() {
		return "users";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST) 
	public String saveEdit(@ModelAttribute("user") @Validated User user,
			BindingResult result) throws Exception {
		if (result.hasErrors()){
			return "edituser";
		}
		System.out.println("@RequestMapping(value=/users/{id}, method = RequestMethod.POST)");
		userManager.updateUser(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public String getUser(@PathVariable long id, Model model) throws Exception {
		System.out.println("@RequestMapping(value=/users/{id}, method = RequestMethod.GET)");
		System.out.println("id=" + id);
		User user = userManager.getUserById(id);
		model.addAttribute("user", user);		
		return "edituser";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showFormAddUser() {
		return "adduser";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userAddDto") @Validated UserAddDto userAddDto,
			BindingResult result, WebRequest request) throws Exception {
		if (result.hasErrors()){
			return "adduser";
		}		
		System.out.println("@RequestMapping(value = /add, method = RequestMethod.POST)");
		System.out.println("=================");
		System.out.println("+++User= " + userAddDto);

		userManager.addUser(userAddDto);
		return "redirect:/users" ;
	}
	
	@ModelAttribute("user")	
	public User getUser(){
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
	
	@ModelAttribute("userAddDto")	
	public UserAddDto getuserAddDto() throws Exception{
		return new UserAddDto();
	}
	
}
