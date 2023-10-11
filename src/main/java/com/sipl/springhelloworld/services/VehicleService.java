package com.sipl.springhelloworld.services;

import java.util.List;

import com.sipl.springhelloworld.dtos.VehicleDto;

public interface VehicleService {
    public List<VehicleDto> getAllVehicles();
    public VehicleDto createNewVehicle(VehicleDto vehicleDto);
}
