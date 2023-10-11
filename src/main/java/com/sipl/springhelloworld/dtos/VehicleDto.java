package com.sipl.springhelloworld.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleDto {
    private long id;
    private String brand;
    private String vehicleRegistrationNumber;
    private LocalDateTime registrationExpires;
    private String ownerName;
    private Boolean isActive;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime creationTime;
    private LocalDateTime modifiedTime;
}
