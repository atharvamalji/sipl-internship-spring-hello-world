package com.sipl.springhelloworld.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.entities.Vehicle;
import com.sipl.springhelloworld.mappers.impl.VehicleMapperImpl;
import com.sipl.springhelloworld.repositories.VehicleRepository;
import com.sipl.springhelloworld.responses.VehicleApiResponse;
import com.sipl.springhelloworld.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapperImpl vehicleMapper;

    public VehicleApiResponse createNewVehicle(VehicleDto vehicleDto) {
        VehicleApiResponse response = new VehicleApiResponse();

        Optional<Vehicle> vehicleExists = vehicleRepository
                .findByVehicleRegistrationNumber(vehicleDto.getVehicleRegistrationNumber());

        if (!vehicleExists.isPresent()) {
            try {
                LocalDateTime registrationExpires = LocalDateTime.now().plusYears(3);
                vehicleDto.setCreationTime(LocalDateTime.now());
                vehicleDto.setModifiedTime(LocalDateTime.now());
                vehicleDto.setRegistrationExpires(registrationExpires);
                Vehicle vehicle = vehicleMapper.mapVehicleDtoToVehicle(vehicleDto);
                Vehicle newVehicle = vehicleRepository.save(vehicle);
                VehicleDto result = vehicleMapper.mapVehicleToVehicleDto(newVehicle);

                JSONObject responseData = new JSONObject();
                JSONObject createdVehicleJsonObject = new JSONObject(result);
                responseData.put("generatedVehicle", createdVehicleJsonObject);

                response.setStatus(true);
                response.setStatusCode(HttpStatus.OK);
                response.setMessage("Vehicle added successfully");
                response.setResponseData(responseData.toMap());

                return response;
            } catch (Exception e) {
                response.setStatus(false);
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setMessage("Error occured while adding the vehicle.");
                return response;
            }

        }

        response.setStatus(false);
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setMessage("Vehicle already exists");
        return response;
    }

    public VehicleApiResponse updateVehicle(VehicleDto vehicleDto) {
        VehicleApiResponse response = new VehicleApiResponse();

        Vehicle updatedVehicle = vehicleMapper.mapVehicleDtoToVehicle(vehicleDto);
        Optional<Vehicle> vehicleInstance = vehicleRepository.findById(updatedVehicle.getId());

        try {
            if (vehicleInstance.isPresent()) {
                Vehicle currentVehicle = vehicleInstance.get();
                setVehicleNotNullAttributes(updatedVehicle, currentVehicle);
                vehicleRepository.save(currentVehicle);
                VehicleDto updatedVehicleDto = vehicleMapper.mapVehicleToVehicleDto(currentVehicle);

                JSONObject responseData = new JSONObject();
                JSONObject updatedVehicleJsonObject = new JSONObject(updatedVehicleDto);
                responseData.put("updatedVehicle", updatedVehicleJsonObject);

                response.setStatus(true);
                response.setStatusCode(HttpStatus.OK);
                response.setMessage("Vehicle updated successfully.");
                response.setResponseData(responseData.toMap());
                
                return response;
            } else {
                response.setStatus(false);
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                response.setMessage("Vehicle does not exists.");
                return response;
            }
        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Error occured while updating the vehicle.");
            return response;
        }

    }

    public VehicleApiResponse deleteVehicle(Long vehicleId) {
        Optional<Vehicle> vehicleInstance = vehicleRepository.findById(vehicleId);
        VehicleApiResponse response = new VehicleApiResponse();

        if (vehicleInstance.isPresent()) {
            vehicleRepository.delete(vehicleInstance.get());
            response.setStatus(true);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Vehicle deleted successfully");
            return response;
        }
        response.setStatus(false);
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.setMessage("Vehicle does not exists");
        return response;
    }

    public VehicleApiResponse getAllVehicles() {
        VehicleApiResponse response = new VehicleApiResponse();

        try {
            List<VehicleDto> listVehicleDto;
            List<Vehicle> listVehicle = new ArrayList<Vehicle>();
            Iterable<Vehicle> vehicleIterable = vehicleRepository.findAll();
            vehicleIterable.forEach(listVehicle::add);
            listVehicleDto = vehicleMapper.maplistVehicleTolistVehicleDto(listVehicle);

            JSONObject responseData = new JSONObject();
            responseData.put("vehicles", listVehicleDto);

            response.setStatus(true);
            response.setStatusCode(HttpStatus.OK);
            response.setMessage("Vehicle fetched successfully");
            response.setResponseData(responseData.toMap());

            return response;
        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Somthing went wrong while fetching vehicles");
            return response;
        }
    }

    public List<VehicleDto> getVehicleByRegistrationNumber(String vehicleRegistrationNumber) {
        List<VehicleDto> response = new ArrayList<VehicleDto>();
        return response;
    }

    // utils
    private String[] getPropertyNames(VehicleDto vehicle, boolean getNull, boolean allValues) {
        final BeanWrapper src = new BeanWrapperImpl(vehicle);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        if (allValues) {
            for (java.beans.PropertyDescriptor pd : pds) {
                emptyNames.add(pd.getName());
            }
        } else {
            if (getNull) {
                for (java.beans.PropertyDescriptor pd : pds) {
                    Object srcValue = src.getPropertyValue(pd.getName());
                    if (srcValue == null)
                        emptyNames.add(pd.getName());
                }
            } else {
                for (java.beans.PropertyDescriptor pd : pds) {
                    Object srcValue = src.getPropertyValue(pd.getName());
                    if (srcValue != null)
                        emptyNames.add(pd.getName());
                }
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private void setVehicleNotNullAttributes(Vehicle source, Vehicle target) {
        if (source.getBrand() != null) {
            target.setBrand(source.getBrand());
        }

        if (source.getVehicleRegistrationNumber() != null) {
            target.setVehicleRegistrationNumber(source.getVehicleRegistrationNumber());
        }

        if (source.getRegistrationExpires() != null) {
            target.setRegistrationExpires(source.getRegistrationExpires());
        }

        if (source.getOwnerName() != null) {
            target.setOwnerName(source.getOwnerName());
        }

        if (source.getIsActive() != null) {
            target.setIsActive(source.getIsActive());
        }

        if (source.getCreatedBy() != null) {
            target.setCreatedBy(source.getCreatedBy());
        }

        if (source.getModifiedBy() != null) {
            target.setModifiedBy(source.getModifiedBy());
        }

        if (source.getCreationTime() != null) {
            target.setCreationTime(source.getCreationTime());
        }

        if (source.getModifiedTime() != null) {
            target.setModifiedTime(source.getModifiedTime());
        }

    }

}
