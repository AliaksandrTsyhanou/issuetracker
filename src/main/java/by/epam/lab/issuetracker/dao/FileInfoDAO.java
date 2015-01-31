package by.epam.lab.issuetracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import by.epam.lab.issuetracker.entity.FileInfo;
import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IFileInfoDAO;

@Service
@Repository
public class FileInfoDAO extends AbstractDAO implements IFileInfoDAO {

	private static final Logger logger = 
			LoggerFactory.getLogger(FileInfoDAO.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileInfo> getAll() throws DAOException {
    	try {
			List<FileInfo> listFile = new ArrayList<FileInfo>();
			listFile = (List<FileInfo>) getSession().
					createCriteria(FileInfo.class).list();
			logger.debug(listFile.toString());
			return listFile;
		} catch (HibernateException e) {
			throw new DAOException("List of File not found.", e);
		}
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileInfo> getAll(long issueId) throws DAOException {
		try {
			List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
			fileInfoList = (List<FileInfo>) getSession().
					createCriteria(FileInfo.class).
					add(Restrictions.eq("idissue", issueId)).list();
			logger.debug(fileInfoList.toString());
			return fileInfoList;
		} catch (HibernateException e) {
			throw new DAOException("List of File info not found.", e);
		}
	}
	
	@Override
	public FileInfo get(long id) throws DAOException{
		try {
			FileInfo fileInfo = (FileInfo) getSession().
					get(FileInfo.class, id);
			return fileInfo;
		} catch (HibernateException e) {
			throw new DAOException("FileInfo with id " + id + " not found.)", e);
		}
	}
	
	@Override
	public FileInfo add(FileInfo fileInfo) throws DAOException {
		try {
			fileInfo.setId((Long) getSession().save(fileInfo));
		} catch (Exception e) {
			throw new DAOException("Could not create file " + fileInfo, e);    
		}
		return fileInfo;
	}	
}