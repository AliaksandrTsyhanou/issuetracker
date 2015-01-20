package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IIssueDAO;


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
}