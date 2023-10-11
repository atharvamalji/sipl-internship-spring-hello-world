package com.sipl.springhelloworld.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sipl.springhelloworld.entities.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long>{

}