package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.manuals.Resolution;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IResolutionDAO;

@Service
@Repository
public class ResolutionDAO extends AbstractDAO implements IResolutionDAO {

	private static final Logger logger = LoggerFactory.getLogger(ResolutionDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Resolution> getAllResolution() throws DAOException {
    	try {
			List<Resolution> listResolution = new ArrayList<Resolution>();
			listResolution = (List<Resolution>) getSession().createCriteria(Resolution.class).list();
			logger.debug(listResolution.toString());
			return listResolution;
		} catch (HibernateException e) {
			throw new DAOException("List of Resolution not found.", e);
		}
	}
	
	public Resolution getResolution(int resolutionId) throws DAOException{
		try {
			Resolution resolution = (Resolution) getSession().get(Resolution.class, resolutionId);
			return resolution;
		} catch (HibernateException e) {
			throw new DAOException("Resolution with id " + resolutionId + " not found.)", e);
		}
	}
	
	public void updateResolution(Resolution resolution) throws DAOException{
        try {
        	logger.debug("getSession().update(resolution), status = " + resolution);
        	getSession().update(resolution);
        } catch (HibernateException e) {
        	logger.error("Could not update resolution " + resolution.getName()+ ", " + e);
        	throw new DAOException("Could not update resolution " + resolution.getName(), e);
        }
    }

	@Override
	public Resolution addResolution(Resolution resolution) throws DAOException {
		try {
			getSession().save(resolution);
		} catch (Exception e) {
			throw new DAOException("Could not create resolution " + resolution, e);    
		}
		return null;
	}
}