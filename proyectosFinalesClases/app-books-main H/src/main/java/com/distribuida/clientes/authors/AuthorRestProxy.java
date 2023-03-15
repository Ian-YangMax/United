package com.distribuida.clientes.authors;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri="http://localhost:8080")
@RegisterRestClient(configKey = "author")
public interface AuthorRestProxy {

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    AuthorsCliente findById(@PathParam("id") Long id);

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    List<AuthorsCliente> findAll();

    @POST
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    void insert(AuthorsCliente obj);

    @PUT
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    void update(AuthorsCliente obj, @PathParam("id") Long id);

    @DELETE
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    void delete( @PathParam("id") Long id );
}
