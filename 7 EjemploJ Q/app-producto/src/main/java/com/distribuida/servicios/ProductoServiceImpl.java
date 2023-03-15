package com.distribuida.servicios;

import com.distribuida.db.ProductoRepository;
import com.distribuida.db.Productos;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoService{
    @Inject
    private ProductoRepository productoRepository;

    @Override
    public Productos findProductById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Productos> findAllProducts() {
        return productoRepository.listAll();
    }

    @Override
    public void insertProduct(Productos producto) {
        productoRepository.persist(producto);
    }

    @Override
    public void updateProduct(Productos producto, Long id) {
        productoRepository.update("nombre = ?1, precio = ?2 where id = ?3",producto.getNombre(),producto.getPrecio(),producto.getId());

    }

    @Override
    public void deleteProduct(Long id) {
        productoRepository.deleteById(id);
    }
}
