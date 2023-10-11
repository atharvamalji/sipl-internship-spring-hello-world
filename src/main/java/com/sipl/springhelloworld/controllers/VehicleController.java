package com.sipl.springhelloworld.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.sipl.springhelloworld.dtos.VehicleDto;

public interface VehicleController {
    ResponseEntity<List<VehicleDto>> getAllVehicles();

    // ResponseEntity<Item> getItemById(Long id);

    // ResponseEntity<Item> createItem(Item item);

    // ResponseEntity<Item> updateItem(Long id, Item item);

    // ResponseEntity<Void> deleteItem(Long id);
}
