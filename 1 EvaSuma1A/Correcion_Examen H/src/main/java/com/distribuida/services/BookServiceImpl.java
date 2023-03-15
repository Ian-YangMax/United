package com.distribuida.services;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService{

//    @Inject
//    private DataSource dataSource;
    @Inject
    private Jdbi jdbi;
    @Override
    public Book findBookById(Integer id) {
//        Connection con = dataSource.getConnection();
//
//        PreparedStatement pstmt = con.prepareStatement("select * from books where id=?");
//        ResultSet result = pstmt.executeQuery();
//
//        while(result.next()){
//            result.getInt(1);
//
//        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> ret = jdbi.withHandle(handle -> {
           return handle.createQuery("select * from Books order by id asc")
                   .mapToBean(Book.class)
                   .list();
        });

        return ret;
    }

    @Override
    public void agregar(Book book) {

    }

    @Override
    public void actualizar(Integer id, Book book) {

    }

    @Override
    public void eliminar(Integer id) {

    }
}
