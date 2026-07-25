package com.example.dronemanagement.Repository;

import com.example.dronemanagement.model.DeliveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest, Long> {
}