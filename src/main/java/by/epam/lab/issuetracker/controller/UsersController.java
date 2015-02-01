package by.epam.lab.issuetracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
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
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.EmailExistException;
import by.epam.lab.issuetracker.service.RoleManager;
import by.epam.lab.issuetracker.service.UserManager;
import by.epam.lab.issuetracker.service.dto.ChangePasswordDto;
import by.epam.lab.issuetracker.service.dto.UserAddDto;
import by.epam.lab.issuetracker.service.dto.UserEditDto;
import by.epam.lab.issuetracker.validation.UserPasswordValidator;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager rolerManager;
	@Autowired
	private UserPasswordValidator userPasswordValidator;	
	

	@InitBinder("userAddDto")
	private void initUserAddDtoBinder(WebDataBinder binder) {
		binder.addValidators(userPasswordValidator);	
	}
	@InitBinder("changePasswordDto")
	private void initChangePasswordDtoBinder(WebDataBinder binder) {
		binder.setValidator(userPasswordValidator);	
	}
	
	
	@ModelAttribute("users")	
	public List<User> getAllUser() throws DAOException {
		return userManager.getAllUser();
	}
	@ModelAttribute("roles")	
	public List<Role> getAllRole(){
		return rolerManager.getAllRole();
	}
	@ModelAttribute("userAddDto")	
	public UserAddDto getUserAddDto(){
		return new UserAddDto();
	}
	@ModelAttribute("userEditDto")	
	public UserEditDto getUserEditDto(){
		return new UserEditDto();
	}	
	@ModelAttribute("changePasswordDto")	
	public ChangePasswordDto getPasswordConfirmation(){
		return new ChangePasswordDto();
	}	

	@RequestMapping(method=RequestMethod.GET) 
	public String getUsers() {
		return "users";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/edit", method = RequestMethod.GET) 
	public String getEditUser(Model model) throws DAOException{
		String authorizedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEditDto userEditDto = userManager.getUserEditDto(authorizedUserName);
		model.addAttribute("userEditDto", userEditDto);	
		return "edituser";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/edit", method = RequestMethod.POST) 
	public String saveEditUser(@ModelAttribute("userEditDto") @Valid UserEditDto userEditDto,
			BindingResult result, WebRequest webRequest) throws DAOException{
		if (result.hasErrors()){
			return "edituser";
		}
		String authorizedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			userManager.updateUser(userEditDto, authorizedUserName);
		} catch (EmailExistException e) {
			result.rejectValue("emailaddress", "user.emailaddress.exist");
			return "edituser";
		}
		return "redirect:/";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public String getUser(@PathVariable long id, Model model) throws DAOException{
		UserEditDto userEditDto = userManager.getUserEditDto(id);
		model.addAttribute("userEditDto", userEditDto);		
		return "edituser";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/{id}", method = RequestMethod.POST) 
	public String saveEdit(@ModelAttribute("userEditDto") @Valid UserEditDto userEditDto,
			BindingResult result) throws DAOException{
		if (result.hasErrors()){
			return "edituser";
		}
		try {
			userManager.updateUser(userEditDto);
		} catch (EmailExistException e) {
			result.rejectValue("emailaddress", "user.emailaddress.exist");	
			return "edituser";
		}
		return "redirect:/users";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showFormAddUser() {
		return "adduser";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userAddDto") @Valid UserAddDto userAddDto,
			BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "adduser";
		}		
		try {
			userManager.addUser(userAddDto);
		} catch (EmailExistException e) {
			result.rejectValue("emailaddress", "user.emailaddress.exist");	
			return "adduser";
		}		
		return "redirect:/users" ;
	}	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String showFormChangePassword() {
		return "changepassword";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute("changePasswordDto") @Valid ChangePasswordDto changePasswordDto,
			BindingResult result) throws DAOException {
		logger.debug("result.hasErrors() = " + result.hasErrors());
		if (result.hasErrors()){
			return "changepassword";
		}
		String authorizedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userManager.getUser(authorizedUserName);
		changePasswordDto.setUserId(user.getId());
		userManager.changePasswordUser(changePasswordDto);
		return "redirect:/";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/{id}/changepassword", method = RequestMethod.GET)
	public String showFormChangePasswordById(@ModelAttribute("changePasswordDto") @Valid ChangePasswordDto changePasswordDto,
			@PathVariable long id) {
		changePasswordDto.setUserId(id);
		return "changepassword";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/{id}/changepassword", method = RequestMethod.POST)
	public String changePasswordById(@ModelAttribute("changePasswordDto") @Valid ChangePasswordDto changePasswordDto,
			BindingResult result) throws DAOException{
		logger.debug("ById result.hasErrors() = " + result.hasErrors());
		if (result.hasErrors()){
			return "changepassword";
		}
		userManager.changePasswordUser(changePasswordDto);
		return "redirect:/users";
	}
}
