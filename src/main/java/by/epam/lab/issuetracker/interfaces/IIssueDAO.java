package by.epam.lab.issuetracker.interfaces;

import java.util.List;
import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IIssueDAO {

	public List<Issue> getAll() throws DAOException;	
	public Issue get(long id) throws DAOException;	
	public void update(Issue issue) throws DAOException;
	public Issue add(Issue issue) throws DAOException;

}