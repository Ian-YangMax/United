package com.distribuida.servicios;

import com.distribuida.db.Producto;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped

public class ProductoRepositoryImpl implements ProductoRepository {
    @PersistenceContext(unitName = "producto")
    private EntityManager entityManager;

    @Override
    public List<Producto> getProductos() {
        return entityManager
                .createNamedQuery("Producto.findAll",Producto.class)
                .getResultList();
    }

    @Override
    public Producto getProductoById(Integer id){
        return entityManager.find(Producto.class,id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void creteProducto(Producto producto) {
        entityManager.persist(producto);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void updateProducto(Integer id, Producto producto){
        var savedProducto=this.getProductoById(id);
        savedProducto.setNombre(producto.getNombre());
        savedProducto.setPrecio(producto.getPrecio());
        entityManager.persist(producto);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer id) {
        entityManager.remove(getProductoById(id));
    }
}
