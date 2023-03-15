package com.distribuida.servicios;

import com.distribuida.db.Producto;

import java.util.List;

public interface ProductoRepository {
    List<Producto> getProductos();

    Producto getProductoById(Integer id);
    void creteProducto(Producto producto);
    void updateProducto(Integer id,Producto producto);
    void delete(Integer id);
}
