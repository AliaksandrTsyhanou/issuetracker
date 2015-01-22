package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IIssueDAO;
import by.epam.lab.issuetracker.service.dto.IssueDto;


@Service
public class IssueManager {
	
	@Autowired
	private IIssueDAO issueDAO;

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
	public void update(IssueDto issueDto) throws DAOException {
		Issue curentIssue = get(issueDto.getId());
		Issue updatedIssue = fillIssue(curentIssue, issueDto);
		update(updatedIssue);
	}
	
	@Transactional
	public Issue add(Issue issue) throws DAOException {
		return issueDAO.add(issue);		 
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