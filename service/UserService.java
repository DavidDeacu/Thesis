package com.school.platform.service;

import java.util.List;

import com.school.platform.entity.User;

public interface UserService {
	public List<User> getUsers();
	public User getUser(String username);
	public void saveUser(User user);
	public void deleteUser(String username);
	public String getFieldValue(String username, String field);
	public void setUserField(String username, String field, String value);
}
