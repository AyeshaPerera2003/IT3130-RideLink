package lk.sliit.ridelink.fare.dto;

import lk.sliit.ridelink.fare.model.PaymentMethod;
import lk.sliit.ridelink.fare.model.Receipt;

import java.math.BigDecimal;
import java.time.Instant;

public record ReceiptResponse(
        Long id,
        String receiptNumber,
        Long paymentId,
        Long rideId,
        BigDecimal amount,
        PaymentMethod method,
        Instant issuedAt
) {
    public static ReceiptResponse from(Receipt receipt) {
        Long paymentId = receipt.getPayment() == null ? null : receipt.getPayment().getId();
        return new ReceiptResponse(
                receipt.getId(),
                receipt.getReceiptNumber(),
                paymentId,
                receipt.getRideId(),
                receipt.getAmount(),
                receipt.getMethod(),
                receipt.getIssuedAt()
        );
    }
}
