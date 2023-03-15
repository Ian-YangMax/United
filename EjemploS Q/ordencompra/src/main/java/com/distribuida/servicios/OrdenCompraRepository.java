package com.distribuida.servicios;

import com.distribuida.db.OrdenCompra;

import java.util.List;

public interface OrdenCompraRepository {
    List<OrdenCompra> getOrdenCompra();

    OrdenCompra getOrdenCompraById(Integer id);
    void creteOrdenCompra(OrdenCompra ordenCompra);
    void updateOrdenCompra(Integer id, OrdenCompra ordenCompra);
    void delete(Integer id);
}
