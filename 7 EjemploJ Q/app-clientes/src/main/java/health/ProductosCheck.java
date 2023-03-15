package health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import java.time.LocalDateTime;

public class ProductosCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("app-productos")
                .withData("time", LocalDateTime.now().toString())
                .down()
                .build();
    }
}
