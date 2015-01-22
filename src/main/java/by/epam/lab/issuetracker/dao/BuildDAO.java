package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IBuildDAO;

@Service
@Repository
public class BuildDAO extends AbstractDAO implements IBuildDAO {

	private static final Logger logger = LoggerFactory.getLogger(BuildDAO.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Build> getAll() throws DAOException {
    	try {
			List<Build> listBuild = new ArrayList<Build>();
			listBuild = (List<Build>) getSession().createCriteria(Build.class).list();
			logger.debug(listBuild.toString());
			return listBuild;
		} catch (HibernateException e) {
			throw new DAOException("List of Build not found.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Build> getAll(long projectId) throws DAOException {
		try {
			List<Build> listBuild = new ArrayList<Build>();
			listBuild = (List<Build>) getSession().createCriteria(Build.class).add(Restrictions.eq("idproject", projectId)).list();
			logger.debug(listBuild.toString());
			return listBuild;
		} catch (HibernateException e) {
			throw new DAOException("List of Build not found.", e);
		}
	}
	
	@Override
	public Build get(long id) throws DAOException{
		try {
			Build build = (Build) getSession().get(Build.class, id);
			return build;
		} catch (HibernateException e) {
			throw new DAOException("Status with id " + id + " not found.)", e);
		}
	}
	
	@Override
	public void update(Build build) throws DAOException{
        try {
        	logger.debug("getSession().update(build), build = " + build);
        	getSession().update(build);
        } catch (HibernateException e) {
        	logger.error("Could not update build " + build + ", " + e);
        	throw new DAOException("Could not update build " + build, e);
        }
    }
	
	@Override
	public Build add(Build build) throws DAOException {
		try {
			getSession().save(build);
		} catch (Exception e) {
			throw new DAOException("Could not create build " + build, e);    
		}
		return build;
	}
}