package lk.sliit.ridelink.fare.repository;

import lk.sliit.ridelink.fare.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FareRepository extends JpaRepository<Fare, Long> {

    Optional<Fare> findByRideId(Long rideId);

    List<Fare> findByPassengerId(String passengerId);

    List<Fare> findByDriverId(String driverId);

    boolean existsByRideId(Long rideId);
}
