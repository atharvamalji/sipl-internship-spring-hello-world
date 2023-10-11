package com.sipl.springhelloworld.mappers;

import java.util.List;

import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.entities.Vehicle;

public interface VehicleMapper {
    Vehicle mapVehicleDtoToVehicle(VehicleDto vehicleDto);

    VehicleDto mapVehicleToVehicleDto(Vehicle vehicle);

    List<VehicleDto> maplistVehicleTolistVehicleDto(List<Vehicle> vehicleList);
}
