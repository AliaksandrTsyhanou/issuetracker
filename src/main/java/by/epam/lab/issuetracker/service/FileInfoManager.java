package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.FileInfo;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IFileInfoDAO;

@Service
public class FileInfoManager {
	
	@Autowired
	private IFileInfoDAO fileInfoDAO;

	
	@Transactional
	public List<FileInfo> getAll() throws DAOException{
		return fileInfoDAO.getAll();
	}	
	
	@Transactional
	public List<FileInfo> getAll(long issueId) throws DAOException {
		return fileInfoDAO.getAll(issueId);
	}
	
	@Transactional
	public FileInfo get(long fileId) throws DAOException{
		FileInfo fileInfo = fileInfoDAO.get(fileId);
		return fileInfo;
	}	
	
	
	@Transactional
	public FileInfo add(FileInfo fileInfo) throws DAOException {
		return fileInfoDAO.add(fileInfo);		 
	}	
}