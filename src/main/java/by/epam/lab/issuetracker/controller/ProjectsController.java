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

import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.service.ProjectManager;



@Controller
public class ProjectsController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);
	
	@Autowired
	private ProjectManager projectManager;

	@ModelAttribute("project")	
	public Project getProject(){
		return new Project();
	}
	
	@ModelAttribute("projects")	
	public List<Project> getAll() throws DAOException{
		return projectManager.getAll();
	}
	
	@RequestMapping(value="/projects", method = RequestMethod.GET) 
	public String showProjects(){
		return "projects";
	}
	
	
	@RequestMapping(value="/projects/{id}", method = RequestMethod.GET) 
	public String getManual(@PathVariable int id, Model model) throws DAOException{
		Project project = projectManager.get(id);		
		model.addAttribute("project", project);		
		return "editproject";
	}
	
}