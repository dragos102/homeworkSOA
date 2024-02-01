package com.globalsoftwaresupport.proxy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.globalsoftwaresupport.model.User;
import com.globalsoftwaresupport.model.Vehicle;

@Component
public class VehicleProxy {

	private final RestTemplate restTemplate;
	private final String url;
	
	public VehicleProxy( RestTemplate restTemplate) {
		this.url = "http://localhost:8081/";
		this.restTemplate = restTemplate;
	}
	
	public Vehicle create(Vehicle vehicle) {
		if(Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8082/v1/user/isUserLoggedIn", Boolean.class))){
			return restTemplate.postForObject(url + "v1/vehicle", vehicle, Vehicle.class);
		}
		else {
			throw new IllegalArgumentException("YOU ARE NOT LOGGED");
		}

	}

	public void associate(String vehicleId, String userId) {
		if(Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8082/" + "v1/user/isUserLoggedIn", Boolean.class))) {

			// null for the request (no request body now)
			restTemplate.postForObject(url + "v1/vehicle/{username}/users/{car}", null, Void.class,
					Map.of("username", vehicleId, "car", userId));
		}
		else {
			throw new IllegalArgumentException("YOU ARE NOT LOGGED");
		}
	}

	public void removeAssociation(String vehicleId, String userId) {
		restTemplate.delete(url + "v1/vehicle/{vehicleId}/users/{userId}", Map.of("vehicleId",
				vehicleId, "userId", userId));
	}

	public List<Vehicle> getVehicle() {
		if(Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8082/" + "v1/user/isUserLoggedIn", Boolean.class))) {
			return restTemplate.getForObject(url + "v1/vehicle", List.class);
		}
		else {
			throw new IllegalArgumentException("YOU ARE NOT LOGGED");
		}

	}
}
