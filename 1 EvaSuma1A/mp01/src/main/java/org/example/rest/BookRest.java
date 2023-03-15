package org.example.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.config.DbConfig;
import org.example.db.Book;
import org.example.servicios.BookService;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {

    @Inject
    private BookService bookSevice;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer id){
        return bookSevice.findBookId(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll(){
        return bookSevice.findAllBooks();
    }

    @POST

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book insertBook(Book book){

        return bookSevice.agregarBook(book);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("id") Integer id,Book book){
        return bookSevice.updateBook(id, book);
    }
    @DELETE
    @Path(value ="/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void eliminarBook(@PathParam("id") Integer id){
        bookSevice.eliminarBook(id);
    }
}
