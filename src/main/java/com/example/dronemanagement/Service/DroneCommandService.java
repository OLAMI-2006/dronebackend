package com.example.dronemanagement.Service;

import com.example.dronemanagement.model.Drone;
import com.example.dronemanagement.model.DroneStatus;
import com.example.dronemanagement.Repository.DroneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DroneCommandService {

    private final DroneRepository droneRepository;

    public DroneCommandService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Transactional
    public Drone processCommand(Long droneId, String commandType) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found with ID: " + droneId));

        switch (commandType.toUpperCase()) {
            case "RTL":
                drone.setStatus(DroneStatus.RETURNING_TO_LAUNCH);
                drone.setCurrentLatitude(6.5244);
                drone.setCurrentLongitude(3.3792);
                break;
            case "HOVER":
                drone.setStatus(DroneStatus.HOVERING);
                break;
            case "RESUME":
                drone.setStatus(DroneStatus.FLYING);
                break;
            default:
                throw new IllegalArgumentException("Unsupported operational command: " + commandType);
        }

        return droneRepository.save(drone);
    }
}