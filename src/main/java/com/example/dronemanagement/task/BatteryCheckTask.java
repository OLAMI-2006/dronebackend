package com.example.dronemanagement.task;

import com.example.dronemanagement.model.Drone;
import com.example.dronemanagement.Repository.DroneRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatteryCheckTask {

    private final DroneRepository droneRepository;

    // Inject the repository directly since we are focusing on controllers, models, and repositories
    public BatteryCheckTask(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void checkFleetBatteryLevels() {
        List<Drone> drones = droneRepository.findAll();

        System.out.println("=== 🔋 STARTING AUTOMATED FLEET BATTERY CHECK ===");

        if (drones.isEmpty()) {
            System.out.println("No drones registered in the fleet yet.");
        } else {
            for (Drone drone : drones) {
                System.out.printf("Drone ID: %d | Serial: %s | Battery: %d%%%n",
                        drone.getId(),
                        drone.getSerialNumber(),
                        drone.getBatteryCapacity());

                // Optional: Print a warning message if a drone's battery is dangerously low
                if (drone.getBatteryCapacity() < 25) {
                    System.out.printf("⚠️ WARNING: Drone %s is low on battery (%d%%)!%n",
                            drone.getSerialNumber(), drone.getBatteryCapacity());
                }
            }
        }

        System.out.println("=== 🔋 BATTERY CHECK COMPLETED ===");
    }
}