package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	
	public List<User> getUsers() {
		return usersDao.getUsers();
	}
	
	public void createUser(User user) {
		usersDao.create(user);
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	public boolean exists(String username) {
		return usersDao.getUser(username) != null;
	}
}
