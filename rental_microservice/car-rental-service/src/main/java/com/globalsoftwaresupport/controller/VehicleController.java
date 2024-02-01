package com.globalsoftwaresupport.controller;

import com.globalsoftwaresupport.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.globalsoftwaresupport.model.Vehicle;
import com.globalsoftwaresupport.service.VehicleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public Vehicle create(@RequestBody Vehicle vehicle) {
		vehicle.setStatus(Status.AVAILABLE);
		return service.create(vehicle);
	}
	
	@PostMapping("{username}/users/{car}")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public void associate(@PathVariable(name = "username") String userId,
			@PathVariable(name = "car") String car) {
		service.associate(userId, car);
	}
	
	@DeleteMapping("{vehicleId}/users/{userId}")
	public void delete(@PathVariable(name = "vehicleId") String vehicleId, 
			@PathVariable(name = "userId") String userId) {
		service.delete(vehicleId, userId);
	}


	@GetMapping
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	public List<Vehicle> getVehicle() {
		return service.getVehicle().stream().filter(vehicle -> vehicle.getStatus().equals(Status.AVAILABLE)).collect(Collectors.toList());
	}
}