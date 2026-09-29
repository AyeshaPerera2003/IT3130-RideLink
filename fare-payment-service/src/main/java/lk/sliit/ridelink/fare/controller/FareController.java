package lk.sliit.ridelink.fare.controller;

import jakarta.validation.Valid;
import lk.sliit.ridelink.fare.dto.CalculateFareRequest;
import lk.sliit.ridelink.fare.dto.FareResponse;
import lk.sliit.ridelink.fare.service.FareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fares")
public class FareController {

    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<FareResponse> calculateFare(@Valid @RequestBody CalculateFareRequest request) {
        FareResponse body = FareResponse.from(fareService.calculateFare(
                request.rideId(),
                request.passengerId(),
                request.driverId(),
                request.distanceKm(),
                request.durationMinutes()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{id}")
    public FareResponse getFareById(@PathVariable Long id) {
        return FareResponse.from(fareService.getFareById(id));
    }

    @GetMapping("/ride/{rideId}")
    public FareResponse getFareByRideId(@PathVariable Long rideId) {
        return FareResponse.from(fareService.getFareByRideId(rideId));
    }
}
