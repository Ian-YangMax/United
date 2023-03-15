package org.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApp extends Application {
    //CDI
    //JAX-RS
    //CONFIG
    //Server
    //se descarga solo las dependencias minimas con poner la dependencia de MP-SERVER
    /*
    implementation group: 'io.helidon.microprofile.server', name: 'helidon-microprofile-server', version: '3.0.2'
    */
}
