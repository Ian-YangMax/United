package servicios;

import db.ClienteRepository;
import db.Clientes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{
    @Inject
    private ClienteRepository clienteRepository;
    @Override
    public Clientes findClientById(Long id) {
        return null;
    }

    @Override
    public List<Clientes> findAllClientes() {
        return null;
    }

    @Override
    public void insertClient(Clientes cliente) {

    }

    @Override
    public void updateCliente(Clientes cliente, Long id) {

    }

    @Override
    public void deleteCliente(Long id) {

    }
}
