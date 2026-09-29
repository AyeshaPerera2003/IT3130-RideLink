package com.ridelink.driver_service.repository;

import com.ridelink.driver_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // Search for available drivers
    List<Driver> findByAvailability(boolean availability);

    // Filter drivers by service area and availability (Eligible available drivers)
    List<Driver> findByServiceAreaAndAvailability(String serviceArea, boolean availability);
}