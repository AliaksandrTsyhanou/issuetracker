package by.epam.lab.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.lab.issuetracker.dao.RoleDAO;
import by.epam.lab.issuetracker.entity.Role;

@Service
public class RoleManager {
	
	@Autowired
	private RoleDAO roleDAO;

	@Transactional
	public List<Role> getAllRole() throws UsernameNotFoundException{
		return roleDAO.getAllRole();		 
	}

}
