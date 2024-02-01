package com.globalsoftwaresupport.proxy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.globalsoftwaresupport.model.PatchUserRequest;
import com.globalsoftwaresupport.model.User;

@Component
public class UserProxy {

	private final RestTemplate restTemplate;
	private final String url;
	
	public UserProxy( RestTemplate restTemplate) {
		this.url = "http://localhost:8082/";
		this.restTemplate = restTemplate;
	}
	
	public List<User> getUsers() {
		if(getIsUserLoggedIn()) {
			return restTemplate.getForObject(url + "v1/user", List.class);
		}
		else {
			throw new IllegalArgumentException("YOU ARE NOT LOGGED");
		}
	}

	public void create(User user) {
		if(getIsUserLoggedIn()) {
			restTemplate.postForObject(url + "v1/user", user, User.class);
		}
		else {
			throw new IllegalArgumentException("YOU ARE NOT LOGGED");
		}
	}

	public void logIn(User user) {
			restTemplate.postForObject(url + "v1/user/login", user, User.class);

	}

	public boolean getIsUserLoggedIn() {
		return Boolean.TRUE.equals(restTemplate.getForObject(url + "v1/user/isUserLoggedIn", Boolean.class));
	}
}
