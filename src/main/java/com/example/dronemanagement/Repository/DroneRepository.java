package com.example.dronemanagement.Repository;

import com.example.dronemanagement.model.Drone;
import com.example.dronemanagement.model.DroneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByStatus(DroneStatus status);
}