package by.epam.lab.issuetracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.NotExistException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.service.ManualManager;
import by.epam.lab.issuetracker.service.dto.ManualDto;


@Controller
public class ManualsController {
	private static final Logger logger = LoggerFactory.getLogger(ManualsController.class);
	
	@Autowired
	private ManualManager manualManager;

	@ModelAttribute("manualDto")	
	public IManual getManualDto(){
		return new ManualDto();
	}
	
	@RequestMapping(value="/manuals/{manualname}", method = RequestMethod.GET) 
	public String getManuals(@PathVariable String manualname, Model model) throws DAOException {
		List<IManual> manuals = manualManager.getAll(manualname);
		model.addAttribute("manuals", manuals);
		model.addAttribute("manualname", manualname);
		boolean isAllowAdditions = manualManager.isAllowAdditions(manualname);
		model.addAttribute("isAllowAdditions", isAllowAdditions);
		return "manuals";
	}
	
	
	@RequestMapping(value="/manuals/{manualname}/{id}", method = RequestMethod.GET) 
	public String getManual(@PathVariable String manualname, @PathVariable int id, Model model) throws DAOException{
		logger.debug("@RequestMapping(value=/manuals/{manualname}/{id}, method = RequestMethod.GET)");
		logger.debug("id=" + id);
		IManual manual = manualManager.get(manualname, id);		
		model.addAttribute("manual", manual);		
		return "editmanual";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/manuals/{manualname}/{id}", method = RequestMethod.POST) 
	public String saveEdit(@ModelAttribute("manualDto") IManual manualDto,
			@PathVariable String manualname, BindingResult result) throws DAOException{
		if (result.hasErrors()){
			return "editmanual";
		}
		logger.debug(manualname);
		logger.debug("@RequestMapping(/manuals/{manualname}/{id}, method = RequestMethod.POST)");
		logger.debug("manualDto = " + manualDto);
		manualManager.update(manualDto, manualname);
		return "redirect:/manuals/" + manualname;
	}
	
	@RequestMapping(value = "/manuals/{manualname}/add", method = RequestMethod.GET)
	public String showFormAddManual(@PathVariable String manualname) {
		if (!manualManager.isAllowAdditions(manualname)){
			throw new NotExistException();
		}
		return "addmanual";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/manuals/{manualname}/add", method = RequestMethod.POST)
	public String addManual(@ModelAttribute("manualDto") IManual manualDto,
			@PathVariable String manualname, BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "addmanual";
		}		
		manualManager.add(manualDto, manualname);
		return ("redirect:/manuals/" + manualname);
	}	
}