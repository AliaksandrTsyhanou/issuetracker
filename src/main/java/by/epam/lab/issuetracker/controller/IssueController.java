package by.epam.lab.issuetracker.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.enums.ManualBeanEnum;
import by.epam.lab.issuetracker.enums.StatusEnum;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.NotExistException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.service.BuildManager;
import by.epam.lab.issuetracker.service.IssueManager;
import by.epam.lab.issuetracker.service.ManualManager;
import by.epam.lab.issuetracker.service.ProjectManager;
import by.epam.lab.issuetracker.service.UserManager;
import by.epam.lab.issuetracker.service.dto.IssueDto;
import by.epam.lab.issuetracker.validation.IssueValidator;

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
	@Autowired
	private IssueValidator issueValidator;	

	@InitBinder("issueDto")
	private void initissueDtoDtoBinder(WebDataBinder binder) {
		binder.setValidator(issueValidator);	
	}
	
	@ModelAttribute("issueDto")	
	public IssueDto getIssueDto(){
		return new IssueDto();
	}
	
	@RequestMapping(value="/issues", method = RequestMethod.GET) 
	public String showAlls(Model model) throws DAOException{
		model.addAttribute("issues", issueManager.getAll());
		return "issues";
	}
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})	
	@RequestMapping(value="/issues/{id}", method = RequestMethod.GET) 
	public String getById(@PathVariable long id, Model model) throws DAOException{
		Issue issue = issueManager.get(id);		
		model.addAttribute("issue", issue);
		List<Build> builds = buildManager.getAll(issue.getProject().getId());
		model.addAttribute("builds", builds);
		fillManual(model);
		int statusId = issue.getStatus().getId();
		model.addAttribute("statuses", getStatusList(statusId));
		model.addAttribute("isClosed", isClosed(statusId));
		model.addAttribute("isResolved", isResolved(statusId));
		if ((issue.getResolution() != null) || isResolved(statusId)){
			List<IManual> resulutions = manualManager.getAll(ManualBeanEnum.RESOLUTION);
			model.addAttribute("resolutions", resulutions);
		}
		return "editissue";
	}
	
	@Secured("ROLE_ADMIN")
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
	
		
	@Secured({"ROLE_USER","ROLE_ADMIN"})	
	@RequestMapping(value = "/issues/add", method = RequestMethod.GET)
	public String showFormAdd(Model model) throws DAOException {
		fillManual(model);	
		model.addAttribute("statuses", getStatusList(0));
		List<Project> projects = projectManager.getAll();		
		List<Build> builds = buildManager.getAll(projects.get(0).getId());
		model.addAttribute("builds", builds);
		List<IManual> resulutions = manualManager.getAll(ManualBeanEnum.RESOLUTION);
		model.addAttribute("resolutions", resulutions);
		return "addissue";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/issues/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("issueDto") @Valid IssueDto issueDto,
			BindingResult result) throws DAOException {
		if (result.hasErrors() && !isClosed(issueDto.getStatus().getId())){
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
		List<IManual> types = manualManager.getAll(ManualBeanEnum.TYPE);
		model.addAttribute("types", types);
		List<IManual> priorities = manualManager.getAll(ManualBeanEnum.PRIORITY);
		model.addAttribute("priorities", priorities);
	}
	
	
	
	private List<IManual> getStatusList(int currentStatus) throws NotExistException, DAOException{
		List<IManual> statusList = new ArrayList<IManual>();

		if(currentStatus==0){
			statusList.add(manualManager.get("status", StatusEnum.NEW.getId()));
			statusList.add(manualManager.get("status", StatusEnum.ASSIGNED.getId()));
		}	
		if (currentStatus == StatusEnum.NEW.getId()
				|| currentStatus == StatusEnum.ASSIGNED.getId()
				|| currentStatus == StatusEnum.IN_PROGRESS.getId()
				|| currentStatus == StatusEnum.RESOLVED.getId()) {
			statusList.add(manualManager.get("status", StatusEnum.IN_PROGRESS.getId()));
			statusList.add(manualManager.get("status", StatusEnum.RESOLVED.getId()));
			statusList.add(manualManager.get("status", StatusEnum.CLOSED.getId()));
		}
		if(currentStatus==StatusEnum.RESOLVED.getId()){
			statusList.add(manualManager.get("status", StatusEnum.RESOLVED.getId()));
			statusList.add(manualManager.get("status", StatusEnum.CLOSED.getId()));
		}	
		if(currentStatus==StatusEnum.CLOSED.getId()){
			statusList.add(manualManager.get("status", StatusEnum.RESOLVED.getId()));
			statusList.add(manualManager.get("status", StatusEnum.CLOSED.getId()));
		}	
		return statusList;
	}
	
	private boolean isClosed(int statusId){
		boolean isClosed = (statusId == StatusEnum.RESOLVED.getId() 
				|| statusId == StatusEnum.CLOSED.getId());
		return isClosed;
	}
	
	private boolean isResolved(int statusId){
		boolean isResolved = (statusId==StatusEnum.RESOLVED.getId());
		return isResolved;
	}
		
}