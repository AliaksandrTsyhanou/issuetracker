package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IProjectDAO;


@Service
@Repository
public class ProjectDAO extends AbstractDAO implements IProjectDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProjectDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Project> getAll() throws DAOException {
    	try {
			List<Project> listProject = new ArrayList<Project>();
			listProject = (List<Project>) getSession().createCriteria(Project.class).list();
			logger.debug(listProject.toString());
			return listProject;
		} catch (HibernateException e) {
			throw new DAOException("List of Project not found.", e);
		}
	}
	
	public Project get(long id) throws DAOException{
		try {
			Project project = (Project) getSession().get(Project.class, id);
			return project;
		} catch (HibernateException e) {
			throw new DAOException("Manual with id " + id + " not found.)", e);
		}
	}
	
	public void update(Project project) throws DAOException{
        try {
        	logger.debug("getSession().update(project), project = " + project);
        	getSession().update(project);
        } catch (HibernateException e) {
        	logger.error("Could not update project " + project.getName()+ ", " + e);
        	throw new DAOException("Could not update project " + project.getName(), e);
        }
    }

	public Project add(Project project) throws DAOException {
		try {
			getSession().save(project);
		} catch (Exception e) {
			throw new DAOException("Could not create manual " + project, e);    
		}
		return null;
	}
}