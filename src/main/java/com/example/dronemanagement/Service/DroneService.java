package com.example.dronemanagement.Service;

import com.example.dronemanagement.model.Drone;
import java.util.List;
import java.util.Optional;

public interface DroneService {
Drone registerDrone(Drone drone);
    List<Drone> getAllDrones();
    Optional<Drone> getDroneById(Long id);
}
