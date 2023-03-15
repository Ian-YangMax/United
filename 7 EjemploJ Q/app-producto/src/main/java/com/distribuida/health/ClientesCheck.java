package com.distribuida.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import java.time.LocalDateTime;

public class ClientesCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("app-clientes")
                .withData("time", LocalDateTime.now().toString())
                .down()
                .build();
    }
}
