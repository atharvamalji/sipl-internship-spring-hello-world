package com.sipl.springhelloworld.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sipl.springhelloworld.controllers.VehicleController;
import com.sipl.springhelloworld.dtos.VehicleDto;
import com.sipl.springhelloworld.responses.VehicleApiResponse;
import com.sipl.springhelloworld.services.impl.VehicleServiceImpl;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleControllerImpl implements VehicleController {

    @Autowired
    VehicleServiceImpl vehicleService;

    @GetMapping()
    public ResponseEntity<VehicleApiResponse> getAllVehicles() {
        VehicleApiResponse fetchedResponse = vehicleService.getAllVehicles();
        ResponseEntity<VehicleApiResponse> response = new ResponseEntity<VehicleApiResponse>(fetchedResponse, null, fetchedResponse.getStatusCode());
        return response;
    }

    @PostMapping()
    public ResponseEntity<VehicleApiResponse> createNewVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleApiResponse fetchedResponse = vehicleService.createNewVehicle(vehicleDto);
        ResponseEntity<VehicleApiResponse> response = new ResponseEntity<VehicleApiResponse>(fetchedResponse, null, fetchedResponse.getStatusCode());
        return response;
    }

    @PutMapping()
    public ResponseEntity<VehicleApiResponse> updateVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleApiResponse fetchedResponse = vehicleService.updateVehicle(vehicleDto);
        ResponseEntity<VehicleApiResponse> response = new ResponseEntity<VehicleApiResponse>(fetchedResponse, null, fetchedResponse.getStatusCode());
        return response;
    }

    @DeleteMapping()
    public ResponseEntity<VehicleApiResponse> deleteVehicle(@RequestParam Long id) {
        VehicleApiResponse fetchedResponse = vehicleService.deleteVehicle(id);
        ResponseEntity<VehicleApiResponse> response = new ResponseEntity<VehicleApiResponse>(fetchedResponse, null, fetchedResponse.getStatusCode());
        return response;
    }
}
