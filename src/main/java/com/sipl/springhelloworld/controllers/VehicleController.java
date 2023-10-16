package com.sipl.springhelloworld.controllers;

import org.springframework.http.ResponseEntity;
import com.sipl.springhelloworld.responses.VehicleApiResponse;

public interface VehicleController {
    ResponseEntity<VehicleApiResponse> getAllVehicles();

    // ResponseEntity<Item> getItemById(Long id);

    // ResponseEntity<Item> createItem(Item item);

    // ResponseEntity<Item> updateItem(Long id, Item item);

    // ResponseEntity<Void> deleteItem(Long id);
}
