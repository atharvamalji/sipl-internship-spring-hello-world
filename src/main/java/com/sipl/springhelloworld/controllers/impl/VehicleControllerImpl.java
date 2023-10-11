package com.sipl.springhelloworld.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.springhelloworld.controllers.VehicleController;
import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.services.impl.VehicleServiceImpl;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleControllerImpl implements VehicleController {

    @Autowired
    VehicleServiceImpl vehicleService;

    @GetMapping()
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> listVehicleDto = vehicleService.getAllVehicles();
        ResponseEntity<List<VehicleDto>> response = new ResponseEntity<List<VehicleDto>>(listVehicleDto, null,
                HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<VehicleDto> createNewVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleDto newVehicle = vehicleService.createNewVehicle(vehicleDto);
        ResponseEntity<VehicleDto> response = new ResponseEntity<VehicleDto>(newVehicle, null, HttpStatus.OK);
        return response;
    }

    @PutMapping()
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleDto updatedVehicle = vehicleService.updateVehicle(vehicleDto);
        ResponseEntity<VehicleDto> response = new ResponseEntity<VehicleDto>(updatedVehicle, null, HttpStatus.OK);
        return response;
    }
}
