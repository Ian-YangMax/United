package org.example.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.servicios.ServicioHolaMundo;

@ApplicationScoped
@Path(value = "/")
public class HolaMundoRest {

    @Inject
    ServicioHolaMundo servicio;

    @GET
    @Path("/hola")
    public String hola(){
        return servicio.Hola();
    }
}
