package com.globalsoftwaresupport.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String userId) {
		var user = Optional.ofNullable(userId)
				.map(u -> Long.valueOf(userId))
				.map(service::getUser)
				.orElseThrow();
					
		service.delete(user.getUserId());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public void create(@Valid @RequestBody User user) {
		service.create(user);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public void login(@Valid @RequestBody User user) {
		service.logIn(user);
	}
	
	@GetMapping
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	public List<User> getUsers() {
		return service.getUsers();
	}

	@GetMapping("/isUserLoggedIn")
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	public boolean getIsUserLoggedIn() {
		return service.getIsUserLoggedIn();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") String userId) {
		var user =  Optional.ofNullable(userId)
						.map(u -> Long.valueOf(userId))
						.map(service::getUser)
						.orElseThrow();
		
		return user;
	}
}
