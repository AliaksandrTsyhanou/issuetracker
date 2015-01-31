package by.epam.lab.issuetracker.interfaces;

import java.util.List;

import by.epam.lab.issuetracker.entity.FileInfo;
import by.epam.lab.issuetracker.exceptions.DAOException;

public interface IFileInfoDAO{
	public List<FileInfo> getAll() throws DAOException;
	public FileInfo add(FileInfo fileInfo) throws DAOException;
	public FileInfo get(long id) throws DAOException;
	public List<FileInfo> getAll(long issueId) throws DAOException;
}