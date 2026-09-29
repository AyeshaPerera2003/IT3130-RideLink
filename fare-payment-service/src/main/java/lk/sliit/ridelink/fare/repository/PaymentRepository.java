package lk.sliit.ridelink.fare.repository;

import lk.sliit.ridelink.fare.model.Payment;
import lk.sliit.ridelink.fare.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByRideId(Long rideId);

    List<Payment> findByPayerAccountId(String payerAccountId);

    List<Payment> findByFare_Id(Long fareId);

    Optional<Payment> findByTransactionRef(String transactionRef);

    Optional<Payment> findByRideIdAndStatus(Long rideId, PaymentStatus status);

    boolean existsByRideIdAndStatus(Long rideId, PaymentStatus status);
}
