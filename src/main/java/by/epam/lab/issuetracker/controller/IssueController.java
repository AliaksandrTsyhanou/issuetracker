package by.epam.lab.issuetracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.enums.ManualBeanEnum;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.ManualNotExistException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.service.BuildManager;
import by.epam.lab.issuetracker.service.IssueManager;
import by.epam.lab.issuetracker.service.ManualManager;
import by.epam.lab.issuetracker.service.ProjectManager;
import by.epam.lab.issuetracker.service.UserManager;
import by.epam.lab.issuetracker.service.dto.IssueDto;

@Controller
public class IssueController {
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	@Autowired
	private IssueManager issueManager;
	@Autowired
	private ProjectManager projectManager;
	@Autowired
	private BuildManager buildManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private ManualManager manualManager;

	@ModelAttribute("issueDto")	
	public IssueDto getIssueDto(){
		return new IssueDto();
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
		List<Build> builds = buildManager.getAll(issue.getProject().getId());
		model.addAttribute("builds", builds);
		fillManual(model);
		int statusId = issue.getStatus().getId();
		model.addAttribute("statuses", getStatusList(statusId));
		model.addAttribute("isClosed", isClosed(statusId));
		model.addAttribute("isResolved", isResolved(statusId));
		return "editissue";
	}
	
	@RequestMapping(value = "/issues/{id}", method = RequestMethod.POST)
	public String UpdateIssie(@ModelAttribute("issueDto") @Valid IssueDto issueDto,
			BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "editissue";
		}		
		String authorizedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		issueManager.update(issueDto, authorizedUserName);
		return ("redirect:/issues/" + issueDto.getId());
	}	
	
	
	
	@RequestMapping(value = "/issues/add", method = RequestMethod.GET)
	public String showFormAddManual(Model model) throws DAOException {
		fillManual(model);	
		model.addAttribute("statuses", getStatusList(0));
		List<Project> projects = projectManager.getAll();		
		List<Build> builds = buildManager.getAll(projects.get(0).getId());
		model.addAttribute("builds", builds);
		return "addissue";
	}
	
	@RequestMapping(value = "/issues/add", method = RequestMethod.POST)
	public String addManual(@ModelAttribute("issueDto") @Valid IssueDto issueDto,
			BindingResult result) throws DAOException {
		if (result.hasErrors()){
			return "addissue";
		}	
		String authorizedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		issueManager.add(issueDto, authorizedUserName);
		return ("redirect:/issues");
	}	
	
	private void fillManual(Model model) throws DAOException{
		List<Project> projects = projectManager.getAll();
		model.addAttribute("projects", projects);
		List<User> assignees = userManager.getAllUser();
		model.addAttribute("assignees", assignees);
		
	
		List<IManual> resulutions = manualManager.getAll(ManualBeanEnum.RESOLUTION);
		model.addAttribute("resolutions", resulutions);
		List<IManual> types = manualManager.getAll(ManualBeanEnum.TYPE);
		model.addAttribute("types", types);
		List<IManual> priorities = manualManager.getAll(ManualBeanEnum.PRIORITY);
		model.addAttribute("priorities", priorities);
	}
	
	private List<IManual> getStatusList(int currentStatus) throws ManualNotExistException, DAOException{
		List<IManual> statusList = new ArrayList<IManual>();

		if(currentStatus==0){
			statusList.add(manualManager.get("status", 1));
			statusList.add(manualManager.get("status", 2));
		}	
		if(currentStatus==1 || currentStatus==2 || currentStatus==3 || currentStatus==6){
			statusList.add(manualManager.get("status", 3));
			statusList.add(manualManager.get("status", 4));
			statusList.add(manualManager.get("status", 5));
		}
		if(currentStatus==4){
			statusList.add(manualManager.get("status", 4));
			statusList.add(manualManager.get("status", 5));
		}	
		if(currentStatus==5){
			statusList.add(manualManager.get("status", 5));
			statusList.add(manualManager.get("status", 6));
		}	
		return statusList;
	}
	
	private boolean isClosed(int statusId){
		boolean isClosed = (statusId==4 || statusId==5);
		return isClosed;
	}
	
	private boolean isResolved(int statusId){
		boolean isResolved = (statusId==4);
		return isResolved;
	}
		
}