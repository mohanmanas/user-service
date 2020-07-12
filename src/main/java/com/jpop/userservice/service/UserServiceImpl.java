package com.jpop.userservice.service;

import java.util.List;


import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jpop.userservice.dto.UserDto;
import com.jpop.userservice.model.User;
import com.jpop.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDto> getAllUsers() {
		List<User> usersList = userRepository.findAll();
		return usersList.stream().map(UserDto::toUserDto).collect(Collectors.toList());
	}
	
	@Override
	public UserDto getUserById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? UserDto.toUserDto(optionalUser.get()) : null ;
	}
	
	@Override
	public List<UserDto> getAllUsersByName(String userName) {
		List<User> usersList = userRepository.findByUserNameContains(userName);
		return usersList.stream().map(UserDto::toUserDto).collect(Collectors.toList());
	}
	
	@Override
	public UserDto createUser(UserDto userDto) {
		return UserDto.toUserDto(userRepository.save(UserDto.fromUserDto(userDto)));
	}
	
	@Override
	public void deleteUser(int id) {
		 userRepository.deleteById(id);
	}

	@Override
	public HttpStatus updateUser(UserDto userDto, int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		User userEntity = optionalUser.isPresent() ? optionalUser.get() : null;
		if(Objects.nonNull(userEntity)) {
			User updatedUser = User.builder()
					.userId(id)
					.userName(StringUtils.isEmpty(userDto.getUserName())?userEntity.getUserName():userDto.getUserName())
					.dob(Objects.nonNull(userDto.getDob())?userEntity.getDob():userDto.getDob())
					.email(StringUtils.isEmpty(userDto.getEmail())?userEntity.getEmail():userDto.getEmail())
					.phoneNumber(StringUtils.isEmpty(userDto.getPhoneNumber())?userEntity.getPhoneNumber():userDto.getPhoneNumber())
					.build();
					userRepository.save(updatedUser);
					return HttpStatus.CREATED;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
