package com.distribuida.servicios;

import com.distribuida.db.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> getClientes();

    Cliente getClienteById(Integer id);
    void creteCliente(Cliente cliente);
    void updateCliente(Integer id, Cliente cliente);
    void delete(Integer id);
}
