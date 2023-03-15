package com.distribuida.servicios;

import com.distribuida.db.Productos;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public interface ProductoService {
    Productos findProductById(Long id);
    List<Productos> findAllProducts();
    void insertProduct(Productos producto);
    void updateProduct (Productos producto, Long id);
    void deleteProduct (Long id);
}
