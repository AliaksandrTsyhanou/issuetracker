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
import org.springframework.web.context.request.WebRequest;

import by.epam.lab.issuetracker.entity.Resolution;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.EmailExistsException;
import by.epam.lab.issuetracker.service.ResolutionManager;
import by.epam.lab.issuetracker.service.dto.UserAddDto;


@Controller
public class ResolutionController {
	private static final Logger logger = LoggerFactory.getLogger(ResolutionController.class);
	
	@Autowired
	private ResolutionManager resolutionManager;
	
	@ModelAttribute("resolutions")	
	public List<Resolution> getAllResolution() throws DAOException {
		return resolutionManager.getAllResolution();
	}	
	
	@ModelAttribute("resolution")	
	public Resolution getResolution(){
		return new Resolution();
	}
	
	@RequestMapping(value="/resolutions", method = RequestMethod.GET) 
	public String getResolutions() {
		return "resolutions";
	}
	
	@RequestMapping(value="/resolutions/{id}", method = RequestMethod.GET) 
	public String getUser(@PathVariable int id, Model model) throws DAOException{
		logger.debug("@RequestMapping(value=/resolutions/{id}, method = RequestMethod.GET)");
		logger.debug("id=" + id);
		Resolution resolution = resolutionManager.getResolution(id);
		model.addAttribute("resolution", resolution);		
		return "editresolution";
	}
	
	@RequestMapping(value="/resolutions/{id}", method = RequestMethod.POST) 
	public String saveEdit(@ModelAttribute("resolution") @Valid Resolution resolution,
			BindingResult result) throws DAOException{
		if (result.hasErrors()){
			return "editresolution";
		}
		logger.debug("@RequestMapping(value=/resolutions/{id}, method = RequestMethod.POST)");
		logger.debug("resolution = " + resolution);
		resolutionManager.updateResolution(resolution);
		return "redirect:/resolutions";
	}
	
	@RequestMapping(value = "/resolutions/add", method = RequestMethod.GET)
	public String showFormAddResolution() {
		return "addresolution";
	}
	
	@RequestMapping(value = "/resolutions/add", method = RequestMethod.POST)
	public String addResolution(@ModelAttribute("resolution") @Valid Resolution resolution,
			BindingResult result) throws DAOException {
		logger.debug("/add result.hasErrors() = " + result.hasErrors());
		if (result.hasErrors()){
			return "addresolution";
		}		
		resolutionManager.addResolution(resolution);
		return "redirect:/resolutions" ;
	}	
	
}