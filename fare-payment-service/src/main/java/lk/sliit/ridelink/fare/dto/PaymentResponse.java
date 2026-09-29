package lk.sliit.ridelink.fare.dto;

import lk.sliit.ridelink.fare.model.Payment;
import lk.sliit.ridelink.fare.model.PaymentMethod;
import lk.sliit.ridelink.fare.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponse(
        Long id,
        Long fareId,
        Long rideId,
        String payerAccountId,
        BigDecimal amount,
        PaymentMethod method,
        PaymentStatus status,
        String transactionRef,
        Instant paidAt,
        Instant createdAt
) {
    public static PaymentResponse from(Payment payment) {
        Long fareId = payment.getFare() == null ? null : payment.getFare().getId();
        return new PaymentResponse(
                payment.getId(),
                fareId,
                payment.getRideId(),
                payment.getPayerAccountId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getTransactionRef(),
                payment.getPaidAt(),
                payment.getCreatedAt()
        );
    }
}
