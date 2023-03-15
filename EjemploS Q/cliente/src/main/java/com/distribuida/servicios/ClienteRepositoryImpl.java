package com.distribuida.servicios;

import com.distribuida.db.Cliente;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped

public class ClienteRepositoryImpl implements ClienteRepository {
    @PersistenceContext(unitName = "cliente")
    private EntityManager entityManager;

    @Override
    public List<Cliente> getClientes() {
        return entityManager
                .createNamedQuery("Cliente.findAll", Cliente.class)
                .getResultList();
    }

    @Override
    public Cliente getClienteById(Integer id){
        return entityManager.find(Cliente.class,id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void creteCliente(Cliente producto) {
        entityManager.persist(producto);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void updateCliente(Integer id, Cliente cliente){
        var savedProducto=this.getClienteById(id);
        savedProducto.setNombre(cliente.getNombre());
        savedProducto.setApellido(cliente.getApellido());
        savedProducto.setDireccion(cliente.getDireccion());
        entityManager.persist(cliente);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer id) {
        entityManager.remove(getClienteById(id));
    }
}
