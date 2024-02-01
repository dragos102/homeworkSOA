package com.globalsoftwaresupport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.proxy.UserProxy;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/users")
public class GatewayUserController {

	@Autowired
	private UserProxy userProxy;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public void create(@Valid @RequestBody User user) {
		userProxy.create(user);
	}

	@GetMapping
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	public List<User> getUsers() {
		return userProxy.getUsers();
	}


	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public void login(@Valid @RequestBody User user) {
		userProxy.logIn(user);
	}

	@GetMapping("/isUserLoggedInto")
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	public boolean getUserLoggedStatus() {
		return userProxy.getIsUserLoggedIn();
	}

}
