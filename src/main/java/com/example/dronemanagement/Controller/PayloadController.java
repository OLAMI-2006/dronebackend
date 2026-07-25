package com.example.dronemanagement.Controller;

import com.example.dronemanagement.model.Payload;
import com.example.dronemanagement.Repository.PayloadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payloads")
@CrossOrigin(origins = "http://localhost:5173")
public class PayloadController {

    private final PayloadRepository payloadRepository;

    public PayloadController(PayloadRepository payloadRepository) {
        this.payloadRepository = payloadRepository;
    }

    // Creating  a new payload entry (POST http://localhost:8080/api/payloads)
    @PostMapping
    public ResponseEntity<Payload> createPayload(@RequestBody Payload payload) {
        Payload savedPayload = payloadRepository.save(payload);
        return new ResponseEntity<>(savedPayload, HttpStatus.OK);
    }

    //  For viewing all payloads (GET http://localhost:8080/api/payloads)
    @GetMapping
    public ResponseEntity<List<Payload>> getAllPayloads() {
        return new ResponseEntity<>(payloadRepository.findAll(), HttpStatus.OK);
    }
}