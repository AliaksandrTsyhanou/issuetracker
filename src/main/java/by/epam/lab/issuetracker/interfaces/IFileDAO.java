package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.File;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IFileDAO{
	public List<File> getAll() throws DAOException;
	public File add(File file) throws DAOException;
}