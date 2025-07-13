package com.bishal.us.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.us.entity.User;
import com.bishal.us.exception.MandatoryFieldException;
import com.bishal.us.response.UserResponse;
import com.bishal.us.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> registeruser(@RequestBody User user) {
		
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmailId().isEmpty()) {
			throw new MandatoryFieldException("Mandatory field missing");
		}
		
		User newUser =  userService.register(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
		UserResponse userResponse = userService.getUserById(id);

		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers(); 
	}
	
}