package com.school.platform.dao;

import java.util.List;

import com.school.platform.entity.User;

public interface UserDAO {

	public List<User> getUsers();
	public User getUser(String username);
	public void saveUser(User user);
	public void deleteUser(String username);
	public String getFieldValue(String username, String field);
	public void setUserField(String username, String field, String value);
}
