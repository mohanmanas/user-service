package com.jpop.userservice.controller;

import java.net.URI;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpop.userservice.model.User;
import com.jpop.userservice.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserContoller {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String userName) {
		List<User> response;
		if(StringUtils.isEmpty(userName)) {
			response =  userService.getAllUsers();
		} else {
			response =  userService.getAllUsersByName(userName);
		}
		return CollectionUtils.isEmpty(response)?new ResponseEntity<>(null, HttpStatus.NOT_FOUND):ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User response =  userService.getUserById(id);
		if(Objects.nonNull(response)) {
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User response =  userService.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(response.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int id) {
		boolean isUpdated = userService.updateUser(user, id);
		if(isUpdated) {
			return ResponseEntity.ok().build();
		} else {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}	
	}
}
