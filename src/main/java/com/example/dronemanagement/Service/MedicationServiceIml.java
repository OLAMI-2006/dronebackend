package com.example.dronemanagement.Service;

import com.example.dronemanagement.model.Drone;
import com.example.dronemanagement.model.DroneStatus;
import com.example.dronemanagement.model.Medication;
import com.example.dronemanagement.Repository.DroneRepository;
import com.example.dronemanagement.Repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceIml implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;

    public MedicationServiceIml(MedicationRepository medicationRepository, DroneRepository droneRepository) {
        this.medicationRepository = medicationRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public Medication loadMedicationToDrone(Long droneId, Medication medication) {
        // For finding the drone
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found with ID: " + droneId));

        // Validating  Payload Name (Letters, numbers, '-', '_')
        if (medication.getName() == null || !medication.getName().matches("^[a-zA-Z0-9-_]+$")) {
            throw new IllegalArgumentException("Invalid payload name. Only letters, numbers, '-', and '_' are allowed.");
        }

        //  Validating Payload Code (UPPERCASE letters, numbers, '_')
        if (medication.getCode() == null || !medication.getCode().matches("^[A-Z0-9_]+$")) {
            throw new IllegalArgumentException("Invalid payload code. Only uppercase letters, numbers, and '_' are allowed.");
        }

        // 4. Checking battery level (Cannot load if below 25%)
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Cannot load medication: Drone battery is below 25%!");
        }

        // 5. Checking weight limit
        double currentWeight = drone.getLoadedMedications().stream()
                .mapToDouble(Medication::getWeight)
                .sum();

        if (currentWeight + medication.getWeight() > drone.getWeightLimit()) {
            throw new IllegalArgumentException("Cannot load medication: Payload exceeds drone weight limit!");
        }

        // 6. Link them together and save
        medication.setDrone(drone);

        drone.setStatus(DroneStatus.LOADING);
        droneRepository.save(drone);

        return medicationRepository.save(medication);
    }
}
