package com.globalsoftwaresupport.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.globalsoftwaresupport.model.Rental;
import com.globalsoftwaresupport.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.globalsoftwaresupport.model.Status;
import com.globalsoftwaresupport.model.Vehicle;
import com.globalsoftwaresupport.repository.VehicleRepository;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository repository;
	@Autowired
	private RentalRepository rentalRepository;

	
	@Override
	public Vehicle create(Vehicle vehicle) {
		return repository.save(vehicle);
	}


	@Override
	public void associate(String username, String car) {
		List<Vehicle> vehicle = (List<Vehicle>) repository.findAll();
		Vehicle vehicle1 = vehicle.stream().filter(element -> car.equals(element.getModel()+" "+element.getYear() )).findFirst().get();
		vehicle1.setStatus(Status.ASSOCIATED);
		repository.save(vehicle1);

		Long userId = Long.valueOf(username);
		Rental rental = new Rental();
		rental.setIdCar(vehicle1.getId());
		rental.setIdUser(userId);
		rentalRepository.save(rental);


	}

	@Override
	public void delete(String vehicleId, String userId) {

	}

	@Override
	public List<Vehicle> getVehicle() {
		return (List<Vehicle>) repository.findAll();
	}
}
