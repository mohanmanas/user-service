package com.epam.userservice.service;

import java.util.List;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.epam.userservice.model.User;
import com.epam.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User getUserById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get(): null ;
	}
	
	@Override
	public List<User> getAllUsersByName(String userName) {
		return userRepository.findByUserNameContains(userName);
	}
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(int id) {
		 userRepository.deleteById(id);
	}

	@Override
	public boolean updateUser(User user, int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		User userEntity = optionalUser.isPresent() ? optionalUser.get() : null;
		if(Objects.nonNull(userEntity)) {
			User updatedUser = User.builder()
					.userId(id)
					.userName(StringUtils.isEmpty(user.getUserName())?userEntity.getUserName():user.getUserName())
					.dob(Objects.nonNull(user.getDob())?userEntity.getDob():user.getDob())
					.email(StringUtils.isEmpty(user.getEmail())?userEntity.getEmail():user.getEmail())
					.phoneNumber(StringUtils.isEmpty(user.getPhoneNumber())?userEntity.getPhoneNumber():user.getPhoneNumber())
					.build();
					userRepository.save(updatedUser);
					return true;
		} else {
			return false;
		}
	}
}
