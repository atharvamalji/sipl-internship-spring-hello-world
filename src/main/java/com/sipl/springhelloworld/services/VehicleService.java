package com.sipl.springhelloworld.services;

import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.responses.VehicleApiResponse;

public interface VehicleService {
    public VehicleApiResponse getAllVehicles();
    public VehicleApiResponse createNewVehicle(VehicleDto vehicleDto);
}
