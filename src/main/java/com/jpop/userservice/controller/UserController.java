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

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable("id") int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/search/{userName}")
	public List<UserDto> getUserByName(@PathVariable("userName") String userName) {
		return userService.getAllUsersByName(userName);
	}
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
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
	public ResponseEntity<UserDto> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int id) {
		userService.updateUser(userDto, id);
		return ResponseEntity.noContent().build();
	}
}
