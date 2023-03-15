package com.distribuida.rest;

import com.distribuida.db.Cliente;
import com.distribuida.servicios.ClienteRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteRest {
    @Inject
    private ClienteRepository clienteRepository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Cliente")
    @APIResponse(responseCode = "207", description = "Todos los clientes", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Cliente.class)))
    public List<Cliente> findAll() {
        return clienteRepository.getClientes();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Cliente")
    @APIResponse(responseCode = "207", description = "Se ha encontrado el cliente", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @APIResponse(responseCode = "400", description = "No se pudo encontrar el cliente")
    public Cliente findById(@Parameter(description = "ID CLIENTE", required = true) @PathParam("id") Integer id)  {
        return clienteRepository.getClienteById(id);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Create Cliente")
    @APIResponse(responseCode = "201", description = "Se ha creado el cliente")
    @APIResponse(responseCode = "500", description = "Presenta problemas con el servidor")
    public Response create(
            @RequestBody(description = "Cliente a Ingresar", required = true,
                    content = @Content(schema = @Schema(implementation = Cliente.class)))
            Cliente b){
        clienteRepository.creteCliente(b);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{id}")
    @Operation(description = "Update Cliente")
    @APIResponse(responseCode = "207", description = "Se actualizo el cliente")
    @APIResponse(responseCode = "500", description = "Presenta problemas de actualizacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "Datos del cliente a modificar", required = true,
                    content = @Content(schema = @Schema(implementation = Cliente.class)))
            Cliente b,
            @Parameter(description = "ID CLIENTE", required = true)
            @PathParam("id") Integer id){
        clienteRepository.updateCliente(id, b);
        return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete Cliente")
    @APIResponse(responseCode = "209", description = "Se ha eliminado el cliente")
    @APIResponse(responseCode = "500", description = "Presenta problemas con el servidor")
    public Response delete(
            @Parameter(description = "ID CLIENTE", required = true)
            @PathParam("id") Integer id) {
        clienteRepository.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }
}
