package lk.sliit.ridelink.fare.controller;

import jakarta.validation.constraints.Positive;
import lk.sliit.ridelink.fare.dto.ReceiptResponse;
import lk.sliit.ridelink.fare.service.PaymentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final PaymentService paymentService;

    public ReceiptController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{paymentId}")
    public ReceiptResponse getReceiptByPaymentId(
            @PathVariable @Positive(message = "paymentId must be greater than 0") Long paymentId
    ) {
        return ReceiptResponse.from(paymentService.getReceiptByPaymentId(paymentId));
    }
}
