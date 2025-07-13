package com.bishal.us.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bishal.us.entity.User;
import com.bishal.us.exception.UserAlreadyExistsException;
import com.bishal.us.repository.UserRepo;

@Service
public class UserService {

	private final UserRepo userRepo;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
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

	public Optional<User> getUserById(int id) {
		return userRepo.findById(id);
	}
}
