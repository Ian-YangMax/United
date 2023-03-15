package clientes.productos;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri="http://localhost:8080")
//@RegisterRestClient(configKey = "cliente")
public interface ProductoRestProxy {

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    ClienteProductos findById(@PathParam("id") Long id);

    @GET
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    List<ClienteProductos> findAll();

    @POST
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    void insert(ClienteProductos obj);

    @PUT
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    void update(ClienteProductos obj, @PathParam("id") Long id);

    @DELETE
    @Retry(maxRetries = 2, maxDuration = 1000)
    @Timeout(value = 3000, unit = ChronoUnit.MILLIS)
    @Path("/{id}")
    void delete( @PathParam("id") Long id );
}
