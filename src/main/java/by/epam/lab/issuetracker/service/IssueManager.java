package by.epam.lab.issuetracker.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.controller.UsersController;
import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IIssueDAO;
import by.epam.lab.issuetracker.service.dto.IssueDto;


@Service
public class IssueManager {
	private static final Logger logger = LoggerFactory.getLogger(IssueManager.class);
	
	@Autowired
	private IIssueDAO issueDAO;
	@Autowired
	private UserManager userManager;

	@Transactional
	public List<Issue> getAll() throws DAOException{
		return issueDAO.getAll();
	}	
	
	@Transactional
	public Issue get(long id) throws DAOException{
		Issue issue = issueDAO.get(id);
		return issue;
	}	
	
	@Transactional
	public void update(Issue issue) throws DAOException{
		issueDAO.update(issue);
	}
	
	@Transactional
	public void update(IssueDto issueDto, String authorizedUserName) throws DAOException {
		Issue curentIssue = get(issueDto.getId());
		Issue updatedIssue = fillIssue(curentIssue, issueDto);
		updatedIssue.setModifydate(new Date());
		User mofifier = userManager.getUser(authorizedUserName);
		updatedIssue.setModifier(mofifier);
		update(updatedIssue);
	}
	
	@Transactional
	public Issue add(IssueDto issueDto, String authorizedUserName) throws DAOException {
		Issue newIssue = new Issue();
		newIssue = fillIssue(newIssue, issueDto);
		Date date = new Date();
		newIssue.setCreatedate(date);
		newIssue.setModifydate(date);
		User authorizedUser = userManager.getUser(authorizedUserName);
		newIssue.setCreator(authorizedUser);
		newIssue.setModifier(authorizedUser);
		logger.debug("newIssue=" + newIssue);
		return issueDAO.add(newIssue);		 
	}

	private Issue fillIssue(Issue issue, IssueDto issueDto){
		issue.setSummary(issueDto.getSummary());
		issue.setDescription(issueDto.getDescription());
		issue.setStatus(issueDto.getStatus());
		issue.setResolution(issueDto.getResolution());
		issue.setType(issueDto.getType());
		issue.setPriority(issueDto.getPriority());
		issue.setProject(issueDto.getProject());
		issue.setBuild(issueDto.getBuild());
		issue.setAssignee(issueDto.getAssignee());
		return issue;
	}
}