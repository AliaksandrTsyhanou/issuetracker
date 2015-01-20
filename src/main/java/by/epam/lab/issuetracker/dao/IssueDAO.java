package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.Issue;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IIssueDAO;



@Service
@Repository
public class IssueDAO extends AbstractDAO implements IIssueDAO{

	private static final Logger logger = LoggerFactory.getLogger(IssueDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Issue> getAll() throws DAOException {
    	try {
			List<Issue> listIssue = new ArrayList<Issue>();
			listIssue = (List<Issue>) getSession().createCriteria(Issue.class).list();
			logger.debug(listIssue.toString());
			return listIssue;
		} catch (HibernateException e) {
			throw new DAOException("List of Issue not found.", e);
		}
	}
	
	public Issue get(long id) throws DAOException{
		try {
			Issue issue = (Issue) getSession().get(Issue.class, id);
			return issue;
		} catch (HibernateException e) {
			throw new DAOException("Issue with id " + id + " not found.)", e);
		}
	}
	
	public void update(Issue issue) throws DAOException{
        try {
        	logger.debug("getSession().update(project), issue = " + issue);
        	getSession().update(issue);
        } catch (HibernateException e) {
        	logger.error("Could not update Issue " + issue+ ", " + e);
        	throw new DAOException("Could not update issue " + issue, e);
        }
    }

	public Issue add(Issue issue) throws DAOException {
		try {
			getSession().save(issue);
		} catch (Exception e) {
			throw new DAOException("Could not create issue " + issue, e);    
		}
		return issue;
	}
}