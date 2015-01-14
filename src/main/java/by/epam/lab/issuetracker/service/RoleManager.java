package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.entity.Role;
import by.epam.lab.issuetracker.interfaces.IRoleDAO;

@Service
public class RoleManager {
	
	@Autowired
	private IRoleDAO roleDAO;

	@Transactional
	public List<Role> getAllRole(){
		return roleDAO.getAllRole();		 
	}

}
