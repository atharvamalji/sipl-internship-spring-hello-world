package com.sipl.springhelloworld.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.html.Option;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Service;

import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.entities.Vehicle;
import com.sipl.springhelloworld.mappers.VehicleMapper;
import com.sipl.springhelloworld.mappers.impl.VehicleMapperImpl;
import com.sipl.springhelloworld.repositories.VehicleRepository;
import com.sipl.springhelloworld.services.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapperImpl vehicleMapper;

    public VehicleDto createNewVehicle(VehicleDto vehicleDto) {
        LocalDateTime registrationExpires = LocalDateTime.now().plusYears(3);
        vehicleDto.setCreationTime(LocalDateTime.now());
        vehicleDto.setModifiedTime(LocalDateTime.now());
        vehicleDto.setRegistrationExpires(registrationExpires);

        Vehicle vehicle = vehicleMapper.mapVehicleDtoToVehicle(vehicleDto);
        Vehicle newVehicle = vehicleRepository.save(vehicle);
        VehicleDto result = vehicleMapper.mapVehicleToVehicleDto(newVehicle);
        return result;
    }

    public VehicleDto updateVehicle(VehicleDto vehicleDto) {
        Vehicle updatedVehicle = vehicleMapper.mapVehicleDtoToVehicle(vehicleDto);
        Optional<Vehicle> vehicleInstance = vehicleRepository.findById(updatedVehicle.getId());
        if (vehicleInstance.isPresent()) {
            Vehicle currentVehicle = vehicleInstance.get();
            System.out.println(Arrays.toString(getPropertyNames(vehicleDto, true, false)));
            setNotNullAttributes(updatedVehicle, currentVehicle);
            System.out.println(currentVehicle.getBrand());
            vehicleRepository.save(currentVehicle);
            VehicleDto updatedVehicleDto = vehicleMapper.mapVehicleToVehicleDto(currentVehicle);
            return updatedVehicleDto;
        } else {
            return null;
        }
    }

    public List<VehicleDto> getAllVehicles() {
        List<VehicleDto> listVehicleDto;
        List<Vehicle> listVehicle = new ArrayList<Vehicle>();
        Iterable<Vehicle> vehicleIterable = vehicleRepository.findAll();
        vehicleIterable.forEach(listVehicle::add);
        listVehicleDto = vehicleMapper.maplistVehicleTolistVehicleDto(listVehicle);
        return listVehicleDto;
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

    private void setNotNullAttributes(Vehicle source, Vehicle target) {
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
