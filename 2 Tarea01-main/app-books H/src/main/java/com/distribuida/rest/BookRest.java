package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.ImplementacionServicioLibros;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private ImplementacionServicioLibros implementacionServicioLibros;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer id) throws ExecutionException, InterruptedException {
        return implementacionServicioLibros.getBookById(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll () throws ExecutionException, InterruptedException {
     return implementacionServicioLibros.getBooks();
    }
    @DELETE
    @Path("/{id}")
    public void delete (@PathParam("id") Integer id) throws ExecutionException, InterruptedException {
        implementacionServicioLibros.delete(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Book book) throws ExecutionException, InterruptedException {
        implementacionServicioLibros.creteBook(book);
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update (Book book, @PathParam("id") Integer id) throws ExecutionException, InterruptedException {
        implementacionServicioLibros.updateBook(id,book);
    }

}
