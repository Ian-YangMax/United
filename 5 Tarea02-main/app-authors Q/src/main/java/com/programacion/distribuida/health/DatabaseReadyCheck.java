package com.programacion.distribuida.health;

import io.agroal.api.AgroalDataSource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

@Readiness
@ApplicationScoped
public class DatabaseReadyCheck implements HealthCheck {

    @Inject
    private AgroalDataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try {
            var con = dataSource.getConnection();
            con.close();
            return HealthCheckResponse.named("database ready")
                    .up()
                    .build();
        } catch (SQLException e) {
            return HealthCheckResponse.named("database not ready")
                    .down()
                    .build();

        }


    }
}
