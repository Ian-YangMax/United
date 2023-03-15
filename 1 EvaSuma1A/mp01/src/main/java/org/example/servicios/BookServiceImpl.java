package org.example.servicios;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.config.DbConfig;
import org.example.db.Book;
import org.jdbi.v3.core.Handle;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService{

    @Inject
    DbConfig db;
    Book libro = new Book();
    List<Book> libros= new ArrayList<>();
    @Override
    public Book findBookId(Integer id) {
        try{
            Handle handle = db.conection();
            libro = handle.createQuery("SELECT * FROM books WHERE id = ?;").bind(0,id).mapToBean(Book.class).findOnly();
            handle.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public List<Book> findAllBooks() {
        try{
            Handle handle = db.conection();
            libros = handle.createQuery("SELECT * FROM books ").mapToBean(Book.class).list();
            handle.close();
        }catch (Exception e){

        }
        return libros;
    }

    @Override
    public Book agregarBook(Book book) {

        try{
            Handle handle = db.conection();
            handle.createUpdate("INSERT INTO  books(isbn, title, author, price) VALUES (:isbn,:title,:author,:price)")
                    .bind("isbn",book.getIsbn())
                    .bind("title",book.getTitle())
                    .bind("author",book.getAuthor())
                    .bind("price", book.getPrice())
                    .execute();
            handle.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public Book updateBook(Integer id, Book book) {

            var libro = findBookId(id);
        try{
            Handle handle = db.conection();

            handle.createUpdate("UPDATE books SET  isbn=:isbn,title=:title,author=:author,price=:price WHERE id = :id")
                    .bind("id",libro.getId())
                    .bind("isbn",book.getIsbn())
                    .bind("title",book.getTitle())
                    .bind("author",book.getAuthor())
                    .bind("price",book.getPrice())
                    .execute();
            handle.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public void eliminarBook(Integer id) {
        try{
            Handle handle = db.conection();
            handle.createQuery("DELETE FROM books WHERE id = ?;").bind(0,id).mapToBean(Book.class).findOnly();
            handle.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
