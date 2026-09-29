package com.ridelink.driver_service;

import com.ridelink.driver_service.entity.Driver;
import com.ridelink.driver_service.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DriverServiceTest {

    @Autowired
    private DriverRepository driverRepository;

    @Test
    public void testSaveAndFindDriver() {
        // Given
        Driver driver = new Driver();
        driver.setName("Test Driver");
        driver.setPhone("0711234567");
        driver.setServiceArea("Malabe"); // මෙතැන camelCase එකට වෙනස් කළා
        driver.setAvailability(true);
        driver.setLatitude(6.9022);
        driver.setLongitude(79.8612);

        // When
        Driver savedDriver = driverRepository.save(driver);

        // Then
        assertThat(savedDriver).isNotNull();
        assertThat(savedDriver.getId()).isGreaterThan(0L);
        
        Optional<Driver> foundDriver = driverRepository.findById(savedDriver.getId());
        assertThat(foundDriver).isPresent();
        assertThat(foundDriver.get().getName()).isEqualTo("Test Driver");
    }
}