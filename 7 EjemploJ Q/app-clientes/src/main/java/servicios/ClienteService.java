package servicios;

import db.Clientes;

import java.util.List;

public interface ClienteService {

    Clientes findClientById(Long id);
    List<Clientes> findAllClientes();
    void insertClient(Clientes cliente);
    void updateCliente(Clientes cliente, Long id);
    void deleteCliente(Long id);
}

