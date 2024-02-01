package com.globalsoftwaresupport.service;

import com.globalsoftwaresupport.model.Vehicle;

import java.util.List;

public interface VehicleService {
	public Vehicle create(Vehicle vehicle);
	public void associate(String vehicleId, String userId);
	 void delete(String vehicleId, String userId);

    List<Vehicle> getVehicle();
}
