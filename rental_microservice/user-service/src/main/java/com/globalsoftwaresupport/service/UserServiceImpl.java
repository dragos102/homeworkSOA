package com.globalsoftwaresupport.service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.globalsoftwaresupport.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalsoftwaresupport.constants.AppConstants;
import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.repository.UserRepository;
import redis.clients.jedis.Jedis;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RedisService redisService;

	private boolean isUserLoggedIn = false;

	@Override
	public List<User> getUsers() {
		return repository.findAll().stream().filter(user-> user.getPassword().equals(" ")).collect(Collectors.toList());
	}

	@Override
	public User getUser(Long id) {
		return repository.findByUserId(id);
	}

	@Override
	public void create(User user) {
		repository.save(user);
	}

	@Override
	public void delete(Long id) {
		repository.deleteByUserId(id);
	}

	@Override
	public void update(User user, PatchUserRequest request) {
		validateForPatch(request);
		updateUser(user, request);
		repository.save(user);
	}

	@Override
	public void logIn(User user) {
		List<User> users =repository.findAll().stream().filter(userFound ->
			 user.getEmail().equals(userFound.getEmail()) &&
					 user.getPassword().equals(userFound.getPassword())
		).collect(Collectors.toList());

		if(users.isEmpty()) {
			throw  new IllegalArgumentException("Enter good username and password!");
		}
		else {
			User userLogged = users.get(0);
			redisService.setValue(user.getEmail(), user.getPassword());
			isUserLoggedIn = true;
		}
	}

	@Override
	public boolean getIsUserLoggedIn() {
		return this.redisService.getAllValues().size() > 0;
	}

	private void validateForPatch(PatchUserRequest request) {
		if(request.getEmail() != null && request.getEmail().isBlank())
			throw new IllegalArgumentException("Email can not be blank");
		
		// verify regular expressions
		if(request.getEmail() != null && !Pattern.matches(AppConstants.EMAIL_REGEXPR, 
				request.getEmail()))
			throw new IllegalArgumentException("Email is not valid");
		
		if(request.getFirstName() != null && request.getFirstName().isBlank())
			throw new IllegalArgumentException("First name can not be blank");
		
		if(request.getLastName() != null && request.getLastName().isBlank())
			throw new IllegalArgumentException("Last name can not be blank");		
	}

	private void updateUser(User user, PatchUserRequest request) {
		// update the values that are present in the request
		if (request.getFirstName() != null)
			user.setFirstName(request.getFirstName());

		if (request.getLastName() != null)
			user.setLastName(request.getLastName());

		if (request.getEmail() != null)
			user.setEmail(request.getEmail());
	}
}
