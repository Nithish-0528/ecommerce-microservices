package com.ecommerce.user_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping()
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {		
		
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>>  getUsers() {
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
		
	}
	
	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("User Deleted Successfully");
		
	}
	
	@GetMapping("{id}")
	public User getUserById(@PathVariable Long id) {
		
		return userService.getUserById(id);
		
	}
	
}






