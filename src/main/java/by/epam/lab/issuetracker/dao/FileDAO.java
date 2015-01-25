package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.File;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IFileDAO;



@Service
@Repository
public class FileDAO extends AbstractDAO implements IFileDAO {

	private static final Logger logger = LoggerFactory.getLogger(FileDAO.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<File> getAll() throws DAOException {
    	try {
			List<File> listFile = new ArrayList<File>();
			listFile = (List<File>) getSession().createCriteria(File.class).list();
			logger.debug(listFile.toString());
			return listFile;
		} catch (HibernateException e) {
			throw new DAOException("List of File not found.", e);
		}
	}	
	
	@Override
	public File add(File file) throws DAOException {
		try {
			file.setId((Long) getSession().save(file));
		} catch (Exception e) {
			throw new DAOException("Could not create file " + file, e);    
		}
		return file;
	}
}