package com.bishal.us.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bishal.us.entity.User;
import com.bishal.us.exception.IDNotFoundException;
import com.bishal.us.exception.UserAlreadyExistsException;
import com.bishal.us.repository.UserRepo;
import com.bishal.us.response.UserResponse;

@Service
public class UserService {

	private final UserRepo userRepo;
	private final ModelMapper modelMapper;
	
	public UserService(UserRepo userRepo, ModelMapper modelMapper) {
		this.userRepo = userRepo;
		this.modelMapper = modelMapper;
	}

	public User register(User user) {
		if(userRepo.findByUsername(user.getUsername()).isPresent()) {
			throw new UserAlreadyExistsException("User Already Exists");
		}

		return userRepo.save(user);
	}

	public List<User> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		return allUsers;
	}

	public UserResponse getUserById(int id) {
		User user = userRepo.findById(id)
				 .orElseThrow(() -> new IDNotFoundException("User not found for the id " + id));
		
		UserResponse userResponse = modelMapper.map(user, UserResponse.class);
		
		return userResponse;
	}
}
