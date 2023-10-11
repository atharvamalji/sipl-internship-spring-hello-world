package com.sipl.springhelloworld.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 64)
    private String brand;

    @Column(nullable = false, unique = true, length = 10)
    private String vehicleRegistrationNumber;

    @Column(nullable = false)
    private LocalDateTime registrationExpires;

    @Column(nullable = false, length = 64)
    private String ownerName;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private String modifiedBy;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column(nullable = false)
    private LocalDateTime modifiedTime;
}
