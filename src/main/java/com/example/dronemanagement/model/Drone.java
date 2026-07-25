package com.example.dronemanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drones")
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String model;

    @Column(name = "weight_limit", nullable = false)
    private Double weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DroneStatus status = DroneStatus.IDLE;


    @Column(name = "current_latitude")
    private Double currentLatitude;

    @Column(name = "current_longitude")
    private Double currentLongitude;


    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medication> loadedMedications = new ArrayList<>();


    public double getLatitude() {
        return currentLatitude != null ? currentLatitude : 0.0;
    }

    public void setLatitude(double latitude) {
        this.currentLatitude = latitude;
    }

    public double getLongitude() {
        return currentLongitude != null ? currentLongitude : 0.0;
    }

    public void setLongitude(double longitude) {
        this.currentLongitude = longitude;
    }
}