package com.example.dronemanagement.Repository;

import com.example.dronemanagement.model.Payload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, Long>{

}
