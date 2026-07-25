package com.example.dronemanagement.Controller;

import com.example.dronemanagement.model.Medication;
import com.example.dronemanagement.Service.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicationController {

    private final MedicationService medicationService;

    // Use the MedicationService instead of talking directly to the Repository
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // 1. Viewing all the stock available (GET http://localhost:8080/api/medications)
    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications() {
        return ResponseEntity.ok(medicationService.getAllMedications());
    }

    // 2. Loading medication onto a specific drone (POST http://localhost:8080/api/medications/load/{droneId})
    @PostMapping("/load/{droneId}")
    public ResponseEntity<?> loadMedication(@PathVariable Long droneId, @RequestBody Medication medication) {
        try {
            Medication savedMedication = medicationService.loadMedicationToDrone(droneId, medication);
            return ResponseEntity.ok(savedMedication);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}