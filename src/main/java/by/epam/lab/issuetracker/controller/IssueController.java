package by.epam.lab.issuetracker.controller;

import java.util.List;

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

import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.service.IssueManager;



@Controller
public class IssueController {
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	@Autowired
	private IssueManager issueManager;

	@ModelAttribute("issue")	
	public Issue getIssue(){
		return new Issue();
	}
	
	@ModelAttribute("issues")	
	public List<Issue> getAll() throws DAOException{
		return issueManager.getAll();
	}
	
	@RequestMapping(value="/issues", method = RequestMethod.GET) 
	public String showAlls(){
		return "issues";
	}
	
	
	@RequestMapping(value="/issues/{id}", method = RequestMethod.GET) 
	public String getById(@PathVariable int id, Model model) throws DAOException{
		Issue issue = issueManager.get(id);		
		model.addAttribute("issue", issue);		
		return "editissue";
	}
	
}