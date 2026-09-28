package com.ecommerce.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.exception.UserAlreadyExistsException;
import com.ecommerce.user_service.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User createUser(User user) {
		
		Optional<User> existingUser = userRepository.findByName(user.getName());
		
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User Already Exists");
		}
		
		return userRepository.save(user);
	}
		
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public User getUserById(Long id) {
	     Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
		}
		
		return user.get();
		
	}
	
	
}















