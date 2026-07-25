package com.example.dronemanagement.Service;

import com.example.dronemanagement.Dto.DroneTelemetryDto;
import com.example.dronemanagement.model.Drone;
import com.example.dronemanagement.model.DroneStatus;
import com.example.dronemanagement.Repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DroneSimulationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DroneRepository droneRepository;

    @Async
    @Transactional
    public void startDroneFlight(Long droneId, double targetLat, double targetLng) {
        // 1. Ground any other currently flying drones
        List<Drone> allDrones = droneRepository.findAll();
        for (Drone drone : allDrones) {
            if (drone.getStatus() == DroneStatus.FLYING && !drone.getId().equals(droneId)) {
                drone.setStatus(DroneStatus.IDLE);
                droneRepository.save(drone);

                // Broadcast grounded telemetry update to sync the frontend instantly
                DroneTelemetryDto groundedDto = new DroneTelemetryDto(
                        drone.getId(),
                        "IDLE",
                        drone.getBatteryCapacity(),
                        drone.getLatitude(),
                        drone.getLongitude()
                );
                messagingTemplate.convertAndSend("/topic/drones", groundedDto);
            }
        }

        // 2. Set the requested drone to FLYING in the database
        Drone targetDrone = droneRepository.findById(droneId).orElse(null);
        if (targetDrone != null) {
            targetDrone.setStatus(DroneStatus.FLYING);
            droneRepository.save(targetDrone);
        }

        double currentLat = targetLat;
        double currentLng = targetLng;
        int battery = targetDrone != null ? targetDrone.getBatteryCapacity() : 100;

        // 3. Run the flight simulation loop
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1500); // Send update every 1.5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            currentLat += 0.0012;
            currentLng += 0.0015;
            battery = Math.max(0, battery - 1);

            // MATCHES YOUR DTO: (Long, String, int, double, double)
            DroneTelemetryDto telemetry = new DroneTelemetryDto(
                    droneId,
                    "FLYING",
                    battery,
                    currentLat,
                    currentLng
            );

            messagingTemplate.convertAndSend("/topic/drones", telemetry);
        }

        // 4. Once simulation finishes, set status back to IDLE
        if (targetDrone != null) {
            targetDrone.setStatus(DroneStatus.IDLE);
            targetDrone.setLatitude(currentLat);
            targetDrone.setLongitude(currentLng);
            targetDrone.setBatteryCapacity(battery);
            droneRepository.save(targetDrone);

            DroneTelemetryDto finishedDto = new DroneTelemetryDto(
                    droneId,
                    "IDLE",
                    battery,
                    currentLat,
                    currentLng
            );
            messagingTemplate.convertAndSend("/topic/drones", finishedDto);
        }
    }
}