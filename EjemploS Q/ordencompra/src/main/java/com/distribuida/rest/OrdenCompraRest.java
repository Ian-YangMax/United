package com.distribuida.rest;

import com.distribuida.clientes.CCProducto;
import com.distribuida.clientes.ClienteRestProxy;
import com.distribuida.clientes.ProductoRestProxy;
import com.distribuida.db.OrdenCompra;
import com.distribuida.dtos.ClienteDto;
import com.distribuida.dtos.ProductoDto;
import com.distribuida.servicios.OrdenCompraRepository;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/ordencompra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdenCompraRest {
    @Inject
    private OrdenCompraRepository ordenCompraRepository;

    @Inject
    OrdenCompraRepository ordenService;
    @RestClient
    @Inject
    ClienteRestProxy proxyCliente;
    ProductoRestProxy proxyProducto;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get OrdenCompra")
    @APIResponse(responseCode = "207", description = "Todas las ordenes", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = OrdenCompra.class)))
    public List<OrdenCompra> findAll() {
        return ordenCompraRepository.getOrdenCompra();

    }

    @GET
    @Path("/allp")
    //@Timeout(value = 5000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 10000, successThreshold = 3)
    @Fallback(fallbackMethod = "findAll")
    //@Bulkhead(value = 5, waitingTaskQueue = 10)
    //@Retry(delay = 400, maxDuration= 3200, jitter= 400, maxRetries = 10)
    public List<ProductoDto> findAllCompleto() throws Exception {

//        AuthorRestProxy proxyAuthor = RestClientBuilder.newBuilder()
//                .baseUrl( new URL("http://127.0.0.1:8080") )
//                .connectTimeout( 1000, TimeUnit.MILLISECONDS )
//                .build( AuthorRestProxy.class );

        var productos = ordenService.findAll();
        List<ProductoDto> ret = productos.stream()
                .map(s -> {
                    System.out.println("*********buscando " + s.getId());

                    CCProducto producto = proxyProducto.findById(s.getId().longValue());
                    return new ProductoDto(
                            s.getId(),
                            s.getNombre(),
                            s.getApellido(),
                   
                    );
                })
                .collect(Collectors.toList());
        return ret;
    }

    @GET
    @Path("/allc")
    //@Timeout(value = 5000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 10000, successThreshold = 3)
    @Fallback(fallbackMethod = "findAll")
    //@Bulkhead(value = 5, waitingTaskQueue = 10)
    //@Retry(delay = 400, maxDuration= 3200, jitter= 400, maxRetries = 10)
    public List<ClienteDto> findAllCompleto() throws Exception {

//        AuthorRestProxy proxyAuthor = RestClientBuilder.newBuilder()
//                .baseUrl( new URL("http://127.0.0.1:8080") )
//                .connectTimeout( 1000, TimeUnit.MILLISECONDS )
//                .build( AuthorRestProxy.class );

        var clientes = ordenService.findAll();
        List<ClienteDto> ret = clientes.stream()
                .map(s -> {
                    System.out.println("*********buscando " + s.getId());

                    CCProducto producto = proxyProducto.findById(s.getId().longValue());
                    return new ProductoDto(
                            s.getId(),
                            s.getNombre(),
                            s.getApellido(),
                            s.getDireccion(),

                            );
                })
                .collect(Collectors.toList());
        return ret;
    }

    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get OrdenCompra")
    @APIResponse(responseCode = "207", description = "Se ha encontrado las ordenes", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = OrdenCompra.class)))
    @APIResponse(responseCode = "400", description = "No se pudo encontrar las ordenes")
    public OrdenCompra findById(@Parameter(description = "ID ORDENCOMPRA", required = true) @PathParam("id") Integer id)  {
        return ordenCompraRepository.getOrdenCompraById(id);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Create OrdenCompra")
    @APIResponse(responseCode = "201", description = "Se ha creado la orden")
    @APIResponse(responseCode = "500", description = "Presenta problemas con el servidor")
    public Response create(
            @RequestBody(description = "Orden a Ingresar", required = true,
                    content = @Content(schema = @Schema(implementation = OrdenCompra.class)))
            OrdenCompra b){
        ordenCompraRepository.creteOrdenCompra(b);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{id}")
    @Operation(description = "Update OrdenCompra")
    @APIResponse(responseCode = "207", description = "Se actualizo la orden")
    @APIResponse(responseCode = "500", description = "Presenta problemas de actualizacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "Datos de la orden a modificar", required = true,
                    content = @Content(schema = @Schema(implementation = OrdenCompra.class)))
            OrdenCompra b,
            @Parameter(description = "ID ORDENCOMPRA", required = true)
            @PathParam("id") Integer id){
        ordenCompraRepository.updateOrdenCompra(id, b);
        return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete OrdenCompra")
    @APIResponse(responseCode = "209", description = "Se ha eliminado la orden")
    @APIResponse(responseCode = "500", description = "Presenta problemas con el servidor")
    public Response delete(
            @Parameter(description = "ID ORDENCOMPRA", required = true)
            @PathParam("id") Integer id) {
        ordenCompraRepository.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }
}
