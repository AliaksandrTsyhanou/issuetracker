package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.Status;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IStatusDAO;

@Service
@Repository
public class StatusDAO extends AbstractDAO implements IStatusDAO {

	private static final Logger logger = LoggerFactory.getLogger(StatusDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Status> getAllStatus() throws DAOException {
    	try {
			List<Status> listStatus = new ArrayList<Status>();
			listStatus = (List<Status>) getSession().createCriteria(
					Status.class).list();
			logger.debug(listStatus.toString());
			return listStatus;
		} catch (HibernateException e) {
			throw new DAOException("List of Status not found.", e);
		}
	}
	
	public Status getStatus(int statusId) throws DAOException{
		try {
			Status status = (Status) getSession().get(Status.class, statusId);
			return status;
		} catch (HibernateException e) {
			throw new DAOException("Status with id " + statusId + " not found.)", e);
		}
	}
	
	public void updateStatus(Status status) throws DAOException{
        try {
        	logger.debug("getSession().update(status), status = " + status);
        	getSession().update(status);
        } catch (HibernateException e) {
        	logger.error("Could not update status " + status.getName()+ ", " + e);
        	throw new DAOException("Could not update status " + status.getName(), e);
        }
    }
}