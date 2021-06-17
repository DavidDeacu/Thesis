package com.school.platform.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.UserDAO;
import com.school.platform.entity.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
	}

	@Override
	@Transactional
	public String getFieldValue(String username, String field) {
		return userDAO.getFieldValue(username, field);
	}

	@Override
	@Transactional
	public void setUserField(String username, String field, String value) {
		userDAO.setUserField(username, field, value);
	}

	
}
