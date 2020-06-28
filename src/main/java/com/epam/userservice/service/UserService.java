package com.epam.userservice.service;

import java.util.List;



import com.epam.userservice.model.User;

public interface UserService {

	public List<User> getAllUsers();

	public User createUser(User user);

	public User getUserById(int id);

	public void deleteUser(int id);

	public boolean updateUser(User user, int id);

	public List<User> getAllUsersByName(String userName);
}
