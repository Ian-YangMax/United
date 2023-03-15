package com.distribuida.clientes;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri="http://localhost:8080")
@RegisterRestClient(configKey = "producto")
public interface ProductoRestProxy {
    @GET
    @Path("/{id}")
    CCliente findById(@PathParam("id") Long id);



    @GET
    List<CCliente> findAll();

    @POST
    void insert(CCliente obj);

    @PUT
    @Path("/{id}")
    void update(CCliente obj, @PathParam("id") Long id);

    @DELETE
    @Path("/{id}")
    void delete( @PathParam("id") Long id );
}
