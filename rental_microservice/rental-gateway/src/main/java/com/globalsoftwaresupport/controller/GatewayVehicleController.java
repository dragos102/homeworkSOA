package com.globalsoftwaresupport.controller;

import com.globalsoftwaresupport.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.globalsoftwaresupport.model.Vehicle;
import com.globalsoftwaresupport.proxy.VehicleProxy;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/vehicles")
public class GatewayVehicleController {

	@Autowired
	private VehicleProxy vehicleProxy;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public Vehicle create(@RequestBody Vehicle vehicle) {
		vehicle.setStatus(Status.AVAILABLE);
		return vehicleProxy.create(vehicle);
	}

	@PostMapping("{username}/users/{car}")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	public void associate(@PathVariable(name = "username") String userId,
						  @PathVariable(name = "car") String car) {
		vehicleProxy.associate(userId, car);
	}

	@GetMapping
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	public List<Vehicle> getVehicle() {
		return vehicleProxy.getVehicle();
	}
	
	@DeleteMapping("{vehicleId}/users/{userId}")
	public void remove(@PathVariable(name = "vehicleId") String vehicleId, 
			@PathVariable(name = "userId") String userId) {
		vehicleProxy.removeAssociation(vehicleId, userId);
	}
}
