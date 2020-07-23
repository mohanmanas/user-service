package com.jpop.userservice.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jpop.userservice.dto.UserDto;
import com.jpop.userservice.exception.NoDataFoundException;
import com.jpop.userservice.exception.UserNotFoundException;
import com.jpop.userservice.model.User;
import com.jpop.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDto> getAllUsers() {
		List<User> usersList = userRepository.findAll();
		if(!CollectionUtils.isEmpty(usersList)) {
			return usersList.stream().map(UserDto::toUserDto).collect(Collectors.toList());
		} else {
			throw new NoDataFoundException();
		}
	}
	
	@Override
	public UserDto getUserById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return UserDto.toUserDto(optionalUser.get());
		} else {
			throw new UserNotFoundException(id);
		}
	}
	
	@Override
	public List<UserDto> getAllUsersByName(String userName) {
		List<User> usersList = userRepository.findByUserNameContains(userName);
		if(!CollectionUtils.isEmpty(usersList)) {
			return usersList.stream().map(UserDto::toUserDto).collect(Collectors.toList());
		} else {
			throw new NoDataFoundException();
		}
	}
	
	@Override
	public UserDto createUser(UserDto userDto) {
		Optional<User> optionalUser = userRepository.findById(userDto.getUserId());
		if(!optionalUser.isPresent()) {
			return UserDto.toUserDto(userRepository.save(UserDto.fromUserDto(userDto)));
		} else {
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteUser(int id) {
		 userRepository.deleteById(id);
	}

	@Override
	public void updateUser(UserDto userDto, int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			userRepository.save(UserDto.fromUserDto(userDto));
		} else {
			throw new UserNotFoundException(id);
		}
	}
}
