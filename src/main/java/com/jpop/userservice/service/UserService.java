package com.jpop.userservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.jpop.userservice.dto.UserDto;
import com.jpop.userservice.model.User;

public interface UserService {

	public List<UserDto> getAllUsers();

	public UserDto createUser(UserDto userDto);

	public UserDto getUserById(int id);

	public void deleteUser(int id);

	public HttpStatus updateUser(UserDto userDto, int id);

	public List<UserDto> getAllUsersByName(String userName);
}
