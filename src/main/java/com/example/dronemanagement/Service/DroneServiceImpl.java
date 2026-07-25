package com.example.dronemanagement.Service;

import com.example.dronemanagement.model.Drone;
import com.example.dronemanagement.Repository.DroneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public Drone registerDrone(Drone drone) {
        // Validating Max Weight Limit (Max 500g)
        if (drone.getWeightLimit() > 500.0) {
            throw new IllegalArgumentException("Drone weight limit cannot exceed 500 grams.");
        }

        // Validating Battery Capacity percentage (0 to 100)
        if (drone.getBatteryCapacity() < 0 || drone.getBatteryCapacity() > 100) {
            throw new IllegalArgumentException("Battery capacity must be between 0% and 100%.");
        }

        return droneRepository.save(drone);
    }

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public Optional<Drone> getDroneById(Long id) {
        return droneRepository.findById(id);
    }
}