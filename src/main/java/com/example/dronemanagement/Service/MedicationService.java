package com.example.dronemanagement.Service;

import com.example.dronemanagement.model.Medication;
import java.util.List;

public interface MedicationService {
    List<Medication> getAllMedications();
    Medication loadMedicationToDrone(Long droneId, Medication medication);
}