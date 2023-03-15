package com.programacion.distribuida.health;


import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@ApplicationScoped
@Liveness
public class SystemLivenessCheck implements HealthCheck {



    @Override
    public HealthCheckResponse call() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        Long memoryUsed=memoryMXBean.getHeapMemoryUsage().getUsed();
        Long memoryCapacity=memoryMXBean.getHeapMemoryUsage().getMax();
        return HealthCheckResponse.
                named("Memory Liveness Check")
                .withData("used",memoryUsed)
                .withData("capacity",memoryCapacity)
                .status(memoryUsed<memoryCapacity*0.9)
                .build();
    }
}
