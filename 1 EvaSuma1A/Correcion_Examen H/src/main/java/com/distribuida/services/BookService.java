package com.distribuida.services;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {

    Book findBookById(Integer id);

    List<Book> findAll();
    void agregar(Book book);
    void actualizar(Integer id,Book book);
    void eliminar(Integer id);
}
