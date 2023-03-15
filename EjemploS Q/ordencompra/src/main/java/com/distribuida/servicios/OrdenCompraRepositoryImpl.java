package com.distribuida.servicios;

import com.distribuida.db.OrdenCompra;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped

public class OrdenCompraRepositoryImpl implements OrdenCompraRepository {
    @PersistenceContext(unitName = "producto")
    private EntityManager entityManager;

    @Override
    public List<OrdenCompra> getOrdenCompra() {
        return entityManager
                .createNamedQuery("OrdenCompra.findAll", OrdenCompra.class)
                .getResultList();
    }

    @Override
    public OrdenCompra getOrdenCompraById(Integer id){
        return entityManager.find(OrdenCompra.class,id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void creteOrdenCompra(OrdenCompra ordenCompra) {
        entityManager.persist(ordenCompra);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void updateOrdenCompra(Integer id, OrdenCompra ordenCompra){
        var savedProducto=this.getOrdenCompraById(id);
        savedProducto.setCliente_id(ordenCompra.getCliente_id());
        savedProducto.setProducto_id(ordenCompra.getProducto_id());
        savedProducto.setPrecio(ordenCompra.getPrecio());
        entityManager.persist(ordenCompra);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer id) {
        entityManager.remove(getOrdenCompraById(id));
    }
}
