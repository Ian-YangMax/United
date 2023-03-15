package com.distribuida.rest;

import com.distribuida.db.Producto;
import com.distribuida.servicios.ProductoRepository;
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
@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProductoRest {
    @Inject
    private ProductoRepository productoRepository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Productos")
    @APIResponse(responseCode = "207", description = "Todos los productos", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Producto.class)))
    public List<Producto> findAll() {
        return productoRepository.getProductos();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Producto")
    @APIResponse(responseCode = "207", description = "Se ha encontrado el producto", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
    @APIResponse(responseCode = "400", description = "No se pudo encontrar el libro")
    public Producto findById(@Parameter(description = "ID BOOK", required = true) @PathParam("id") Integer id)  {
        return productoRepository.getProductoById(id);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "Create Producto")
    @APIResponse(responseCode = "201", description = "Se ha creado el producto")
    @APIResponse(responseCode = "500", description = "Presenta problemas con el servidor")
    public Response create(
            @RequestBody(description = "Producto a Ingresar", required = true,
                    content = @Content(schema = @Schema(implementation = Producto.class)))
            Producto b){
        productoRepository.creteProducto(b);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{id}")
    @Operation(description = "Update Producto")
    @APIResponse(responseCode = "207", description = "Se actualizo el producto")
    @APIResponse(responseCode = "500", description = "Presenta problemas de actualizacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "Datos del producto a modificar", required = true,
                    content = @Content(schema = @Schema(implementation = Producto.class)))
            Producto b,
            @Parameter(description = "ID PRODUCTO", required = true)
            @PathParam("id") Integer id){
        productoRepository.updateProducto(id, b);
        return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "Delete Producto")
    @APIResponse(responseCode = "209", description = "Se ha eliminado el producto")
    @APIResponse(responseCode = "500", description = "Presenta problemas con el servidor")
    public Response delete(
            @Parameter(description = "ID PRODUCTO", required = true)
            @PathParam("id") Integer id) {
        productoRepository.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }
}
