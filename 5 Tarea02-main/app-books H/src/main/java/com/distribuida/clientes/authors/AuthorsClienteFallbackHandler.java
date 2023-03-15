package com.distribuida.clientes.authors;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;



@ApplicationScoped
public class AuthorsClienteFallbackHandler implements FallbackHandler<AuthorsCliente> {
    @Override
    public AuthorsCliente handle(ExecutionContext context) {
        AuthorsCliente authorsCliente = new AuthorsCliente();
        authorsCliente.setFirtName("not available this moment try later");
        authorsCliente.setLastName("not available this moment try later");
        return authorsCliente;
    }
}
