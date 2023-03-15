package com.distribuida.rest;

import com.distribuida.clientes.clientes.ClienteClientes;
import com.distribuida.clientes.clientes.ClienteRestProxy;
import com.distribuida.db.Productos;
import com.distribuida.dtos.ProductoDto;
import com.distribuida.servicios.ProductoService;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoRest {

    @Inject
    ProductoService productoService;

    @Inject
    ProductoService productoRepository;

    @RestClient
    @Inject
    ClienteRestProxy proxyCliente;

    @GET
    @Path("/{id}")
    @Operation(summary = "Find Product by Id", operationId = "findProductById")
    @APIResponse(name = "Success Find Product by Id", responseCode = "200", description = "Product Founded by Id", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Productos.class)))
    @APIResponse(name = "Error Find Product by Id", responseCode = "500", description = "Product not Founded By Id")
    public Productos findById(@PathParam("id") Long id) {
        return productoRepository.findProductById(id);
    }




    @GET
    @Operation(summary = "Find All Products", operationId = "findAllProducts")
    @APIResponse(name = "Success Find All Products",responseCode = "201", description = "Find All Products", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Productos.class)))
    public List<Productos> findAll() {
        return productoRepository.findAllProducts();
    }

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 1000, successThreshold = 3)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Bulkhead(value = 5, waitingTaskQueue = 10)
    @Path("/all")
    public List<ProductoDto> findAllCompleto() throws Exception{
        var productos = productoService.findAllProducts();
        List<ProductoDto> ret = productos.stream()
                .map(s ->{
                    System.out.println("********Buscando Orden " + s.getId());
                    ClienteClientes clientes = proxyCliente.findById(s.getId().longValue());
                    return new ProductoDto(
                            s.getId(),
                            s.getId(),
                            Long.valueOf(clientes.getId()),
                            s.getPrecio()
                    );
                })
                .collect(Collectors.toList());
        return ret;
    }

    @POST
    @Operation(description = "Insert Product", operationId = "insertProduct")
    @APIResponse(name = "Success Insert Product",responseCode = "202", description = "Product Inserted")
    @APIResponse(name = "Error Insert Product",responseCode = "502", description = "Error Inserting Product")
    public void insert(Productos producto) {
        productoRepository.insertProduct(producto);
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "Update Product by Id", operationId = "updateProduct")
    @APIResponse(name = "Success Update Product",responseCode = "203", description = "Product Updated")
    @APIResponse(name = "Error Update Product",responseCode = "503", description = "Error Updating Product")
    public void update(Productos producto, @PathParam("id") Long id) {
        productoRepository.updateProduct(producto,id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete Product by Id", operationId = "deleteProduct")
    @APIResponse(name = "Success Delete Product", responseCode = "204", description = "Product Deleted")
    @APIResponse(name = "Error Delete Product", responseCode = "504", description = "Error Deleting Product")
    public void delete( @PathParam("id") Long id ) {
        productoRepository.deleteProduct(id);
    }
}
