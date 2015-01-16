package by.epam.lab.issuetracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.epam.lab.issuetracker.entity.Status;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.service.StatusManager;

@Controller
public class ManualsController {
	private static final Logger logger = LoggerFactory.getLogger(ManualsController.class);
	
	@Autowired
	private StatusManager statusManager;
	
	@ModelAttribute("statuses")	
	public List<Status> getAllStatus() throws DAOException {
		return statusManager.getAllStatus();
	}	
	
	@ModelAttribute("status")	
	public Status getStatus(){
		return new Status();
	}
	
	@RequestMapping(value="/statuses", method = RequestMethod.GET) 
	public String getUsers() {
		return "statuses";
	}
	
	@RequestMapping(value="/statuses/{id}", method = RequestMethod.GET) 
	public String getUser(@PathVariable int id, Model model) throws DAOException{
		logger.debug("@RequestMapping(value=/statuses/{id}, method = RequestMethod.GET)");
		logger.debug("id=" + id);
		Status status = statusManager.getStatus(id);
		model.addAttribute("status", status);		
		return "editstatus";
	}
	
	@RequestMapping(value="/statuses/{id}", method = RequestMethod.POST) 
	public String saveEdit(@ModelAttribute("status") @Valid Status status,
			BindingResult result) throws DAOException{
		if (result.hasErrors()){
			return "editstatus";
		}
		logger.debug("@RequestMapping(value=/statuses/{id}, method = RequestMethod.POST)");
		logger.debug("status = " + status);
		statusManager.updateStatus(status);
		return "redirect:/statuses";
	}
}