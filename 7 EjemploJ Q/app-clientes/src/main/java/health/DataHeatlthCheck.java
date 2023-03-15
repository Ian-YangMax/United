package health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@Liveness
@ApplicationScoped
public class DataHeatlthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Health Check with Data")
                .up()
                .withData("time", LocalDateTime.now().toString())
                .build();
    }
}
