package lk.sliit.ridelink.fare.repository;

import lk.sliit.ridelink.fare.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByRideId(Long rideId);

    Optional<Receipt> findByReceiptNumber(String receiptNumber);

    Optional<Receipt> findByPayment_Id(Long paymentId);

    boolean existsByPayment_Id(Long paymentId);
}
