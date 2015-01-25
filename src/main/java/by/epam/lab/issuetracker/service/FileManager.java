package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.File;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.interfaces.IFileDAO;



@Service
public class FileManager {
	
	@Autowired
	private IFileDAO fileDAO;

	@Transactional
	public List<File> getAll() throws DAOException{
		return fileDAO.getAll();
	}	
	
	@Transactional
	public File add(File file) throws DAOException {
		return fileDAO.add(file);		 
	}
}