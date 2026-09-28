package com.example.ride_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DriverServiceClient {

    private final RestClient restClient;

    public DriverServiceClient(
            RestClient.Builder restClientBuilder,
            @Value("${driver.service.base-url}") String driverServiceBaseUrl) {

        this.restClient = restClientBuilder
                .baseUrl(driverServiceBaseUrl)
                .build();
    }

    public String getAvailableDriver() {

        return restClient.get()
                .uri("/api/drivers/available")
                .retrieve()
                .body(String.class);
    }
}