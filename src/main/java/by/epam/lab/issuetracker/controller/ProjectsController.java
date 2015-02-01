package by.epam.lab.issuetracker.controller;

import java.util.List;

import javax.validation.Valid;

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

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.service.BuildManager;
import by.epam.lab.issuetracker.service.ProjectManager;
import by.epam.lab.issuetracker.service.UserManager;
import by.epam.lab.issuetracker.service.dto.BuildsDto;
import by.epam.lab.issuetracker.service.dto.ManualDto;

@Controller
public class ProjectsController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);
	
	@Autowired
	private ProjectManager projectManager;	
	@Autowired
	private BuildManager buildManager;
	@Autowired
	private UserManager userManager;

	@ModelAttribute("project")	
	public Project getProject(){
		return new Project();
	}	
	@ModelAttribute("projects")	
	public List<Project> getAll() throws DAOException{
		return projectManager.getAll();
	}
	@ModelAttribute("manualDto")	
	public IManual getManualDto(){
		return new ManualDto();
	}
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/projects", method = RequestMethod.GET) 
	public String showProjects(){
		return "projects";
	}
	
	@Secured("ROLE_ADMIN")	
	@RequestMapping(value="/projects/{id}", method = RequestMethod.GET) 
	public String getProject(@PathVariable long id, Model model) throws DAOException{
		Project project = projectManager.get(id);		
		model.addAttribute("project", project);
		List<Build> builds = buildManager.getAll(id);
		model.addAttribute("builds", builds);
		List<User> mangers = userManager.getAllUser();
		model.addAttribute("mangers", mangers);
		return "editproject";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.POST)
	public String updateProject(@ModelAttribute("project") @Valid Project project,
			BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "editproject";
		}		
		projectManager.update(project);
		return ("redirect:/projects");
	}	
	
	
	@RequestMapping(value="/projects/{id}/builds", method = RequestMethod.GET) 
	public String getBuilds(@PathVariable long id, Model model) throws DAOException{
		BuildsDto builds = new BuildsDto();
		builds.setBuilds(buildManager.getAll(id));
		model.addAttribute("builds", builds);
		return "builds";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/projects/{id}/builds/add", method = RequestMethod.GET) 
	public String showAddBuild() throws DAOException{
		return "addmanual";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/projects/{id}/builds/add", method = RequestMethod.POST)
	public String addBuild(@ModelAttribute("manualDto") ManualDto manualDto,
			@PathVariable int id, BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "addmanual";
		}	
		Build build = new Build();
		build.setId(manualDto.getId());
		build.setName(manualDto.getName());
		build.setIdproject(id);
		buildManager.add(build);
		return ("redirect:/projects/" + id +"/builds/");
	}	
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/projects/add", method = RequestMethod.GET) 
	public String showAddProject(Model model) throws DAOException{
		List<User> mangers = userManager.getAllUser();
		model.addAttribute("mangers", mangers);
		return "addproject";
	}	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/projects/add", method = RequestMethod.POST)
	public String addProject(@ModelAttribute("project") Project project,
			BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "addproject";
		}	
		Build addedbuild =  buildManager.add(project.getBuild());
		project.setBuild(addedbuild);
		Project addedProject = projectManager.add(project);
		System.out.println("addedProject.getId()=" + addedProject.getId());
		addedbuild.setIdproject(addedProject.getId());
		buildManager.update(addedbuild);
		return ("redirect:/projects/");
	}	
}