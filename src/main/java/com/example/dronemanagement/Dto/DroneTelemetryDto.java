package com.example.dronemanagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneTelemetryDto {
    private Long id;
    private String status;
    private int batteryCapacity;
    private double latitude;
    private double longitude;
}