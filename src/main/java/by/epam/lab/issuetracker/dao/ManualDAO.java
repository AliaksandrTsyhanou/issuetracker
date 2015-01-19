package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.enums.ManualBeanEnum;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IManual;
import by.epam.lab.issuetracker.interfaces.IManualDAO;

@Service
@Repository
public class ManualDAO extends AbstractDAO implements IManualDAO {

	private static final Logger logger = LoggerFactory.getLogger(ManualDAO.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IManual> getAll(ManualBeanEnum manualBeanEnum) throws DAOException {
    	try {
			List<IManual> listManual = new ArrayList<IManual>();
			listManual = (List<IManual>) getSession().createCriteria(manualBeanEnum.getClazz()).list();
			logger.debug(listManual.toString());
			return listManual;
		} catch (HibernateException e) {
			throw new DAOException("List of manual not found.", e);
		}
	}
	
	@Override
	public IManual get(ManualBeanEnum manualBeanEnum, int manualId) throws DAOException{
		try {
			IManual resolution = (IManual) getSession().get(manualBeanEnum.getClazz(), manualId);
			return resolution;
		} catch (HibernateException e) {
			throw new DAOException("Manual with id " + manualId + " not found.)", e);
		}
	}
	
	@Override
	public void update(IManual manual) throws DAOException{
        try {
        	logger.debug("getSession().update(manual), manual = " + manual);
        	getSession().update(manual);
        } catch (HibernateException e) {
        	logger.error("Could not update manual " + manual.getName()+ ", " + e);
        	throw new DAOException("Could not update manual " + manual.getName(), e);
        }
    }

	@Override
	public IManual add(IManual manual) throws DAOException {
		try {
			getSession().save(manual);
		} catch (Exception e) {
			throw new DAOException("Could not create manual " + manual, e);    
		}
		return null;
	}
}