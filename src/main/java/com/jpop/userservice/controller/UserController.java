package com.jpop.userservice.controller;

import java.net.URI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpop.userservice.dto.UserDto;
import com.jpop.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/users")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<UserDto> getAllUsers() {
		log.info("Fetch users from User API"); 
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable("id") int userId) {
		log.info("Fetch {} user from User API", userId); 
		return userService.getUserById(userId);
	}
	
	@GetMapping("/search/{userName}")
	public List<UserDto> getUserByName(@PathVariable("userName") String userName) {
		log.info("Fetch {} user from User API", userName); 
		return userService.getAllUsersByName(userName);
	}
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		log.info("Create user from User API"); 
		try {
			UserDto response = userService.createUser(userDto);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(response.getUserId()).toUri();
			return ResponseEntity.created(location).build();
		} catch(Exception exception) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable int userId) {
		log.info("Delete {} user from User API", userId); 
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId) {
		log.info("Update {} user from User API", userId); 
		userService.updateUser(userDto, userId);
		return ResponseEntity.noContent().build();
	}
}
