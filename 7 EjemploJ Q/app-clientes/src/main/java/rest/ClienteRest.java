package rest;

import clientes.productos.ClienteProductos;
import clientes.productos.ProductoRestProxy;
import db.Clientes;
import dtos.ClienteDto;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import servicios.ClienteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteRest {

    @Inject
    ClienteService clienteRepository;

    @RestClient
    @Inject
    ProductoRestProxy proxyProducto;

    @GET
    @Path("/{id}")
    @Operation(summary = "Find Client by Id", operationId = "findClientById")
    @APIResponse(name = "Success Find Client by Id", responseCode = "200", description = "Client Founded by Id", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Clientes.class)))
    @APIResponse(name = "Error Find Client by Id", responseCode = "500", description = "Client not Founded By Id")
    public Clientes findById(@PathParam("id") Long id) {
        return clienteRepository.findClientById(id);
    }

    @GET
    @Operation(summary = "Find All Clients", operationId = "findAllClients")
    @APIResponse(name = "Success Find All Clients",responseCode = "201", description = "Find All Cients", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Clientes.class)))
    public List<Clientes> findAll() {
        return clienteRepository.findAllClientes();
    }

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 1000, successThreshold = 3)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Bulkhead(value = 5, waitingTaskQueue = 10)
    @Fallback(fallbackMethod = "findAll")
    @Path("/all")
    public List<ClienteDto> findAllCompleto() throws Exception{
        var clientes = clienteRepository.findAllClientes();
        List<ClienteDto> ret = clientes.stream()
                .map(s ->{
                    System.out.println("********Buscando Orden " + s.getId());
                    ClienteProductos productos = proxyProducto.findById(s.getId().longValue());
                    return new ClienteDto(
                            s.getId(),
                            s.getId(),
                            Long.valueOf(productos.getId()),
                            Double.valueOf(productos.getPrecio())

                    );
                })
                .collect(Collectors.toList());
        return ret;
    }

    @POST
    @Operation(description = "Insert Client", operationId = "insertClient")
    @APIResponse(name = "Success Insert Client",responseCode = "202", description = "Client Inserted")
    @APIResponse(name = "Error Insert Client",responseCode = "502", description = "Error Inserting Client")
    public void insert(Clientes cliente) {
        clienteRepository.insertClient(cliente);
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "Update Client by Id", operationId = "updateClient")
    @APIResponse(name = "Success Update Client",responseCode = "203", description = "Client Updated")
    @APIResponse(name = "Error Update Client",responseCode = "503", description = "Error Updating Client")
    public void update(Clientes cliente, @PathParam("id") Long id) {
        clienteRepository.updateCliente(cliente,id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete Client by Id", operationId = "deleteClient")
    @APIResponse(name = "Success Delete Client", responseCode = "204", description = "Client Deleted")
    @APIResponse(name = "Error Delete Client", responseCode = "504", description = "Error Deleting Client")
    public void delete( @PathParam("id") Long id ) {
        clienteRepository.deleteCliente(id);
    }
}

