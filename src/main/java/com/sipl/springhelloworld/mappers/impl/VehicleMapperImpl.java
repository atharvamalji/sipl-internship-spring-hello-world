package com.sipl.springhelloworld.mappers.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.entities.Vehicle;
import com.sipl.springhelloworld.mappers.VehicleMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class VehicleMapperImpl implements VehicleMapper {
    public Vehicle mapVehicleDtoToVehicle(VehicleDto vehicleDto) {
        if (vehicleDto == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setVehicleRegistrationNumber(vehicleDto.getVehicleRegistrationNumber());
        vehicle.setRegistrationExpires(vehicleDto.getRegistrationExpires());
        vehicle.setOwnerName(vehicleDto.getOwnerName());
        vehicle.setIsActive(vehicleDto.getIsActive());
        vehicle.setCreatedBy(vehicleDto.getCreatedBy());
        vehicle.setModifiedBy(vehicleDto.getModifiedBy());
        vehicle.setCreationTime(vehicleDto.getCreationTime());
        vehicle.setModifiedTime(vehicleDto.getModifiedTime());
        return vehicle;
    }

    public VehicleDto mapVehicleToVehicleDto(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setBrand(vehicle.getBrand());
        vehicleDto.setVehicleRegistrationNumber(vehicle.getVehicleRegistrationNumber());
        vehicleDto.setRegistrationExpires(vehicle.getRegistrationExpires());
        vehicleDto.setOwnerName(vehicle.getOwnerName());
        vehicleDto.setIsActive(vehicle.getIsActive());
        vehicleDto.setCreatedBy(vehicle.getCreatedBy());
        vehicleDto.setModifiedBy(vehicle.getModifiedBy());
        vehicleDto.setCreationTime(vehicle.getCreationTime());
        vehicleDto.setModifiedTime(vehicle.getModifiedTime());
        return vehicleDto;
    }

    public List<VehicleDto> maplistVehicleTolistVehicleDto(List<Vehicle> vehicleList) {
        if (vehicleList == null) {
            return null;
        }
        List<VehicleDto> listVehicleDto = vehicleList.stream()
                .map(this::mapVehicleToVehicleDto)
                .collect(Collectors.toList());
        return listVehicleDto;
    }
}
