package com.example.dronemanagement.Service;
import com.example.dronemanagement.model.DeliveryRequest;
import com.example.dronemanagement.model.DeliveryStatus;
import com.example.dronemanagement.Repository.DeliveryRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    public DeliveryRequest updateStatus(Long id, DeliveryStatus status) {
        DeliveryRequest request = deliveryRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery request not found with id: " + id));

        request.setStatus(status);
        return deliveryRequestRepository.save(request);
    }
}
