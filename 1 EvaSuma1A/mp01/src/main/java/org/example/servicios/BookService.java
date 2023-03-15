package org.example.servicios;

import org.example.db.Book;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.ArrayList;
import java.util.List;
public interface BookService {

    Book findBookId(@Bind("id") Integer id);
    List<Book> findAllBooks();

    Book agregarBook(Book book);
    Book updateBook(@Bind("id") Integer id, Book book);

    void eliminarBook(Integer id);
}
