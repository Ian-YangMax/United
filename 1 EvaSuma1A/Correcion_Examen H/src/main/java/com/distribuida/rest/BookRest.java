package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.services.BookRepository;
import com.distribuida.services.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookRest {

    @Inject
    private BookRepository bookService;

    @GET
    @Path("/{id}")
    public Book findBookId(@PathParam("id")Integer id){
//        Book book = new Book();
//        book.setId(id );
//        book.setIsbn("aaaaaaaaaaaaaa");
//        book.setAuthor("bbbbbbbbbbbbbb");
//        book.setTitle("eeeeeeeeeeeeee");
//        book.setPrice(10.5);
        return bookService.findBookById(id);
    }

    @GET
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @POST
    public void agregar(Book book){
        bookService.agregar(book);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id,Book book){
        book.setId(id);
        bookService.actualizar(book);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id")Integer id){
        bookService.eliminar(id);
    }
}
