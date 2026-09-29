package com.ridelink.driver_service.controller;

import com.ridelink.driver_service.entity.Driver;
import com.ridelink.driver_service.repository.DriverRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    // 1. Get the list of all drivers (GET)
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // 2. Get the driver by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Create a new driver (POST) - @Valid is included
    @PostMapping
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        return ResponseEntity.status(201).body(savedDriver);
    }

    // 4. Update driver information (PUT) - @Valid is included
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @Valid @RequestBody Driver driverDetails) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setName(driverDetails.getName());
            driver.setPhone(driverDetails.getPhone());
            driver.setAvailability(driverDetails.isAvailability());
            driver.setServiceArea(driverDetails.getServiceArea());
            driver.setLatitude(driverDetails.getLatitude());
            driver.setLongitude(driverDetails.getLongitude());
            return ResponseEntity.ok(driverRepository.save(driver));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Update driver's availability (PATCH)
    @PatchMapping("/{id}/availability")
    public ResponseEntity<Driver> updateAvailability(@PathVariable Long id, @RequestParam boolean availability) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setAvailability(availability);
            return ResponseEntity.ok(driverRepository.save(driver));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. Update driver's GPS location (PATCH)
    @PatchMapping("/{id}/location")
    public ResponseEntity<Driver> updateLocation(@PathVariable Long id, @RequestParam double latitude, @RequestParam double longitude) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            driver.setLatitude(latitude);
            driver.setLongitude(longitude);
            return ResponseEntity.ok(driverRepository.save(driver));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 7. Search drivers by service area and availability (GET)
    @GetMapping("/search")
    public List<Driver> searchDrivers(@RequestParam String serviceArea, @RequestParam boolean availability) {
        return driverRepository.findByServiceAreaAndAvailability(serviceArea, availability);
    }

    // 8. Delete a driver (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}