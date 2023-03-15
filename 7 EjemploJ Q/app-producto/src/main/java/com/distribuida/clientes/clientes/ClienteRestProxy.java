package com.distribuida.clientes.clientes;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri="http://localhost:9090")
//@RegisterRestClient(configKey = "cliente")
public interface ClienteRestProxy {
    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    ClienteClientes findById(@PathParam("id") Long id);

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    List<ClienteClientes> findAll();

    @POST
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    void insert(ClienteClientes obj);

    @PUT
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    void update(ClienteClientes obj, @PathParam("id") Long id);

    @DELETE
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    void delete( @PathParam("id") Long id );
}
