package com.example.dronemanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payloads")
@Data
public class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A payload belongs to exactly one delivery request
    @OneToOne
    @JoinColumn(name = "delivery_request_id", nullable = false)
    private DeliveryRequest deliveryRequest;

    // What medical item is inside this payload
    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    @Column(nullable = false)
    private Integer quantity; // How many of this item are in the box
}